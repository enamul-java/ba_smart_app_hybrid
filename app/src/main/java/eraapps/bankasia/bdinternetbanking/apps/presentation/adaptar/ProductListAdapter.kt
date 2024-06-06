package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
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
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ProductDto
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadProductImages
import java.util.ArrayList


class ProductListAdapter(
    private var notificationList: ArrayList<ProductDto>,
    context: Context,
    dialog1: Dialog,
    listenerInit: OnItemClickListener,
) :
    RecyclerView.Adapter<ProductListAdapter.MyViewHolder>(), Filterable {
    var requestFilterList = ArrayList<ProductDto>()
    var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog

    init {
        requestFilterList = notificationList
        listener = listenerInit
        dialog = dialog1
        mcontext = context

    }

    interface OnItemClickListener {
        fun onItemClick(item: ProductDto?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val listView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_product_itemlist_dialog, parent, false)
        return MyViewHolder(listView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.menu_item_name.text = currentItem.title
        holder.menu_item_description.text = currentItem.message

        val imagload =
            Constants.BASE_URL+"access/v1/products/image/"+currentItem.imageId

        if (currentItem.imageFlg == "Y") {
            holder.menu_icon.visibility = View.VISIBLE

            if (imagload == "") {
                holder.menu_icon.setImageResource(R.drawable.product)
            } else {
                holder.menu_icon.loadProductImages(
                    imagload,
                    getProgressDrawble(holder.menu_icon.context)
                )
            }


        }

        holder.ivView.setOnClickListener {
            val selectedList: ProductDto = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.cancel()

        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        notifyDataSetChanged()
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = notificationList
                } else {

                    val resultList = ArrayList<ProductDto>()
                    for (row in notificationList) {
                        if (
                            row.imageId.lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.imageId.lowercase()
                                .contains(constraint.toString().lowercase()

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
                requestFilterList = results?.values as ArrayList<ProductDto>
                notifyDataSetChanged()
            }

        }
    }


    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var menu_item_name:TextView
        var menu_item_description:TextView
        var menu_icon:ImageView
        var ivView:ImageView

        init {
            menu_item_name = itemView.findViewById(R.id.menu_item_name)
            menu_item_description = itemView.findViewById(R.id.menu_item_description)
            menu_icon = itemView.findViewById(R.id.menu_icon)
            ivView = itemView.findViewById(R.id.ivView)
        }
    }

}