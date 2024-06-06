package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuItemModel


class MenuItemListAdaptar(
    private var menuList: ArrayList<MenuItemModel>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<MenuItemModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = menuList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: MenuItemModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_menu_list_item, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val iv_menu_icon: ImageView? = holder.itemView.findViewById(R.id.iv_menu_icon)
        val tv_display_menu_name: TextView? =
            holder.itemView.findViewById(R.id.tv_display_menu_name)
        val tv_optional_value: TextView? = holder.itemView.findViewById(R.id.tv_optional_value)
        val full_view: LinearLayout? = holder.itemView.findViewById(R.id.full_view)

        tv_display_menu_name!!.text = currentItem.menuTitle
        currentItem.imageId?.let { iv_menu_icon?.setImageResource(it) }

        val content = SpannableString(currentItem.optional)
        content.setSpan(UnderlineSpan(), 0, content.length, 0)
        tv_optional_value!!.text = content



        full_view!!.setOnClickListener {
            val selectedList: MenuItemModel = currentItem
            listener.onItemClick(selectedList)

        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size

    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = menuList
                } else {

                    val resultList = ArrayList<MenuItemModel>()
                    for (row in menuList) {
                        if (
                            row.menuTitle.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.optional.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<MenuItemModel>
                notifyDataSetChanged()
            }

        }
    }
}