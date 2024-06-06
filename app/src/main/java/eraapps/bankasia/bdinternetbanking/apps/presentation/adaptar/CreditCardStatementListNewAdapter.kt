package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementObjectDto

class CreditCardStatementListNewAdapter(
    private var acList: ArrayList<CreditCardStatementObjectDto>,
    listenerInit: OnItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), Filterable {
    var requestFilterList = ArrayList<CreditCardStatementObjectDto>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = acList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: CreditCardStatementObjectDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_transaction_details_credit_card, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        if (position % 2 == 1) {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"))
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        } else {
            holder.itemView.setBackgroundColor(Color.parseColor("#CCCCCC"))
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
        val tv_trns_date: TextView = holder.itemView.findViewById(R.id.tv_trns_date)
        val tv_trns_no: TextView = holder.itemView.findViewById(R.id.tv_trns_no)
        val tv_trns_particulars: TextView = holder.itemView.findViewById(R.id.tv_trns_particulars)
        val tv_trns_amount: TextView = holder.itemView.findViewById(R.id.tv_trns_amount)
        val view_row: LinearLayout = holder.itemView.findViewById(R.id.view_row)

        tv_trns_date.text = currentItem.tranTime!!.subSequence(0,10)
        tv_trns_no.text = currentItem.tranNumber.toString()
        tv_trns_particulars.text = currentItem.termName+" - " +
                ""+decodeTermOwner(currentItem.termOwner.toString())+", " +
                ""+sentenceCase(currentItem.termCity.toString())+", " +
                ""+sentenceCase(currentItem.termCountryName.toString())

        //<TermName> - <TermOwner>, <TermCity>, <TermCountryName>
        //tv_trns_amount!!.text = currentItem.availaleBalance

        tv_trns_date.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
        tv_trns_amount.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)
        tv_trns_particulars.setTextSize(TypedValue.COMPLEX_UNIT_SP,12F)

        var damount = 0.0
        var camount = 0.0

        /*try {
            if (currentItem.debtAmount.isNotEmpty()) {
                damount = ValidationUtil.replacecomma(currentItem.debtAmount).toDouble()
            } else {
                damount = 0.0
            }
            if (currentItem.creditAmount.isNotEmpty()) {
                camount = ValidationUtil.replacecomma(currentItem.creditAmount).toDouble()
            } else {
                camount = 0.0
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        if (damount > 0) {
            tv_trns_amount.text =
                "-" + ValidationUtil.commaSeparateAmountStatment(currentItem.debtAmount)

        }

        if (camount > 0) {
            tv_trns_amount.text =
                "+" + ValidationUtil.commaSeparateAmountStatment(currentItem.creditAmount)

        }*/

        if(currentItem.currencyAcct.equals("50")){
            tv_trns_amount.text =currentItem.amountAcct.toString()+" BDT"
        }else{
            tv_trns_amount.text =currentItem.amountAcct.toString()+" USD"
        }



        view_row.setOnClickListener {
            val selectedList: CreditCardStatementObjectDto = currentItem
            listener.onItemClick(selectedList)

        }

    }

    private fun decodeTermOwner(input:String):String{
        try {
            if(null == input || input.isNullOrEmpty() || input.equals("null")){
                return ""
            }
            if(input.equals("BAL")){
                return "Bank Asia Ltd"
            }
        }catch (e:Exception){}
        return input;
    }

    private fun sentenceCase(input:String):String{
        var res = input
        try {
            res =  input.lowercase()
            res = res.substring(0,1).uppercase() + res.substring(1)
        }catch (e:Exception){}
        return res
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

                    val resultList = ArrayList<CreditCardStatementObjectDto>()
                    for (row in acList) {
                        if (
                            row.amountAcct.toString().lowercase()
                                .contains(constraint.toString().lowercase())

                            || row.amount.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CreditCardStatementObjectDto>
                notifyDataSetChanged()
            }

        }
    }
}