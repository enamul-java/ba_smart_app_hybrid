package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.telephony.SubscriptionInfo
import android.telephony.SubscriptionManager
import android.telephony.TelephonyManager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.FinalScoreModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model.LoanViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAlert
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.HelpDialog
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.permission.PermissionUtil
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AppSettingsDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions
import org.json.JSONObject

class LoanApplicationActivity : AppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks{

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView


    private lateinit var terms_condition_layout: LinearLayout
    private lateinit var btn_proceed_terms: AppCompatButton

    private lateinit var loan_input_layout: LinearLayout
    private lateinit var loan_input_confirm_layout: LinearLayout
    private lateinit var loan_success_layout: LinearLayout

    private lateinit var sp_month_value: MaterialAutoCompleteTextView
    private lateinit var sp_applied_for_value: MaterialAutoCompleteTextView
    private lateinit var sp_receiving_ac_value: MaterialAutoCompleteTextView

    private lateinit var et_loan_amount_value: TextInputEditText
    //   private lateinit var et_duration_months_value: TextInputEditText

    private lateinit var btn_proceed: AppCompatButton
    private lateinit var tv_loan_confirm_amount_value: TextView
    private lateinit var tv_loan_confirm_month_value: TextView
    private lateinit var tv_loan_coanfirm_processing_fee_vat_value: TextView
    private lateinit var tv_loan_confirm_usabale_amount_value: TextView
    private lateinit var tv_loan_confirm_receiver_account_value: TextView
    private lateinit var tv_loan_confirm_loan_payable_permonth_value: TextView
    private lateinit var tv_loan_confirm_total_interest_payable_value: TextView
    private lateinit var tv_loan_confirm_total_payment_value: TextView

    private lateinit var btnApplyLoan: AppCompatButton

    private lateinit var tv_success_message: TextView
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

    //  private lateinit var sp_applied_for_value: MaterialAutoCompleteTextView
//    private lateinit var sp_receiving_ac_value: MaterialAutoCompleteTextView

    //var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var sourceAccountList: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var appliedForList: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()

    var accodeDesOptions: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()
    var installMonthList: ArrayList<CodeDesOptions> = ArrayList<CodeDesOptions>()

    private lateinit var globalVariable: GlobalVariable
    private lateinit var loanViewModel: LoanViewModel
    private lateinit var pDialog: SweetAlertDialog


    private val REQUEST_EXTERNAL_STORAGE = 1
    private val PERMISSIONS_STORAGE = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE
    )
    var appliedFor = ""
    var accountNo = ""

    var month = "";
    var monthDes = "";
    var emi = "0"
    var loanAmount = "0"
    var loanId = ""
    private val PERMISSION_REQUEST_CODE = 123

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loan_application)

        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)

        globalVariable = this.applicationContext as GlobalVariable
        loanViewModel = ViewModelProvider(this).get(LoanViewModel::class.java)
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)

        terms_condition_layout = findViewById(R.id.terms_condition_layout)
        btn_proceed_terms = findViewById(R.id.btn_proceed_terms)

        loan_input_layout = findViewById(R.id.loan_input_layout)
        loan_input_confirm_layout = findViewById(R.id.loan_input_confirm_layout)
        loan_success_layout = findViewById(R.id.loan_success_layout)
        sp_month_value = findViewById(R.id.sp_month_value)
        sp_applied_for_value = findViewById(R.id.sp_applied_for_value)
        sp_receiving_ac_value = findViewById(R.id.sp_receiving_ac_value)
        et_loan_amount_value = findViewById(R.id.et_loan_amount_value)
        // et_duration_months_value = findViewById(R.id.et_duration_months_value)
        btn_proceed = findViewById(R.id.btn_proceed)

        tv_loan_confirm_amount_value = findViewById(R.id.tv_loan_confirm_amount_value)
        tv_loan_confirm_month_value = findViewById(R.id.tv_loan_confirm_month_value)
        tv_loan_coanfirm_processing_fee_vat_value = findViewById(R.id.tv_loan_coanfirm_processing_fee_vat_value)
        tv_loan_confirm_usabale_amount_value = findViewById(R.id.tv_loan_confirm_usabale_amount_value)
        tv_loan_confirm_receiver_account_value = findViewById(R.id.tv_loan_confirm_receiver_account_value)
        tv_loan_confirm_loan_payable_permonth_value = findViewById(R.id.tv_loan_confirm_loan_payable_permonth_value)
        tv_loan_confirm_total_interest_payable_value = findViewById(R.id.tv_loan_confirm_total_interest_payable_value)
        tv_loan_confirm_total_payment_value = findViewById(R.id.tv_loan_confirm_total_payment_value)

        btnApplyLoan = findViewById(R.id.btnApplyLoan)

        tv_success_message = findViewById(R.id.tv_success_message)
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



        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.apply_loan)

        //  layout_bottom_diburse_cancel.visibility = View.GONE

        iv_header_logout.setOnClickListener{
            HelpDialog.showHelpDialog(this, HelpDialog.NANO_LONAN_APPLICATION)
        }

        iv_header_back.setOnClickListener {

            if (loan_input_confirm_layout.isVisible) {
                loan_input_confirm_layout.visibility = View.GONE
                loan_input_layout.visibility = View.VISIBLE
            } else {
                val intent = Intent(this, LoanDashboardActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }


        }


        /*installMonthList.add(CodeDesOptions("1 month", "1"))
        installMonthList.add(CodeDesOptions("2 months", "2"))
        installMonthList.add(CodeDesOptions("3 months", "3"))
        installMonthList.add(CodeDesOptions("4 month", "4"))
        installMonthList.add(CodeDesOptions("5 month", "5"))
        installMonthList.add(CodeDesOptions("6 month", "6"))*/
        /*
                appliedForList.add(CodeDesOptions("Please Select Applied for", ""))
                appliedForList.add(CodeDesOptions("Personal Loans", "PL"))
                appliedForList.add(CodeDesOptions("Motorbike Purchase", "MP"))
                appliedForList.add(CodeDesOptions("E-commerce purchase", "EP"))
                appliedForList.add(CodeDesOptions("Credit cards", "CC"))
                appliedForList.add(CodeDesOptions("Electronics items", "ET"))
                appliedForList.add(CodeDesOptions("Education Loan", "EL"))
                */



        /* val arrayAdapter3 = ArrayAdapter(this, R.layout.dropdown_item, installMonthList)
         sp_month_value.setAdapter(arrayAdapter3)
         */

        btn_proceed_terms.setOnClickListener {
            terms_condition_layout.visibility = View.GONE
            loan_input_layout.visibility = View.VISIBLE
        }


        sp_month_value.setOnItemClickListener { parent, arg1, position, id ->
            if (position > 0) {

                month = installMonthList[position].code.toString()
                monthDes = installMonthList[position].desc.toString()

            }
        }

        sourceAccountList.add(CodeDesOptions("Select Account", ""))
        for (i in 0 until globalVariable.nanoLoanSourceAccountList.size) {
            sourceAccountList.add(
                CodeDesOptions(
                    globalVariable.nanoLoanSourceAccountList[i].accountNumber,
                    globalVariable.nanoLoanSourceAccountList[i].accountNumber
                )
            )

        }




        sp_applied_for_value.setOnItemClickListener { parent, arg1, position, id ->
            if (position > 0) {
                //  appliedFor = appliedForList[position].desc.toString()
                appliedFor = appliedForList[position].code.toString()

            }
        }



        val arrayAdapter = ArrayAdapter(this, R.layout.dropdown_item, sourceAccountList)
        sp_receiving_ac_value.setAdapter(arrayAdapter)

        /*
        if (sourceAccountList.size > 1) {
            sp_receiving_ac_value.setText(globalVariable.primaryAc, false)
            accountNo = globalVariable.primaryAc
        } else {
            sp_receiving_ac_value.setText(sp_receiving_ac_value.adapter.getItem(0).toString(), false)
        }*/



        sp_receiving_ac_value.setOnItemClickListener { parent, arg1, position, id ->
            Log.e("RecAc", sourceAccountList[position].code.toString())
            if (position > 0) {
                accountNo = sourceAccountList[position].code.toString()
            }
        }

        try {
            sp_receiving_ac_value.setSelection(0)
        }catch (e:Exception){
            Log.e("Receving Ac","Initial Selection Err: "+e.toString())
        }

        btn_proceed.setOnClickListener {
            if (et_loan_amount_value.text.toString().isEmpty()) {
                et_loan_amount_value.requestFocus()
                CustomAlert.showErrorMessage(this,
                    "Please Enter Loan Amount",
                    globalVariable.languageCode)
            } else if (month.isEmpty()) {
                CustomAlert.showErrorMessage(this,
                    "Please Select Months",
                    globalVariable.languageCode)
            } else if (appliedFor.isEmpty()) {
                CustomAlert.showErrorMessage(this,
                    "Please Select Applied Reason",
                    globalVariable.languageCode)
            } else if (accountNo.isEmpty()) {
                CustomAlert.showErrorMessage(this,
                    "Please Select Loan Receiving Account",
                    globalVariable.languageCode)
            } else if (!CustomAlert.isOnline(this@LoanApplicationActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoanApplicationActivity,
                    globalVariable.languageCode
                )
            } else {
                loanAmount =  et_loan_amount_value.text.toString();
                loanEmiCalculation()
            }

        }




        btnApplyLoan.setOnClickListener {
            pDialog.show()
            //btnApplyLoan.setBackgroundColor(getResources().getColor(R.color.gray_btn_bg_color));
            btnApplyLoan.isEnabled = false
            btnApplyLoan.setBackgroundResource(R.color.app_color);
            applyLoan()
        }


        btnDisburse.setOnClickListener {
            if (!CustomAlert.isOnline(this@LoanApplicationActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoanApplicationActivity,
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

                    if (loan_input_confirm_layout.isVisible) {
                        loan_input_confirm_layout.visibility = View.GONE
                        loan_input_layout.visibility = View.VISIBLE
                    } else {
                        val intent = Intent(this@LoanApplicationActivity, LoanDashboardActivity::class.java)
                        CustomActivityClear.doClearActivity(intent, this@LoanApplicationActivity)
                    }
                }
            })

        if (!CustomAlert.isOnline(this@LoanApplicationActivity)) {
            CustomAlert.showInternetConnectionMessage(
                this@LoanApplicationActivity,
                globalVariable.languageCode
            )
        } else {
            getLoanReason();
            getTenore()
        }

        /* if (ContextCompat.checkSelfPermission(
                 this,
                 Manifest.permission.READ_PHONE_STATE
             ) != PackageManager.PERMISSION_GRANTED
         ) {
             // Permission not granted, request it
             ActivityCompat.requestPermissions(
                 this,
                 arrayOf(Manifest.permission.READ_PHONE_STATE),
                 PERMISSION_REQUEST_CODE
             )
         } else {
             // Permission already granted, retrieve the phone numbers
             getPhoneNumbers()
         }
 */



        //permission()
        permissionAllChecking()
        observeViewModel()
    }



    /* @SuppressLint("MissingSuperCall")
     @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
     override fun onRequestPermissionsResult(
         requestCode: Int,
         permissions: Array<String>,
         grantResults: IntArray
     ) {
         if (requestCode == PERMISSION_REQUEST_CODE) {
             if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                 // Permission granted, retrieve the phone numbers
                 getPhoneNumbers()
             } else {
                 // Permission denied, handle accordingly
                 // You may want to display a message to the user or disable certain functionality
             }
         }
     }
     */

    /* @SuppressLint("MissingPermission")
     @RequiresApi(Build.VERSION_CODES.LOLLIPOP_MR1)
     private fun getPhoneNumbers() {
         val telephonyManager = getSystemService(TELEPHONY_SERVICE) as TelephonyManager
         val subscriptionManager = getSystemService(SUBSCRIPTION_SERVICE) as SubscriptionManager

         if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP_MR1) {
             val activeSubscriptionInfoList: List<SubscriptionInfo> =
                 subscriptionManager.activeSubscriptionInfoList

             for (subscriptionInfo in activeSubscriptionInfoList) {
                 val simSlotIndex = subscriptionInfo.simSlotIndex
                 val phoneNumber = telephonyManager.getLine1Number()

                 // Use the retrieved phone number as needed
                 // phoneNumber will be null or empty if the number is unavailable or permission denied
                 // simSlotIndex represents the SIM slot (0 for SIM 1, 1 for SIM 2, etc.)
             }
         }
     }
     */

    fun observeViewModel() {

        loanViewModel.error_response.observe(this) {
            it?.let {
                pDialog.dismiss()

                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }
        }

        loanViewModel.scoreUpdateResponse.observe(this) { it ->
            it?.let {
                pDialog.dismiss()
                btnApplyLoan.isEnabled = true
                if (it.outCode == "0") {

                    tv_success_message.setText(it.outMessage)
                    loan_input_confirm_layout.visibility = View.GONE
                    loan_success_layout.visibility = View.VISIBLE
                    //layout_bottom_diburse_cancel.visibility = View.VISIBLE

                    loanAmount =  it.amount.toString();
                    loanId=  it.customer_id.toString();
                    loanEmiCalculation2();

                    tv_loan_success_amount_value.setText(it.amount);
                    tv_loan_success_month_value.setText(monthDes);
                    tv_loan_success_receiver_account_value.setText(accountNo);



                } else {
                    CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
                }

            }
        }

        loanViewModel.loanEmiCalculationResponse.observe(this) {

            pDialog.dismiss()
            emi =  it.emi.toString()
            if (it.outCode == "0") {

                try {

                    loan_input_layout.visibility = View.GONE
                    loan_input_confirm_layout.visibility = View.VISIBLE

                    tv_loan_confirm_amount_value.setText(et_loan_amount_value.text.toString() + " BDT")
                    tv_loan_confirm_month_value.setText(monthDes)



                    tv_loan_coanfirm_processing_fee_vat_value.setText(it.chargeWithVat+ " BDT")
                    tv_loan_confirm_usabale_amount_value.setText(it.creditAmount+ " BDT")
                    tv_loan_confirm_receiver_account_value.setText(accountNo)

                    tv_loan_confirm_loan_payable_permonth_value.setText(it.emi.toString() + " BDT")
                    tv_loan_confirm_total_interest_payable_value.setText(it.totalInterest.toString() + " BDT")
                    tv_loan_confirm_total_payment_value.setText(it.totalPayment.toString() + " BDT")


                    tv_loan_success_loan_payable_permonth_value.setText(it.emi.toString() + " BDT")
                    tv_loan_success_total_interest_payable_value.setText(it.totalInterest.toString() + " BDT")
                    tv_loan_success_total_payment_value.setText(it.totalPayment.toString() + " BDT")

                }catch (e:Exception){
                    e.printStackTrace()
                }





            } else {
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
            }


        }

        loanViewModel.loanEmiCalculationResponse2.observe(this) {

            pDialog.dismiss()
            emi =  it.emi.toString()
            if (it.outCode == "0") {

                try {


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

        loanViewModel.reasonResponse.observe(this) {

            pDialog.dismiss()
            appliedForList.add(CodeDesOptions("Please Select Applied for", ""))
            for (i in 0 until it.size) {
                appliedForList.add(CodeDesOptions(it[i].desc.toString(), it[i].code.toString()))

            }

            val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, appliedForList)
            sp_applied_for_value.setAdapter(arrayAdapter2)

        }

        loanViewModel.tenoreResponse.observe(this) {

            pDialog.dismiss()
            // appliedForList.add(CodeDesOptions("Please Select Applied for", ""))
            installMonthList.add(CodeDesOptions("Please Select Tenor or Period", ""))
            for (i in 0 until it.size) {
                installMonthList.add(CodeDesOptions(it[i].desc.toString(), it[i].code.toString()))

            }

            val arrayAdapter2 = ArrayAdapter(this, R.layout.dropdown_item, installMonthList)
            sp_month_value.setAdapter(arrayAdapter2)

        }

        loanViewModel.disburseExecuteResponse.observe(this) {

            pDialog.dismiss()
            if (it.outCode == "0") {

                tv_loan_disburse_message.setText(it.outMessage)

                loan_success_layout.visibility = View.GONE
                loan_input_layout.visibility = View.GONE
                loan_input_confirm_layout.visibility = View.GONE
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

    private fun getLoanReason(){
        pDialog.show()

        var model = LoanModel()
        model.authorization = globalVariable.token
        this.let { it1 -> loanViewModel.getLoanReason(HeaderData.headerWelcome(globalVariable),model) }
    }

    private fun getTenore(){
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        this.let { it1 -> loanViewModel.getTenore(HeaderData.headerWelcome(globalVariable),model) }
    }


    private fun loanEmiCalculation() {
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.amount = loanAmount
        model.month = month
        model.mailId = globalVariable.mailId
        model.receiverAccountNo = accountNo
        model.deviceInfo = globalVariable.deviceInfo
        model.versionCode = "version:" + BuildConfig.VERSION_NAME + "-code:" + BuildConfig.VERSION_CODE
        model.mobileNumber = ""

        this.let { it1 -> loanViewModel.loanEmiCalculation(HeaderData.headerWelcome(globalVariable),model) }
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

    private fun loanScoreUpdate(jsonObject: JsonObject) {
        pDialog.show()
        pDialog.show()
        var model = LoanModel()
        model.authorization = globalVariable.token
        model.mailId = globalVariable.mailId
        model.sessionId = globalVariable.sessionId
        model.customerCode = globalVariable.customerCode
        model.emi = emi
        model.month = month
        model.amount = et_loan_amount_value.text.toString()
        model.receiverAccountNo = accountNo
        model.imei= globalVariable.imei
        model.jsonObject = jsonObject.toString()
        this.let { it1 -> loanViewModel.loanScoreUpdate(HeaderData.headerWelcome(globalVariable),model) }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun applyLoan(){
        // create your json here
        val jsonObject = JSONObject()
        // val jsonObject = JsonObject()
        val finalScoreModel = FinalScoreModel(applicationContext)

        //var Final_score_with_details = HashMap<String?, Int?>()
        var Final_score_with_details = finalScoreModel.FinalScore()
        Log.e("Final_score_*****", "----")
        for (Score_Name in Final_score_with_details.keys) {
            Log.e("Final_score-->",
                "Score_Name: " + Score_Name + " Score: " + Final_score_with_details[Score_Name])
            jsonObject.put(Score_Name, Final_score_with_details[Score_Name].toString())
        }

        jsonObject.put("customer_number", globalVariable.customerCode)
        // jsonObject.put("customer_number",  "01780131079")
        //  jsonObject.put("customer_number",  globalVariable.cusomerCode)
        jsonObject.put("data_source_id", "1")

        jsonObject.put("applied_for", appliedFor)
        jsonObject.put("applied_amount", et_loan_amount_value.text.toString())
        jsonObject.put("customer_name", globalVariable.userName)
        //jsonObject.put("bank_name", TextContants.company_name);

        jsonObject.put("Received_account_number", accountNo);
        jsonObject.put("tenor", month);
        jsonObject.put("ref_id", "");


        jsonObject.put("READ_SMS_permission", 1)
        jsonObject.put("INTERNET_permission", 1)
        jsonObject.put("READ_PHONE_STATE_permission", 1)
        jsonObject.put("READ_PHONE_NUMBERS_permission", 1)
        jsonObject.put("READ_CONTACTS_permission", 1)
        jsonObject.put("ACCESS_FINE_LOCATION_permission", 1)
        jsonObject.put("ACCESS_COARSE_LOCATION_permission", 1)
        jsonObject.put("READ_CALL_LOG_permission", 1)
        jsonObject.put("READ_CALENDAR_permission", 1)
        jsonObject.put("READ_EXTERNAL_STORAGE_permission", 1)

        /*
        Calendar_Event_Time_Score: str
        Image_File_Score: str
        Vpn_Score: str
        Installed_Apps_Score: str
        Pic_Taken_Per_Day: str
        Calendar_Event_Count_Score_LifeHabitsModel: str
        Calendar_Event_Score_EmploymentModel: str
        Calendar_Event_Count_Score_SocialCapitalModel: str
        Location_Score: str
        READ_CALL_LOG_permission: str
        */

        val jsonParser = JsonParser()
        val jsonObject2 = jsonParser.parse(jsonObject.toString()) as JsonObject

        loanScoreUpdate(jsonObject2)

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

    fun  permission(){
        //Permission
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                /*val READ_CALL_LOG = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CALL_LOG
                )*/

                val READ_CALENDAR = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CALENDAR
                )


                val READ_SMS = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_SMS
                )

                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_CONTACTS
                )

                val ACCESS_FINE_LOCATION = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
                val ACCESS_COARSE_LOCATION = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )
                val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                if (
                    //READ_CALL_LOG != PackageManager.PERMISSION_GRANTED
                    READ_SMS != PackageManager.PERMISSION_GRANTED
                    || READ_CALENDAR != PackageManager.PERMISSION_GRANTED
                    || READ_CONTACTS != PackageManager.PERMISSION_GRANTED
                    || ACCESS_FINE_LOCATION != PackageManager.PERMISSION_GRANTED
                    || ACCESS_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED
                    || READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                ) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            //Manifest.permission.READ_CALL_LOG,
                            Manifest.permission.READ_CALENDAR,
                            Manifest.permission.READ_SMS,
                            Manifest.permission.READ_CONTACTS,
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_COARSE_LOCATION,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                        ), 1
                    )
                }


            }


        } catch (ex: Exception) {
            pDialog.dismiss()
            ex.message?.let { Log.e("", it) }
        }
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
            if (!CustomAlert.isOnline(this@LoanApplicationActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoanApplicationActivity,
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


    //====================Permission+++++
    val TAG = "NanoLoan"
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            PermissionUtil().showAcceptedAccessList(this,
                "Your Accepted Access",
                PermissionUtil.FROM_NANO_LOAN
                )
        }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsGranted:" + requestCode + ":" + perms.size)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: MutableList<String>) {
        Log.d(TAG, "onPermissionsDenied:$requestCode" + ":" + perms.size)

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.

        // (Optional) Check whether the user denied any permissions and checked "NEVER ASK AGAIN."
        // This will display a dialog directing them to enable the permission in app settings.
        if (EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
            AppSettingsDialog
                .Builder(this)
                .setTitle(PermissionUtil().rationalTitle(this))
                .setRationale(PermissionUtil().rationalMessage(this, PermissionUtil.FROM_LOGIN))
                .build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }

    @AfterPermissionGranted(PermissionUtil.ALL_PERMISSION_PERM_NANO)
    fun permissionAllChecking() {
        if (checkAllPermission()) {
            // Have permissions, do the thing!
            //Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
            Log.d(TAG, "All Permission Accepted!")
        } else {
            // Ask for all permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_ask),
                PermissionUtil.ALL_PERMISSION_PERM_NANO,
                *PermissionUtil.ALL_PERMISSIONS_NANO
            )
        }
    }
    private fun checkAllPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, *PermissionUtil.ALL_PERMISSIONS_NANO)
    }

}