package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OperatorModel
import eraapps.bankasia.bdinternetbanking.apps.util.getProgressDrawble
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImages
import eraapps.bankasia.bdinternetbanking.apps.util.loadOperatorsImagesClear

class OperatorListAdaptar(
    private var operatorModelList: ArrayList<OperatorModel>,
    listenerInit: OnItemClickListener, activity: Activity
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<OperatorModel>()
    var mcontext: Context
    var selected_item = -1


    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = operatorModelList
        listener = listenerInit

        mcontext = activity
    }

    interface OnItemClickListener {
        fun onItemClick(item: OperatorModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_operator_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_operator_name: TextView = holder.itemView.findViewById(R.id.tv_operator_name)
        val iv_operator_icon: ImageView = holder.itemView.findViewById(R.id.iv_operator_icon)

        tv_operator_name.text = currentItem.menuTitle
        // currentItem.imageId?.let { iv_operator_icon?.setImageResource(it) }

        val image = Constants.BASE_URL + "access/v1/menuimage/" + currentItem.imageId
        Log.d("operatoriamge-->", image)


        if (image == "") {
            iv_operator_icon.setImageResource(R.drawable.ic_baseline_person_24)
        } else {
            iv_operator_icon.loadOperatorsImages(
                image,
                getProgressDrawble(iv_operator_icon.context)
            )
        }



        iv_operator_icon.setOnClickListener {
            val selectedList: OperatorModel = currentItem
            selected_item = position
            notifyDataSetChanged()
            listener.onItemClick(selectedList)

        }
        if (selected_item == position) {
            tv_operator_name.setTextColor(ContextCompat.getColor(mcontext, R.color.app_color))
            iv_operator_icon.background = ResourcesCompat.getDrawable(
                mcontext.resources, R.drawable.btn_round_border_transparent_select,
                null
            )

        } else {
            tv_operator_name.setTextColor(ContextCompat.getColor(mcontext, R.color.black))
            iv_operator_icon.background = ResourcesCompat.getDrawable(
                mcontext.resources, R.drawable.btn_round_border_transparent_unselect,
                null
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
                    requestFilterList = operatorModelList
                } else {

                    val resultList = ArrayList<OperatorModel>()
                    for (row in operatorModelList) {
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
                requestFilterList = results?.values as ArrayList<OperatorModel>
                notifyDataSetChanged()
            }

        }
    }
}