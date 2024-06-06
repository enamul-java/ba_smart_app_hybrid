package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen


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
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.widget.AppCompatButton
import androidx.biometric.BiometricPrompt
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat.from
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.FirebaseApp
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserFingerEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CodeDesOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.adaptar.AutoCompleteAdapter
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model.UserFingerViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model.UserViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.view_model.LoginViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.screen.Reregistration
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.AccountViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.permission.PermissionUtil
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AppSettingsDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions
import java.util.*
import java.util.concurrent.Executor

@AndroidEntryPoint
class LoginActivity : CustomAppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    private val TAG = "LoginActivity"

    private lateinit var executor: Executor
    private lateinit var biometricPrompt: BiometricPrompt
    private lateinit var promptInfo: BiometricPrompt.PromptInfo

    private lateinit var et_user_id_value: MaterialAutoCompleteTextView
    private lateinit var et_password_value: TextInputEditText

    private lateinit var btn_login: AppCompatButton
    private lateinit var btn_call: AppCompatButton
    private lateinit var btn_ac_opening: AppCompatButton
    private lateinit var btn_signup: AppCompatButton
    private lateinit var btn_finger: AppCompatButton

    // private lateinit var btnMobileToken: AppCompatButton
    private lateinit var iv_finger_touch_id: ImageView
    private lateinit var layout_finger: LinearLayout

    private lateinit var btn_call_layout: LinearLayout

    private lateinit var btn_login_touchid_layout: LinearLayout
    // private lateinit var btn_login_mobile_token_layout: LinearLayout

    private lateinit var tv_forgot_user_id: TextView
    private lateinit var tv_forgot_password: TextView
    private lateinit var tv_powered_by: TextView
    private lateinit var chk_sure_checkbox: CheckBox

    private lateinit var tv_bank_name: TextView
    private lateinit var tv_user_id_label: TextView
    private lateinit var tv_password_label: TextView
    private lateinit var tv_remember_id: TextView

    val loginViewModel: LoginViewModel by viewModels()
    val accountViewModel: AccountViewModel by viewModels()
    val userViewModel: UserViewModel by viewModels()
    val userFingerViewModel: UserFingerViewModel by viewModels()


    private lateinit var globalVariable: GlobalVariable

    private lateinit var pDialog: SweetAlertDialog
    // private lateinit var pDialog: KProgressHUD

    private var FROM: String? = ""
    var sourceAccountList: ArrayList<SourceAccountListDto> = ArrayList()
    var useridList: ArrayList<UserIdEntity> = ArrayList()
    var codeDesOptions: ArrayList<CodeDesOptions> = ArrayList()
    var fingerflg = ""
    var password = ""
    var id = 0


    @SuppressLint("MissingPermission", "SuspiciousIndentation", "MissingInflatedId")
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        executor = ContextCompat.getMainExecutor(this)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        //pDialog = CustomAlert.showProgressDialog1(this, globalVariable.languageCode)

        et_user_id_value = findViewById(R.id.et_user_id_value)
        et_password_value = findViewById(R.id.et_password_value)

        btn_login = findViewById(R.id.btn_login)
        btn_call_layout = findViewById(R.id.btn_call_layout)
        btn_call = findViewById(R.id.btn_call)
        tv_powered_by = findViewById(R.id.tv_powered_by)

        btn_ac_opening = findViewById(R.id.btn_ac_opening)
        btn_signup = findViewById(R.id.btn_signup)
        tv_forgot_user_id = findViewById(R.id.tv_forgot_user_id)
        tv_forgot_password = findViewById(R.id.tv_forgot_password)
        btn_login_touchid_layout = findViewById(R.id.btn_login_touchid_layout)

        btn_finger = findViewById(R.id.btn_finger)

        iv_finger_touch_id = findViewById(R.id.iv_finger_touch_id)
        layout_finger = findViewById(R.id.layout_finger)
        chk_sure_checkbox = findViewById(R.id.chk_sure_checkbox)

        tv_bank_name = findViewById(R.id.tv_bank_name)
        tv_user_id_label = findViewById(R.id.tv_user_id_label)
        tv_password_label = findViewById(R.id.tv_password_label)
        tv_remember_id = findViewById(R.id.tv_remember_id)

        if(BuildConfig.FLAVOR.equals("dev")) {
            //Nano loan test
            //et_user_id_value.setText("00774289@bankasia.net") //CBS UAT Ashraf
            //et_password_value.setText("Test@12345")

            //et_user_id_value.setText("01300270@bankasia.net") //CBS UAT Nashedvai
            //et_password_value.setText("Test#321") //CBS UAT Nashedvai

            //et_user_id_value.setText("00742877@bankasia.net") //CBS UAT Ashraf
            //et_password_value.setText("Test#12345")

            //et_user_id_value.setText("rabbel") //ABS UAT
            //et_password_value.setText("Test#123")

            //et_user_id_value.setText("era@mybank.com") //ERA CBS
            //et_password_value.setText("A1@a")

            et_user_id_value.setText("00774289@bankasia.net") //Enamul CBS
            et_password_value.setText("Test@123456")

            //et_user_id_value.setText("aniken_2004@hotmail.com") // Samiul vai
            //et_password_value.setText("Test@1234")


            //et_user_id_value.setText("aniken_2004@hotmail.com") // Samiul vai
            //et_password_value.setText("Test@12345")


            //et_user_id_value.setText("sahinur.rahman@bankasia-bd.com") //Shahinur CBS
            //et_password_value.setText("Test@1234")

            //et_user_id_value.setText("nashid.qurishy@gmail.com") //Shahinur CBS
            //et_password_value.setText("Test@1234")


            //et_user_id_value.setText("i0042077@bankasia.net") //Islamic Coven
            //et_password_value.setText("Test@12345")


            //et_user_id_value.setText("33307904291") //Rabbel ABS
            //et_password_value.setText("Test@123")

            //et_password_value.setText("Rabbel@2005")//Live
        }else if((BuildConfig.FLAVOR.equals("luat") || BuildConfig.FLAVOR.equals("uat"))
            && BuildConfig.BUILD_TYPE.equals("debug")) {

            et_user_id_value.setText("00774289@bankasia.net") //Enamul CBS
            et_password_value.setText("Test@123456")

            //et_user_id_value.setText("00774289@bankasia.net") //CBS UAT Ashraf
            //et_password_value.setText("Test@12345")

            //et_user_id_value.setText("33321900051") //Abs
            //et_password_value.setText("Test@123")

            //et_user_id_value.setText("i0039029@bankasia.net") //Hikmah
            //et_password_value.setText("Test@1234")

            //et_user_id_value.setText("aniken_2004@hotmail.com") // Samiul vai
            //et_password_value.setText("Test@1234")
        }else if(BuildConfig.FLAVOR.equals("nanouat")) {
            //et_user_id_value.setText("00774289@bankasia.net") //Enamul CBS
            //et_password_value.setText("Test@12345")
            //et_user_id_value.setText("aniken_2004@hotmail.com") // Samiul vai
            //et_password_value.setText("Test@1234")
            //et_user_id_value.setText("00774289@bankasia.net") //Enamul CBS
            //et_password_value.setText("Test@12345")
            //et_user_id_value.setText("00774289@bankasia.net") //CBS UAT Ashraf
            //et_password_value.setText("Test@12345")
            et_user_id_value.setText("33321900051") //Abs
            et_password_value.setText("Test@123")
        }


        val intent = intent
        intent.getStringExtra("FROM").also { FROM = it }

        btn_login_touchid_layout.visibility = View.GONE


        userViewModel.getUserId()


        /*
        if (!checkCallPermission()) {
            getCallPermission()
        }

        if (!checkPhonePermission()) {
            getPhonePermission()
        }*/

        permissionAllChecking()

        fontset()

        globalVariable.dashboardPosition = ""

        tv_forgot_password.setOnClickListener {
            //forget user
        }

        tv_forgot_user_id.setOnClickListener {
            //forget user id
        }

        btn_ac_opening.setOnClickListener {

            //account opening

        }

        btn_signup.setOnClickListener {
            //New Reg
        }

        ForceUpdateChecker.playStoreVersionCheck(this, globalVariable.languageCode)


        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val manager = from(this)
                if (!manager.isHardwareDetected) {
                    Log.e("tag", "Fingerprint hardware not detected.")
                    btn_login_touchid_layout.visibility = View.GONE
                    btn_finger.visibility = View.GONE
                    iv_finger_touch_id.visibility = View.GONE
                    layout_finger.visibility = View.GONE
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        iv_finger_touch_id.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val manager = from(this)
                if (!manager.isHardwareDetected) {
                    Log.e("tag", "Fingerprint hardware not detected.")
                    CustomAlert.showErrorMessage(
                        this,
                        "Fingerprint hardware not detected.",
                        globalVariable.languageCode
                    )

                } else if (!manager.hasEnrolledFingerprints()) {
                    Log.e("tag", "No fingerprint is set")
                    CustomAlert.showErrorMessage(
                        this,
                        "Fingerprint hardware detected. But no fingerprint is set",
                        globalVariable.languageCode
                    )

                } else if (!ValidationUtil.editTextValidation(et_user_id_value.text.toString())) {
                    et_user_id_value.requestFocus()
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.useridbangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.userid,
                            globalVariable.languageCode
                        )
                    }

                } else {
                    Log.e("tag", "Fingerprint is set")
                    userFingerViewModel.getUserFingerInfo(et_user_id_value.text.toString())
                    fingerCheck()

                }
            }
        }

        btn_login_touchid_layout.setOnClickListener {
            val manager = from(this)
            if (!manager.isHardwareDetected) {
                Log.e("tag", "Fingerprint hardware not detected.")
                CustomAlert.showErrorMessage(
                    this,
                    "Fingerprint hardware not detected.",
                    globalVariable.languageCode
                )

            } else if (!manager.hasEnrolledFingerprints()) {
                Log.e("tag", "No fingerprint is set")
                CustomAlert.showErrorMessage(
                    this,
                    "Fingerprint hardware detected. But no fingerprint is set",
                    globalVariable.languageCode
                )

            } else if (!ValidationUtil.editTextValidation(et_user_id_value.text.toString())) {
                et_user_id_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.useridbangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.userid,
                        globalVariable.languageCode
                    )
                }

            } else {
                Log.e("tag", "Fingerprint is set")

                fingerCheck()
            }
        }

        btn_finger.setOnClickListener {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val manager = from(this)
                if (!manager.isHardwareDetected) {
                    Log.e("tag", "Fingerprint hardware not detected.")
                    CustomAlert.showErrorMessage(
                        this,
                        "Fingerprint hardware not detected.",
                        globalVariable.languageCode
                    )

                } else if (!manager.hasEnrolledFingerprints()) {
                    Log.e("tag", "No fingerprint is set")
                    CustomAlert.showErrorMessage(
                        this,
                        "Fingerprint hardware detected. But no fingerprint is set",
                        globalVariable.languageCode
                    )

                } else if (!ValidationUtil.editTextValidation(et_user_id_value.text.toString())) {
                    et_user_id_value.requestFocus()
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.useridbangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.userid,
                            globalVariable.languageCode
                        )
                    }

                } else {
                    Log.e("tag", "Fingerprint is set")
                    userFingerViewModel.getUserFingerInfo(et_user_id_value.text.toString())
                    fingerCheck()
                }
            }

        }


        tv_powered_by.setOnClickListener {
            if (!NetworkUtil.isOnline(this@LoginActivity)) {
                CustomAlert.showInternetConnectionMessage(
                    this@LoginActivity,
                    globalVariable.languageCode
                )
            } else {
                ValidationUtil.goToUrl(this, "https://erainfotechbd.com/")
            }
        }

        btn_call_layout.setOnClickListener {
            //if (!checkCallPermission()) {
            if (!isPermissionGranted(Manifest.permission.CALL_PHONE)) {
                permissionAllChecking()
            } else {
                CustomActivityClear.callDialog(
                    this@LoginActivity,
                    globalVariable.languageCode
                )
            }
        }
        btn_call.setOnClickListener {
            //if (!checkCallPermission()) {
            if (!isPermissionGranted(Manifest.permission.CALL_PHONE)) {
                permissionAllChecking()
            } else {
                CustomActivityClear.callDialog(
                    this@LoginActivity,
                    globalVariable.languageCode
                )
            }
        }

        btn_login.setOnClickListener {
            /*if (!checkPhonePermission()) {
                getPhonePermission()
            }*/
            if (!isPermissionGranted(Manifest.permission.CALL_PHONE)) {
                permissionAllChecking()
            } else {
                if (!ValidationUtil.editTextValidation(et_user_id_value.text.toString())) {
                    et_user_id_value.requestFocus()
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.useridbangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.userid,
                            globalVariable.languageCode
                        )
                    }

                } else if (!ValidationUtil.editTextValidation(et_password_value.text.toString())) {
                    et_password_value.requestFocus()
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.passwordbangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.password,
                            globalVariable.languageCode
                        )
                    }
                } else if (!NetworkUtil.isOnline(this)) {
                    CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
                } else {
                    loginAction()
                }
            }

        }


        try {
            globalVariable.deviceInfo =
                "MODEL:" + Build.MODEL + ", Manufacture: " + Build.MANUFACTURER + ", Brand: " + Build.BRAND + ", OS: " + Build.VERSION.RELEASE + ", App Version: " + BuildConfig.VERSION_NAME + ", Version Code " + BuildConfig.VERSION_CODE
        } catch (e: Exception) {
            e.printStackTrace()
        }




        getImei()
        observeEvents()
        FirebaseApp.initializeApp(this)


        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    this@LoginActivity.finishAffinity()
                }
            }
            )
    }



    @SuppressLint("SuspiciousIndentation")
    private fun observeEvents() {

        loginViewModel.loginResponse.observe(this) {
            //  et_password_value.setText("")
            globalVariable.chequesourceAccountList.clear()
            globalVariable.sourceAcStatementList.clear()
            globalVariable.sourceAccountList.clear()
            globalVariable.primaryAc = ""

            globalVariable.customerCode = it.cusomerCode.toString()
            globalVariable.identifyBanking = it.identifyBanking.toString()
            globalVariable.sessionId = it.sessionId.toString()
            globalVariable.mailId = it.mailId.toString()
            globalVariable.outCode = it.outCode.toString()
            globalVariable.outMessage = it.outMessage.toString()
            globalVariable.userId = et_user_id_value.text.toString()
            globalVariable.token = "Bearer " + it.token
            globalVariable.userName = it.userName.toString()
            globalVariable.emailId = it.emailId.toString()
            globalVariable.mobileNo = it.mobileNo.toString()
            globalVariable.address = it.address.toString()
            globalVariable.nidNo = it.nidNo.toString()

            //Log.e("emailId", it.emailId.toString())


            if ("0" == it.outCode) {

                globalVariable.userImage = Constants.BASE_URL + "access/v1/userimage/" + it.mailId

                if ("MYB" == it.identifyBanking) {
                    soureceAccount()
                } else if ("AGB" == it.identifyBanking) {
                    sourceAcforStatement()
                }

                var isExist = ""
                for (i in useridList) {
                    // Log.e("Userlistdata", i.toString())
                    if (i.userid == globalVariable.userId && chk_sure_checkbox.isChecked
                    ) {
                        //  Log.e("Usercheck", "User Already Exist")
                        isExist = "0"
                    }
                }
                if (isExist == "" && chk_sure_checkbox.isChecked) {
                    dataInsert(et_user_id_value.text.toString())
                }

                et_password_value.setText("")


            } else if ("11" == it.outCode) {
                pDialog.dismiss()
                //password change
                val userFingerEntity = UserFingerEntity(
                    id,
                    et_user_id_value.text.toString(),
                    "Y", Encript_Parameter.getRsa_encrypt(et_password_value.text.toString()),
                    ValidationUtil.getCurrentDate()
                )
                if (id > 0) {
                    updateUserFingerInfo(userFingerEntity)
                } else {
                    insertUserFingerInfo(userFingerEntity)
                }

            } else if ("13" == it.outCode && "MYB" == it.identifyBanking) {
                pDialog.dismiss()
                val intent = Intent(this, Reregistration::class.java)
                intent.putExtra("userId", et_user_id_value.text.toString())
                startActivity(intent)
                val userFingerEntity = UserFingerEntity(
                    id,
                    et_user_id_value.text.toString(),
                    "Y", Encript_Parameter.getRsa_encrypt(et_password_value.text.toString()),
                    ValidationUtil.getCurrentDate()
                )
                if (id > 0) {
                    updateUserFingerInfo(userFingerEntity)
                } else {
                    insertUserFingerInfo(userFingerEntity)
                }

            } else if ("13" == it.outCode && "AGB" == it.identifyBanking) {
                pDialog.dismiss()
                val intent = Intent(this, Reregistration::class.java)
                intent.putExtra("userId", et_user_id_value.text.toString())
                startActivity(intent)
                val userFingerEntity = UserFingerEntity(
                    id,
                    et_user_id_value.text.toString(),
                    "Y", Encript_Parameter.getRsa_encrypt(et_password_value.text.toString()),
                    ValidationUtil.getCurrentDate()
                )
                if (id > 0) {
                    updateUserFingerInfo(userFingerEntity)
                } else {
                    insertUserFingerInfo(userFingerEntity)
                }

            } else if ("3" == it.outCode) {
                pDialog.dismiss()
                HelpDialog.showHelpDialog(this, "FL")
            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
                et_password_value.setText("")

            }

        }

        loginViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)
        }


        accountViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)
        }

        userFingerViewModel.userFingerInfoResponse.observe(this) {
            pDialog.dismiss()
            try {
                password = it.password.toString()
                id = it.id
                Log.d("useridList", it.toString())
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }



        accountViewModel.sourceAccountResponse.observe(this) {

            if ("0" == it.outCode) {
                soureceAccountList()
            } else {
                pDialog.dismiss()
                when (FROM) {
                    "W" -> {
                        pDialog.dismiss()
                        val intent = Intent(this, WelcomeDashboardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }

                    "TOPUP" -> {
                        pDialog.dismiss()
                        //recharge
                    }

                    "OT" -> {
                        pDialog.dismiss()
                        //other bank trasfer
                    }

                    "OW" -> {
                        pDialog.dismiss()
                        //own bank trasfer
                    }

                    "MFS" -> {
                        pDialog.dismiss()
                        //MFS transfer
                    }

                    "EBP" -> {
                        pDialog.dismiss()
                        //Electricity bill pay
                    }

                    "IBP" -> {
                        pDialog.dismiss()
                        //insurence bill pay
                    }

                    "UBP" -> {
                        pDialog.dismiss()
                        //university bill pay
                    }

                    "CREDIT" -> {
                        pDialog.dismiss()
                        //cedit card transfer
                    }

                    "CREDITP" -> {
                        pDialog.dismiss()
                        //credit card bill payment
                    }

                    "AC" -> {
                        pDialog.dismiss()
                        //account service dashboard
                    }

                    "QR" -> {
                        //QR Scanning
                    }

                    "RTID" -> {
                        //touch reg
                    }

                    "POSITIVE" -> {
                        //reg touch id
                    }

                    "CHEQUE_REQ" -> {
                        //reg touch id
                    }

                    "UBP" -> {
                        //university fee
                    }
                    "INS" -> {
                        //insurenc bill
                    }


                    else -> {
                        pDialog.dismiss()
                        val intent = Intent(this, WelcomeDashboardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }
                }
            }

        }

        accountViewModel.sourceAccountListResponse.observe(this) {
            pDialog.dismiss()

            if (it.isNotEmpty()) {
                //  globalVariable.sourceAccountList = (it as ArrayList<SourceAccountListDto>?)!!
                Log.d("sourceAccountList", it.toString())

                for (i in it.indices) {

                    try {
                        if (it[i].accountNumber.equals(null) || it[i].accountNumber.equals("null") || it[i].accountNumber!!.isEmpty()) {

                        } else {
                            val model = SourceAccountListDto(
                                it[i].accountNumber.toString(),
                                it[i].accountTitle.toString(),
                                it[i].primaryAccount.toString()
                            )
                            globalVariable.sourceAccountList.add(model)

                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }

                for (i in it.indices) {
                    if (it[i].primaryAccount == "P") {
                        globalVariable.primaryAc = it[i].accountNumber.toString()
                        break
                    } else if (it.size == 1) {
                        globalVariable.primaryAc = it[i].accountNumber.toString()
                    } else {
                        globalVariable.primaryAc = it[0].accountNumber.toString()
                    }
                }

            } else {
                CustomAlert.showErrorMessage(
                    this,
                    "No Source Account Found for Transfer",
                    globalVariable.languageCode
                )

            }


            //  val intent = Intent(this, WelcomeDashboardActivity::class.java)
            //  startActivity(intent)

            when (FROM) {
                "W" -> {
                    pDialog.dismiss()
                    val intent = Intent(this, WelcomeDashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }

                "TOPUP" -> {
                    pDialog.dismiss()
                    //recharge
                }

                "OT" -> {
                    pDialog.dismiss()
                    //other bank trasfer
                }

                "OW" -> {
                    pDialog.dismiss()
                    //own bank trasfer
                }

                "MFS" -> {
                    pDialog.dismiss()
                    //MFS transfer
                }

                "EBP" -> {
                    pDialog.dismiss()
                    //Electricity bill pay
                }

                "IBP" -> {
                    pDialog.dismiss()
                    //insurence bill pay
                }

                "UBP" -> {
                    pDialog.dismiss()
                    //university bill pay
                }

                "CREDIT" -> {
                    pDialog.dismiss()
                    //cedit card transfer
                }

                "CREDITP" -> {
                    pDialog.dismiss()
                    //credit card bill payment
                }

                "AC" -> {
                    pDialog.dismiss()
                    //account service dashboard
                }

                "QR" -> {
                    //QR Scanning
                }

                "RTID" -> {
                    //touch reg
                }

                "POSITIVE" -> {
                    //reg touch id
                }

                "CHEQUE_REQ" -> {
                    //reg touch id
                }

                "UBP" -> {
                    //university fee
                }
                "INS" -> {
                    //insurenc bill
                }
                else -> {
                    pDialog.dismiss()
                    val intent = Intent(this, WelcomeDashboardActivity::class.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    startActivity(intent)
                    finish()
                }
            }


        }

        accountViewModel.sourceAcforStatement_response.observe(this) {
            if ("0" == it.outCode) {
                // pDialog.dismiss()
                sourceAcforStatementList()
            } else if ("2" == it.outCode) {
                pDialog.dismiss()

                CustomActivityClear.forceLogout(this, it.outMessage.toString())

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    this,
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }

        accountViewModel.sourceAcforStatementListResponse.observe(this) {
            pDialog.dismiss()
            globalVariable.sourceAcStatementList.clear()
            if (it.isNotEmpty()) {
                // globalVariable.sourceAcStatementList = (it as ArrayList<SourceAccountListDto>?)!!
                // globalVariable.sourceAccountList = it!!

                if ("AGB" == globalVariable.identifyBanking) {
                    globalVariable.sourceAccountList.clear()
                    sourceAccountList.clear()
                    for (i in it.indices) {

                        try {
                            if (it[i].accountNumber.equals(null) || it[i].accountNumber.equals("null") || it[i].accountNumber!!.isEmpty()) {

                            } else {
                                sourceAccountList.add(
                                    SourceAccountListDto(
                                        it[i].accountNumber,
                                        it[i].accountTitle,
                                        it[i].primaryAccount,
                                    )
                                )

                            }
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }


                    }

                    globalVariable.sourceAccountList = sourceAccountList
                    globalVariable.chequesourceAccountList = sourceAccountList

                    for (i in it.indices) {
                        if (it[i].primaryAccount == "P") {
                            globalVariable.primaryAc = it[i].accountNumber.toString()
                            break
                        } else if (it.size == 1) {
                            globalVariable.primaryAc = it[i].accountNumber.toString()
                        } else {
                            globalVariable.primaryAc = it[0].accountNumber.toString()
                        }


                    }

                }

                when (FROM) {
                    "W" -> {
                        pDialog.dismiss()
                        val intent = Intent(this, WelcomeDashboardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }

                    "TOPUP" -> {
                        pDialog.dismiss()
                        //recharge
                    }

                    "OT" -> {
                        pDialog.dismiss()
                        //other bank trasfer
                    }

                    "OW" -> {
                        pDialog.dismiss()
                        //own bank trasfer
                    }

                    "MFS" -> {
                        pDialog.dismiss()
                        //MFS transfer
                    }

                    "EBP" -> {
                        pDialog.dismiss()
                        //Electricity bill pay
                    }

                    "IBP" -> {
                        pDialog.dismiss()
                        //insurence bill pay
                    }

                    "UBP" -> {
                        pDialog.dismiss()
                        //university bill pay
                    }

                    "CREDIT" -> {
                        pDialog.dismiss()
                        //cedit card transfer
                    }

                    "CREDITP" -> {
                        pDialog.dismiss()
                        //credit card bill payment
                    }

                    "AC" -> {
                        pDialog.dismiss()
                        //account service dashboard
                    }

                    "QR" -> {
                        //QR Scanning
                    }

                    "RTID" -> {
                        //touch reg
                    }

                    "POSITIVE" -> {
                        //reg touch id
                    }

                    "CHEQUE_REQ" -> {
                        //reg touch id
                    }

                    "UBP" -> {
                        //university fee
                    }
                    "INS" -> {
                        //insurenc bill
                    }

                    else -> {
                        pDialog.dismiss()
                        val intent = Intent(this, WelcomeDashboardActivity::class.java)
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                        startActivity(intent)
                        finish()
                    }
                }
            }
        }

        userViewModel.userIdResponse.observe(this) {
            pDialog.dismiss()
            //CustomAlert.showErrorMessage(this, it.toString(), "")
            codeDesOptions.clear()
            useridList = it as ArrayList<UserIdEntity>
            Log.d("useridList", useridList.toString())

            for (i in 0 until useridList.size) {
                val model = CodeDesOptions(
                    useridList[i].id.toString(),
                    useridList[i].userid
                )
                codeDesOptions.add(model)
            }

            val adapter =
                AutoCompleteAdapter(
                    this,
                    codeDesOptions
                )
            et_user_id_value.setAdapter(adapter)
            adapter.setDropDownViewResource(R.layout.dropdown_item)

        }

        userViewModel.addUseridResponse.observe(this) {
            pDialog.dismiss()
            Log.e("insert", it.message)

        }


        userViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            //CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)
            Log.e("login", it.message)
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


    fun loginAction() {
        pDialog.show()

        if (globalVariable.imei == "" || globalVariable.imei == "null") {
            pDialog.dismiss()
            getImei()
        }
        if (globalVariable.imei == "") {
            pDialog.dismiss()

            CustomAlert.showErrorMessage(
                this,
                "Please Allow PHONE Permission  for Apps use",
                globalVariable.languageCode
            )

        } else {
            pDialog.show()

            val model = LoginModel()
            model.userId = Encript_Parameter.getRsa_encrypt(et_user_id_value.text.toString())
            model.passowrd = Encript_Parameter.getRsa_encrypt(et_password_value.text.toString())
            model.appVersion = Encript_Parameter.getRsa_encrypt(Constants.appVersion)
            model.serverMac = Encript_Parameter.getRsa_encrypt("")
            model.clientMac = Encript_Parameter.getRsa_encrypt("")
            model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
            model.authUser = Constants.app_user
            model.authPassword = Constants.app_user_pwd
            model.requestFrom = Encript_Parameter.getRsa_encrypt(Constants.requestFrom)
            model.requestType = Encript_Parameter.getRsa_encrypt("LOGIN")
            model.fingerflg = Encript_Parameter.getRsa_encrypt("")
            model.deviceInfo = globalVariable.deviceInfo
            model.versionCode =
                "version:" + BuildConfig.VERSION_NAME + "-code:" + BuildConfig.VERSION_CODE
            loginViewModel.login(HeaderData.headerHome(globalVariable), model)
        }

        //App Version: "+ BuildConfig.VERSION_NAME+", Version Code "+BuildConfig.VERSION_CODE
    }

    fun fingerLoginAction() {
        pDialog.show()

        if (globalVariable.imei == "" || globalVariable.imei == "null") {
            pDialog.dismiss()
            getImei()
        }
        if (globalVariable.imei == "") {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(
                this,
                "Please Allow PHONE Permission  for Apps use",
                globalVariable.languageCode
            )

        } else {
            pDialog.show()
            val model = LoginModel()
            model.userId = Encript_Parameter.getRsa_encrypt(et_user_id_value.text.toString())
            if (password.isEmpty()) {
                model.passowrd = Encript_Parameter.getRsa_encrypt("")
            } else {
                model.passowrd = password
            }
            model.appVersion = Encript_Parameter.getRsa_encrypt(Constants.appVersion)
            model.serverMac = Encript_Parameter.getRsa_encrypt("")
            model.clientMac = Encript_Parameter.getRsa_encrypt("")
            model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
            model.authUser = Constants.app_user
            model.authPassword = Constants.app_user_pwd
            model.requestFrom = Encript_Parameter.getRsa_encrypt(Constants.requestFrom)
            model.requestType = Encript_Parameter.getRsa_encrypt("LOGIN")
            model.fingerflg = Encript_Parameter.getRsa_encrypt(fingerflg)
            model.deviceInfo = globalVariable.deviceInfo
            model.versionCode =
                "version:" + BuildConfig.VERSION_NAME + "-code:" + BuildConfig.VERSION_CODE
            loginViewModel.fingerLogin(HeaderData.headerHome(globalVariable), model)
        }

        //App Version: "+ BuildConfig.VERSION_NAME+", Version Code "+BuildConfig.VERSION_CODE
    }


    private fun soureceAccount() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAccount(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun soureceAccountList() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAccountList(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun sourceAcforStatement() {
        pDialog.show()
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAcforStatement(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun sourceAcforStatementList() {
        val model = AccountRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accat = Encript_Parameter.getRsa_encrypt("")
        model.authorization = globalVariable.token
        accountViewModel.sourceAcforStatementList(HeaderData.headerWelcome(globalVariable), model)
    }

    private fun insertUserFingerInfo(userFingerEntity: UserFingerEntity) {
        userFingerViewModel.insertUserFingerInfo(userFingerEntity)
    }

    private fun updateUserFingerInfo(userFingerEntity: UserFingerEntity) {
        userFingerViewModel.updateUserFingerInfo(userFingerEntity)
    }

    /*
    private fun checkCallPermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        return result == PackageManager.PERMISSION_GRANTED
    }

    private fun checkPhonePermission(): Boolean {
        val result = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE)
        val result1 =
            ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_NETWORK_STATE)
        return result == PackageManager.PERMISSION_GRANTED && result1 == PackageManager.PERMISSION_GRANTED
    }
    */

    /*
    fun getCallPermission() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                )


                if (
                    READ_CONTACTS != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.CALL_PHONE
                        ), 1
                    )


                }


            }


        } catch (ex: Exception) {
            ex.message?.let { Log.e("", it) }
        }
    }
    */

    /*
    fun getPhonePermission() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val permission = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_PHONE_STATE
                )

                val ACCESS_NETWORK_STATE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.ACCESS_NETWORK_STATE
                )

                if (permission != PackageManager.PERMISSION_GRANTED || ACCESS_NETWORK_STATE != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.READ_PHONE_STATE,
                            Manifest.permission.ACCESS_NETWORK_STATE
                        ), 2
                    )
                }


            }


        } catch (ex: Exception) {
            ex.message?.let { Log.e("", it) }
        }
    }
    */

    /*
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "Phone Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getPhonePermission()
        }

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //Toast.makeText(this, "Call Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getCallPermission()
        }

    }
    */

    @SuppressLint("MissingPermission")
    fun isHardwareSupported(context: Context): Boolean {
        val fingerprintManager = from(context)
        return fingerprintManager.isHardwareDetected && fingerprintManager.hasEnrolledFingerprints()
    }

    fun fingerCheck() {
        biometricPrompt = BiometricPrompt(this, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int,
                    errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(
                        applicationContext,
                        "Authentication error: $errString", Toast.LENGTH_SHORT
                    )
                        .show()
                }

                override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)
                    Toast.makeText(
                        applicationContext,
                        "Authentication succeeded.", Toast.LENGTH_SHORT
                    )
                        .show()
                    fingerflg = "Y"

                    fingerLoginAction()


                }

                override fun onAuthenticationFailed() {
                    super.onAuthenticationFailed()
                    Toast.makeText(
                        applicationContext, "Authentication failed",
                        Toast.LENGTH_SHORT
                    )
                        .show()
                }
            })

        promptInfo = BiometricPrompt.PromptInfo.Builder()
            //.setTitle("Biometric login for my app")
            .setTitle("Biometric login for Smart App")
            .setSubtitle("Log in using your biometric credential")
            .setNegativeButtonText("Use account password")
            .setConfirmationRequired(true)
            .build()

        biometricPrompt.authenticate(promptInfo)

    }


    fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            btn_login.setText(R.string.login_bangla)
            btn_signup.setText(R.string.register_bangla)
            btn_ac_opening.setText(R.string.a_c_opening_bangla)
            tv_forgot_password.setText(R.string.forgot_password_bangla)
            tv_forgot_user_id.setText(R.string.forgot_userid_bangla)
            tv_bank_name.setText(R.string.smart_app_bangla)
            tv_user_id_label.setText(R.string.user_id_bangla)
            et_user_id_value.setHint(R.string.user_id_bangla)
            et_password_value.setHint(R.string.password_bangla)
            tv_password_label.setText(R.string.password_bangla)
            btn_call.setText(R.string._16205_bangla)
            btn_finger.setText(R.string.login_with_touch_id_bangla)
            tv_remember_id.setText(R.string.remember_user_id_bangla)

            // tv_powered_by.setText(R.string.register_bangla)
        }
    }


    private fun dataInsert(userid: String) {
        val optionsEntity = UserIdEntity(0, userid)
        userViewModel.insertUserId(optionsEntity)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        /*
        if (requestCode == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  Toast.makeText(this, "Phone Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getPhonePermission()
        }

        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            //  Toast.makeText(this, "Call Permission is Ok", Toast.LENGTH_SHORT).show()
        } else {
            getCallPermission()
        }
        */

        // Forward results to EasyPermissions
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            val yes = getString(R.string.yes)
            val no = getString(R.string.no)

            // Do something after user returned from app settings screen, like showing a Toast.
            /*
            Toast.makeText(
                this,
                getString(
                    R.string.returned_from_app_settings_to_activity,
                    if (isPermissionGranted(Manifest.permission.CAMERA)) yes else no,
                    if (isPermissionGranted(Manifest.permission.READ_PHONE_STATE)) yes else no,
                    if (isPermissionGranted(Manifest.permission.ACCESS_NETWORK_STATE)) yes else no,
                    if (isPermissionGranted(Manifest.permission.CALL_PHONE)) yes else no
                ),
                Toast.LENGTH_LONG
            )
                .show()
            */

            PermissionUtil().showAcceptedAccessList(this,
                "Your Accepted Access",
                PermissionUtil.FROM_LOGIN)
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
                .setRationale(PermissionUtil().rationalMessage(this,PermissionUtil.FROM_LOGIN))
                .build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }

    @AfterPermissionGranted(PermissionUtil.ALL_PERMISSION_PERM)
    fun permissionAllChecking() {
        if (checkAllPermission()) {
            // Have permissions, do the thing!
            //Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
            Log.d(TAG, "All Permission Accepted!");
        } else {
            // Ask for all permissions
            EasyPermissions.requestPermissions(
                this,
                getString(R.string.rationale_ask),
                PermissionUtil.ALL_PERMISSION_PERM,
                *PermissionUtil.ALL_PERMISSIONS
            )
        }
    }
    private fun checkAllPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, *PermissionUtil.ALL_PERMISSIONS)
    }

    private fun isPermissionGranted(name: String) = ContextCompat.checkSelfPermission(
        this, name
    ) == PackageManager.PERMISSION_GRANTED

}