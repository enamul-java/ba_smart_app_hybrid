package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsTransResponse
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class TransHistoryListAdapter(
    private var accountList: ArrayList<TransferHistoryDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<TransferHistoryDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

    }

    interface OnItemClickListener {
        fun onItemClick(item: TransferHistoryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_transfer_history_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_destination_a_c_title: TextView =
            holder.itemView.findViewById(R.id.tv_destination_a_c_title)
        val tv_transaction_type: TextView = holder.itemView.findViewById(R.id.tv_transaction_type)
        val tv_transaction_date: TextView = holder.itemView.findViewById(R.id.tv_transaction_date)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_wallet_no: TextView = holder.itemView.findViewById(R.id.tv_wallet_no)
        val tv_transaction_id: TextView = holder.itemView.findViewById(R.id.tv_transaction_id)
        val tv_amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)

        val tv_destination_a_c_title_label: TextView =
            holder.itemView.findViewById(R.id.tv_destination_a_c_title_label)
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


        tv_destination_a_c_title.text = currentItem.destAcTitle
        tv_transaction_type.text = currentItem.transType
        tv_transaction_date.text = currentItem.transDate
        tv_account_no.text = currentItem.sourceAcNo
        tv_wallet_no.text = currentItem.destAcNo
        tv_transaction_id.text = currentItem.transId
        // tv_status.text = currentItem.status
        tv_amount.text = ValidationUtil.commaSeparateAmount(currentItem.amount.toString())

        if (currentItem.status.toString().lowercase().startsWith("s")) {
            tv_status.setTextColor(Color.parseColor("#4BB543"))
            tv_status.setText("Successful")
        } else if (currentItem.status.toString().lowercase().startsWith("0-y")) {
            tv_status.setTextColor(Color.parseColor("#4BB543"))
            tv_status.setText("Successful")
        } else {
            tv_status.setTextColor(Color.RED)
            tv_status.setText("Unsuccessful")
        }

        btn_download.setOnClickListener {
            val selectedList: TransferHistoryDto = requestFilterList[position]
            listener.onItemClick(selectedList)
        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_destination_a_c_title_label.setText(R.string.destination_account_title_bangla)
            tv_transaction_type_label.setText(R.string.transaction_type_bangla)
            tv_transaction_date_label.setText(R.string.transaction_date_bangla)
            tv_account_no_label.setText(R.string.account_no_bangla)
            tv_wallet_no_label.setText(R.string.destination_account_no_bangla)
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
                    val resultList = ArrayList<TransferHistoryDto>()
                    for (row in accountList) {
                        if (
                            row.transType.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.transDate.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.transId.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.destAcNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.sourceAcNo.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.destAcTitle.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<TransferHistoryDto>
                notifyDataSetChanged()
            }

        }
    }

}