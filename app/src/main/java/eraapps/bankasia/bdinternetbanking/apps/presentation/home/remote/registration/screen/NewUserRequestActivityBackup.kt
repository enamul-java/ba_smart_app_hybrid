package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.screen

import android.Manifest
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.telephony.TelephonyManager
import android.text.Editable
import android.text.TextWatcher
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
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import com.canhub.cropper.CropImage
import com.canhub.cropper.CropImageView
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import com.iceteck.silicompressorr.SiliCompressor
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.HeaderData
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.forgot_pass_user.view_model.ForgotViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.liveness.CapturePhotoLivenessActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.view_model.NewUserRequestViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.view_model.ReRegistrationViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.permission.PermissionUtil
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AppSettingsDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


@Suppress("DEPRECATION")
@AndroidEntryPoint
class NewUserRequestActivityBackup : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView


    private lateinit var new_user_request_registration_layout: LinearLayout

    private lateinit var lyt_account_no: TextInputLayout
    private lateinit var lyt_date_of_birth: TextInputLayout
    private lateinit var lyt_nid_no: TextInputLayout
    private lateinit var edt_account_no: TextInputEditText
    private lateinit var edt_date_of_birth: TextInputEditText
    private lateinit var edt_nid_no: TextInputEditText
    private lateinit var iv_user_placeholder: ImageView
    private lateinit var iv_camera_icon: ImageView

    private lateinit var tv_mobile_verification: TextView

    private var stringImage: String = ""
    private var fileName: String? = ""
    var file: File? = null

    //var userImage_part: MultipartBody.Part? = null
    var userImageBitmapValue: Bitmap? = null

    private lateinit var btn_proceed: AppCompatButton

    private lateinit var btn_done: AppCompatButton

    private lateinit var new_user_request_verify_layout: LinearLayout
    private lateinit var lyt_otp: TextInputLayout
    private lateinit var edt_otp: TextInputEditText
    private lateinit var btn_submit: AppCompatButton

    private lateinit var date_of_month_input: TextInputLayout
    lateinit var mDateSateListenerFromDate: DatePickerDialog.OnDateSetListener
    //

    private lateinit var new_user_request_success_layout: LinearLayout
    private lateinit var tv_success: TextView
    private lateinit var tv_success_message: TextView

    private lateinit var tv_account: TextView
    private lateinit var tv_card: TextView


    val newUserRequestViewModel: NewUserRequestViewModel by viewModels()
    val forgotViewModel: ForgotViewModel by viewModels()

    private var confirmflg = "Y"
    private var sessionid = ""
    private var accessflg = ""

    private var page = 1
    var destType = "A"
    var bankCode = ""
    var branchCode = ""
    var bankName = ""
    var branch_name = ""
    var apiOutCode = ""
    var shadowAc = ""
    var destAccountTitle = ""

    var FROM = ""
    val reRegistrationViewModel: ReRegistrationViewModel by viewModels()

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_user_request)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        date_of_month_input = findViewById(R.id.date_of_month_input)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)


        new_user_request_registration_layout =
            findViewById(R.id.new_user_request_registration_layout)
        lyt_account_no = findViewById(R.id.lyt_account_no)
        lyt_date_of_birth = findViewById(R.id.date_of_month_input)
        lyt_nid_no = findViewById(R.id.lyt_nid_no)
        edt_account_no = findViewById(R.id.edt_account_no)
        edt_date_of_birth = findViewById(R.id.edt_date_of_birth)
        edt_nid_no = findViewById(R.id.edt_nid_no)
        iv_user_placeholder = findViewById(R.id.iv_user_placeholder)
        iv_camera_icon = findViewById(R.id.iv_camera_icon)
        btn_proceed = findViewById(R.id.btn_proceed)
        tv_mobile_verification = findViewById(R.id.tv_mobile_verification)

        new_user_request_verify_layout = findViewById(R.id.new_user_request_verify_layout)
        lyt_otp = findViewById(R.id.lyt_otp)
        edt_otp = findViewById(R.id.edt_otp)

        btn_submit = findViewById(R.id.btn_submit)

        new_user_request_success_layout = findViewById(R.id.new_user_request_success_layout)
        tv_success = findViewById(R.id.tv_success)
        tv_success_message = findViewById(R.id.tv_success_message)
        btn_done = findViewById(R.id.btn_done)
        tv_account = findViewById(R.id.tv_account)
        tv_card = findViewById(R.id.tv_card)

        tv_success.visibility = View.GONE

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.new_user_request)


        //edt_account_no.setText("04934008894")
        //edt_date_of_birth.setText("12/02/1997")
        //edt_nid_no.setText("19975117365000215")

        val i = intent
        FROM = i.getStringExtra("FROM").toString()
        if ("LIVENESS" == FROM) {
            edt_account_no.setText(globalVariable.accountNo)
            edt_nid_no.setText(globalVariable.nidNo)
            edt_date_of_birth.setText(globalVariable.dob)
            userImageBitmapValue = globalVariable.livePhotoBitmapValue
            try {
                stringImage =
                    Base64Util.convertToBase64FromBitmap(globalVariable.livePhotoBitmapValue)
                iv_user_placeholder.setImageBitmap(globalVariable.livePhotoBitmapValue)

                Log.e("stringImage-->", stringImage)
            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        iv_header_back.setOnClickListener {
            goBack()
        }

        iv_header_logout.setOnClickListener {
            HelpDialog.showHelpDialog(this, "NUSUP")
        }
        /*
                edt_date_of_birth.setOnClickListener {
                    CustomDailog.dateSelect(this, edt_date_of_birth)
                }
                */


        if (globalVariable.type == "C") {
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
                lyt_account_no.hint = getString(R.string.card_non_bangla)
            } else {
                lyt_account_no.hint = getString(R.string.card_no)
            }
        } else {
            globalVariable.type = "A"
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
                lyt_account_no.hint = getString(R.string.account_no_bangla)
            } else {
                lyt_account_no.hint = getString(R.string.account_no)
            }
        }

        tv_account.setOnClickListener {
            edt_account_no.setText("")
            destType = "A"
            globalVariable.type = "A"
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
                lyt_account_no.hint = getString(R.string.account_no_bangla)
            } else {
                lyt_account_no.hint = getString(R.string.account_no)
            }

        }


        tv_card.setOnClickListener {
            edt_account_no.setText("")
            destType = "C"
            globalVariable.type = "C"

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
                lyt_account_no.hint = getString(R.string.card_non_bangla)
            } else {
                lyt_account_no.hint = getString(R.string.card_no)
            }

        }

        edt_account_no.addTextChangedListener(object : TextWatcher {
            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                if (!NetworkUtil.isOnline(this@NewUserRequestActivityBackup)) {
                    CustomAlert.showInternetConnectionMessage(
                        this@NewUserRequestActivityBackup,
                        globalVariable.languageCode
                    )
                } else {
                    if (s.length >= 16 && destType == "C") {
                        if (!CustomAlert.isOnline(this@NewUserRequestActivityBackup)) {
                            CustomAlert.showInternetConnectionMessage(
                                this@NewUserRequestActivityBackup,
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



        iv_camera_icon.setOnClickListener {
            //  showOptions()

            if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
            } else if (edt_account_no.text.toString().isEmpty()) {
                edt_account_no.requestFocus()
                if (globalVariable.type == "A") {
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

            } else if (edt_date_of_birth.text.toString().isEmpty()) {
                if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
            } else if (globalVariable.apiOutCode != "0" && globalVariable.type == "C") {
                edt_account_no.requestFocus()
                if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
                    CustomAlert.showErrorMessage(
                        this@NewUserRequestActivityBackup,
                        TextContants.validCardNObangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this@NewUserRequestActivityBackup,
                        TextContants.validCardNO,
                        globalVariable.languageCode
                    )
                }
            } else if (!ValidationUtil.getDatevalidate(edt_date_of_birth.text.toString())) {
                if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
            } else if (edt_nid_no.text.toString().isEmpty()) {
                edt_nid_no.requestFocus()
                if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
            } else if (edt_nid_no.text.toString().length < 10 || edt_nid_no.text.toString().length > 10 && edt_nid_no.text.toString().length < 13 || edt_nid_no.text.toString().length > 13 && edt_nid_no.text.toString().length < 17 || edt_nid_no.text.toString().length > 17) {
                if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
            } else {

                globalVariable.accountNo = edt_account_no.text.toString()
                globalVariable.dob = edt_date_of_birth.text.toString()
                globalVariable.nidNo = edt_nid_no.text.toString()

                if (globalVariable.type == "A") {
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
                        lyt_account_no.hint = getString(R.string.account_no_bangla)
                    } else {
                        lyt_account_no.hint = getString(R.string.account_no)
                    }

                } else {
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
                        lyt_account_no.hint = getString(R.string.card_non_bangla)
                    } else {
                        lyt_account_no.hint = getString(R.string.card_no)
                    }
                }

                val intent = Intent(this, CapturePhotoLivenessActivity::class.java)
                globalVariable.FROM = "SU"
                startActivity(intent)
            }


        }

        btn_proceed.setOnClickListener {
            proceedAction()
        }


        btn_submit.setOnClickListener {
            submitAction()
        }

        date_of_month_input.setEndIconOnClickListener {

            showPickerFromDate()

        }
        mDateSateListenerFromDate =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val month = month + 1
                var dateString = "$day/$month/$year"
                edt_date_of_birth.setText(dateString)
            }



        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goBack()
                }
            }
            )

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

        try {

            val file = File(
                this.getExternalFilesDir(null)?.getAbsolutePath(),
                "img"
            )

            if (!file.exists()) {
                file.mkdir()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        btn_done.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

        getpermission()
        observeViewModel()
        fontSet()

    }

    private fun fontSet() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.setText(R.string.new_user_request_bangla)
            lyt_account_no.setHint(R.string.account_no_bangla)
            lyt_date_of_birth.setHint(R.string.date_of_birth_bangla)
            lyt_nid_no.setHint(R.string.nid_no_bangla)
            btn_done.text = getText(R.string.done_bangla)
            btn_proceed.text = getText(R.string.btn_proced_bangla)
            btn_submit.text = getText(R.string.submit_bangla)
            lyt_otp.hint = getText(R.string.otp_bangla)
            tv_mobile_verification.text = getText(R.string.verification_bangla)
        } else {
            toolbar_title.setText(R.string.new_user_request)
            lyt_account_no.setHint(R.string.account_no)
            lyt_date_of_birth.setHint(R.string.date_of_birth)
            lyt_nid_no.setHint(R.string.nid_no)
        }
    }


    private fun observeViewModel() {

        newUserRequestViewModel.newUserRequestRegistrationVerifyResponse
            .observe(this) {

                // Log.e("veryfy response --->",it.toString())
                // Log.e("sessionId response --->",it.sessionId.toString())
                if ("0" == it.outCode) {
                    pDialog.dismiss()

                    accessflg = it.accessflg.toString()
                    sessionid = it.sessionId.toString()

                    gotoOtpPage()

                } else {
                    pDialog.dismiss()
                    CustomAlert.showErrorMessage(this, it.outMessage, globalVariable.languageCode)
                }

            }

        newUserRequestViewModel.newUserRequestRegistrationResponse
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


        forgotViewModel.getCardShadowAcInfoResponse.observe(this) {
            apiOutCode = it.outCode.toString()
            globalVariable.apiOutCode = it.outCode.toString()

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

        newUserRequestViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)

        }
    }

    private fun proceedAction() {
        if (!NetworkUtil.isOnline(this)) {
            CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
        } else if (edt_account_no.text.toString().isEmpty()) {
            edt_account_no.requestFocus()
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
        } else if (edt_date_of_birth.text.toString().isEmpty()) {
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
        } else if (!ValidationUtil.getDatevalidate(edt_date_of_birth.text.toString())) {
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
        } else if (edt_nid_no.text.toString().isEmpty()) {
            edt_nid_no.requestFocus()
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
        } else if (edt_nid_no.text.toString().length < 10 || edt_nid_no.text.toString().length in 11..12 || edt_nid_no.text.toString().length in 14..16 || edt_nid_no.text.toString().length > 17) {
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
        } else if (userImageBitmapValue == null) {
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
                CustomAlert.showErrorMessage(
                    this,
                    TextContants.photo_select_validation_bangla,
                    globalVariable.languageCode
                )
            } else {
                CustomAlert.showErrorMessage(
                    this,
                    TextContants.photo_select_validation,
                    globalVariable.languageCode
                )
            }

        } else if (globalVariable.imei == "") {
            getImei()
        } else {
            newUserRegReqVerify()
        }
    }

    fun submitAction() {
        if (!NetworkUtil.isOnline(this)) {
            CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
        } else if (edt_otp.text.toString().isEmpty()) {
            if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
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
        } else {
            submitNewUserRegistration()
        }
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

    private fun getCardShadowAcInfo() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.mailId = Encript_Parameter.getRsa_encrypt(globalVariable.mailId)
        model.sessionId = Encript_Parameter.getRsa_encrypt(globalVariable.sessionId)
        model.companyCode = Encript_Parameter.getRsa_encrypt(globalVariable.companycode)
        model.currentDate = Encript_Parameter.getRsa_encrypt("")
        model.accountNo = Encript_Parameter.getRsa_encrypt(edt_account_no.text.toString())
        model.destType = Encript_Parameter.getRsa_encrypt("C")
        model.bankType = Encript_Parameter.getRsa_encrypt("BDT")
        forgotViewModel.getCardShadowAcInfo(HeaderData.headerHome(globalVariable), model)
    }

    private fun newUserRegReqVerify() {
        pDialog.show()

        if (globalVariable.imei == "" || globalVariable.imei == "null") {
            pDialog.dismiss()
            getImei()
        }
        if (globalVariable.imei == "") {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(
                this,
                TextContants.phone_permission_for_app_use,
                globalVariable.languageCode
            )

        } else {
            pDialog.show()
            val model = NewUserRequestVerifyRequestModel()
            model.terminalid = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
            model.ipimeino = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
            model.sessionid = Encript_Parameter.getRsa_encrypt("")
            if (globalVariable.type == "C") {
                model.actnum = Encript_Parameter.getRsa_encrypt(globalVariable.shadowAc)
            } else {
                model.actnum =
                    Encript_Parameter.getRsa_encrypt(edt_account_no.text.toString().trim())
            }
            model.mobileno = Encript_Parameter.getRsa_encrypt("")
            model.nid = Encript_Parameter.getRsa_encrypt(edt_nid_no.text.toString().trim())
            model.dob = Encript_Parameter.getRsa_encrypt(edt_date_of_birth.text.toString().trim())
            model.userid = Encript_Parameter.getRsa_encrypt("")
            model.image = stringImage


            //val data: ByteArray = text.getBytes("UTF-8")
            //val base64: String = Base64.Encoder(data, Base64.getEncoder().de)

            newUserRequestViewModel.newUserRegReqVerify(
                HeaderData.headerHome(globalVariable),
                model
            )
        }
    }


    private fun submitNewUserRegistration() {
        pDialog.show()
        if (globalVariable.imei == "" || globalVariable.imei == "null" || globalVariable.imei == null) {
            getImei()
        }
        if (globalVariable.imei == "") {
            CustomAlert.showErrorMessage(
                this,
                TextContants.phone_state_and_network_state_permission,
                globalVariable.languageCode
            )
            pDialog.dismiss()
        } else {
            var nidNumber = "";
            if (edt_nid_no.text.toString().length == 13) {
                var d = edt_date_of_birth.text.toString().trim()
                val year: String = d.substring(d.length - 4)
                nidNumber = year + edt_nid_no.text.toString()
            } else {
                nidNumber = edt_nid_no.text.toString().trim()
            }


            val model = NewUserRequestRegistrationRequestModel()
            model.terminalid = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
            model.ipimeino = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
            model.sessionid = Encript_Parameter.getRsa_encrypt(sessionid)
            model.otp = Encript_Parameter.getRsa_encrypt(edt_otp.text.toString().trim())
            if (globalVariable.type == "C") {
                model.actnum =
                    Encript_Parameter.getRsa_encrypt(globalVariable.shadowAc)
            } else {
                model.actnum =
                    Encript_Parameter.getRsa_encrypt(edt_account_no.text.toString().trim())
            }

            model.mobileno =
                Encript_Parameter.getRsa_encrypt("")
            model.nid = Encript_Parameter.getRsa_encrypt(nidNumber)
            model.web = Encript_Parameter.getRsa_encrypt("")
            model.app = Encript_Parameter.getRsa_encrypt("A")
            model.confirmflg = Encript_Parameter.getRsa_encrypt(confirmflg)
            model.dob = Encript_Parameter.getRsa_encrypt(edt_date_of_birth.text.toString().trim())
            model.mailid = Encript_Parameter.getRsa_encrypt("")
            model.userid = Encript_Parameter.getRsa_encrypt("")
            model.image = stringImage

            //model.file = userImage_part
            this.let {

                newUserRequestViewModel.newUserRegistrationRequest(
                    HeaderData.headerHome(
                        globalVariable
                    ), model
                )
            }

        }

    }


    /*
    *
    * View Visibility
     */


    private fun gotoRegistrationPage() {
        page = 1
        new_user_request_registration_layout.visibility = View.VISIBLE
        toolbar_layout.visibility = View.VISIBLE
        new_user_request_verify_layout.visibility = View.GONE
        new_user_request_success_layout.visibility = View.GONE
    }

    private fun gotoOtpPage() {
        page = 2
        new_user_request_registration_layout.visibility = View.GONE
        new_user_request_verify_layout.visibility = View.VISIBLE
        toolbar_layout.visibility = View.VISIBLE
        new_user_request_success_layout.visibility = View.GONE
        edt_otp.setText("")
    }

    private fun gotoSuccessPage() {
        page = 3
        new_user_request_registration_layout.visibility = View.GONE
        new_user_request_verify_layout.visibility = View.GONE
        toolbar_layout.visibility = View.GONE
        new_user_request_success_layout.visibility = View.VISIBLE
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
                    //do something
                }
                deviceid = tel.deviceId


            } catch (e: Exception) {
                e.printStackTrace()
            }


        }

        globalVariable.imei = deviceid
        Log.d("globalVariable.imei", globalVariable.imei)

    }


    private fun showOptions() {
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON)
            .start(this)

    }
    /*
        @Deprecated("Deprecated in Java")
        override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
            pDialog.dismiss()
            super.onActivityResult(requestCode, resultCode, data)
            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                val result = CropImage.getActivityResult(data)
                if (resultCode == RESULT_OK) {
                    try {

                        //Uri uri = data.getParcelableExtra("path");
                        val uri = result!!.uri
                        file = File(uri.path!!)
                        fileName = uri.lastPathSegment
                        // Log.e("file", file.toString())
                        fileName?.let {

                            //Log.e("fileName", it)

                        }
                        (findViewById<View>(R.id.iv_user_placeholder) as ImageView).setImageURI(
                            result.uri
                        )


                        //Save nid Front
                        val rootPath = this.getExternalFilesDir(null)?.absolutePath + "/img/"

                        val drawNidFont: BitmapDrawable =
                            iv_user_placeholder.drawable as BitmapDrawable
                        val bitmapNidFont: Bitmap = drawNidFont.bitmap

                        var outStreamBack: FileOutputStream? = null
                        val nidFontFileName = "file.jpg"

                        // Log.e("ccc",globalVariable.nomineeList.size.toString())

                        val nidfontFile = File(rootPath, nidFontFileName)

                        outStreamBack = FileOutputStream(nidfontFile)
                        bitmapNidFont.compress(Bitmap.CompressFormat.JPEG, 20, outStreamBack)
                        outStreamBack.flush()
                        outStreamBack.close()



                        val selectedImageURI: Uri? = getImageUri(this, bitmapNidFont)
                        val imageBitmap = SiliCompressor.with(this)
                            .getCompressBitmap(selectedImageURI.toString(), true)

                        //userImage_part = userImagePart
                        userImageBitmapValue = imageBitmap

                        //try{stringImage = convertToBase64(nidfontFile)}catch (e:Exception){}
                        try {
                            stringImage = Base64Util.convertToBase64FromBitmap(userImageBitmapValue!!)
                        } catch (_: Exception) {
                        }

                        //Log.e("FILE", "onActivityResult: ${userImage_part!!.headers!!.size}")


                    } catch (e: Error) {
                        e.printStackTrace()
                    }
                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    // Toast.makeText(this, "Cropping failed: " + result!!.error, Toast.LENGTH_LONG).show()
                }
            }
        }
        */


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        pDialog.dismiss()
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            val result = CropImage.getActivityResult(data)
            if (resultCode == RESULT_OK) {
                try {

                    //Uri uri = data.getParcelableExtra("path");
                    val uri = result!!.uri
                    file = File(uri.path!!)
                    fileName = uri.lastPathSegment
                    // Log.e("file", file.toString())
                    fileName?.let {

                        //Log.e("fileName", it)

                    }
                    (findViewById<View>(R.id.iv_user_placeholder) as ImageView).setImageURI(
                        result.uri
                    )
                    /*val bitmap = (nomineePhoto.drawable as BitmapDrawable).getBitmap()
                    globalVariable.nidFrontBitmapValue = bitmap*/

                    //Save nid Front
                    val rootPath = this.getExternalFilesDir(null)?.absolutePath + "/img/"

                    val drawNidFont: BitmapDrawable =
                        iv_user_placeholder.drawable as BitmapDrawable
                    val bitmapNidFont: Bitmap = drawNidFont.bitmap

                    var outStreamBack: FileOutputStream? = null
                    val nidFontFileName = "file.jpg"

                    // Log.e("ccc",globalVariable.nomineeList.size.toString())

                    val nidfontFile = File(rootPath, nidFontFileName)

                    outStreamBack = FileOutputStream(nidfontFile)
                    bitmapNidFont.compress(Bitmap.CompressFormat.JPEG, 20, outStreamBack)
                    outStreamBack.flush()
                    outStreamBack.close()


                    val selectedImageURI: Uri? = getImageUri(this, bitmapNidFont)
                    val imageBitmap = SiliCompressor.with(this)
                        .getCompressBitmap(selectedImageURI.toString(), true)

                    //userImage_part = userImagePart
                    userImageBitmapValue = imageBitmap

                    //try{stringImage = convertToBase64(nidfontFile)}catch (e:Exception){}
                    try {
                        stringImage = Base64Util.convertToBase64FromBitmap(userImageBitmapValue!!)
                    } catch (_: Exception) {
                    }

                    //Log.e("FILE", "onActivityResult: ${userImage_part!!.headers!!.size}")


                } catch (e: Error) {
                    e.printStackTrace()
                }
            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                // Toast.makeText(this, "Cropping failed: " + result!!.error, Toast.LENGTH_LONG).show()
            }
        }
    }


    fun getImageUri(inContext: Context, inImage: Bitmap): Uri? {
        val bytes = ByteArrayOutputStream()
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes)
        //String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        val path = MediaStore.Images.Media.insertImage(
            inContext.contentResolver,
            inImage,
            "IMG_" + Calendar.getInstance().time,
            null
        )
        return Uri.parse(path)
    }

    private fun goBack() {
        when (page) {
            1 -> {
                val intent = Intent(this, LoginActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }

            2 -> {
                gotoRegistrationPage()
            }

            else -> {
                val intent = Intent(this, LoginActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }
        }

    }

    fun getpermission() {
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
    }


}