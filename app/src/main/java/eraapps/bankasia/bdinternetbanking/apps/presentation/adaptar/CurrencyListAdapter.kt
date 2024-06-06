package eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.RecyclerView
import com.amulyakhare.textdrawable.TextDrawable
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CurrencyDto
import java.util.*


class CurrencyListAdapter(private var list: ArrayList<CurrencyDto>, flg: String) :
    RecyclerView.Adapter<CurrencyListAdapter.MyViewHolder>(), Filterable {

    var requestFilterList = ArrayList<CurrencyDto>()
    lateinit var mcontext: Context
     var flag : String


    init {
        requestFilterList = list
        flag = flg
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        val listView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_currency_rate_layout, parent, false)
        return MyViewHolder(listView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        holder.tvCurrencyName.text = currentItem.curCode.toString()
        if (flag=="buy"){
            holder.tvCurrencyAmt.text = currentItem.ttCleanRate.toString()
        }
        else{
            holder.tvCurrencyAmt.text = currentItem.ttOdRate.toString()
        }

        if (currentItem.curCode!!.isNotEmpty()){

            val cur: Currency = Currency.getInstance(currentItem.curCode.toString())
            val symbol: String = cur.symbol
            // Log.d("symbol-->", symbol)
            //holder.itemView.ivCurrency.text = symbol
            val value = symbol.uppercase()
            val drawable = TextDrawable.builder()
                .buildRect(value, Color.parseColor("#5778F2"))

            holder.ivCurrency.setImageDrawable(drawable)
        }




        /* val imagload =
                   IpConfigure.getIp() + "image-api?requestCode=3&itemId=" + currentItem.curCode.toString()
                       .trim()
               holder.itemView.ivCurrency.loadCurrencyImagesClear(
                    imagload,
                    getProgressDrawble(holder.itemView.ivCurrency.context)
                )*/

    }

    override fun getItemCount(): Int {
        return requestFilterList.size
    }

    fun setData(atmdata: ArrayList<CurrencyDto>) {
        this.requestFilterList = atmdata
        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                if (constraint.toString().isEmpty()) {
                    requestFilterList = list
                } else {

                    val resultList = ArrayList<CurrencyDto>()
                    for (row in list) {
                        if (
                            row.curCode.toString().lowercase()
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
                requestFilterList = results?.values as ArrayList<CurrencyDto>
                notifyDataSetChanged()
            }

        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        var tvCurrencyName : TextView
        var tvCurrencyAmt : TextView
        var ivCurrency : ImageView
        init {
            tvCurrencyName = itemView.findViewById(R.id.tvCurrencyName)
            tvCurrencyAmt = itemView.findViewById(R.id.tvCurrencyAmt)
            ivCurrency = itemView.findViewById(R.id.ivCurrency)
        }
    }

}