package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanListResponseModel
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.DateUtil
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil


class LoanSettlementListAdaptar(
    private var movieList: List<LoanListResponseModel>,
    listenerInit: OnItemClickListener,
    actionFlag:String
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    var requestFilterList = ArrayList<LoanListResponseModel>()
    lateinit var mcontext: Context
    lateinit var actionFlag:String

    var listener: OnItemClickListener

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    init {
        this.requestFilterList = movieList as ArrayList<LoanListResponseModel>
        this.listener = listenerInit
        this.actionFlag = actionFlag
    }

    interface OnItemClickListener {
        fun onItemClick(item: LoanListResponseModel, actionFlag:String, payableAmount:Double)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val atmListView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.row_loan_settle_list, parent, false)
        val sch = MyViewHolder(atmListView)
        mcontext = parent.context
        return sch
    }

    override fun getItemCount(): Int {
        return  movieList.size
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val currentItem = requestFilterList[position]

        val lo_container: LinearLayout? = holder.itemView.findViewById(R.id.lo_container)
        val tv_account_no_value: TextView? = holder.itemView.findViewById(R.id.tv_account_no_value)
        val tv_amount_value: TextView? = holder.itemView.findViewById(R.id.tv_amount_value)
        val tv_status_value: TextView? = holder.itemView.findViewById(R.id.tv_status_value)

        val tv_account_title_value: TextView? = holder.itemView.findViewById(R.id.tv_account_title_value)
        val tv_outstanding_amount_value: TextView? = holder.itemView.findViewById(R.id.tv_outstanding_amount_value)
        val tv_sanction_date_value: TextView? = holder.itemView.findViewById(R.id.tv_sanction_date_value)
        val tv_expiry_date_value: TextView? = holder.itemView.findViewById(R.id.tv_expiry_date_value)
        val tv_link_ac_bal_value: TextView? = holder.itemView.findViewById(R.id.tv_link_ac_bal_value)

        val lo_full_payment: LinearLayout? = holder.itemView.findViewById(R.id.lo_full_payment)
        val lo_partial_payment: LinearLayout? = holder.itemView.findViewById(R.id.lo_partial_payment)
        val edt_repay_amount_value: EditText? = holder.itemView.findViewById(R.id.edt_repay_amount_value)

        if(this.actionFlag.equals("F")){
            lo_full_payment?.visibility = View.VISIBLE
            lo_partial_payment?.visibility = View.GONE
        }else if(this.actionFlag.equals("P")){
            lo_full_payment?.visibility = View.GONE
            lo_partial_payment?.visibility = View.VISIBLE
            edt_repay_amount_value?.requestFocus()
        }

        tv_account_no_value!!.text = currentItem.accountNo.toString()
        tv_amount_value!!.text = formatValue(currentItem.amount.toString())
        tv_status_value!!.text = currentItem.status.toString()

        tv_account_title_value?.text = currentItem.accountTitle.toString()
        tv_outstanding_amount_value?.text = formatValue(currentItem.curBal.toString())
        tv_sanction_date_value?.text = formatDate(currentItem.openDate.toString())
        tv_expiry_date_value?.text = formatDate(currentItem.expiryDate.toString())
        tv_link_ac_bal_value?.text = formatValue(currentItem.sourceAcBalance.toString())


        lo_container!!.setOnClickListener {
            var isValid:Boolean = true
            val selectedList = requestFilterList[position]
            var payableAmount: Double = 0.0
            if (this.actionFlag.equals("F")) {
                payableAmount = selectedList.amount!!.toDouble()
            } else if (this.actionFlag.equals("P")) {
                var partialAmount:String = edt_repay_amount_value?.text.toString()
                if(!partialAmount.isNullOrEmpty()) {
                   payableAmount = partialAmount!!.toDouble()
                }else{
                    isValid = false
                    CustomAlert.showErrorMessage(mcontext as Activity,"Please Enter Partial Amount!", TextContants.englishLanguageCode)
                }
            }

            /*
            try {
                if(payableAmount>selectedList.curBal!!.toDouble()){
                    isValid = false
                    CustomAlert.showErrorMessage(mcontext as Activity,"Insufficient Balance!", TextContants.languageCode)
                }
            }catch (e:Exception){
            }*/

            if(isValid) {
                listener.onItemClick(selectedList, this.actionFlag, payableAmount)
            }

        }

    }

    private fun formatValue(inputValue:String):String{
        var output:String = inputValue
        try {
            output = ValidationUtil.commaSeparateAmount(inputValue)
        }catch (e:Exception){

        }
        return  output
    }

    private fun formatDate(inputValue:String):String{
        var output:String = inputValue
        try {
            output = DateUtil.formatFullDate(inputValue,"")
        }catch (e:Exception){

        }
        return  output
    }



}
