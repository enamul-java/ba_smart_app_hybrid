package eraapps.bankasia.bdinternetbanking.apps.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants


class PayoneerHistoryListAdapter(
    private var accountList: ArrayList<PayoneerDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<PayoneerDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable


    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: PayoneerDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_payoneer_history_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val item = requestFilterList[position]
        val tv_hisotry_payee_id: TextView = holder.itemView.findViewById(R.id.tv_hisotry_payee_id)
        val tv_hisotry_amount: TextView = holder.itemView.findViewById(R.id.tv_hisotry_amount)
        val tv_hisotry_payee_usd_amount: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_payee_usd_amount)
        val tv_hisotry_payee_bdt_amount: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_payee_bdt_amount)
        val tv_hisotry_rate: TextView = holder.itemView.findViewById(R.id.tv_hisotry_rate)
        val tv_hisotry_tc: TextView = holder.itemView.findViewById(R.id.tv_hisotry_tc)
        val tv_hisotry_transaction_date: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_transaction_date)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)

        val tv_hisotry_payee_id_lable: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_payee_id_lable)
        val tv_hisotry_amount_label: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_amount_label)
        val tv_hisotry_payee_usd_amount_label: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_payee_usd_amount_label)
        val tv_hisotry_payee_bdt_amount_label: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_payee_bdt_amount_label)
        val tv_hisotry_rate_label: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_rate_label)
        val tv_hisotry_tc_label: TextView = holder.itemView.findViewById(R.id.tv_hisotry_tc_label)
        val tv_hisotry_transaction_date_label: TextView =
            holder.itemView.findViewById(R.id.tv_hisotry_transaction_date_label)
        val tv_status_label: TextView =
            holder.itemView.findViewById(R.id.tv_status_label)

        val btn_download: Button = holder.itemView.findViewById(R.id.btn_download)


        tv_hisotry_payee_id.text = ""


        tv_hisotry_amount.text = item.amount.toString() + " USD"
        tv_hisotry_payee_usd_amount.text = item.usdAmountPortion.toString()
        tv_hisotry_payee_bdt_amount.text = item.totalBDTAmount.toString()
        tv_hisotry_rate.text = item.midrate
        tv_hisotry_transaction_date.text = item.transactiondate
        tv_hisotry_tc.text = "SUC"
        // tv_reference_id?.text = item.referenceId
        // tv_txn_complete_flag?.text = item.trans_complete_flg
        tv_hisotry_tc.setTextColor(Color.parseColor("#28a745"))



        if ("N".equals(item.cbsStatusCode) && "N".equals(item.trans_complete_flg)) {
            tv_hisotry_tc.text = "BA"
            tv_hisotry_tc.setTextColor(Color.parseColor("#E20000"))
            btn_download.visibility = View.VISIBLE
        }


        if ("Y".equals(item.cbsStatusCode) && "Y".equals(item.trans_complete_flg)) {
            tv_status.text = "Completed"
            tv_hisotry_tc.text = "SUC"

            tv_status.setTextColor(Color.parseColor("#28a745"))
            tv_hisotry_tc.setTextColor(Color.parseColor("#28a745"))
        } else {
            tv_status.text = "Not Complete. Please Refresh"
            tv_status.setTextColor(Color.parseColor("#E20000"))
        }


        btn_download.setOnClickListener {
            val selectedList: PayoneerDto = requestFilterList[position]
            listener.onItemClick(selectedList)
        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_hisotry_payee_id_lable.setText(R.string.payee_id_bangla)
            tv_hisotry_amount_label.setText(R.string.amout_bangla)
            tv_hisotry_payee_usd_amount_label.setText(R.string.usd_amount_bangla)
            tv_hisotry_payee_bdt_amount_label.setText(R.string.bdt_amount_bangla)
            tv_hisotry_rate_label.setText(R.string.rate_bangla)
            tv_hisotry_tc_label.setText(R.string.tc_bangla)
            tv_hisotry_transaction_date_label.setText(R.string.transaction_date_bangla)
            tv_status_label.setText(R.string.Transaction_status_bangla)

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
                    val resultList = ArrayList<PayoneerDto>()
                    for (row in accountList) {
                        if (
                            row.type.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.transactiondate.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.transactinId.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.totalBDTAmount.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.transactinId.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.amount.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<PayoneerDto>
                notifyDataSetChanged()
            }

        }
    }

}