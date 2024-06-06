package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.util.ConvertUtil


class OwnBankViewBeneficiaryAdapter(

    private var accountList: ArrayList<OwnBankViewBeneficiaryDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<OwnBankViewBeneficiaryDto>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: OwnBankViewBeneficiaryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_own_bank_view_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvNickName: TextView = holder.itemView.findViewById(R.id.tvNickName)
        val tvAcNo: TextView = holder.itemView.findViewById(R.id.tvAcNo)
        val tvAcTitle: TextView = holder.itemView.findViewById(R.id.tvAcTitle)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)
        val iv_edit: ImageView = holder.itemView.findViewById(R.id.iv_edit)
        val iv_delete: ImageView = holder.itemView.findViewById(R.id.iv_delete)


        tvAcNo.text = currentItem.cardNo

        if(!ConvertUtil.emptyCheck(currentItem.cardNo).equals("")){
            tvAcNo.text = currentItem.cardNo
        }else{
            tvAcNo.text = currentItem.destinationAccount
        }

        tvAcTitle.text = currentItem.destinationAccountTitle

        if (currentItem.nickname.isEmpty() || currentItem.nickname == "" || currentItem.nickname == null || currentItem.nickname == "null" || currentItem.nickname == " ") {
            tvNickName.text = currentItem.destinationAccountTitle
        } else {
            tvNickName.text = currentItem.nickname
        }
        if (currentItem.status == "Y") {
            tv_status.setText(R.string.active)
            tv_status.setTextColor(Color.parseColor("#288435"))
        } else {
            tv_status.setText(R.string.inactive)
            tv_status.setTextColor(Color.parseColor("#F13530"))
        }

        iv_edit.setOnClickListener {
            val selectedList: OwnBankViewBeneficiaryDto = requestFilterList[position]
            selectedList.oprMode = "E"
            listener.onItemClick(selectedList)
        }

        iv_delete.setOnClickListener {
            val selectedList: OwnBankViewBeneficiaryDto = requestFilterList[position]
            selectedList.oprMode = "D"
            listener.onItemClick(selectedList)
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

                    val resultList = ArrayList<OwnBankViewBeneficiaryDto>()
                    for (row in accountList) {
                        if (
                            row.destinationAccount.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickname.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileNumber.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.destinationAccountTitle.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.email.lowercase()
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
                requestFilterList = results?.values as ArrayList<OwnBankViewBeneficiaryDto>
                notifyDataSetChanged()
            }

        }
    }

}