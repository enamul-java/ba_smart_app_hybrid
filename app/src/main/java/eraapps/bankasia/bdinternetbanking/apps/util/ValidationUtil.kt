package eraapps.bankasia.bdinternetbanking.apps.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Context.VIBRATOR_SERVICE
import android.content.Intent
import android.graphics.Bitmap
import android.media.AudioAttributes
import android.media.MediaPlayer
import android.net.Uri
import android.os.Build
import android.os.CountDownTimer
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.provider.MediaStore
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import eraapps.bankasia.bdinternetbanking.apps.R
import okhttp3.internal.and
import java.text.DecimalFormat
import java.text.Format
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Formatter
import java.util.Locale
import java.util.regex.Pattern


@Suppress("DEPRECATION")
class ValidationUtil {

    companion object {

        fun editTextValidation(str: String): Boolean {
            return !(str.isEmpty() || str.isEmpty() || str == "")
        }

        fun commaSeparateAmount(str: String): String {
            var str = str
            var rValue = "0.00"
            var amount = 0.00
            try {
                if (str.isNotEmpty()) {
                    val regx = "[-+^:*#_/, %@$@!*]"
                    str = str.replace(regx.toRegex(), "")
                    amount = str.toDouble()
                    val f: Format = DecimalFormat.getNumberInstance(Locale("en", "IN"))
                    (f as DecimalFormat).isDecimalSeparatorAlwaysShown = true
                    f.minimumFractionDigits = 2
                    rValue = f.format(amount)
                } else {
                    rValue = "0.00"
                }
            } catch (e: Exception) {
                rValue = "0.00"
            }
            return "$rValue BDT"
        }

        fun commaSeparateAmountUSD(str: String): String {
            var str = str
            var rValue = "0.00"
            var amount = 0.00
            try {
                if (str.isNotEmpty()) {
                    val regx = "[-+^:*#_/, %@$@!*]"
                    str = str.replace(regx.toRegex(), "")
                    amount = str.toDouble()
                    val f: Format = DecimalFormat.getNumberInstance(Locale("en", "IN"))
                    (f as DecimalFormat).isDecimalSeparatorAlwaysShown = true
                    f.minimumFractionDigits = 2
                    rValue = f.format(amount)
                } else {
                    rValue = "0.00"
                }
            } catch (e: Exception) {
                rValue = "0.00"
            }
            return "$rValue USD"
        }

        fun commaSeparateAmountStatment(str: String): String {
            var str = str
            var rValue = "0.00"
            var amount = 0.00
            try {
                if (str.isNotEmpty()) {
                    val regx = "[-+^:*#_/, %@$@!*]"
                    str = str.replace(regx.toRegex(), "")
                    amount = str.toDouble()
                    val f: Format = DecimalFormat.getNumberInstance(Locale("en", "IN"))
                    (f as DecimalFormat).isDecimalSeparatorAlwaysShown = true
                    f.minimumFractionDigits = 2
                    rValue = f.format(amount)
                } else {
                    rValue = "0.00"
                }
            } catch (e: Exception) {
                rValue = "0.00"
            }
            return rValue
        }


        fun replacecomma(str: String): String {
            var rValue = "0.00"
            try {
                if (str.isNotEmpty()) {
                    val regx = "[-+^:*#_/, %@$@!*N/A]"
                    rValue = str.replace(regx.toRegex(), "")
                } else {
                    rValue = "0.00"
                }

            } catch (e: Exception) {
                rValue = "0.00"
                e.printStackTrace()
            }
            return rValue
        }

        fun getNullCheck(str: String): String {
            var rValue = ""
            rValue = if (str.isEmpty() || str === "") {
                "    "
            } else {
                str
            }
            return rValue
        }

        fun isNumeric(str: String): Boolean {
            var b = false
            try {
                if (str != "") {
                    str.toDouble()
                    b = true
                }
            } catch (e: NumberFormatException) {
                b = false
            }
            return b
        }

        fun isNumericStartZero(str: String): Boolean {
            var b = false
            b = try {
                if (str != "") {
                    val num = str.toInt()
                    num.toString().startsWith("01") && num.toString().length == 11
                } else {
                    false
                }
            } catch (e: NumberFormatException) {
                false
            }
            return b
        }

        fun getRetrofit_NullCheck(s: String?): String {
            var rValue = ""
            rValue = if (null == s || s.isEmpty() || s.endsWith("null") || s === "") {
                ""
            } else {
                s
            }
            return rValue
        }

        fun printNumbers(str: String): Double {
            var digit = 0.0
            digit = try {
                if (str != "") {
                    str.toDouble()
                } else {
                    0.0
                }
            } catch (e: NumberFormatException) {
                0.0
            }
            return digit
        }

        fun printNumbersCheck(acNo: Int, min: Int, max: Int): Boolean {
            return acNo in min..max
        }

        fun printCardNumbers(cardNo: Int): Boolean {
            return cardNo in 15..19
        }

        fun amountCheck(balnce: String, inputbal: String): Boolean {
            var b = true
            var oldbal = 0.0
            var inbal = 0.0
            try {
                if (balnce !== "" && inputbal !== "") {
                    oldbal = balnce.toDouble()
                    inbal = inputbal.toDouble()
                    b = if (oldbal < inbal) {
                        true
                    } else inbal < 1
                    //b = true;
                }
            } catch (e: NumberFormatException) {
                b = true
            }
            Log.d("balnce+inputbal", balnce + inputbal)
            return b
        }

        @SuppressLint("SimpleDateFormat")
        fun getTransactionCurrentDate(): String? {
            val dNow = Date()
            val ft = SimpleDateFormat("MMM dd',' 'at' hh:mm a")
            return ft.format(dNow)
        }

        @SuppressLint("SimpleDateFormat")
        fun getCurrentDate(): String {
            val dNow = Date()
            val ft = SimpleDateFormat("MM/dd/yyyy")
            return ft.format(dNow)
        }

        fun stringUnderline(text: String, textView: TextView) {
            val content = SpannableString(text)
            content.setSpan(UnderlineSpan(), 0, content.length, 0)
            textView.setText(content)
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

        fun getEmailValidate(userId: String): String {
            var rValue = ""
            try {
                //  val emailregex =  "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$"

                val EMAIL_ADDRESS_PATTERN = Pattern.compile(
                    "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                            "\\@" +
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                            "(" +
                            "\\." +
                            "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                            ")+"
                )
                val b = EMAIL_ADDRESS_PATTERN.matcher(userId).matches()
                ///  val b = userId.matches(emailregex.toRegex())
                if (!b) {
                    rValue = "1"
                } else if (b == true) {
                    rValue = "0"
                }
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
                println(e.message)
            }
            return rValue
        }

        @SuppressLint("SimpleDateFormat")
        fun getDatevalidate(strDate: String): Boolean {
            return if (strDate.trim { it <= ' ' } == "") {
                true
            } else {
                val sdfrmt = SimpleDateFormat("dd/MM/yyyy")
                sdfrmt.isLenient = false
                try {
                    val javaDate = sdfrmt.parse(strDate)
                } catch (e: ParseException) {
                    return false
                }
                true
            }
        }

        fun amounttoPercentage(pamount: String, percentage: String): String {
            var value = "0"
            var newPamount = 0.0
            var newPercentage = 0.0
            try {
                if (pamount != "" && percentage != "") {
                    newPamount = pamount.toDouble()
                    newPercentage = percentage.toDouble()
                    val test = (newPercentage * newPamount / 100).toString()
                    val formatter = Formatter()
                    formatter.format("%.2f", test.toDouble())
                    //System.out.println("Double upto 2 decimal places: " + formatter.toString());
                    value = formatter.toString()
                }
            } catch (e: java.lang.NumberFormatException) {
                value = "0"
            }
            return value
        }

        fun crc16(buffer: ByteArray): Int {
            var crc = 0xFFFF
            for (b in buffer) {
                crc = crc ushr 8 or (crc shl 8) and 0xffff
                crc = crc xor (b and 0xff) //byte to int, trunc sign
                crc = crc xor (crc and 0xff shr 4)
                crc = crc xor (crc shl 12 and 0xffff)
                crc = crc xor (crc and 0xFF shl 5 and 0xffff)
            }
            crc = crc and 0xffff
            return crc
        }

        fun goToUrl(activity: Activity, url: String) {
            val uriUrl = Uri.parse(url)
            val launchBrowser = Intent(Intent.ACTION_VIEW, uriUrl)
            activity.startActivity(launchBrowser)
        }


        fun mobileNumberCheeck(mobile: String): Boolean {
            var b = true
            if (mobile != "") {
                if (mobile.length > 11 || mobile.length < 11) {
                    b = false
                } else if (mobile.length == 11) {
                    b = true
                }
            }
            return b
        }

        fun cardNoMask(text: String?): String? {
            val MASKCARD = "$1******$2"
            val PATTERNCARD = Pattern.compile("([0-9]{6})[0-9]{0,9}([0-9]{4})")
            val matcher = PATTERNCARD.matcher(text)
            return if (matcher.find()) {
                matcher.replaceAll(MASKCARD)
            } else text
        }


        fun emailAddressCheeck(email: String): Boolean {
            var b = true
            if (email != "") {
                val e = getEmailValidate(email);
                if ("1".equals(e)) {
                    b = false
                } else if ("0".equals(e)) {
                    b = true
                }
            }
            return b
        }




        fun convertEnToBn(data: String): String {
            return data
                .replace("0".toRegex(), "\u09E6")
                .replace("1".toRegex(), "\u09E7")
                .replace("2".toRegex(), "\u09E8")
                .replace("3".toRegex(), "\u09E9")
                .replace("4".toRegex(), "\u09EA")
                .replace("5".toRegex(), "\u09EB")
                .replace("6".toRegex(), "\u09EC")
                .replace("7".toRegex(), "\u09ED")
                .replace("8".toRegex(), "\u09EE")
        }


        fun theMonth(month: Int): String {
            return MONTH[month]
        }

        private val MONTH = arrayOf(
            "Month",
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

        fun String.capitalizeFirstLetter(): String {
            return this.substring(0, 1).uppercase() + this.substring(1)
        }

        fun cardDateFormate(dateString: String): String {
            var rValue = ""
            try {
                val dt = SimpleDateFormat("dd/MM/yyyy")
                val date = dt.parse(dateString)
                val dt1 = SimpleDateFormat("MMyy")
                println(dt1.format(date))
                rValue = dt1.format(date)
            } catch (e: java.lang.Exception) {
               e.printStackTrace()
            }
            return rValue
        }

        fun share(bitmap: Bitmap, activity: Activity) {
            val pathofBmp = MediaStore.Images.Media.insertImage(
                activity.contentResolver,
                bitmap, "title", null
            )
            val uri = Uri.parse(pathofBmp)
            val shareIntent = Intent(Intent.ACTION_SEND)
            shareIntent.type = "image/*"
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "SMART App")
            shareIntent.putExtra(Intent.EXTRA_TEXT, "")
            shareIntent.putExtra(Intent.EXTRA_STREAM, uri)
            activity.startActivity(Intent.createChooser(shareIntent, "Voucher"))
        }

        fun qrScanSound(activity: Activity) {
            try {
                val vib = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                    val vibratorManager =
                        activity.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
                    vibratorManager.defaultVibrator
                } else {
                    @Suppress("DEPRECATION")
                    activity.getSystemService(VIBRATOR_SERVICE) as Vibrator
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vib.vibrate(
                        VibrationEffect.createOneShot(
                            1000,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    )
                } else {
                    //deprecated in API 26
                    vib.vibrate(1000)
                }

                val mMediaPlayer = MediaPlayer.create(activity, R.raw.beep)
                mMediaPlayer.setVolume(1.0f, 1.0f)
                mMediaPlayer.setAudioAttributes(
                    AudioAttributes.Builder()
                        .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                        .build()
                )

                object : CountDownTimer(700, 1000) {
                    override fun onTick(millisUntilFinished: Long) {
                        mMediaPlayer.start()
                    }

                    override fun onFinish() {
                        mMediaPlayer.stop()
                    }
                }.start()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }


}