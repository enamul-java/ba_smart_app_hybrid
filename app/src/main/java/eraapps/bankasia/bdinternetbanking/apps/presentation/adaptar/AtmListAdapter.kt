package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto


class AtmListAdapter(
    private var list: ArrayList<ATMLocationDto>,
    context: Context,
    listenerInit: OnItemClickListener,
) :
    RecyclerView.Adapter<AtmListAdapter.MyViewHolder>(), Filterable {

    var requestFilterList = ArrayList<ATMLocationDto>()
    var mcontext: Context
    var listener: OnItemClickListener

    init {
        requestFilterList = list
        listener = listenerInit
        mcontext = context
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: ATMLocationDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val listView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_atm_location_layout, parent, false)
        return MyViewHolder(listView)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tv_atm_name.text = currentItem.atmName.toString().uppercase()
        holder.tv_atm_address.text = currentItem.atmLocation.toString()
        holder.tv_atm_location_map.text = "Switch to Map Mode"

        holder.tv_atm_location_map.setOnClickListener {
            Toast.makeText(mcontext, currentItem.atmName.toString(), Toast.LENGTH_SHORT).show()
            val selectedList = requestFilterList[position]
            listener.onItemClick(selectedList)

        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        notifyDataSetChanged()
    }

    fun setData(branchdata: ArrayList<ATMLocationDto>) {
        this.requestFilterList = branchdata
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = list
                } else {


                    val resultList = ArrayList<ATMLocationDto>()
                    for (row in list) {
                        if (
                            row.atmName.lowercase()
                                .contains(constraint.toString().lowercase()) or
                            row.atmLocation.lowercase()
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
                requestFilterList = results?.values as ArrayList<ATMLocationDto>
                notifyDataSetChanged()
            }

        }
    }



    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_atm_name : TextView
        var tv_atm_address : TextView
        var tv_atm_location_map : TextView
        init {
            tv_atm_name = itemView.findViewById(R.id.tv_atm_name)
            tv_atm_address = itemView.findViewById(R.id.tv_atm_address)
            tv_atm_location_map = itemView.findViewById(R.id.tv_atm_location_map)
        }
    }

}