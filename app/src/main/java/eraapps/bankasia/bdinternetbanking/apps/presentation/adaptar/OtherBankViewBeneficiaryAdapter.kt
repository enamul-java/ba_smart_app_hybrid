package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear


class OtherBankViewBeneficiaryAdapter(

    private var accountList: ArrayList<OtherBankOptions>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<OtherBankOptions>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: OtherBankOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_other_bank_view_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_nick_name: TextView = holder.itemView.findViewById(R.id.tv_nick_name)
        val tv_account_title: TextView = holder.itemView.findViewById(R.id.tv_account_title)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_bank_name: TextView = holder.itemView.findViewById(R.id.tv_bank_name)
        val tv_branch_name: TextView = holder.itemView.findViewById(R.id.tv_branch_name)
        val tv_network: TextView = holder.itemView.findViewById(R.id.tv_network)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)
        val tv_type: TextView = holder.itemView.findViewById(R.id.tv_type)
        val iv_bankicon: ImageView = holder.itemView.findViewById(R.id.iv_bankicon)
        val iv_edit: ImageView = holder.itemView.findViewById(R.id.iv_edit)
        val iv_delete: ImageView = holder.itemView.findViewById(R.id.iv_delete)

        val image =
            Constants.BASE_URL + "access/v1/bankimage/" + currentItem.bankCode
        Log.d("bankimage-->", image)


        if (image == "") {
            iv_bankicon.setImageResource(R.drawable.ic_baseline_person_24)
        } else {
            iv_bankicon.loadOperatorsImages(
                image,
                getProgressDrawble(iv_bankicon.context)
            )
        }


        tv_account_title.text = currentItem.accountTitle
        if (currentItem.type == "C") {
            tv_type.text = "Card"
            tv_account_no.text = ValidationUtil.cardNoMask(currentItem.accountNo)
        } else {
            tv_type.text = "Account"
            tv_account_no.text = currentItem.accountNo
        }
        tv_bank_name.text = currentItem.bankName
        tv_branch_name.text = currentItem.branch_name
        tv_network.text = currentItem.network

        if (currentItem.nickName!!.isEmpty() || currentItem.nickName == null || currentItem.nickName == "null" || currentItem.nickName == "") {
            tv_nick_name.text = currentItem.accountTitle
        } else {
            tv_nick_name.text = currentItem.nickName
        }

        if (currentItem.status == "Y") {
            tv_status.setText(R.string.active)
            tv_status.setTextColor(Color.parseColor("#288435"))
        } else {
            tv_status.setText(R.string.inactive)
            tv_status.setTextColor(Color.parseColor("#F13530"))

        }

        iv_edit.setOnClickListener {
            val selectedList: OtherBankOptions = requestFilterList[position]
            selectedList.oprMode = "E"
            listener.onItemClick(selectedList)
        }

        iv_delete.setOnClickListener {
            val selectedList: OtherBankOptions = requestFilterList[position]
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

                    val resultList = ArrayList<OtherBankOptions>()
                    for (row in accountList) {
                        if (
                            row.nickName.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.accountTitle.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.accountNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.emailId.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.bankName.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<OtherBankOptions>
                notifyDataSetChanged()
            }

        }
    }

}