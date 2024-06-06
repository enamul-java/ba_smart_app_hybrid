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
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.util.DateUtil
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class ShohozPaymentHistoryListAdapter(
    private var accountList: ArrayList<ShohozPaymentHistoryDto>,
    historyFor:String,
    listenerInit: OnItemClickListener,
    deleteClick:OnDeleteClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<ShohozPaymentHistoryDto>()
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
        fun onItemClick(item: ShohozPaymentHistoryDto)
    }

    interface OnDeleteClickListener {
        fun onItemClick(item: ShohozPaymentHistoryDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_shohoz_payment_history_listview, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]
        val tv_order_id: TextView = holder.itemView.findViewById(R.id.tv_order_id)
        val tv_bus_name: TextView = holder.itemView.findViewById(R.id.tv_bus_name)
        val tv_seat_no: TextView = holder.itemView.findViewById(R.id.tv_seat_no)
        val tv_journey_date: TextView = holder.itemView.findViewById(R.id.tv_journey_date)
        val tv_contact_no: TextView = holder.itemView.findViewById(R.id.tv_contact_no)
        val tv_journey_from: TextView = holder.itemView.findViewById(R.id.tv_journey_from)
        val tv_journey_to: TextView = holder.itemView.findViewById(R.id.tv_journey_to)
        val tv_purchase_date: TextView = holder.itemView.findViewById(R.id.tv_purchase_date)
        val tv_boarding_point: TextView = holder.itemView.findViewById(R.id.tv_boarding_point)
        val tv_reporting_time: TextView = holder.itemView.findViewById(R.id.tv_reporting_time)
        val tv_amount: TextView = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView = holder.itemView.findViewById(R.id.tv_status)
        val tv_total_amount: TextView = holder.itemView.findViewById(R.id.tv_total_amount)
        val tv_pnr: TextView = holder.itemView.findViewById(R.id.tv_pnr)
        val lo_message: LinearLayout = holder.itemView.findViewById(R.id.lo_message)
        val lo_details: LinearLayout = holder.itemView.findViewById(R.id.lo_details)

        /*
        val tv_transaction_id: TextView = holder.itemView.findViewById(R.id.tv_transaction_id)
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
        */

        val btn_download: Button = holder.itemView.findViewById(R.id.btn_download)

        val lo_cancel: LinearLayout = holder.itemView.findViewById(R.id.lo_cancel)
        val btn_cancel_withdraw: Button = holder.itemView.findViewById(R.id.btn_cancel_withdraw)




        if(DateUtil.isValidToCancel(currentItem.LASTCANCELDATE)
            && currentItem.CANCEL_FLG.equals("N")){
            lo_cancel.visibility = View.VISIBLE
        }else{
            lo_cancel.visibility = View.GONE
        }

        tv_order_id.text = currentItem.SHOHOZREF
        tv_bus_name.text = currentItem.COMPANY_NAME
        tv_seat_no.text = currentItem.SEAT_NO

        tv_journey_date.text = currentItem.JOURNEY_DATE
        tv_contact_no.text = currentItem.CONTACT_NO
        tv_journey_from.text = currentItem.JOURNEY_FROM
        tv_journey_to.text = currentItem.JOURNEY_TO
        tv_purchase_date.text = currentItem.TIMSTAMP
        tv_boarding_point.text = currentItem.BORDING_POINT
        tv_reporting_time.text = currentItem.BORDING_TIME

        tv_pnr.text = currentItem.PNR
        tv_amount.text = ValidationUtil.commaSeparateAmount(currentItem.AMOUNT)
        tv_total_amount.text = ValidationUtil.commaSeparateAmount(currentItem.TOTAL_AMOUNT)

        if(currentItem.CANCEL_FLG.equals("C")){
            tv_status.text = "You have canceled your ticket"
            tv_status.setTextColor(Color.RED)
        }else if(currentItem.CANCEL_FLG.equals("S")){
            tv_status.text = "Your ticket canceled by bus operator"
            tv_status.setTextColor(Color.RED)
        }else{
            tv_status.text = currentItem.SHOHOZPAYSTATUS
            tv_status.setTextColor(Color.BLACK)
        }


        if(currentItem.COMPANY_NAME.equals(" ")
            && currentItem.PNR.equals(" ")){
            lo_message.visibility = View.VISIBLE
            lo_details.visibility = View.GONE
        }else{
            lo_message.visibility = View.GONE
            lo_details.visibility = View.VISIBLE
        }

        /*
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
        */


        btn_download.setOnClickListener {
            val selectedList: ShohozPaymentHistoryDto = requestFilterList[position]
            //listener.onItemClick(selectedList)
        }

        btn_cancel_withdraw.setOnClickListener {
            val selectedList: ShohozPaymentHistoryDto = requestFilterList[position]
            deleteClick.onItemClick(selectedList)
        }

        /*
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


        }*/


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
                    val resultList = ArrayList<ShohozPaymentHistoryDto>()
                    for (row in accountList) {
                        if (
                            row.COMPANY_NAME.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.JOURNEY_DATE.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.SHOHOZREF.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.CONTACT_NO.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.JOURNEY_FROM.lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.JOURNEY_TO.lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.AMOUNT.lowercase()
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
                requestFilterList = results?.values as ArrayList<ShohozPaymentHistoryDto>
                notifyDataSetChanged()
            }

        }
    }



}