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
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class StatementListAdaptar(
    private var acList: ArrayList<AccountStatementDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<AccountStatementDto>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: AccountStatementDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_transaction_details, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#CCCCCC"))
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        val tv_trns_date: TextView = holder.itemView.findViewById(R.id.tv_trns_date)
        val tv_trns_particulars: TextView = holder.itemView.findViewById(R.id.tv_trns_particulars)
        val tv_trns_amount: TextView = holder.itemView.findViewById(R.id.tv_trns_amount)
        val view_row: LinearLayout = holder.itemView.findViewById(R.id.view_row)

        tv_trns_date.text = currentItem.transactionDate
        tv_trns_particulars.text = currentItem.remarks
        //tv_trns_amount!!.text = currentItem.availaleBalance

        var damount = 0.0
        var camount = 0.0

        try {
            if (currentItem.debtAmount.isNotEmpty()) {
                damount = ValidationUtil.replacecomma(currentItem.debtAmount).toDouble()
            } else {
                damount = 0.0
            }
            if (currentItem.creditAmount.isNotEmpty()) {
                camount = ValidationUtil.replacecomma(currentItem.creditAmount).toDouble()
            } else {
                camount = 0.0
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (damount > 0) {
            tv_trns_amount.text =
                "-" + ValidationUtil.commaSeparateAmountStatment(currentItem.debtAmount)

        }

        if (camount > 0) {
            tv_trns_amount.text =
                "+" + ValidationUtil.commaSeparateAmountStatment(currentItem.creditAmount)

        }


        view_row.setOnClickListener {
            val selectedList: AccountStatementDto = currentItem
            listener.onItemClick(selectedList)

        }

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
                    requestFilterList = acList
                } else {

                    val resultList = ArrayList<AccountStatementDto>()
                    for (row in acList) {
                        if (
                            row.availaleBalance.lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.creditAmount.lowercase()
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
                requestFilterList = results?.values as ArrayList<AccountStatementDto>
                notifyDataSetChanged()
            }

        }
    }
}