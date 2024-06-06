package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransactionLimitDto


class TransactionLimitListAdapter(private var transLimitlist: ArrayList<TransactionLimitDto>) :
    RecyclerView.Adapter<TransactionLimitListAdapter.MyViewholder>(), Filterable {

    var requestFilterList = ArrayList<TransactionLimitDto>()
    lateinit var mcontext: Context



    class MyViewholder(itemView: View) : RecyclerView.ViewHolder(itemView){
         var tv_trans_type : TextView
         var tv_trans_daily : TextView
         var tv_trans_per_trans : TextView
        init {
            tv_trans_type = itemView.findViewById(R.id.tv_trans_type)
            tv_trans_daily = itemView.findViewById(R.id.tv_trans_daily)
            tv_trans_per_trans = itemView.findViewById(R.id.tv_trans_per_trans)
        }
    }

    init {
        requestFilterList = transLimitlist
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewholder {

        val listView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_transaction_limit, parent, false)
        return MyViewholder(listView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewholder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tv_trans_type.text = currentItem.menuname.toString()
        holder.tv_trans_daily.text =
            currentItem.dailyCount.toString() + " | " + currentItem.totalLimit.toString()
        holder.tv_trans_per_trans.text =
            currentItem.minAmount.toString() + " | " + currentItem.maxAmount.toString()


    }

    override fun getItemCount(): Int {
        return requestFilterList.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = transLimitlist
                } else {

                    val resultList = ArrayList<TransactionLimitDto>()
                    for (row in transLimitlist) {
                        if (
                            row.menuname.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<TransactionLimitDto>
                notifyDataSetChanged()
            }

        }
    }

}