package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.adapter.LoanSettlementListAdaptar
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanListResponseModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model.LoanViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*

@AndroidEntryPoint
class LoanEarlyPayment : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView


    private lateinit var recyclerView: RecyclerView

    private lateinit var globalVariable: GlobalVariable
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var mAdapter: LoanSettlementListAdaptar
    var loanList: List<LoanListResponseModel> = ArrayList<LoanListResponseModel>()
    private lateinit var dialog: Dialog

    var  account_number = ""
    var  accountType = ""
    var  branchCode = ""
    var  amount = ""
    var  intAmount = ""

    lateinit var btn_full:Button
    lateinit var btn_partial:Button
    lateinit var lo_settlement_type:LinearLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_early_payment)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)


        recyclerView = findViewById(R.id.recyclerView)

        btn_full = findViewById(R.id.btn_full)
        btn_partial = findViewById(R.id.btn_partial)
        lo_settlement_type = findViewById(R.id.lo_settlement_type)

        lo_settlement_type.visibility = View.VISIBLE

        globalVariable = this.applicationContext as GlobalVariable
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.early_settlement)


        iv_header_back.setOnClickListener {
            val intent = Intent(this@LoanEarlyPayment, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@LoanEarlyPayment)
        }

        if (!CustomAlert.isOnline(this@LoanEarlyPayment)) {
            CustomAlert.showInternetConnectionMessage(
                this@LoanEarlyPayment,
                globalVariable.languageCode
            )
        }else {
            getLoanList()
        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                        val intent = Intent(this@LoanEarlyPayment, LoanDashboardActivity::class.java)
                        CustomActivityClear.doClearActivity(intent, this@LoanEarlyPayment)

                }
            })

        btn_full.setOnClickListener {
            paymentMethodChange("F")
        }

        btn_partial.setOnClickListener {
            //paymentMethodChange("P")
            CustomAlert.showErrorMessage(
                this@LoanEarlyPayment,
                "Under Development!",
                globalVariable.languageCode
            )
        }

        observeViewModel()
    }

    private fun paymentMethodChange(actionFlag: String){
        if(null != loanList && loanList.size>0){
            try {
                if (actionFlag.equals("P")) {
                    var sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btn_partial.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_active
                            )
                        )
                        btn_full.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_inactive
                            )
                        )
                    } else {
                        btn_partial.setBackground(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_active
                            )
                        )
                        btn_full.setBackground(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_inactive
                            )
                        )
                    }
                } else if (actionFlag.equals("F")) {
                    var sdk = android.os.Build.VERSION.SDK_INT;
                    if (sdk < android.os.Build.VERSION_CODES.JELLY_BEAN) {
                        btn_partial.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_inactive
                            )
                        )
                        btn_full.setBackgroundDrawable(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_active
                            )
                        )
                    } else {
                        btn_partial.setBackground(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_inactive
                            )
                        )
                        btn_full.setBackground(
                            ContextCompat.getDrawable(
                                applicationContext,
                                R.drawable.btn_background_active
                            )
                        )
                    }
                }
            }catch (e:Exception){
                Log.e("Background Change: ", e.toString())
            }
            populateLoanList(actionFlag+"")
        }else{
            CustomAlert.showErrorMessage(
                this@LoanEarlyPayment,
                "No Loan Data Found!",
                globalVariable.languageCode
            )
        }
    }

    fun observeViewModel() {
        loanViewModel.error_response.observe(this) {
            it?.let {
                pDialog.dismiss()

                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }

        loanViewModel.loanListResponse.observe(this) {

            pDialog.dismiss()
            Log.e("loanListResponse--",it.toString())
            loanList = it
            populateLoanList("F")

        }

        loanViewModel.accountClosingResponse.observe(this) {

            pDialog.dismiss()
           if("0".equals(it.outCode)){
               CustomAlert.showOK(this, it.outMessage, globalVariable.languageCode)
           }else{
               CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
           }

        }


    }


    fun populateLoanList(actionFlag:String){
        val mLayoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = mLayoutManager
        recyclerView.itemAnimator = DefaultItemAnimator()

        mAdapter =
            LoanSettlementListAdaptar(loanList,
                object : LoanSettlementListAdaptar.OnItemClickListener{
                override fun onItemClick(item: LoanListResponseModel,
                                         actionFlag: String,
                                         payableAmount: Double) {
                 Log.e("",item.toString())

                    account_number = item.accountNo.toString()
                    accountType = item.accountType.toString()
                    branchCode = item.branchCode.toString()
                    amount = item.amount.toString()
                    intAmount = item.intAmount.toString()

                    var dueOrPrincipalAmount:Double = 0.0
                    var interestAmount:Double = 0.0

                    try {
                        dueOrPrincipalAmount = item.curBal!!.toDouble()
                        //If amount is negative the multiply by -1 to make it positive value
                        if(dueOrPrincipalAmount<0){
                            dueOrPrincipalAmount = dueOrPrincipalAmount*-1
                        }
                    }catch (e:Exception){

                    }

                    try {
                        if(actionFlag.equals("F")) {
                            interestAmount = payableAmount - dueOrPrincipalAmount
                        }else if(actionFlag.equals("P")) {
                            dueOrPrincipalAmount = dueOrPrincipalAmount - payableAmount
                        }
                    }catch (e:Exception){
                       Log.e("Loan", "Intrest Reate Add problem"+e.toString())
                    }

                    if(actionFlag.equals("P")) {
                        CustomAlert.showErrorMessage(
                            this@LoanEarlyPayment,
                            "Under Development!",
                            globalVariable.languageCode
                        )
                    }else {
                        showDialogDestAcc(
                            actionFlag,
                            "$dueOrPrincipalAmount",
                            "$interestAmount",
                            "$payableAmount"
                        )
                    }
                }

            },
                actionFlag+"")
        recyclerView.adapter = mAdapter

    }

    private fun formatValue(inputValue:String):String{
        var output:String = inputValue
        try {
            output = ValidationUtil.commaSeparateAmount(inputValue)
        }catch (e:Exception){

        }
        return  output
    }
    private fun showDialogDestAcc(
        actionFlag: String,
        dueOrPrincipalAmount:String,
        interestAmount:String,
        payableAmount:String) {

        dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
        dialog.setContentView(R.layout.nano_loan_early_settlement_dialog)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        val lp = WindowManager.LayoutParams()
        lp.copyFrom(dialog.window!!.attributes)
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT

        val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
        val btn_cancel = dialog.findViewById(R.id.btn_cancel) as AppCompatButton
        val btn_submit = dialog.findViewById(R.id.btn_submit) as AppCompatButton
        //val tv_account_no = dialog.findViewById(R.id.tv_account_no) as TextView

        val tv_due_amount_label = dialog.findViewById(R.id.tv_due_amount_label) as TextView
        val tv_due_amount_value = dialog.findViewById(R.id.tv_due_amount_value) as TextView


        val tv_payable_amount_label = dialog.findViewById(R.id.tv_payable_amount_label) as TextView
        val tv_payable_amount_value = dialog.findViewById(R.id.tv_payable_amount_value) as TextView

        val tv_interest_amount = dialog.findViewById(R.id.tv_interest_amount) as TextView

        val lo_interest = dialog.findViewById(R.id.lo_interest) as LinearLayout

        if(actionFlag.equals("P")){
            tv_due_amount_label.setText("Remaining Balance")
            tv_payable_amount_label.setText("Total Repayment Amount")
            lo_interest.visibility = View.GONE

            tv_due_amount_value.setText(dueOrPrincipalAmount)
            tv_payable_amount_value.setText(payableAmount)

        }else if(actionFlag.equals("F")){
            tv_due_amount_label.setText("Principal Amount")
            tv_payable_amount_label.setText("Final Settlement Amount")
            tv_interest_amount.setText(formatValue(interestAmount))
            lo_interest.visibility = View.VISIBLE

            tv_due_amount_value.setText(formatValue(dueOrPrincipalAmount))
            tv_payable_amount_value.setText(formatValue(payableAmount))
        }






        btn_submit.setOnClickListener {
            if (account_number.isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                      this,
                        TextContants.laonAccount,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                      this,
                        TextContants.laonAccount,
                        globalVariable.languageCode
                    )
                }
            } else if (payableAmount.toString().isEmpty()) {
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                       this,
                        TextContants.laonAmount,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.laonAmount,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(
                  this,
                    globalVariable.languageCode
                )
            } else {
                accountClosing(payableAmount,actionFlag)
            }
        }

        btn_cancel.setOnClickListener {
            dialog.cancel()
        }

        ivClose.setOnClickListener {
            dialog.cancel()
        }


        dialog.show()
        dialog.window!!.attributes = lp
    }

    private fun getLoanList(){
        pDialog.show()
        var model = LoanModel()
        model.customerCode = globalVariable.customerCode
        model.actStateFlag = "and acstat = 'ACT' " // ""ACT"//Active //acstat = 'ACT'
        model.authorization = globalVariable.token
        this.let { it1 -> loanViewModel.getLoanList(HeaderData.headerWelcome(globalVariable),model) }
    }

    private fun accountClosing(payableAmount:String,actionFlag: String) {

        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.loanAccount= account_number
        model.accountType = accountType
        model.branchCode= branchCode
        model.amount = payableAmount
        model.settlementFlag = actionFlag

        this.let { it1 -> loanViewModel.accountClosing(HeaderData.headerWelcome(globalVariable),model) }
    }
}