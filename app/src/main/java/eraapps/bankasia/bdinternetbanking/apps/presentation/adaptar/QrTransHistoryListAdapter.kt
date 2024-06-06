package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class QrTransHistoryListAdapter(
    private var accountList: ArrayList<TransferHistoryDto>,
    historyFor:String,
    listenerInit: OnItemClickListener,
    deleteClick:OnDeleteClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<TransferHistoryDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener
    var deleteClick: OnDeleteClickListener

    var historyFor:String

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit

        this.historyFor = historyFor
        this.deleteClick = deleteClick
    }

    interface OnItemClickListener {
        fun onItemClick(item: TransferHistoryDto)
    }

    interface OnDeleteClickListener {
        fun onItemClick(item: TransferHistoryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_qr_transfer_history_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_merchant_name: TextView = holder.itemView.findViewById(R.id.tv_merchant_name)
        val tv_transaction_type: TextView = holder.itemView.findViewById(R.id.tv_transaction_type)
        val tv_transaction_date: TextView = holder.itemView.findViewById(R.id.tv_transaction_date)
        val tv_source_card_no: TextView = holder.itemView.findViewById(R.id.tv_source_card_no)
        val tv_merchant_pan_no: TextView = holder.itemView.findViewById(R.id.tv_merchant_pan_no)
        val tv_transaction_id: TextView = holder.itemView.findViewById(R.id.tv_transaction_id)
        val tv_amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)

        val tv_merchant_name_label: TextView =
            holder.itemView.findViewById(R.id.tv_merchant_name_label)
        val tv_transaction_type_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_type_label)
        val tv_transaction_date_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_date_label)
        val tv_source_card_no_label: TextView =
            holder.itemView.findViewById(R.id.tv_source_card_no_label)
        val tv_merchant_pan_no_label: TextView =
            holder.itemView.findViewById(R.id.tv_merchant_pan_no_label)
        val tv_transaction_id_label: TextView =
            holder.itemView.findViewById(R.id.tv_transaction_id_label)
        val tv_amount_label: TextView = holder.itemView.findViewById(R.id.tv_amount_label)
        val tv_status_label: TextView = holder.itemView.findViewById(R.id.tv_status_label)

        val btn_download: Button = holder.itemView.findViewById(R.id.btn_download)

        val lo_cancel: LinearLayout = holder.itemView.findViewById(R.id.lo_cancel)
        val btn_cancel_withdraw: Button = holder.itemView.findViewById(R.id.btn_cancel_withdraw)



        if(historyFor.equals("CASHQR") && currentItem.status.equals("Pending")){
            lo_cancel.visibility = View.VISIBLE
        }else{
            lo_cancel.visibility = View.GONE
        }

        tv_merchant_name.text = currentItem.destAcTitle
        tv_transaction_type.text = currentItem.transType

        tv_merchant_pan_no.text = currentItem.destAcNo
        tv_transaction_id.text = currentItem.transId
        tv_status.text = currentItem.status
        tv_amount.text = ValidationUtil.commaSeparateAmount(currentItem.amount)

        if(historyFor.equals("CASHQR")){
            tv_transaction_date.text = currentItem.transDate
        }else{
            tv_transaction_date.text = currentItem.transDate
        }

        if (currentItem.status.lowercase().startsWith("s")) {
            tv_status.setTextColor(Color.parseColor("#4BB543"))
            //  tv_status.text = ContextCompat.getString(tv_status.context, R.string.success)
        } else {
            tv_status.setTextColor(Color.RED)
            // tv_status.text = ContextCompat.getString(tv_status.context, R.string.failed)
        }

        if (currentItem.transType.startsWith("QR Cash")) {
            tv_source_card_no_label.text =
                ContextCompat.getString(tv_status.context, R.string.source_account_no)
            tv_transaction_id_label.text =
                ContextCompat.getString(tv_status.context, R.string.token)
            tv_merchant_pan_no_label.text =
                ContextCompat.getString(tv_status.context, R.string.queue_id)
            tv_merchant_name_label.text =
                ContextCompat.getString(tv_status.context, R.string.branch_name)
            tv_source_card_no.text = currentItem.sourceAcNo
        } else {
            tv_source_card_no.text = ValidationUtil.cardNoMask(currentItem.sourceAcNo)
        }

        btn_download.setOnClickListener {
            val selectedList: TransferHistoryDto = requestFilterList[position]
            listener.onItemClick(selectedList)
        }

        btn_cancel_withdraw.setOnClickListener {
            val selectedList: TransferHistoryDto = requestFilterList[position]
            deleteClick.onItemClick(selectedList)
        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            if (currentItem.transType.startsWith("QR Cash")) {
                tv_merchant_name_label.setText(R.string.branch_name_bangla)
                tv_transaction_type_label.setText(R.string.transaction_type_bangla)
                tv_transaction_date_label.setText(R.string.transaction_date_bangla)
                tv_source_card_no_label.setText(R.string.source_account_no_bangla)
                tv_merchant_pan_no_label.setText(R.string.queue_id)
                tv_transaction_id_label.setText(R.string.token)
                tv_status_label.setText(R.string.status_bangla)
                tv_amount_label.setText(R.string.amout_bangla)
            } else {
                tv_merchant_name_label.setText(R.string.merchant_detail_merchant_name_bangla)
                tv_transaction_type_label.setText(R.string.transaction_type_bangla)
                tv_transaction_date_label.setText(R.string.transaction_date_bangla)
                tv_source_card_no_label.setText(R.string.source_card_no_bangla)
                tv_merchant_pan_no_label.setText(R.string.merchant_pan_no_bangla)
                tv_transaction_id_label.setText(R.string.transaction_id_bangla)
                tv_status_label.setText(R.string.status_bangla)
                tv_amount_label.setText(R.string.amout_bangla)
            }

        }


    }

    override fun getItemCount(): Int {
        return requestFilterList.size
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
                            row.transType.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.transDate.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.transId.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.destAcNo.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.sourceAcNo.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.destAcTitle.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.amount.lowercase()
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
                requestFilterList = results?.values as ArrayList<TransferHistoryDto>
                notifyDataSetChanged()
            }

        }
    }

}