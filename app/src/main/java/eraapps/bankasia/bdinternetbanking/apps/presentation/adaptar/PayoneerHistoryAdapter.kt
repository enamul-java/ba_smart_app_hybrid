package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar


import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import android.widget.TextView
import eraapps.bankasia.bdinternetbanking.apps.R



import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout

import androidx.recyclerview.widget.RecyclerView

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto


class PayoneerHistoryAdapter(

    private var accountList: ArrayList<PayoneerDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<PayoneerDto>()
    lateinit var mcontext: Context

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
                .inflate(R.layout.row_payoneer_history2, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_hisotry_payee_id: TextView = holder.itemView.findViewById(R.id.tv_hisotry_payee_id)
        val tv_hisotry_amount: TextView = holder.itemView.findViewById(R.id.tv_hisotry_amount)
        val tv_hisotry_payee_usd_amount: TextView = holder.itemView.findViewById(R.id.tv_hisotry_payee_usd_amount)
        val tv_hisotry_payee_bdt_amount: TextView = holder.itemView.findViewById(R.id.tv_hisotry_payee_bdt_amount)
        val tv_hisotry_rate: TextView =holder.itemView.findViewById(R.id.tv_hisotry_rate)
        val tv_hisotry_tc: TextView = holder.itemView.findViewById(R.id.tv_hisotry_tc)
        val tv_hisotry_transaction_date: TextView = holder.itemView.findViewById(R.id.tv_hisotry_transaction_date)
        val tv_hisotry_complete: TextView = holder.itemView.findViewById(R.id.tv_hisotry_complete)
        val iv_refresh: ImageView = holder.itemView.findViewById(R.id.iv_refresh)
        val lo_container: LinearLayout = holder.itemView.findViewById(R.id.lo_container)



        tv_hisotry_payee_id.text = currentItem.payment_id.toString()
        tv_hisotry_amount.text = currentItem.amount.toString()
        tv_hisotry_payee_usd_amount.text = currentItem.usdAmountPortion.toString()
        tv_hisotry_payee_bdt_amount.text = currentItem.totalBDTAmount.toString()
        tv_hisotry_rate.text = currentItem.midrate.toString()
        tv_hisotry_transaction_date.text = currentItem.transactiondate.toString()

        // Log.e("trans_complete_flg--",currentItem.trans_complete_flg.toString())
        // Log.e("CBSStatusCode--",currentItem.cbsStatusCode.toString())
        iv_refresh.visibility = View.GONE

        if ("N".equals(currentItem.cbsStatusCode) && "N".equals(currentItem.trans_complete_flg)) {
            tv_hisotry_tc.text = "BA"
            tv_hisotry_tc.setTextColor(Color.parseColor("#E20000"))
            iv_refresh.visibility = View.VISIBLE
        }


        if ("Y".equals(currentItem.cbsStatusCode) && "Y".equals(currentItem.trans_complete_flg)) {
            tv_hisotry_complete.text = "Completed"
            tv_hisotry_tc.text = "SUC"

            tv_hisotry_complete.setTextColor(Color.parseColor("#28a745"));
            tv_hisotry_tc.setTextColor(Color.parseColor("#28a745"));
        } else {
            tv_hisotry_complete.text = "Not Complete. Please Refresh"
            tv_hisotry_complete.setTextColor(Color.parseColor("#E20000"))
        }



        lo_container.setOnClickListener {
            val selectedList: PayoneerDto = requestFilterList[position]
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

                    val resultList = ArrayList<PayoneerDto>()
                    for (row in accountList) {
                        if (
                            row.amount.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.transactiondate.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<PayoneerDto>
                notifyDataSetChanged()
            }

        }
    }

}