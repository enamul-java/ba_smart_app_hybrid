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
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.provider.Settings
import android.telephony.TelephonyManager
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
import androidx.core.view.isVisible
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
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.*
import eraapps.bankasia.bdinternetbanking.apps.util.permission.PermissionUtil
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AppSettingsDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.util.*


@AndroidEntryPoint
class ForgotPassword : CustomAppCompatActivity(),
    EasyPermissions.PermissionCallbacks,
    EasyPermissions.RationaleCallbacks {
    private lateinit var globalVariable: GlobalVariable
    private lateinit var pDialog: SweetAlertDialog

    private lateinit var toolbar: Toolbar
    private lateinit var iv_header_back: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var iv_header_logout: ImageView
    private lateinit var iv_camera_icon: ImageView

    private lateinit var btn_submit: AppCompatButton
    private lateinit var btn_proceed: AppCompatButton
    private lateinit var btn_done: AppCompatButton

    private lateinit var et_user_id_value: TextInputEditText
    private lateinit var et_date_of_birth_value: TextInputEditText
    private lateinit var et_otp_value: TextInputEditText

    private lateinit var forgot_password_input_layout: LinearLayout
    private lateinit var forgot_password_verify_layout: LinearLayout
    private lateinit var forgot_password_success_layout: LinearLayout
    private lateinit var btn_proceed_layout: LinearLayout
    private lateinit var btn_submit_layout: LinearLayout
    private lateinit var iv_user_placeholder: ImageView
    private lateinit var date_of_month_input: TextInputLayout
    private lateinit var scroll_layout: ScrollView
    private lateinit var toolbar_layout: LinearLayout

    private lateinit var tv_success: TextView
    private lateinit var tv_success_message: TextView

    val forgotViewModel: ForgotViewModel by viewModels()
    lateinit var mDateSateListenerFromDate: DatePickerDialog.OnDateSetListener


    var outSessionId = ""
    var dbNid = ""
    private var stringImage: String = ""
    private var fileName: String? = ""
    var file: File? = null

    //var userImage_part: MultipartBody.Part? = null
    var userImageBitmapValue: Bitmap? = null

    var FROM = "";

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forgot_password)

        globalVariable = this.applicationContext as GlobalVariable
        pDialog = CustomAlert.showProgressDialog(this, globalVariable.languageCode)
        toolbar = findViewById(R.id.toolbar)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        btn_submit = findViewById(R.id.btn_submit)
        btn_proceed = findViewById(R.id.btn_proceed)
        et_user_id_value = findViewById(R.id.et_user_id_value)
        et_date_of_birth_value = findViewById(R.id.et_date_of_birth_value)
        et_otp_value = findViewById(R.id.et_otp_value)
        forgot_password_input_layout = findViewById(R.id.forgot_password_input_layout)
        forgot_password_verify_layout = findViewById(R.id.forgot_password_verify_layout)
        forgot_password_success_layout = findViewById(R.id.forgot_password_success_layout)
        btn_proceed_layout = findViewById(R.id.btn_proceed_layout)
        btn_submit_layout = findViewById(R.id.btn_submit_layout)
        //   btn_done_layout = findViewById(R.id.btn_done_layout)
        btn_done = findViewById(R.id.btn_done)
        scroll_layout = findViewById(R.id.scroll_layout)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        tv_success = findViewById(R.id.tv_success)
        tv_success_message = findViewById(R.id.tv_success_message)
        iv_user_placeholder = findViewById(R.id.iv_user_placeholder)
        iv_camera_icon = findViewById(R.id.iv_camera_icon)
        date_of_month_input = findViewById(R.id.date_of_month_input)

        tv_success.visibility = View.GONE

        setSupportActionBar(toolbar)
        toolbar_title.text = getString(R.string.forgot_password)

        var i = intent
        FROM = i.getStringExtra("FROM").toString()

        if("LIVENESS"==FROM){
            et_user_id_value.setText(globalVariable.userId)
            et_date_of_birth_value.setText(globalVariable.dob)
            userImageBitmapValue =  globalVariable.livePhotoBitmapValue
            try {
                stringImage = Base64Util.convertToBase64FromBitmap(userImageBitmapValue!!)
                iv_user_placeholder.setImageBitmap(globalVariable.livePhotoBitmapValue)
            } catch (e: Exception) {
                e.printStackTrace()
            }



        }

        iv_header_back.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)

            if (forgot_password_verify_layout.isVisible) {
                forgot_password_verify_layout.visibility = View.GONE
                forgot_password_success_layout.visibility = View.GONE
                forgot_password_input_layout.visibility = View.VISIBLE
            } else {
                CustomActivityClear.doClearActivity(intent, this)
            }
        }

        iv_header_logout.setOnClickListener {
            HelpDialog.showHelpDialog(this, "FPASS")
        }

      /*  et_date_of_birth_value.setOnClickListener {
            CustomDailog.dateSelect(this, et_date_of_birth_value)
        }

        */

        /*
        editText.setDrawableClickListener(new DrawableClickListener() {


        public void onClick(DrawablePosition target) {
            switch (target) {
            case LEFT:
                //Do something here
                break;

            default:
                break;
            }
        }

    });
        * */
       /* et_date_of_birth_value.setDrawableClickListener{
           Log.e("date item clicked: ",it.toString())
            when (it) {

                DrawableClickListener.DrawablePosition.RIGHT -> {
                    //actj

                    CustomDailog.dateSelect(this, et_date_of_birth_value)
                }
                else -> {}
            }
        }
        */


//        et_date_of_birth_value.setDrawableClickListener(object : DrawableClickListener {
//            override fun onClick(target: DrawablePosition) {
//                Log.e("date item clicked: ",target.toString())
//                when (target)
//                {
//                    DrawablePosition.RIGHT ->{
//                        CustomDailog.dateSelect(this@ForgotPassword, et_date_of_birth_value)
//                    }
//
//                    else -> {}
//                }
//            }
//        })

        date_of_month_input.setEndIconOnClickListener {
            // Respond to end icon presses
            showPickerFromDate()
          //  CustomDailog.dateSelect(this@ForgotPassword, et_date_of_birth_value)
        }




        iv_camera_icon.setOnClickListener {



           // showOptions()
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
            }else{
                globalVariable.userId = et_user_id_value.text.toString()
                globalVariable.dob =et_date_of_birth_value.text.toString()

                val intent = Intent(this,CapturePhotoLivenessActivity::class.java)
                globalVariable.FROM = "FP"

                startActivity(intent)
            }

        }
        btn_proceed.setOnClickListener {

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
            } else if (userImageBitmapValue == null || FROM != "LIVENESS") {
                if (TextContants.banglaLanguageCode.equals(globalVariable.languageCode)) {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.capture_live_photo_bangla,
                        globalVariable.languageCode
                    )
                } else {
                    CustomAlert.showErrorMessage(
                        this,
                        TextContants.capture_live_photo,
                        globalVariable.languageCode
                    )
                }

            } else if (globalVariable.imei == "") {
                getImei()
            } else if (!NetworkUtil.isOnline(this)) {
                CustomAlert.showInternetConnectionMessage(this, globalVariable.languageCode)
            } else {
                forgotPassInfoVerify()
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
                forgotPassExe()
            }
        }

        btn_done.setOnClickListener {
            val intent = Intent(this@ForgotPassword, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this@ForgotPassword)
        }

        mDateSateListenerFromDate =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                val month = month + 1
               var dateString = "$day/$month/$year"
                et_date_of_birth_value.setText(dateString)
            }

        //getpermission()
        permissionAllChecking()

        getImei()
        observeEvents()

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    val intent = Intent(this@ForgotPassword, LoginActivity::class.java)
                    if (forgot_password_verify_layout.isVisible) {
                        forgot_password_verify_layout.visibility = View.GONE
                        forgot_password_success_layout.visibility = View.GONE
                        forgot_password_input_layout.visibility = View.VISIBLE
                    } else {
                        CustomActivityClear.doClearActivity(intent, this@ForgotPassword)
                    }

                }
            }
            )

    }

    private fun observeEvents() {

        forgotViewModel.forgotPassInfoVerifyResponse.observe(this) {
            if ("0" == it.outCode) {
                pDialog.dismiss()
                outSessionId = it.outSessionId.toString()
                dbNid = it.dbNid.toString()
                forgot_password_input_layout.visibility = View.GONE

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

        forgotViewModel.forgotPassExeResponse.observe(this) {
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

        forgotViewModel.errorResponse.observe(this) {
            pDialog.dismiss()
            CustomAlert.showErrorMessage(this, it.message, globalVariable.languageCode)

        }
    }

    private fun showOptions() {
        CropImage.activity(null).setGuidelines(CropImageView.Guidelines.ON)
            .start(this)

    }


    @Deprecated("Deprecated in Java")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        pDialog.dismiss()
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            PermissionUtil().showAcceptedAccessList(this,
                "Your Accepted Access",
                PermissionUtil.FROM_FORGET_PASS)
        }
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

                    ///Log.e("nid_front_file_Name->",nid_front_file_Name);
                    /*val userImagePart: MultipartBody.Part =
                        MultipartBody.Part.createFormData(
                            "file", nidfontFile.name, RequestBody.create(
                                //"image/".toMediaTypeOrNull(), nidfontFile
                            )
                        )*/

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

    private fun forgotPassInfoVerify() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.terminalId = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
        model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
        model.userId = Encript_Parameter.getRsa_encrypt(et_user_id_value.text.toString().trim())
        model.accountNo = Encript_Parameter.getRsa_encrypt("")
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.nid = Encript_Parameter.getRsa_encrypt("")
        model.dob = Encript_Parameter.getRsa_encrypt(et_date_of_birth_value.text.toString().trim())
        model.sessionId = Encript_Parameter.getRsa_encrypt("")
        model.otp = Encript_Parameter.getRsa_encrypt("")
        model.operationMode = Encript_Parameter.getRsa_encrypt("")
        model.image = stringImage

        forgotViewModel.forgotPassInfoVerify(HeaderData.headerHome(globalVariable), model)
    }

    private fun forgotPassExe() {
        pDialog.show()
        val model = ForgotRequestModel()
        model.terminalId = Encript_Parameter.getRsa_encrypt(TextContants.terminal_id)
        model.imei = Encript_Parameter.getRsa_encrypt(globalVariable.imei)
        model.userId = Encript_Parameter.getRsa_encrypt(et_user_id_value.text.toString().trim())
        model.accountNo = Encript_Parameter.getRsa_encrypt("")
        model.mobileNumber = Encript_Parameter.getRsa_encrypt("")
        model.nid = Encript_Parameter.getRsa_encrypt(dbNid)
        model.dob = Encript_Parameter.getRsa_encrypt(et_date_of_birth_value.text.toString().trim())
        model.sessionId = Encript_Parameter.getRsa_encrypt(outSessionId)
        model.otp = Encript_Parameter.getRsa_encrypt(et_otp_value.text.toString())
        model.operationMode = Encript_Parameter.getRsa_encrypt("")
        model.image = stringImage

        forgotViewModel.forgotPassExe(HeaderData.headerHome(globalVariable), model)
    }


    //============ Permission==============
    val TAG = "ForgetPassword"
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray,
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)

    }

    /*
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == AppSettingsDialog.DEFAULT_SETTINGS_REQ_CODE) {
            PermissionUtil().showAcceptedAccessList(this,
                "Your Accepted Access",
                PermissionUtil.FROM_LOGIN)
        }
    }*/

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
                .setRationale(PermissionUtil().rationalMessage(this, PermissionUtil.FROM_FORGET_PASS))
                .build().show()
        }
    }

    override fun onRationaleAccepted(requestCode: Int) {
        Log.d(TAG, "onRationaleAccepted:" + requestCode);
    }

    override fun onRationaleDenied(requestCode: Int) {
        Log.d(TAG, "onRationaleDenied:" + requestCode);
    }

    @AfterPermissionGranted(PermissionUtil.ALL_PERMISSION_PERM_FORGET_PASS)
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
                PermissionUtil.ALL_PERMISSION_PERM_FORGET_PASS,
                *PermissionUtil.ALL_PERMISSIONS_FORGET_PASS
            )
        }
    }
    private fun checkAllPermission(): Boolean {
        return EasyPermissions.hasPermissions(this, *PermissionUtil.ALL_PERMISSIONS_FORGET_PASS)
    }


}