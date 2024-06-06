package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import java.util.ArrayList


class BranchListAdapter(
    private var branchlist: ArrayList<BranchLocationDto>,
    context: Context,
    listenerInit: OnItemClickListener,
) :
    RecyclerView.Adapter<BranchListAdapter.MyViewHolder>(), Filterable {

    var requestFilterList = ArrayList<BranchLocationDto>()
    var mcontext: Context
    var listener: OnItemClickListener

    init {
        requestFilterList = branchlist
        listener = listenerInit
        mcontext = context
        notifyDataSetChanged()
    }

    interface OnItemClickListener {
        fun onItemClick(position: BranchLocationDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val listView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_branch_location_layout, parent, false)
        return MyViewHolder(listView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tv_branch_name.text = currentItem.branchName.uppercase()
        holder.tv_branch_address.text = currentItem.branchAddress
        holder.tv_branch_phone.text = currentItem.phone
        holder.tv_branch_open_date_time.text = currentItem.opendate
        //holder.itemView.branchFax.text = currentItem.fax.toString()
        holder.tv_branch_location_map.text = "Switch to Map Mode"

        holder.tv_branch_location_map.setOnClickListener {
            Toast.makeText(mcontext, currentItem.branchName, Toast.LENGTH_SHORT).show()
            val selectedList = requestFilterList[position]
            listener.onItemClick(selectedList)

        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        notifyDataSetChanged()
    }

    fun setData(branchdata: ArrayList<BranchLocationDto>) {
        this.requestFilterList = branchdata
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = branchlist
                } else {


                    val resultList = ArrayList<BranchLocationDto>()
                    for (row in branchlist) {
                        if (
                            row.branchName.lowercase()
                                .contains(constraint.toString().lowercase()) or
                            row.branchAddress.lowercase()
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
                requestFilterList = results?.values as ArrayList<BranchLocationDto>
                notifyDataSetChanged()
            }

        }
    }



    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tv_branch_name : TextView
        var tv_branch_address : TextView
        var tv_branch_open_date_time : TextView
        var tv_branch_phone : TextView
        var tv_branch_location_map : TextView
        init {
            tv_branch_name = itemView.findViewById(R.id.tv_branch_ame)
            tv_branch_address = itemView.findViewById(R.id.tv_branch_address)
            tv_branch_open_date_time = itemView.findViewById(R.id.tv_branch_open_date_time)
            tv_branch_phone = itemView.findViewById(R.id.tv_branch_phone)
            tv_branch_location_map = itemView.findViewById(R.id.tv_branch_location_map)
        }
    }

}