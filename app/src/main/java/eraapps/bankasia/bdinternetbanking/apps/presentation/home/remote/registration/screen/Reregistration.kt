package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.screen

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.view_model.ReRegistrationViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ReRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.*

@AndroidEntryPoint
class Reregistration : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var re_registration_acceptance_layout: LinearLayout
    private lateinit var btn_proceed_layout: LinearLayout
    private lateinit var btn_proceed: AppCompatButton

    private lateinit var re_registration_verify_layout: LinearLayout
    private lateinit var et_otp_value: TextInputEditText
    private lateinit var btn_submit: AppCompatButton

    private lateinit var re_registration_success_layout: LinearLayout
    private lateinit var tv_success_message: TextView
    private lateinit var btn_done: AppCompatButton

    val reRegistrationViewModel: ReRegistrationViewModel by viewModels()

    private var otp_sessionid = ""

    private var page = 1
    var userId = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reregistration)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        btn_submit = findViewById(R.id.btn_submit)
        btn_proceed = findViewById(R.id.btn_proceed)
        btn_proceed_layout = findViewById(R.id.btn_proceed_layout)
        re_registration_acceptance_layout = findViewById(R.id.re_registration_acceptance_layout)
        re_registration_verify_layout = findViewById(R.id.re_registration_verify_layout)
        et_otp_value = findViewById(R.id.et_otp_value)
        re_registration_success_layout = findViewById(R.id.re_registration_success_layout)
        tv_success_message = findViewById(R.id.tv_success_message)
        btn_done = findViewById(R.id.btn_done)

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.re_registration)
        iv_header_logout.visibility=View.INVISIBLE

        iv_header_back.setOnClickListener {
            goBack()
        }

        btn_proceed.setOnClickListener {
            if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(
                    this@Reregistration,
                    globalVariable.languageCode
                )
            } else {
                doAcceptAndProceed()
            }
        }

        try {
            userId = intent.getStringExtra("userId").toString()
        } catch (e: Exception) {
            e.printStackTrace()
        }



        btn_submit.setOnClickListener {
            if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(
                    this@Reregistration,
                    globalVariable.languageCode
                )
            } else if (!ValidationUtil.editTextValidation(et_otp_value.text.toString())) {
                et_otp_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.otpbangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.otp,
                        globalVariable.languageCode
                    )
                }

            } else if (globalVariable.imei == "") {
                getImei()
            } else {
                doSubmitReRegistrationRequest()
            }
        }

        btn_done.setOnClickListener {
            goBack()
        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goBack()
                }
            }
            )

        observeViewModel()
    }

    private fun observeViewModel() {

        reRegistrationViewModel.reRegistrationOtpResponse
            .observe(this) {

                if ("0" == it.outCode) {
                    pDialog.dismiss()

                    otp_sessionid = it.sessionId.toString()

                    gotoOtpPage()

                } else {
                    pDialog.dismiss()
                    CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
                }

            }

        reRegistrationViewModel.reRegistrationRequestResponse
            .observe(this) {

                if ("0" == it.outCode) {
                    pDialog.dismiss()

                    tv_success_message.text = it.outMessage

                    gotoSuccessPage()

                } else {
                    pDialog.dismiss()
                    CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
                }

            }

        reRegistrationViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)

        }
    }

    private fun doAcceptAndProceed() {
        pDialog.show()
        val model = OTPRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.actFlg = Encript_Parameter.getRsa_encrypt("A")
        model.userId = Encript_Parameter.getRsa_encrypt(userId)
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.customerCode = Encript_Parameter.getRsa_encrypt(globalVariable.customerCode)
        model.authorization = globalVariable.token
        reRegistrationViewModel.sentDeviceReRegistrationOtp(
            HeaderData.headerWelcome(globalVariable),
            model
        )
    }

    private fun doSubmitReRegistrationRequest() {
        pDialog.show()
        val model = ReRegistrationRequestModel()
        model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
        model.userId = Encript_Parameter.getRsa_encrypt(userId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(otp_sessionid)
        model.otp = Encript_Parameter.getRsa_encrypt(et_otp_value.text.toString().trim())
        model.operationMode = Encript_Parameter.getRsa_encrypt("R")
        model.authorization = globalVariable.token
        reRegistrationViewModel.deviceReRegistrationRequest(
            HeaderData.headerWelcome(globalVariable),
            model
        )
    }


    private fun gotoAcceptancePage() {
        page = 1
        re_registration_acceptance_layout.visibility = View.VISIBLE
        btn_proceed_layout.visibility = View.VISIBLE
        toolbar_layout.visibility = View.VISIBLE
        re_registration_verify_layout.visibility = View.GONE
        re_registration_success_layout.visibility = View.GONE
    }


    private fun gotoOtpPage() {
        page = 2
        re_registration_acceptance_layout.visibility = View.GONE
        btn_proceed_layout.visibility = View.GONE
        toolbar_layout.visibility = View.VISIBLE
        re_registration_verify_layout.visibility = View.VISIBLE
        re_registration_success_layout.visibility = View.GONE
    }


    private fun gotoSuccessPage() {
        page = 3
        re_registration_acceptance_layout.visibility = View.GONE
        btn_proceed_layout.visibility = View.GONE
        toolbar_layout.visibility = View.GONE
        re_registration_verify_layout.visibility = View.GONE
        re_registration_success_layout.visibility = View.VISIBLE
    }

    private fun goBack() {

        if (page == 1) {
            val intent = Intent(this@Reregistration, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@Reregistration)
        } else if (page == 2) {
            gotoAcceptancePage()
        } else {
            val intent = Intent(this@Reregistration, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@Reregistration)
        }

    }

    @SuppressLint("HardwareIds", "MissingPermission")
    fun getImei() {

        var deviceid = ""
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            deviceid = Settings.Secure.getString(
                this.contentResolver,
                Settings.Secure.ANDROID_ID
            )

        } else if (Build.VERSION.SDK_INT < Build.VERSION_CODES.Q) {
            try {
                @SuppressWarnings("deprecation")
                val tel = getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
                if (ActivityCompat.checkSelfPermission(
                        this,
                        Manifest.permission.READ_PHONE_STATE
                    ) != PackageManager.PERMISSION_GRANTED
                ) {

                }
                deviceid = tel.deviceId


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        globalVariable.imei = deviceid
        Log.d("globalVariable.imei", globalVariable.imei)

    }


}