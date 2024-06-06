package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.UtilityDetailsDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class UtilityDetailsListAdapter(
    private var accountList: ArrayList<UtilityDetailsDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<UtilityDetailsDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: UtilityDetailsDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_statement_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_bill_type: TextView = holder.itemView.findViewById(R.id.tv_bill_type)
        val tv_bill_no: TextView = holder.itemView.findViewById(R.id.tv_bill_no)
        val tv_transaction_date: TextView = holder.itemView.findViewById(R.id.tv_transaction_date)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_bill_amount: TextView = holder.itemView.findViewById(R.id.tv_bill_amount)
        val tv_vat_amount: TextView = holder.itemView.findViewById(R.id.tv_vat_amount)
        val tv_total_amount: TextView = holder.itemView.findViewById(R.id.tv_total_amount)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)
        val tv_sur_charge: TextView = holder.itemView.findViewById(R.id.tv_sur_charge)
        val tv_sewer_bill: TextView = holder.itemView.findViewById(R.id.tv_sewer_bill)
        val tv_recharge_token_no: TextView = holder.itemView.findViewById(R.id.tv_recharge_token_no)

        val btn_download: Button = holder.itemView.findViewById(R.id.btn_download)

        val token_layout: LinearLayout = holder.itemView.findViewById(R.id.token_layout)
        val wasalayout: LinearLayout = holder.itemView.findViewById(R.id.wasalayout)
        val view_wasalayout: View = holder.itemView.findViewById(R.id.view_wasalayout)

        val tv_bill_type_label: TextView = holder.itemView.findViewById(R.id.tv_bill_type_label)
        val tv_bill_date_label: TextView = holder.itemView.findViewById(R.id.tv_bill_date_label)
        val tv_transaction_date_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_date_label)
        val tv_account_no_label: TextView = holder.itemView.findViewById(R.id.tv_account_no_label)
        val tv_bill_amount_label: TextView = holder.itemView.findViewById(R.id.tv_bill_amount_label)
        val tv_vat_amount_label: TextView = holder.itemView.findViewById(R.id.tv_vat_amount_label)
        val tv_status_label: TextView = holder.itemView.findViewById(R.id.tv_status_label)
        val tv_sewer_bill_label: TextView = holder.itemView.findViewById(R.id.tv_sewer_bill_label)
        val tv_sur_charge_label: TextView = holder.itemView.findViewById(R.id.tv_sur_charge_label)
        val tv_total_amount_label: TextView =
            holder.itemView.findViewById(R.id.tv_total_amount_label)


        tv_bill_type.text = currentItem.billTypeName
        tv_bill_no.text = currentItem.billNo
        tv_transaction_date.text = currentItem.billDate
        tv_account_no.text = currentItem.accountno
        tv_bill_amount.text = ValidationUtil.commaSeparateAmount(currentItem.billAmount.toString())
        tv_vat_amount.text = ValidationUtil.commaSeparateAmount(currentItem.vatAmount.toString())
        // tv_status.text = currentItem.status
        tv_recharge_token_no.text = currentItem.rechargeToken
        tv_total_amount.text =
            ValidationUtil.commaSeparateAmount(currentItem.totalAmount.toString())
        tv_sur_charge.text = ValidationUtil.commaSeparateAmount(currentItem.surcharge.toString())
        tv_sewer_bill.text = ValidationUtil.commaSeparateAmount(currentItem.sweregebill.toString())


        if (currentItem.rechargeToken!!.isEmpty() || (currentItem.rechargeToken.lowercase() == "null")) {
            token_layout.visibility = View.GONE
        } else {
            token_layout.visibility = View.VISIBLE
            tv_recharge_token_no.text = currentItem.rechargeToken
        }

        if (currentItem.status == "Y" || currentItem.status == "P" || currentItem.status!!.uppercase()
                .startsWith("S")
        ) {
            tv_status.setText(R.string.success)
            tv_status.setTextColor(Color.parseColor("#288435"))
        } else {
            tv_status.setText(R.string.failed)
            tv_status.setTextColor(Color.parseColor("#F13530"))
        }

        if ("WASA" == currentItem.billType.toString()) {
            view_wasalayout.visibility = View.VISIBLE
            wasalayout.visibility = View.VISIBLE
        } else {
            view_wasalayout.visibility = View.GONE
            wasalayout.visibility = View.GONE
        }

        btn_download.setOnClickListener {
            val selectedList: UtilityDetailsDto = requestFilterList[position]
            listener.onItemClick(selectedList)
        }

        if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
            tv_bill_type_label.setText(R.string.select_bill_type_bangla)
            tv_bill_date_label.setText(R.string.bill_number_bangla)
            tv_transaction_date_label.setText(R.string.transaction_date_bangla)
            tv_account_no_label.setText(R.string.account_no_bangla)
            tv_bill_amount_label.setText(R.string.bill_amount_bangla)
            tv_vat_amount_label.setText(R.string.vat_amount_bangla)
            tv_total_amount_label.setText(R.string.total_bill_bangla)
            tv_status_label.setText(R.string.status_bangla)
            tv_sewer_bill_label.setText(R.string.sewer_bill_bangla)
            tv_sur_charge_label.setText(R.string.sur_charge_bangla)
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
                    val resultList = ArrayList<UtilityDetailsDto>()
                    for (row in accountList) {
                        if (
                            row.billDate.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.billNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.billTypeName.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.slNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.accountno.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.totalAmount.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<UtilityDetailsDto>
                notifyDataSetChanged()
            }

        }
    }

}