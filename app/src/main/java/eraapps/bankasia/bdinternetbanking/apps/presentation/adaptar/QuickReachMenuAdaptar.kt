package com.emon.raihan.dynamicutility.adaptar

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuModel

import java.util.ArrayList

class QuickReachMenuAdaptar(
    private var movieList: ArrayList<MenuModel>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<MenuModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: MenuModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_popular_service, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val menu_icon: ImageView? = holder.itemView.findViewById(R.id.menu_icon)
        val menu_name: TextView? = holder.itemView.findViewById(R.id.menu_name)
        val menu_soft_code: TextView? = holder.itemView.findViewById(R.id.menu_soft_code)

        menu_name!!.text = currentItem.menuTitle
        if (menu_soft_code != null) {
            menu_soft_code.text = currentItem.id.toString()
        }

        currentItem.imageId?.let { menu_icon?.setImageResource(it) }




        menu_icon?.setOnClickListener {
            val selectedList: MenuModel = currentItem
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
                    requestFilterList = movieList
                } else {

                    val resultList = ArrayList<MenuModel>()
                    for (row in movieList) {
                        if (
                            row.menuTitle.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.id.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<MenuModel>
                notifyDataSetChanged()
            }

        }
    }
}