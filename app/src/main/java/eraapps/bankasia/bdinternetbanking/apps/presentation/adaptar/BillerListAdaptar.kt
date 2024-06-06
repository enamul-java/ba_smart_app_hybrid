package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillerModel

class BillerListAdaptar(
    private var movieList: ArrayList<BillerModel>,
    dialog1: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<BillerModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit
        dialog = dialog1

    }

    interface OnItemClickListener {
        fun onItemClick(item: BillerModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_biller_view_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvNickName: TextView = holder.itemView.findViewById(R.id.tvNickName)
        val tvCustomerCode: TextView = holder.itemView.findViewById(R.id.tvCustomerCode)
        val tvCustomerName: TextView = holder.itemView.findViewById(R.id.tvCustomerName)
        val tvBillType: TextView = holder.itemView.findViewById(R.id.tvBillType)
        val view_layout: LinearLayout = holder.itemView.findViewById(R.id.view_layout)

        tvNickName.text = currentItem.nickName
        tvCustomerCode.text = currentItem.customerCode
        tvCustomerCode.text = currentItem.customerCode
        tvCustomerName.text = currentItem.customerName
        tvBillType.text = currentItem.billtypedes

        view_layout.setOnClickListener {
            val selectedList: BillerModel = currentItem
            listener.onItemClick(selectedList)

        }

        /*    val image =
                Constants.BASE_URL + "access/v1/menuimage-new/" + currentItem.imageId + "/" + currentItem.code
            Log.d("utilityiamge-->", image)


            if (image == "") {
                iv_bill_type.setImageResource(R.drawable.ic_baseline_person_24)
            } else {
                iv_bill_type.loadOperatorsImagesClear(
                    image,
                    getProgressDrawble(iv_bill_type.context)
                )
            }
    */

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        // notifyDataSetChanged()
        // Log.e("requestFilterList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = movieList
                } else {

                    val resultList = ArrayList<BillerModel>()
                    for (row in movieList) {
                        if (
                            row.customerCode.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.customerName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.billtypedes.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.emailid.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileno.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.nickName.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                )
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
                requestFilterList = results?.values as ArrayList<BillerModel>
                notifyDataSetChanged()
            }

        }
    }
}