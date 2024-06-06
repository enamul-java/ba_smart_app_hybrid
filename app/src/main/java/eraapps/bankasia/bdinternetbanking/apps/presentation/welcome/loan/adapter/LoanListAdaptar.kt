package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CardCodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel


class LoanListAdaptar(
    private var movieList: ArrayList<LoanResultDataModel>,
    listenerInit: OnItemClickListener,
    var displayType: String,
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var requestFilterList = ArrayList<LoanResultDataModel>()
    lateinit var mcontext: Context

    var listener: OnItemClickListener


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        requestFilterList = movieList
        listener = listenerInit


    }

    interface OnItemClickListener {
        fun onItemClick(item: LoanResultDataModel)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loan_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {

        return  movieList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]
        val tv_amount: TextView? = holder.itemView.findViewById(R.id.tv_amount)
        val tv_status: TextView? = holder.itemView.findViewById(R.id.tv_status)
        val tv_display_amount: TextView? = holder.itemView.findViewById(R.id.tv_display_amount)
        val tv_applied_for: TextView? = holder.itemView.findViewById(R.id.tv_applied_for)
        val tv_date: TextView? = holder.itemView.findViewById(R.id.tv_date)
        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)

        if ("ALL".equals(displayType)) {
            tv_amount!!.text = currentItem.applied_amount.toString()
            tv_display_amount!!.text = currentItem.approved_amount.toString()
            tv_applied_for!!.text = currentItem.applied_for.toString()
            tv_date!!.text = currentItem.applied_date
            if (currentItem.resultflag.startsWith("A") || currentItem.resultflag.startsWith("D")) {
                tv_status!!.text = currentItem.result
                tv_status.setBackgroundResource(R.drawable.btn_background_app_color)
            } else {
                tv_status!!.text = currentItem.result
                tv_status.setBackgroundResource(R.drawable.bt_background_rounded_red)
            }
        }

        if ("A".equals(displayType)) {
            tv_amount!!.text = currentItem.applied_amount.toString()
            tv_display_amount!!.text = currentItem.approved_amount.toString()
            tv_applied_for!!.text = currentItem.applied_for.toString()
            tv_date!!.text = currentItem.applied_date
            if (currentItem.resultflag.startsWith("A") || currentItem.resultflag.startsWith("D")) {
                tv_status!!.text = currentItem.result
                tv_status.setBackgroundResource(R.drawable.btn_background_app_color)
            }
        }

        if ("R".equals(displayType)) {
            tv_amount!!.text = currentItem.applied_amount.toString()
            tv_display_amount!!.text = currentItem.approved_amount.toString()
            tv_applied_for!!.text = currentItem.applied_for.toString()
            tv_date!!.text = currentItem.applied_date
            if (currentItem.resultflag.startsWith("N")||currentItem.resultflag.startsWith("U")) {
                tv_status!!.text = currentItem.result
                tv_status.setBackgroundResource(R.drawable.bt_background_rounded_red)
            }
        }

        lo_container!!.setOnClickListener {
            val selectedList: LoanResultDataModel = requestFilterList[position]
            listener.onItemClick(selectedList)
        }



//        if ("A" == currentItem.a) {
//            tv_status!!.text = "Approved"
//            tv_status.setBackgroundResource(R.drawable.button_background)
//
//        } else {
//            tv_status!!.text = "Rejected"
//            tv_status.setBackgroundResource(R.drawable.bt_background_rounded_red)
//
//        }


        /*  tv_product_name.setOnClickListener {
              val selectedList: Loan = currentItem
              listener.onItemClick(selectedList)

          }*/

    }



}
