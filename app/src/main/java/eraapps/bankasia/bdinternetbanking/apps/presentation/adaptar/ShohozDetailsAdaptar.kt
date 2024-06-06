package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozDetailsDto
import java.util.*


class ShohozDetailsAdaptar(
    private var list: ArrayList<ShohozDetailsDto>,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<ShohozDetailsDto>()
    lateinit var mcontext: Context



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = list


    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_shohoz_details_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_shohoz_label: TextView? = holder.itemView.findViewById(R.id.tv_shohoz_label)
        val tv_shohoz_value: TextView? = holder.itemView.findViewById(R.id.tv_shohoz_value)

        tv_shohoz_label?.setText(currentItem.label)
        tv_shohoz_value?.setText(currentItem.value)
     //   val image = Constants.BASE_URL + "access/v1/bankimage/" + currentItem.bankCode








    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        //Log.e("mobileNumberList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = list
                } else {

                    val resultList = ArrayList<ShohozDetailsDto>()
                    for (row in list) {
                        if (
                            row.label.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.value.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<ShohozDetailsDto>
                notifyDataSetChanged()
            }

        }
    }

}