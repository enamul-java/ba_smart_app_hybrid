package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillerModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear


class UtilityViewBeneficiaryAdapter(
    private var accountList: ArrayList<BillerModel>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<BillerModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: BillerModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_mfs_view_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_nick_name: TextView = holder.itemView.findViewById(R.id.tv_nick_name)
        val tv_account_title: TextView = holder.itemView.findViewById(R.id.tv_account_title)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_mfs_name: TextView = holder.itemView.findViewById(R.id.tv_mfs_name)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)
        val iv_bankicon: ImageView = holder.itemView.findViewById(R.id.iv_bankicon)
        val iv_edit: ImageView = holder.itemView.findViewById(R.id.iv_edit)
        val iv_delete: ImageView = holder.itemView.findViewById(R.id.iv_delete)

        val image =
            Constants.BASE_URL + "access/v1/mfsimage/" + currentItem.billtype
        Log.d("mfs-->", image)

        if (image == "") {
            iv_bankicon.setImageResource(R.drawable.ic_baseline_person_24)
        } else {
            iv_bankicon.loadOperatorsImages(
                image,
                getProgressDrawble(iv_bankicon.context)
            )
        }

        tv_account_title.text = currentItem.customerName
        tv_account_no.text = currentItem.customerCode
        tv_mfs_name.text = currentItem.billtypedes

        if (currentItem.nickName!!.isEmpty() || currentItem.nickName.toString()
                .trim() == null || currentItem.nickName!!.trim() == "null" || currentItem.nickName!!.trim() == ""
        ) {
            tv_nick_name.text = currentItem.customerName
        } else {
            tv_nick_name.text = currentItem.nickName
        }
        if (currentItem.status == "Y") {
            tv_status.text = "Active"
            tv_status.setTextColor(Color.parseColor("#288435"))
        } else {
            tv_status.text = "Inactive"
            tv_status.setTextColor(Color.parseColor("#F13530"))
        }


        iv_edit.setOnClickListener {
            val selectedList: BillerModel = requestFilterList[position]
            selectedList.oprMode = "E"
            listener.onItemClick(selectedList)
        }

        iv_delete.setOnClickListener {
            val selectedList: BillerModel = requestFilterList[position]
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
                    val resultList = ArrayList<BillerModel>()
                    for (row in accountList) {
                        if (
                            row.nickName.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.nickName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileno.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.emailid.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.customerCode.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.billtypedes.toString().lowercase()
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<BillerModel>
                notifyDataSetChanged()
            }

        }
    }

}