package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.forgot_pass_user.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.activity.viewModels
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.isVisible
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.forgot_pass_user.view_model.ForgotViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model.AccountViewModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.permission.PermissionUtil
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AppSettingsDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions
import okhttp3.MultipartBody
import java.io.File
import java.util.*

@AndroidEntryPoint
class ForgotUserId : CustomAppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {

    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView

    private lateinit var btn_submit: AppCompatButton
    private lateinit var btn_proceed: AppCompatButton
    private lateinit var btn_done: AppCompatButton
    private lateinit var et_date_of_birth_value: TextInputEditText
    private lateinit var et_otp_value: TextInputEditText
    private lateinit var et_account_no_value: TextInputEditText
    private lateinit var et_nid_no_value: TextInputEditText


    private lateinit var btn_proceed_layout: LinearLayout
    private lateinit var btn_submit_layout: LinearLayout
    private lateinit var forgot_userid_input_layout: LinearLayout
    private lateinit var forgot_password_verify_layout: LinearLayout
    private lateinit var forgot_password_success_layout: LinearLayout

    private lateinit var scroll_layout: ScrollView
    private lateinit var toolbar_layout: LinearLayout

    private lateinit var tv_success_message: TextView
    private lateinit var date_of_month_input: TextInputLayout
    lateinit var mDateSateListenerFromDate: DatePickerDialog.OnDateSetListener

    private lateinit var tv_account: TextView
    private lateinit var tv_card: TextView

    private lateinit var account_no_input: TextInputLayout

    val forgotViewModel: ForgotViewModel by viewModels()


    var outSessionId = ""
    private var fileName: String? = ""
    var file: File? = null
    var userImage_part: MultipartBody.Part? = null
    var userImageBitmapValue: Bitmap? = null
    var destType = "A"
    var bankCode = ""
    var branchCode = ""
    var bankName = ""
    var branch_name = ""
    var apiOutCode = ""
    var shadowAc = ""
    var destAccountTitle = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_user_id)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        btn_submit = findViewById(R.id.btn_submit)
        btn_proceed = findViewById(R.id.btn_proceed)
        et_date_of_birth_value = findViewById(R.id.et_date_of_birth_value)
        et_otp_value = findViewById(R.id.et_otp_value)
        btn_proceed_layout = findViewById(R.id.btn_proceed_layout)
        btn_submit_layout = findViewById(R.id.btn_submit_layout)
        btn_done = findViewById(R.id.btn_done)
        scroll_layout = findViewById(R.id.scroll_layout)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        tv_success_message = findViewById(R.id.tv_success_message)
        et_account_no_value = findViewById(R.id.et_account_no_value)
        et_nid_no_value = findViewById(R.id.et_nid_no_value)
        forgot_userid_input_layout = findViewById(R.id.forgot_userid_input_layout)
        forgot_password_verify_layout = findViewById(R.id.forgot_password_verify_layout)
        forgot_password_success_layout = findViewById(R.id.forgot_password_success_layout)
        date_of_month_input = findViewById(R.id.date_of_month_input)
        tv_account = findViewById(R.id.tv_account)
        tv_card = findViewById(R.id.tv_card)
        account_no_input = findViewById(R.id.account_no_input)


        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.forgot_userid)

        iv_header_back.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            if (forgot_password_verify_layout.isVisible) {
                forgot_password_verify_layout.visibility = View.GONE
                forgot_password_success_layout.visibility = View.GONE
                forgot_userid_input_layout.visibility = View.VISIBLE
            } else {
                CustomActivityClear.doClearActivity(intent, this)
            }
        }

        iv_header_logout.setOnClickListener {
            HelpDialog.showHelpDialog(this, "FUID")
        }

        date_of_month_input.setEndIconOnClickListener {

            showPickerFromDate()

        }
        mDateSateListenerFromDate =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val month = month + 1
                val dateString = "$day/$month/$year"
                et_date_of_birth_value.setText(dateString)
            }


        /*  et_date_of_birth_value.setOnClickListener {
              CustomDailog.dateSelect(this, et_date_of_birth_value)
          }
          */

        tv_account.setOnClickListener {
            et_account_no_value.setText("")
            destType = "A"
            tv_account.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.app_color
                )
            )
            tv_card.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            tv_account.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )
            tv_card.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                account_no_input.hint = getString(R.string.account_no_bangla)
            } else {
                account_no_input.hint = getString(R.string.account_no)
            }


        }

        //Coming Soon Again on 27 Dec 2023
        tv_card.setOnClickListener{
            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                CustomAlert.showComingSoon(
                    this@ForgotUserId,
                    "Credit Card",
                    globalVariable.languageCode
                )}
            else{
                CustomAlert.showComingSoon(
                    this@ForgotUserId,
                    " ক্রেডিট কার্ড ",
                    globalVariable.languageCode
                )

            }
        }

        //remove for version 2.0.3
        //Re open on 3 Dec 2023 -enamul
        /*tv_card.setOnClickListener {
            et_account_no_value.setText("")
            destType = "C"
            tv_card.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.app_color
                )
            )
            tv_account.setTextColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.black
                )
            )
            tv_card.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_select,
                null
            )
            tv_account.background = ResourcesCompat.getDrawable(
                resources,
                R.drawable.btn_round_border_transparent_unselect,
                null
            )

            if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                account_no_input.hint = getString(R.string.card_non_bangla)
            } else {
                account_no_input.hint = getString(R.string.card_no)
            }

        }
        */


        et_account_no_value.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!NetworkUtil.isOnline(this@ForgotUserId)) {
                    CustomAlert.showInternetConnectionMessage(
                        this@ForgotUserId,
                        globalVariable.languageCode
                    )
                } else {
                    if (s.length >= 16 && destType == "C") {
                        if (!CustomAlert.isOnline(this@ForgotUserId)) {
                            CustomAlert.showInternetConnectionMessage(
                                this@ForgotUserId,
                                globalVariable.languageCode
                            )
                        } else {
                            getCardShadowAcInfo()
                        }
                    }
                }
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun afterTextChanged(s: Editable) {

            }
        })




        btn_proceed.setOnClickListener {
            if (!ValidationUtil.editTextValidation(et_account_no_value.text.toString())) {
                et_account_no_value.requestFocus()
                if (destType == "A") {
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.accountnoBangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.accountno,
                            globalVariable.languageCode
                        )
                    }
                } else {
                    if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.selectCardNObangla,
                            globalVariable.languageCode
                        )
                    } else {
                        CustomAlert.showErrorMessage(
                            this,
                            TextContants.selectCardNO,
                            globalVariable.languageCode
                        )
                    }
                }
            } else if (!ValidationUtil.editTextValidation(et_date_of_birth_value.text.toString())) {
                et_date_of_birth_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.dob_validation_bangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.dob_validation,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.editTextValidation(et_nid_no_value.text.toString())) {
                et_nid_no_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.nidno_validation_message_bangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.nidno_validation_message,
                        globalVariable.languageCode
                    )
                }
            } else if (apiOutCode != "0" && destType == "C") {
                et_account_no_value.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this@ForgotUserId,
                        TextContants.validCardNObangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this@ForgotUserId,
                        TextContants.validCardNO,
                        globalVariable.languageCode
                    )
                }
            } else if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
            } else {
                forgotUserIDInfoVerify()
            }
        }

        btn_submit.setOnClickListener {
            if (!ValidationUtil.editTextValidation(et_otp_value.text.toString())) {
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
            } else if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
            } else {
                forgotUserIdExe()
            }
        }

        btn_done.setOnClickListener {
            val intent = Intent(this@ForgotUserId, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@ForgotUserId)
        }

        getImei()
        observeEvents()

        permissionAllChecking()
        /*
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )

                val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

                val CAMERA = ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CAMERA
                )

                if (WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED || READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED || CAMERA != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(
                        this, arrayOf(
                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
                            Manifest.permission.READ_EXTERNAL_STORAGE,
                            Manifest.permission.CAMERA
                        ), 1
                    )
                }


            }


        } catch (ex: Exception) {
            ex.message?.let {
                // Log.e("", it)
            }
        }
        */


        try {

            val file = File(
                this.getExternalFilesDir(null)?.absolutePath,
                "img"
            )

            if (!file.exists()) {
                file.mkdir()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(this@ForgotUserId, LoginActivity::class.java)
                    if (forgot_password_verify_layout.isVisible) {
                        forgot_password_verify_layout.visibility = View.GONE
                        forgot_password_success_layout.visibility = View.GONE
                        forgot_userid_input_layout.visibility = View.VISIBLE
                    } else {
                        CustomActivityClear.doClearActivity(intent, this@ForgotUserId)
                    }

                }
            }
            )

    }

    private fun observeEvents() {

        forgotViewModel.forgotUserIDInfoVerifyResponse.observe(this) {
            if ("0" == it.outCode) {
                pDialog.dismiss()
                outSessionId = it.outSessionId.toString()
                forgot_userid_input_layout.visibility = View.GONE
                forgot_password_verify_layout.visibility = View.VISIBLE

            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    this,
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }

        forgotViewModel.forgotUserIdExeResponse.observe(this) {
            if ("0" == it.outCode) {
                pDialog.dismiss()
                forgot_password_verify_layout.visibility = View.GONE
                btn_submit_layout.visibility = View.GONE
                scroll_layout.visibility = View.GONE
                toolbar_layout.visibility = View.GONE

                forgot_password_success_layout.visibility = View.VISIBLE
                tv_success_message.text = it.outMessage


            } else {
                pDialog.dismiss()
                CustomAlert.showErrorMessage(
                    this,
                    it.outMessage,
                    globalVariable.languageCode
                )
            }

        }

        forgotViewModel.getCardShadowAcInfoResponse.observe(this) {
              apiOutCode = it.outCode.toString()
            when (it.outCode) {
                "0" -> {
                    pDialog.dismiss()

                    bankCode = it.bankCode.toString()
                    branchCode = it.branchCode.toString()
                    destAccountTitle = it.accountTitle.toString()
                    bankName = it.bankName.toString()
                    branch_name = it.branchName.toString()
                    shadowAc = it.shadowAc.toString()
                    globalVariable.shadowAc = it.shadowAc.toString()

                }

                "2" -> {
                    pDialog.dismiss()
                    CustomActivityClear.forceLogout(this, it.outMessage.toString())
                }

                else -> {
                    pDialog.dismiss()
                    CustomAlert.showErrorMessage(
                        this,
                        it.outMessage,
                        globalVariable.languageCode
                    )
                }
            }

        }


        forgotViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)

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

    private fun showPickerFromDate() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            this, AlertDialog.THEME_HOLO_LIGHT, mDateSateListenerFromDate, year, month, day
        )
        datePickerDialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        datePickerDialog.show()
    }


    private fun forgotUserIDInfoVerify() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.terminalId = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
        model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)

        if (destType == "C") {
            model.accountNo = Encript_Parameter.getRsa_encrypt(globalVariable.shadowAc)
        } else {
            model.accountNo = Encript_Parameter.getRsa_encrypt(et_account_no_value.text.toString())
        }

        model.userId = Encript_Parameter.getRsa_encrypt("")
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.nid = Encript_Parameter.getRsa_encrypt(et_nid_no_value.text.toString())
        model.dob = Encript_Parameter.getRsa_encrypt(et_date_of_birth_value.text.toString())
        model.sessionId = Encript_Parameter.getRsa_encrypt("")
        model.otp = Encript_Parameter.getRsa_encrypt("")
        model.operationMode = Encript_Parameter.getRsa_encrypt("")
        forgotViewModel.forgotUserIDInfoVerify(HeaderData.headerHome(globalVariable), model)
    }

    private fun forgotUserIdExe() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.terminalId = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
        model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
        if (destType == "C") {
            model.accountNo = Encript_Parameter.getRsa_encrypt(globalVariable.shadowAc)
        } else {
            model.accountNo = Encript_Parameter.getRsa_encrypt(et_account_no_value.text.toString())
        }
        model.userId = Encript_Parameter.getRsa_encrypt("")
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.nid = Encript_Parameter.getRsa_encrypt(et_nid_no_value.text.toString())
        model.dob = Encript_Parameter.getRsa_encrypt(et_date_of_birth_value.text.toString())
        model.sessionId = Encript_Parameter.getRsa_encrypt(outSessionId)
        model.otp = Encript_Parameter.getRsa_encrypt(et_otp_value.text.toString())
        model.operationMode = Encript_Parameter.getRsa_encrypt("")
        forgotViewModel.forgotUserIdExe(HeaderData.headerHome(globalVariable), model)
    }

    private fun getCardShadowAcInfo() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accountNo = Encript_Parameter.getRsa_encrypt(et_account_no_value.text.toString())
        model.destType = Encript_Parameter.getRsa_encrypt("C")
        model.bankType = Encript_Parameter.getRsa_encrypt("BDT")
        forgotViewModel.getCardShadowAcInfo(HeaderData.headerHome(globalVariable), model)
    }

    //============ Permission==============
    val TAG = "ForgetUserId"
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
                PermissionUtil.FROM_FORGET_USER)
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
                .setRationale(PermissionUtil().rationalMessage(this, PermissionUtil.FROM_FORGET_USER))
                .build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }

    @AfterPermissionGranted(PermissionUtil.ALL_PERMISSION_PERM_FORGET_USER)
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
                PermissionUtil.ALL_PERMISSION_PERM_FORGET_USER,
                *PermissionUtil.ALL_PERMISSIONS_FORGET_USER
            )
        }
    }
    private fun checkAllPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, *PermissionUtil.ALL_PERMISSIONS_FORGET_USER)
    }


}