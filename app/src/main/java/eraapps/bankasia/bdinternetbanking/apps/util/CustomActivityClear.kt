package eraapps.bankasia.bdinternetbanking.apps.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity


class CustomActivityClear {

    companion object {
        //

        @SuppressLint("StaticFieldLeak")
        private lateinit var activity: Activity
        private lateinit var alertDialog: Dialog
        fun doClearActivity(intent: Intent, activity: Activity) {
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            activity.startActivity(intent)
            activity.finish()

        }

        fun progressDialog(myActivity: Activity, languagecode: String): Dialog {
            activity = myActivity
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.loading_dialog)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT

            // val loadingAnim = dialog.findViewById<LoadingAnimation>(R.id.loadingAnim);


            dialog.show()
            dialog.window!!.attributes = lp

            return dialog
        }

        fun dismiss() {
            alertDialog.dismiss()
        }


        fun progressDialog1(activity: Activity, languagecode: String): Dialog {

            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.loading_dialog)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT

            //    val loadingAnim = dialog.findViewById<LoadingAnimation>(R.id.loadingAnim);


            dialog.show()
            dialog.window!!.attributes = lp

            return dialog

        }

        fun callDialog(activity: Activity, languagecode: String) {
            // For Call
            val callIntent = Intent(Intent.ACTION_CALL)
            callIntent.data = Uri.parse("tel:" + "16205")
            callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK

            //For Mail

            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val reg_layout: View =
                inflater.inflate(R.layout.call_diallog, null)

            val btn_call = reg_layout.findViewById<Button>(R.id.btn_call)
            val ivClose = reg_layout.findViewById<ImageView>(R.id.ivClose)
            val tv_mail_send = reg_layout.findViewById<TextView>(R.id.tv_mail_send)


            val welcome_to = reg_layout.findViewById<TextView>(R.id.welcome_to)
            val bdbl_digital_bank_help_center =
                reg_layout.findViewById<TextView>(R.id.bank_asia_call_center)
            val tv_call_message = reg_layout.findViewById<TextView>(R.id.tv_call_message)
            val tv_call_action = reg_layout.findViewById<TextView>(R.id.tv_call_action)
            val or_send_an_email_to = reg_layout.findViewById<TextView>(R.id.or_send_an_email_to)


            dialog.setView(reg_layout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

            if (TextContants.banglaLanguageCode.equals(languagecode)) {
                welcome_to.setText(R.string.welcome_welcome_bangla)
                bdbl_digital_bank_help_center.setText(R.string.bank_asia_call_center_bangla)
                tv_call_message.setText(R.string.you_are_able_to_call_this_number_bangla)
                tv_call_action.setText(R.string._16205_bangla)
                or_send_an_email_to.setText(R.string.or_send_an_email_to_bangla)
                btn_call.setText(R.string.call_now_bangla)
            }

            btn_call.setOnClickListener {
                activity.startActivity(callIntent)
            }

            ivClose.setOnClickListener {
                alertDialog.dismiss()
            }

            tv_mail_send.setOnClickListener {

                val emailIntent = Intent(
                    Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto", "contact.center@bankasia-bd.com", null
                    )
                )
                emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
                emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
                emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
                if (emailIntent.resolveActivity(activity.packageManager) != null) {
                    activity.startActivity(Intent.createChooser(emailIntent, "Send email..."))
                }

            }

            alertDialog.show()

        }

        fun logoutExpireTime(intent: Intent, activity: Activity, diffInMs: Long, logoutTime: Int) {

            //Log.e("erra logoutExpireTime","indie****")
            // Log.e("idl exeper **","logout****"+ logoutTime)
            //  Log.e("idl diffInMs-->",diffInMs.toString())

            if (diffInMs > logoutTime) {
                val message = "Your Active Session is Expired! Please login again."
                forceLogout(activity, message)

            }


        }

        fun forceLogout(activity: Activity, message: String) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.force_logout_dialog)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT


            val title_text = dialog.findViewById(R.id.title_text) as TextView
            val confirm_button = dialog.findViewById(R.id.confirm_button) as Button



            title_text.text = message


            confirm_button.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                doClearActivity(intent, activity)
            }


            dialog.show()
            dialog.window!!.attributes = lp
        }

        fun forceLogoutPassword(activity: Activity, message: String) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.force_logout_dialog)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.MATCH_PARENT


            val title_text = dialog.findViewById(R.id.title_text) as TextView
            val title_text1 = dialog.findViewById(R.id.title_text1) as TextView
            val confirm_button = dialog.findViewById(R.id.confirm_button) as Button
            val custom_image = dialog.findViewById(R.id.custom_image) as ImageView



            title_text.text = message
            title_text1.text = "Successful"
            custom_image.setImageResource(R.drawable.icon_success)


            confirm_button.setOnClickListener {
                val intent = Intent(activity, LoginActivity::class.java)
                doClearActivity(intent, activity)
            }


            dialog.show()
            dialog.window!!.attributes = lp
        }


    }
}