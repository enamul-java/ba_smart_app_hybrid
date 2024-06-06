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
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ServiceModel
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import java.util.ArrayList

class ServiceListAdapter(
    private var atmlist: ArrayList<BillModel>,
    context: Context,
    listenerInit: ServiceListAdapter.OnItemClickListener,
) :
    RecyclerView.Adapter<ServiceListAdapter.MyViewHolder>(), Filterable {

    var requestFilterList = ArrayList<BillModel>()
    var mcontext: Context
    var listener: ServiceListAdapter.OnItemClickListener


    init {
        requestFilterList = atmlist
        listener = listenerInit
        mcontext = context
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_service_list_item, parent, false)
        return MyViewHolder(atmListView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tv_service_name.text = currentItem.menuTitle.toString()

        val image =
            Constants.BASE_URL + "access/v1/menuimage-new/" + currentItem.imageId + "/" + currentItem.code


        if (currentItem.imageId == "") {
            holder.iv_service_icon
                .setImageResource(R.drawable.icon_image_placeholder)
        } else {
            holder.iv_service_icon.loadOperatorsImages(
                image, getProgressDrawble(holder.iv_service_icon.context)
            )
        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
    }

    interface OnItemClickListener {
        fun onItemClick(item: BillModel)
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = atmlist
                } else {

                    val resultList = ArrayList<BillModel>()
                    for (row in atmlist) {
                        if (
                            row.menuTitle.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<BillModel>
                notifyDataSetChanged()
            }

        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var tv_service_name: TextView
        var iv_service_icon: ImageView

        init {
            tv_service_name = itemView.findViewById(R.id.tv_service_name)
            iv_service_icon = itemView.findViewById(R.id.iv_service_icon)

        }
    }

}