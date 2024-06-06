package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class CardListNewAdapter (
    private var acList: ArrayList<CardInfoResponseDto>,
    indialog: Dialog,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<CardInfoResponseDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener
    var dialog: Dialog



    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit

        dialog = indialog

    }

    interface OnItemClickListener {
        fun onItemClick(item: CardInfoResponseDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_view_card_list_new, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        globalVariable = mcontext.applicationContext as GlobalVariable

        val currentItem = requestFilterList[position]

        val tv_card_no: TextView? = holder.itemView.findViewById(R.id.tv_card_no)
        val tv_card_title: TextView? = holder.itemView.findViewById(R.id.tv_card_title)
        val tv_expiry_day: TextView? = holder.itemView.findViewById(R.id.tv_expiry_day)
        val tv_status: TextView? = holder.itemView.findViewById(R.id.tv_status)

        val tv_card_title_lavel: TextView = holder.itemView.findViewById(R.id.tv_card_title_lavel)
        val tv_expiry_day_lavel: TextView = holder.itemView.findViewById(R.id.tv_expiry_day_lavel)
        val tv_status_lavel: TextView = holder.itemView.findViewById(R.id.tv_status_lavel)
        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)


        val iv_edit: ImageView? = holder.itemView.findViewById(R.id.iv_edit)
        val iv_delete: ImageView? = holder.itemView.findViewById(R.id.iv_delete)

        tv_card_no!!.text = ValidationUtil.cardNoMask(currentItem.cardNumber)
        tv_card_title!!.text = currentItem.nameOnCard
        // tv_expiry_day!!.text = DateUtil.getCardDate(currentItem.expiryDate)
        tv_expiry_day!!.text = currentItem.cardDesc

        if (currentItem.status.equals("Y")) {
            tv_status!!.text = "Active"
            tv_status.setTextColor(Color.parseColor("#28a745"))

        } else {
            tv_status!!.text = "Inactive"
            tv_status.setTextColor(Color.parseColor("#F50000"))

        }



        iv_edit!!.setOnClickListener {
            val selectedList: CardInfoResponseDto = currentItem
            selectedList.oprMode = "E"
            listener.onItemClick(selectedList)

        }

        iv_delete!!.setOnClickListener {
            val selectedList: CardInfoResponseDto = currentItem
            selectedList.oprMode = "D"
            listener.onItemClick(selectedList)
        }

        lo_container!!.setOnClickListener {
            val selectedList: CardInfoResponseDto = requestFilterList[position]
            listener.onItemClick(selectedList)
            dialog.dismiss()

        }

        if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
            tv_card_title_lavel.setText(R.string.card_title_bangla)
            tv_expiry_day_lavel.setText(R.string.select_card_type_bangla)
            tv_status_lavel.setText(R.string.status_bangla)
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
                    requestFilterList = acList
                } else {

                    val resultList = ArrayList<CardInfoResponseDto>()
                    for (row in acList) {
                        if (
                            row.cardNumber.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.nameOnCard.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CardInfoResponseDto>
                notifyDataSetChanged()
            }

        }
    }
}