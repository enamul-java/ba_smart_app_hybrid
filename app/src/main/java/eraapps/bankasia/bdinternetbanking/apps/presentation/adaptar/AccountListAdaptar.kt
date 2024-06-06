package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel

class AccountListAdaptar(
    private var acList: ArrayList<AccountDetailsModel>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<AccountDetailsModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
//

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: AccountDetailsModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_ac_info_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_ac_type: TextView? = holder.itemView.findViewById(R.id.tv_ac_type)
        val tv_ac_no: TextView? = holder.itemView.findViewById(R.id.tv_ac_no)
        val tv_balance: TextView? = holder.itemView.findViewById(R.id.tv_balance)
        val iv_ac_flag: ImageView? = holder.itemView.findViewById(R.id.iv_ac_flag)
        val iv_ac_view: ImageView? = holder.itemView.findViewById(R.id.iv_ac_view)

        tv_ac_type!!.text = currentItem.accountTypeDesc
        tv_ac_no!!.text = currentItem.accountNumber
        tv_balance!!.text = currentItem.balance + " " + currentItem.currency

        if (currentItem.status!!.startsWith("A")) {
            iv_ac_flag!!.setImageResource(R.drawable.active_icon)

        } else {
            iv_ac_flag!!.setImageResource(R.drawable.inactive_icon)

        }


        iv_ac_view!!.setOnClickListener {
            val selectedList: AccountDetailsModel = currentItem
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

                    val resultList = ArrayList<AccountDetailsModel>()
                    for (row in acList) {
                        if (
                            row.accountNumber.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.accountTypeDesc.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<AccountDetailsModel>
                notifyDataSetChanged()
            }

        }
    }
}