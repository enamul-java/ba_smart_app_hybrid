package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.AccountViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model.LoanViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import java.util.ArrayList
@AndroidEntryPoint
class LoanDisburseActivity : AppCompatActivity() {


    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView




/*
    private lateinit var loan_input_layout: LinearLayout
    private lateinit var tv_amount_value: TextView
    private lateinit var tv_laon_id_value: TextView
    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_receiving_ac_value: MaterialAutoCompleteTextView
    private lateinit var et_remarks_value: TextInputEditText

    private lateinit var loan_input_confirm_layout: LinearLayout
    private lateinit var btn_proceed: AppCompatButton
    private lateinit var tv_loan_confirm_amount_value: TextView
    private lateinit var tv_loan_confirm_month_value: TextView
    private lateinit var tv_loan_confirm_receiver_account_value: TextView
    private lateinit var tv_loan_confirm_loan_payable_permonth_value: TextView
    private lateinit var tv_loan_confirm_total_interest_payable_value: TextView
    private lateinit var tv_loan_confirm_total_payment_value: TextView
    private lateinit var et_otp_value: TextInputEditText

    private lateinit var loan_success_layout: LinearLayout
    private lateinit var btnApplyLoan: AppCompatButton
    private lateinit var tv_success_message: TextView

    */
    //private lateinit var btn_add_beneficiary_go_home: LinearLayout
   // private lateinit var btn_home: AppCompatButton

    private lateinit var loan_success_layout: LinearLayout
    private lateinit var tv_loan_success_amount_value: TextView
    private lateinit var tv_loan_success_month_value: TextView
    private lateinit var tv_loan_success_receiver_account_value: TextView
    private lateinit var tv_loan_success_interest_rate_value: TextView
    private lateinit var tv_loan_success_processing_fee_vat_value: TextView
    private lateinit var tv_loan_success_usabale_amount_value: TextView
    private lateinit var tv_loan_success_payment_date_value: TextView
    private lateinit var tv_loan_success_first_installment_value: TextView
    private lateinit var tv_loan_success_loan_payable_permonth_value: TextView
    private lateinit var tv_loan_success_total_interest_payable_value: TextView
    private lateinit var tv_loan_success_total_payment_value: TextView

    private lateinit var btnDisburse: AppCompatButton
    private lateinit var btnLater: AppCompatButton
    private lateinit var btbCancelLoan: AppCompatButton


    private lateinit var layout_loan_disburse_success: LinearLayout
    private lateinit var tv_loan_disburse_message: TextView
    private lateinit var btn_loan_disburse_go_home: LinearLayout
    private lateinit var btn_home_disburse: AppCompatButton

    private lateinit var layout_loan_cancel: LinearLayout
    private lateinit var tv_loan_cancel_message: TextView
    private lateinit var btn_loan_cancel_go_home: LinearLayout
    private lateinit var btn_home_cancel: AppCompatButton


    private lateinit var layout_bottom_diburse_cancel: LinearLayout


    private lateinit var globalVariable: GlobalVariable
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var pDialog: SweetAlertDialog
    val accountViewModel: AccountViewModel by viewModels()

    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var installMonthList: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var emiResponse:LoanResponse? = null;
    var month = "";
    var monthDes = "";
    var accountNo = "";


    var appliedFor = ""

    var emi = "0"
    var loanAmount = "0"
    var loanId = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_disburse)


        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        globalVariable = this.applicationContext as GlobalVariable
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        loanId = globalVariable.loanItemSelected?.customer_id.toString();
        loanAmount = globalVariable.loanItemSelected?.approved_amount.toString();
        month = globalVariable.loanItemSelected?.tenor.toString();
        accountNo = globalVariable.loanItemSelected?.account_number.toString();

        Log.e("desbaseDeta", "Loan id ${loanId} Ammount: ${loanAmount} Month: ${month} Account: ${accountNo}")


        loan_success_layout = findViewById(R.id.loan_success_layout)
        tv_loan_success_amount_value = findViewById(R.id.tv_loan_success_amount_value)
        tv_loan_success_month_value = findViewById(R.id.tv_loan_success_month_value)
        tv_loan_success_receiver_account_value = findViewById(R.id.tv_loan_success_receiver_account_value)
        tv_loan_success_interest_rate_value = findViewById(R.id.tv_loan_success_interest_rate_value)
        tv_loan_success_processing_fee_vat_value = findViewById(R.id.tv_loan_success_processing_fee_vat_value)
        tv_loan_success_usabale_amount_value = findViewById(R.id.tv_loan_success_usabale_amount_value)
        tv_loan_success_payment_date_value = findViewById(R.id.tv_loan_success_payment_date_value)
        tv_loan_success_first_installment_value = findViewById(R.id.tv_loan_success_first_installment_value)
        tv_loan_success_loan_payable_permonth_value = findViewById(R.id.tv_loan_success_loan_payable_permonth_value)
        tv_loan_success_total_interest_payable_value = findViewById(R.id.tv_loan_success_total_interest_payable_value)
        tv_loan_success_total_payment_value = findViewById(R.id.tv_loan_success_total_payment_value)

        btnDisburse = findViewById(R.id.btnDisburse)
        btnLater = findViewById(R.id.btnLater)
        btbCancelLoan = findViewById(R.id.btbCancelLoan)


        layout_loan_disburse_success = findViewById(R.id.layout_loan_disburse_success)
        tv_loan_disburse_message = findViewById(R.id.tv_loan_disburse_message)
        btn_loan_disburse_go_home = findViewById(R.id.btn_loan_disburse_go_home)
        btn_home_disburse = findViewById(R.id.btn_home_disburse)

        layout_loan_cancel = findViewById(R.id.layout_loan_cancel)
        tv_loan_cancel_message = findViewById(R.id.tv_loan_cancel_message)
        btn_loan_cancel_go_home = findViewById(R.id.btn_loan_cancel_go_home)
        btn_home_cancel = findViewById(R.id.btn_home_cancel)
        layout_bottom_diburse_cancel = findViewById(R.id.layout_bottom_diburse_cancel)

/*
        loan_input_layout = findViewById(R.id.loan_input_layout)
        tv_amount_value = findViewById(R.id.tv_amount_value)
        tv_laon_id_value = findViewById(R.id.tv_laon_id_value)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_receiving_ac_value = findViewById(R.id.sp_receiving_ac_value)
        et_remarks_value = findViewById(R.id.et_remarks_value)

        loan_input_confirm_layout = findViewById(R.id.loan_input_confirm_layout)
        btn_proceed = findViewById(R.id.btn_proceed)

        tv_loan_confirm_amount_value = findViewById(R.id.tv_loan_confirm_amount_value)
        tv_loan_confirm_month_value = findViewById(R.id.tv_loan_confirm_month_value)
        tv_loan_confirm_receiver_account_value = findViewById(R.id.tv_loan_confirm_receiver_account_value)
        tv_loan_confirm_loan_payable_permonth_value = findViewById(R.id.tv_loan_confirm_loan_payable_permonth_value)
        tv_loan_confirm_total_interest_payable_value = findViewById(R.id.tv_loan_confirm_total_interest_payable_value)
        tv_loan_confirm_total_payment_value = findViewById(R.id.tv_loan_confirm_total_payment_value)
        et_otp_value = findViewById(R.id.et_otp_value)

        btnApplyLoan = findViewById(R.id.btnApplyLoan)

        loan_success_layout = findViewById(R.id.loan_success_layout)
        tv_success_message = findViewById(R.id.tv_success_message)
        */
      //  btn_add_beneficiary_go_home = findViewById(R.id.btn_add_beneficiary_go_home)
      //  btn_home = findViewById(R.id.btn_home)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.loan_disburse)

     //   tv_amount_value.setText((globalVariable.loanItemSelected?.applied_amount ?: 0).toString())
    //    tv_laon_id_value.setText((globalVariable.loanItemSelected?.customer_id ?: 0).toString())

        iv_header_back.setOnClickListener {

            val intent = Intent(this@LoanDisburseActivity, LoanResultActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@LoanDisburseActivity)
           /* if (loan_input_confirm_layout.isVisible) {
                loan_input_confirm_layout.visibility = View.GONE
                loan_input_layout.visibility = View.VISIBLE
            } else {
                val intent = Intent(this, LoanDashboardActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }
            */

        }






        btnDisburse.setOnClickListener {
            if (!CustomAlert.isOnline(this@LoanDisburseActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoanDisburseActivity,
                    globalVariable.languageCode
                )
            }else {
                disburseLoanExecute()
            }
        }
        btnLater.setOnClickListener {
            // val intent = Intent(this, LoanDashboardActivity::class.java)
            //  CustomActivityClear.doClearActivity(intent, this)

            disburseLaterDialog()
        }

        btbCancelLoan.setOnClickListener {

            confirmDialog()


        }

        btn_loan_disburse_go_home.setOnClickListener {
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        btn_home_disburse.setOnClickListener {
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }


        btn_loan_cancel_go_home.setOnClickListener {
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        btn_home_cancel.setOnClickListener {
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }




        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    val intent = Intent(this@LoanDisburseActivity, LoanResultActivity::class.java)
                    CustomActivityClear.doClearActivity(intent, this@LoanDisburseActivity)

                    /*  if (loan_input_confirm_layout.isVisible) {
                          loan_input_confirm_layout.visibility = View.GONE
                          loan_input_layout.visibility = View.VISIBLE
                      } else {
                          val intent = Intent(this@LoanDisburseActivity, LoanDashboardActivity::class.java)
                          CustomActivityClear.doClearActivity(intent, this@LoanDisburseActivity)
                      }
                      */
                }
            })

        if (!CustomAlert.isOnline(this@LoanDisburseActivity)) {
            CustomAlert.showInternetConnectionMessage(
                this@LoanDisburseActivity,
                globalVariable.languageCode
            )
        } else {

            loanEmiCalculation2()
        }


        observeViewModel()
    }
    fun observeViewModel() {

        loanViewModel.error_response.observe(this) {
            it?.let {
                pDialog.dismiss()

                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }

        loanViewModel.loanEmiCalculationResponse.observe(this) {

            pDialog.dismiss()

            if (it.outCode == "0") {
                emiResponse = it
              /*  loan_input_layout.visibility = View.GONE
                loan_input_confirm_layout.visibility = View.VISIBLE

                tv_loan_confirm_amount_value.setText((globalVariable.loanItemSelected?.applied_amount ?: 0).toString() + " BDT")
                tv_loan_confirm_month_value.setText(monthDes)
                tv_loan_confirm_receiver_account_value.setText(accountNo)

                tv_loan_confirm_loan_payable_permonth_value.setText(it.emi.toString() + " BDT")
                tv_loan_confirm_total_interest_payable_value.setText(it.totalInterest.toString() + " BDT")
                tv_loan_confirm_total_payment_value.setText(it.totalPayment.toString() + " BDT")
                */

            } else {
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }






        loanViewModel.loanEmiCalculationResponse2.observe(this) {

            pDialog.dismiss()
            emi =  it.emi.toString()
            if (it.outCode == "0") {

                try {

                    tv_loan_success_amount_value.setText(loanAmount);
                    tv_loan_success_month_value.setText(month+" Month");
                    tv_loan_success_receiver_account_value.setText(accountNo);

                    tv_loan_success_loan_payable_permonth_value.setText(it.emi.toString() + " BDT")
                    tv_loan_success_total_interest_payable_value.setText(it.totalInterest.toString() + " BDT")
                    tv_loan_success_total_payment_value.setText(it.totalPayment.toString() + " BDT")
                    tv_loan_success_interest_rate_value.setText(it.interestRate.toString() + " %")
                    tv_loan_success_processing_fee_vat_value.setText(it.chargeWithVat + " BDT")
                    tv_loan_success_usabale_amount_value.setText(it.creditAmount.toString() + " BDT")
                    tv_loan_success_payment_date_value.setText(it.sanctionDate.toString())
                    tv_loan_success_first_installment_value.setText(it.firstInstallmentDate.toString())

                }catch (e:Exception){
                    e.printStackTrace()
                }




            } else {
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }

        loanViewModel.disburseExecuteResponse.observe(this) {

            pDialog.dismiss()
            if (it.outCode == "0") {

                tv_loan_disburse_message.setText(it.outMessage)

                loan_success_layout.visibility = View.GONE

                layout_bottom_diburse_cancel.visibility = View.GONE

                layout_loan_disburse_success.visibility = View.VISIBLE

            }else {
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }

        loanViewModel.cancelLoanResponse.observe(this) {

            Log.e("cancelLoanResponse-",it.toString())
            pDialog.dismiss()
            if (it.outCode == "0") {
                tv_loan_cancel_message.setText(it.outMessage)
                loan_success_layout.visibility = View.GONE
                loan_success_layout.visibility = View.GONE
                layout_bottom_diburse_cancel.visibility = View.GONE

                layout_loan_cancel.visibility = View.VISIBLE
            }else {
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }


    }
    private fun loanEmiCalculation() {
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.receiverAccountNo = accountNo
        model.amount =(globalVariable.loanItemSelected?.applied_amount ?: 0).toString()
        model.month = month

        this.let { it1 -> loanViewModel.loanEmiCalculation(HeaderData.headerWelcome(globalVariable),model) }
    }



    private fun disburseLoanExecute() {

        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.creditScoreId = loanId
        model.receiverAccountNo = accountNo
        model.amount= loanAmount
        model.emi = emi
        model.month = month
        model.loanId = loanId
        model.imei= globalVariable.imei
        model.remarks = ""
        this.let { it1 -> loanViewModel.disburseLoanExecute(HeaderData.headerWelcome(globalVariable),model) }
    }

    private fun loanEmiCalculation2() {
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.amount = loanAmount
        model.month = month
        model.mailId = globalVariable.mailId
        model.receiverAccountNo = accountNo

        this.let { it1 -> loanViewModel.loanEmiCalculation2(HeaderData.headerWelcome(globalVariable),model) }
    }

    private fun cancelLoan() {

        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.creditScoreId = loanId
        model.receiverAccountNo = accountNo
        model.amount= loanAmount
        model.emi = emi
        model.month = month
        model.loanId = loanId
        model.imei= globalVariable.imei
        model.remarks = ""
        this.let { it1 -> loanViewModel.cancelLoan(HeaderData.headerWelcome(globalVariable),model) }
    }


    @SuppressLint("MissingInflatedId")
    fun confirmDialog() {
        val dialog = AlertDialog.Builder(this).setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val reg_layout = inflater.inflate(R.layout.confirm_dialog, null)
        val btn_cancel = reg_layout.findViewById<Button>(R.id.btn_cancel)
        val btn_submit = reg_layout.findViewById<Button>(R.id.btn_submit)
        val cancel_message_title = reg_layout.findViewById<TextView>(R.id.cancel_message_title)
        val message_body = reg_layout.findViewById<TextView>(R.id.message_body)

        cancel_message_title.setText("Are You Sure to Cancel Loan?")
        message_body.setText("If Yes, Your Loan Approval will be Canceled. ")
        dialog.setView(reg_layout)

        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btn_submit.setOnClickListener { v: View? ->
            alertDialog.dismiss()
            if (!CustomAlert.isOnline(this@LoanDisburseActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoanDisburseActivity,
                    globalVariable.languageCode
                )
            }else {
                cancelLoan()
            }
        }
        btn_cancel.setOnClickListener { v: View? -> alertDialog.cancel() }
        alertDialog.show()
    }


    @SuppressLint("MissingInflatedId")
    fun disburseLaterDialog() {
        val dialog = AlertDialog.Builder(this).setCancelable(false)
        val inflater = LayoutInflater.from(this)
        val reg_layout = inflater.inflate(R.layout.confirm_dialog, null)
        val btn_cancel = reg_layout.findViewById<Button>(R.id.btn_cancel)
        val btn_submit = reg_layout.findViewById<Button>(R.id.btn_submit)
        val cancel_message_title = reg_layout.findViewById<TextView>(R.id.cancel_message_title)
        val message_body = reg_layout.findViewById<TextView>(R.id.message_body)

        cancel_message_title.setText("Disburse Later?")
        message_body.setText("To disburse later, please visit Loan Result Menu. Thank you for Banking with us ")
        dialog.setView(reg_layout)

        val alertDialog = dialog.create()
        alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        btn_submit.setOnClickListener { v: View? ->
            alertDialog.dismiss()
            val intent = Intent(this, LoanDashboardActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }
        btn_cancel.setOnClickListener { v: View? -> alertDialog.cancel() }
        alertDialog.show()
    }

}