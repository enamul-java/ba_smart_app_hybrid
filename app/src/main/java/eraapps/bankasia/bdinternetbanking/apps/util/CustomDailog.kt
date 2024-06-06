package com.emon.raihan.dynamicutility.util

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.media.MediaScannerConnection
import android.os.Build
import android.os.Environment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.google.android.material.textfield.TextInputEditText
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.io.OutputStream
import java.text.SimpleDateFormat
import java.util.*

class CustomDailog {


    companion object {
        private val months = arrayOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )
        private const val REQUEST_EXTERNAL_STORAGE = 1
        private val PERMISSIONS_STORAGE = arrayOf(
            Manifest.permission.WRITE_EXTERNAL_STORAGE,
            Manifest.permission.READ_EXTERNAL_STORAGE
        )

        fun createYearPicker(activity: Activity, setYear: MaterialAutoCompleteTextView) {
            lateinit var yearPicker: NumberPicker
            lateinit var ivClose: ImageView
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            //val dateValue = reg_layout.findViewById<TextView>(R.id.dateValue)
            yearPicker = regLayout.findViewById(R.id.yearPicker)
            ivClose = regLayout.findViewById(R.id.ivClose)
            val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
            val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
            dialog.setView(regLayout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            val cal = Calendar.getInstance()
            val year = cal.get(Calendar.YEAR)


            yearPicker.minValue = 1950
            yearPicker.maxValue = year
            yearPicker.value = year
            yearPicker.wrapSelectorWheel = false
            // yearPicker.setOnValueChangedListener(activity)

            btnOk.setOnClickListener {
                setYear.setText(yearPicker.value.toString())
                alertDialog.dismiss()
            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }

            ivClose.setOnClickListener { alertDialog.dismiss() }


            alertDialog.show()

        }

        fun createMonthPicker(activity: Activity, setYear: MaterialAutoCompleteTextView) {
            lateinit var dateValue: TextView
            lateinit var monthPicker: NumberPicker
            lateinit var ivClose: ImageView
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val regLayout = inflater.inflate(R.layout.dialog_year_picker, null)
            dateValue = regLayout.findViewById(R.id.dateValue)
            monthPicker = regLayout.findViewById(R.id.yearPicker)
            ivClose = regLayout.findViewById(R.id.ivClose)
            val btnOk = regLayout.findViewById<Button>(R.id.btn_ok)
            val btnCancel = regLayout.findViewById<Button>(R.id.btn_cancel)
            dialog.setView(regLayout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dateValue.setText(R.string.select_month)
            val cal = Calendar.getInstance()
            val month = cal.get(Calendar.MONTH) + 1



            monthPicker.minValue = 1
            monthPicker.maxValue = 12
            monthPicker.value = month
            monthPicker.displayedValues = months



            btnOk.setOnClickListener {
                val newmonth: String = if (monthPicker.value < 10) {
                    "0" + (monthPicker.value).toString()
                } else {
                    monthPicker.value.toString()
                }
                setYear.setText(newmonth)
                alertDialog.dismiss()

            }

            btnCancel.setOnClickListener { alertDialog.dismiss() }
            ivClose.setOnClickListener { alertDialog.dismiss() }



            alertDialog.show()


        }

        @SuppressLint("SetTextI18n")
        fun fromDate(activity: Activity, et_from_date: TextInputEditText) {
            // Get Current Date
            val mYear: Int
            val mMonth: Int
            val mDay: Int
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                activity,
                { view, year, monthOfYear, dayOfMonth ->
                    val month1: String = if (monthOfYear < 9) {
                        "0" + (monthOfYear + 1)
                    } else {
                        "" + (monthOfYear + 1)
                    }
                    val day1: String = if (view!!.dayOfMonth <= 9) {
                        "0" + view.dayOfMonth
                    } else {
                        "" + view.dayOfMonth
                    }

                    et_from_date.setText("$day1/$month1/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }


        @SuppressLint("SetTextI18n")
        fun toDate(activity: Activity, et_to_date: TextInputEditText) {
            // Get Current Date
            val mYear: Int
            val mMonth: Int
            val mDay: Int
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                activity,
                { view, year, monthOfYear, dayOfMonth ->
                    val month1: String = if (monthOfYear < 9) {
                        "0" + (monthOfYear + 1)
                    } else {
                        "" + (monthOfYear + 1)
                    }
                    val day1: String = if (view!!.dayOfMonth <= 9) {
                        "0" + view.dayOfMonth
                    } else {
                        "" + view.dayOfMonth
                    }

                    et_to_date.setText("$day1/$month1/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        @SuppressLint("SetTextI18n")
        fun dateSelect(activity: Activity, dateInput: TextInputEditText) {
            // Get Current Date
            val mYear: Int
            val mMonth: Int
            val mDay: Int
            // Get Current Date
            val c = Calendar.getInstance()
            mYear = c[Calendar.YEAR]
            mMonth = c[Calendar.MONTH]
            mDay = c[Calendar.DAY_OF_MONTH]


            val datePickerDialog = DatePickerDialog(
                activity,
                { view, year, monthOfYear, dayOfMonth ->
                    val month1: String = if (monthOfYear < 9) {
                        "0" + (monthOfYear + 1)
                    } else {
                        "" + (monthOfYear + 1)
                    }
                    val day1: String = if (view!!.dayOfMonth <= 9) {
                        "0" + view.dayOfMonth
                    } else {
                        "" + view.dayOfMonth
                    }

                    dateInput.setText("$day1/$month1/$year")
                }, mYear, mMonth, mDay
            )
            datePickerDialog.show()
        }

        fun getBitmapFromView(view: View): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            view.draw(canvas)
            return bitmap
        }

        fun getBitmapFromView(view: View, defaultColor: Int): Bitmap {
            val bitmap = Bitmap.createBitmap(
                view.width, view.height, Bitmap.Config.ARGB_8888
            )
            val canvas = Canvas(bitmap)
            canvas.drawColor(defaultColor)
            view.draw(canvas)
            return bitmap
        }

        fun getAlbumStorageDir(albumName: String): File {
            // Get the directory for the user's public pictures directory.

            val file = File(
                Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_PICTURES
                ), albumName
            )
            if (!file.mkdirs()) {
                Log.e("SmartApp-->", "Directory not created")
            }
            return file
        }

        @Throws(IOException::class)
        fun saveBitmapToJPG(bitmap: Bitmap, photo: File) {
            val newBitmap =
                Bitmap.createBitmap(bitmap.width, bitmap.height, Bitmap.Config.ARGB_8888)
            val canvas = Canvas(newBitmap)
            canvas.drawColor(Color.WHITE)
            canvas.drawBitmap(bitmap, 0f, 0f, null)
            val stream: OutputStream = FileOutputStream(photo)
            newBitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
            stream.close()
        }

        fun addJpgSignatureToGallery(activity: Activity, signature: Bitmap): Boolean {
            var result = false
            try {
                val photo = File(
                    getAlbumStorageDir("Bank Asia"),
                    String.format("voucher_%d.jpg", System.currentTimeMillis())
                )
                saveBitmapToJPG(signature, photo)
                scanMediaFile(activity, photo)
                result = true
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return result
        }

        private fun scanMediaFile(activity: Activity, photo: File) {
            /* val mediaScanIntent = Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE)
              val contentUri = Uri.fromFile(photo)
              mediaScanIntent.data = contentUri
              this@QRGenerateActivity.sendBroadcast(mediaScanIntent)*/

//            val scan = MediaScannerConnection.scanFile(
//                activity, arrayOf(photo.toString()),
//                null, null
//            )

            MediaScannerConnection.scanFile(
                activity, arrayOf(photo.absolutePath),
                null
            ) { path, uri ->
                Log.v(
                    "vslue-->",
                    "file $path was scanned seccessfully: $uri"
                )
            }
        }

        fun verifyStoragePermissions(activity: Activity?) {
            // Check if we have write permission
            val permission = ActivityCompat.checkSelfPermission(
                activity!!,
                Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            if (permission != PackageManager.PERMISSION_GRANTED) {
                // We don't have permission so prompt the user
                ActivityCompat.requestPermissions(
                    activity,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
                )
            }
        }

        fun requestPermission(activity: Activity) {
            try {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                    val READ_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.READ_EXTERNAL_STORAGE
                    )

                    val WRITE_EXTERNAL_STORAGE = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE
                    )

                    val CAMERA = ContextCompat.checkSelfPermission(
                        activity,
                        Manifest.permission.CAMERA
                    )



                    if (READ_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                        || WRITE_EXTERNAL_STORAGE != PackageManager.PERMISSION_GRANTED
                        || CAMERA != PackageManager.PERMISSION_GRANTED
                    ) {
                        ActivityCompat.requestPermissions(
                            activity, arrayOf(
                                Manifest.permission.READ_EXTERNAL_STORAGE,
                                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                                Manifest.permission.CAMERA
                            ), 1
                        )
                    }


                }


            } catch (ex: Exception) {
                Log.e("", ex.message.toString())
            }

        }

        fun hideKeyboard(activity: Activity?) {
            if (activity != null && activity.window != null
            ) {
                val imm = activity
                    .getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(
                    activity.window.decorView
                        .windowToken, 0
                )
            }
        }

        @SuppressLint("SimpleDateFormat")
        fun languageChangeDialog(activity: Activity, languageCode: String): String {
            var codeValue = ""
            var codeDesc = ""
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.language_setting)
            dialog.setCancelable(true)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT


            val banglaLan = dialog.findViewById(R.id.languageBangla) as RadioButton
            val engLan = dialog.findViewById(R.id.languageEnglish) as RadioButton
            val btnCancel = dialog.findViewById(R.id.lanbtnCancel) as Button
            val btnSubtmit = dialog.findViewById(R.id.lanbtnSubtmit) as Button
            val tv_recharge_success_message =
                dialog.findViewById(R.id.tv_recharge_success_message) as TextView

            if (TextContants.banglaLanguageCode.equals(languageCode)) {
                btnCancel.setText(R.string.cancel_bangla)
                // btnSubtmit.setText(R.string.submit_bangla)
                // banglaLan.setText(R.string.bangla_bangla)
                //engLan.setText(R.string.english_bangla)
                //   tv_recharge_success_message.setText(R.string.please_select_language_bangla)
            }


            // Null Check and Auto Refresh Option List//

//        atmInfoViewModel.readLanguage.observe(this) { optionInfo ->
//
//            val sdf = SimpleDateFormat("dd/MM/yyyy")
//            val currentDate = sdf.format(Date())
//
//            if (optionInfo.isEmpty() || optionInfo == null) {
//                val optionsModel =
//                    OptionsModel(
//                        0,
//                        TextContants.englishLanguageCode,
//                        TextContants.englishLanguageCodeDes,
//                        TextContants.languageHardCode,
//                        "Y",
//                        currentDate
//                    )
//                atmInfoViewModel.insertAllOption(optionsModel)
//                //Log.e("", optionsModel.hardCode.toString())
//
//            } else {
//
//                for (i in optionInfo) {
//                    id = i.id
//                    //globalVariable.languageCode = lancode
//                }
//            }
//
//        }


// Null Check and Auto Refresh ATM List//

// Language radio Button Check by database //
            if (languageCode == "ENG") {
                engLan.isChecked = true
            } else {
                banglaLan.isChecked = true
            }
// Language radio Button Check by database //


            engLan.setOnClickListener {
                banglaLan.isChecked = false
                engLan.isChecked
                codeValue = "ENG"
                codeDesc = "English"
            }

            banglaLan.setOnClickListener {
                engLan.isChecked = false
                banglaLan.isChecked
                codeValue = "BAN"
                codeDesc = "Bangla"

            }



            btnCancel.setOnClickListener {
                dialog.cancel()
            }

            btnSubtmit.setOnClickListener {
                val sdf = SimpleDateFormat("dd/MM/yyyy")
                val currentDate = sdf.format(Date())
                //   val upadteoptionsModel =  OptionsModel(id, codeValue, codeDesc, "LAN", "Y", currentDate)
                //  atmInfoViewModel.updateLanguage(upadteoptionsModel)
                //  reload()
                dialog.cancel()


            }


            dialog.show()
            dialog.window!!.attributes = lp

            return codeValue
        }



    }


}