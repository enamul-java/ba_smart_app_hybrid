package eraapps.bankasia.bdinternetbanking.apps.adapter

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardTypeModel
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil
import java.util.*


class CardViewListAdaptar(
    private var accountList: ArrayList<CreditCardTypeModel>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {

    var requestFilterList = ArrayList<CreditCardTypeModel>()
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
        fun onItemClick(item: CreditCardTypeModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_card_list_dialog, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tvCardType: TextView = holder.itemView.findViewById(R.id.tvCardType)
        val tvCardNo: TextView = holder.itemView.findViewById(R.id.tvCardNo)
        val lo_container: LinearLayout = holder.itemView.findViewById(R.id.lo_container)
        val iv_cardicon: ImageView = holder.itemView.findViewById(R.id.iv_cardicon)

        tvCardType.text = currentItem.cardDes
        tvCardNo.text = ValidationUtil.cardNoMask(currentItem.cardNo)

        if (currentItem.cardType!!.startsWith("V")) {
            iv_cardicon.setImageResource(R.drawable.icon_visa)
        } else {
            iv_cardicon.setImageResource(R.drawable.icon_master)
        }


        lo_container.setOnClickListener {
            val selectedList: CreditCardTypeModel = requestFilterList[position]
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

                    val resultList = ArrayList<CreditCardTypeModel>()
                    for (row in accountList) {
                        if (
                            row.cardNo.toString().lowercase()
                                .contains(
                                    constraint.toString().lowercase()
                                ) || row.cardDes.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CreditCardTypeModel>
                notifyDataSetChanged()
            }

        }
    }

}