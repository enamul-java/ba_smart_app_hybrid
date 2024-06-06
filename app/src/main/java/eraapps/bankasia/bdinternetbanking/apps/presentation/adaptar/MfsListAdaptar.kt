package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import de.hdodenhof.circleimageview.CircleImageView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear


class MfsListAdaptar(
    private var accountList: ArrayList<MfsViewResponse>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<MfsViewResponse>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit
        dialog = indialog

    }

    interface OnItemClickListener {
        fun onItemClick(item: MfsViewResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_mfs_beneficiary_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvMfsNickname: TextView = holder.itemView.findViewById(R.id.tvMfsNickname)
        val tvMfsWalletTitle: TextView = holder.itemView.findViewById(R.id.tvMfsWalletTitle)
        val tvMfsWalletNo: TextView = holder.itemView.findViewById(R.id.tvMfsWalletNo)
        val ivMfsicon: ImageView = holder.itemView.findViewById(R.id.ivMfsicon)
        val lo_container: LinearLayout = holder.itemView.findViewById(R.id.lo_container)

        if (currentItem.nickName!!.trim().isEmpty()) {
            tvMfsNickname.text = currentItem.mfsAccountTitle
        } else {
            tvMfsNickname.text = currentItem.nickName
        }

        val image = Constants.BASE_URL + "access/v1/mfsimage/" + currentItem.mfsCode
        Log.d("mfs-->", image)


        ivMfsicon.loadOperatorsImagesClear(image, getProgressDrawble(ivMfsicon.context))


        tvMfsWalletNo.text = currentItem.mfsAccountNo.toString()
        tvMfsWalletTitle.text = currentItem.mfsAccountTitle.toString()


        lo_container.setOnClickListener {
            val selectedList: MfsViewResponse = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.dismiss()

        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        //Log.e("mobileNumberList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = accountList
                } else {

                    val resultList = ArrayList<MfsViewResponse>()
                    for (row in accountList) {
                        if (
                            row.codeDesc.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                        ) {
                            resultList.add(row)
                        }
                    }
                    requestFilterList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = requestFilterList
                return filterResults
            }

            @SuppressLint("NotifyDataSetChanged")
            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<MfsViewResponse>
                notifyDataSetChanged()
            }

        }
    }

}