package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsTransResponse
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class MfsTransListAdapter(
    private var accountList: ArrayList<MfsTransResponse>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<MfsTransResponse>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: MfsTransResponse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_mfs_statement_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_sl: TextView = holder.itemView.findViewById(R.id.tv_sl)
        val tv_transaction_type: TextView = holder.itemView.findViewById(R.id.tv_transaction_type)
        val tv_transaction_date: TextView = holder.itemView.findViewById(R.id.tv_transaction_date)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_wallet_no: TextView = holder.itemView.findViewById(R.id.tv_wallet_no)
        val tv_transaction_id: TextView = holder.itemView.findViewById(R.id.tv_transaction_id)
        val tv_amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)

        val tv_sl_label: TextView = holder.itemView.findViewById(R.id.tv_sl_label)
        val tv_transaction_type_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_type_label)
        val tv_transaction_date_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_date_label)
        val tv_account_no_label: TextView = holder.itemView.findViewById(R.id.tv_account_no_label)
        val tv_wallet_no_label: TextView = holder.itemView.findViewById(R.id.tv_wallet_no_label)
        val tv_transaction_id_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_id_label)
        val tv_amount_label: TextView = holder.itemView.findViewById(R.id.tv_amount_label)
        val tv_status_label: TextView = holder.itemView.findViewById(R.id.tv_status_label)

        val btn_download: Button = holder.itemView.findViewById(R.id.btn_download)


        tv_sl.text = currentItem.slNo
        tv_transaction_type.text = currentItem.mfsType
        tv_transaction_date.text = currentItem.transDate
        tv_account_no.text = currentItem.accountNumber
        tv_wallet_no.text = currentItem.walletNo
        tv_transaction_id.text = currentItem.transactionId
        tv_status.text = currentItem.status
        tv_amount.text = ValidationUtil.commaSeparateAmount(currentItem.amount.toString())

        if (currentItem.status.toString().lowercase().startsWith("s")) {
            tv_status.setTextColor(Color.parseColor("#4BB543"))
        } else {
            tv_status.setTextColor(Color.RED)
        }

        btn_download.setOnClickListener {
            val selectedList: MfsTransResponse = requestFilterList[position]
            listener.onItemClick(selectedList)
        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_sl_label.setText(R.string.slno_bangla)
            tv_transaction_type_label.setText(R.string.wallet_type_bangla)
            tv_transaction_date_label.setText(R.string.transaction_date_bangla)
            tv_account_no_label.setText(R.string.account_no_bangla)
            tv_wallet_no_label.setText(R.string.wallet_number_bangla)
            tv_transaction_id_label.setText(R.string.transaction_id_bangla)
            tv_status_label.setText(R.string.status_bangla)
            tv_amount_label.setText(R.string.amout_bangla)

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
                    val resultList = ArrayList<MfsTransResponse>()
                    for (row in accountList) {
                        if (
                            row.mfsType.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.transDate.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.transactionId.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.slNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.accountNumber.toString().lowercase()
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<MfsTransResponse>
                notifyDataSetChanged()
            }

        }
    }

}