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
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ChequeStatusModel
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class ChequeStatusListAdaptar(
    private var acList: ArrayList<ChequeStatusModel>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<ChequeStatusModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: ChequeStatusModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_cheque_details, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#CCCCCC"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        val tv_cheque_number: TextView = holder.itemView.findViewById(R.id.tv_cheque_number)
        val tv_account_no: TextView = holder.itemView.findViewById(R.id.tv_account_no)
        val tv_trns_amount: TextView = holder.itemView.findViewById(R.id.tv_trns_amount)
        val view_row: LinearLayout = holder.itemView.findViewById(R.id.view_row)

        tv_cheque_number.text = currentItem.leafNo
        tv_account_no.text = currentItem.accountNumber
        tv_trns_amount.text = currentItem.amount




        view_row.setOnClickListener {
            val selectedList: ChequeStatusModel = currentItem
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

                    val resultList = ArrayList<ChequeStatusModel>()
                    for (row in acList) {
                        if (
                            row.accountNumber.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.accountTitle.toString().lowercase()
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

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                requestFilterList = results?.values as ArrayList<ChequeStatusModel>
                notifyDataSetChanged()
            }

        }
    }
}