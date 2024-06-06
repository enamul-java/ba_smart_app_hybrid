package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardStatementDto
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class CreditCardStatementAdaptar(
    private var acList: ArrayList<CreditCardStatementDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<CreditCardStatementDto>()
    lateinit var mcontext: Context
    lateinit var globalVariable: GlobalVariable

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: CreditCardStatementDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_view_st_list, parent, false)
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
        val tv_statement_date: TextView? = holder.itemView.findViewById(R.id.tv_statement_date)
        val tv_payment_date: TextView? = holder.itemView.findViewById(R.id.tv_payment_date)
        val tv_currency: TextView? = holder.itemView.findViewById(R.id.tv_currency)
        val tv_client_id: TextView? = holder.itemView.findViewById(R.id.tv_client_id)
        val iv_edit: ImageView? = holder.itemView.findViewById(R.id.iv_edit)

        val tv_client_id_lavel: TextView = holder.itemView.findViewById(R.id.tv_client_id_lavel)
        val tv_card_title_lavel: TextView = holder.itemView.findViewById(R.id.tv_card_title_lavel)
        val tv_statement_date_lavel: TextView =
            holder.itemView.findViewById(R.id.tv_statement_date_lavel)
        val tv_payment_date_lavel: TextView =
            holder.itemView.findViewById(R.id.tv_payment_date_lavel)
        val tv_currency_lavel: TextView = holder.itemView.findViewById(R.id.tv_currency_lavel)

        tv_card_no!!.text = ValidationUtil.cardNoMask(currentItem.cardNo)
        tv_card_title!!.text = currentItem.clientName
        tv_statement_date!!.text = currentItem.statementDate
        tv_payment_date!!.text = currentItem.paymentDate
        tv_currency!!.text = currentItem.currency
        tv_client_id!!.text = currentItem.clientId


        iv_edit!!.setOnClickListener {
            val selectedList: CreditCardStatementDto = currentItem
            listener.onItemClick(selectedList)

        }

        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            tv_client_id_lavel.setText(R.string.client_id_bangla)
            tv_card_title_lavel.setText(R.string.card_title_bangla)
            tv_statement_date_lavel.setText(R.string.statement_date_bangla)
            tv_payment_date_lavel.setText(R.string.payment_date_bangla)
            tv_currency_lavel.setText(R.string.currency_bangla)
        }

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
    }


    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = acList
                } else {

                    val resultList = ArrayList<CreditCardStatementDto>()
                    for (row in acList) {
                        if (
                            row.cardNo.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.currency.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CreditCardStatementDto>
                notifyDataSetChanged()
            }

        }
    }
}