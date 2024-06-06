package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CardCodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil
import java.util.*


class DestinationCardListAdaptar(
    private var accountList: ArrayList<CardCodeDesOptions>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<CardCodeDesOptions>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener
    var dialog: Dialog


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = accountList
        listener = listenerInit
        dialog = indialog

    }

    interface OnItemClickListener {
        fun onItemClick(item: CardCodeDesOptions)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_source_card_list_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvCardTitle: TextView? = holder.itemView.findViewById(R.id.tvCardTitle)
        val tvCardNo: TextView? = holder.itemView.findViewById(R.id.tvCardNo)
        val tvCardType: TextView? = holder.itemView.findViewById(R.id.tvCardType)
        val iv_bankicon: ImageView = holder.itemView.findViewById(R.id.iv_bankicon)
        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)




        if (tvCardTitle != null) {
            tvCardTitle.text = currentItem.code
        }


        if (tvCardNo != null) {
            tvCardNo.text = ValidationUtil.cardNoMask(currentItem.desc)
        }
        if (tvCardType != null) {
            tvCardType.text = currentItem.cardDesc
        }



        lo_container!!.setOnClickListener {
            val selectedList: CardCodeDesOptions = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.dismiss()

        }
    }

    override fun getItemCount(): Int {
        return requestFilterList.size
        //Log.e("mobileNumberList--->", requestFilterList.size.toString())
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = accountList
                } else {

                    val resultList = ArrayList<CardCodeDesOptions>()
                    for (row in accountList) {
                        if (
                            row.desc.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.code.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.desc.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.mobileNumber.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.emailId.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.cardDesc.toString().lowercase()
                                .contains(constraint.toString().lowercase())
                            || row.cvv2.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CardCodeDesOptions>
                notifyDataSetChanged()
            }

        }
    }

}