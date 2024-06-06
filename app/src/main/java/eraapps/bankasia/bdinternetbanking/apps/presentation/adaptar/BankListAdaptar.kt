package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear

class BankListAdaptar(
    private var movieList: ArrayList<BillModel>,
    dialog1: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<BillModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit
        dialog = dialog1

    }

    interface OnItemClickListener {
        fun onItemClick(item: BillModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_dropdown_item, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_bill_type_name: TextView = holder.itemView.findViewById(R.id.tv_bill_type_name)
        val iv_bill_type: ImageView = holder.itemView.findViewById(R.id.iv_bill_type)
        val view_layout: LinearLayout = holder.itemView.findViewById(R.id.view_layout)

        tv_bill_type_name.text = currentItem.menuTitle

        view_layout.setOnClickListener {
            val selectedList: BillModel = currentItem
            listener.onItemClick(selectedList)

        }

        val image =
            Constants.BASE_URL + "access/v1/bankimage/" + currentItem.imageId
        Log.d("bankimage-->", image)


        if (image == "") {
            iv_bill_type.setImageResource(R.drawable.ic_baseline_person_24)
        } else {
            iv_bill_type.loadOperatorsImages(
                image,
                getProgressDrawble(iv_bill_type.context)
            )
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

                    val resultList = ArrayList<BillModel>()
                    for (row in movieList) {
                        if (
                            row.menuTitle.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.code.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<BillModel>
                notifyDataSetChanged()
            }

        }
    }
}