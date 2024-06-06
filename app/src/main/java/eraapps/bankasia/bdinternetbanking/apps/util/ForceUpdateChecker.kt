package eraapps.bankasia.bdinternetbanking.apps.util

import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.FirebaseRemoteConfigSettings
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R


class ForceUpdateChecker {
    companion object {
        fun playStoreVersionCheck(activity: Activity, languageCode: String) {

            val remoteConfig: FirebaseRemoteConfig = FirebaseRemoteConfig.getInstance()

            val remoteConfigDefaults: MutableMap<String, Any> = HashMap()
            remoteConfigDefaults["KEY_UPDATE_REQUIRED"]
            remoteConfigDefaults["KEY_CURRENT_VERSION"]
            remoteConfigDefaults["PRIORITY_LEVEL"]
            remoteConfigDefaults["MESSAGE"]
            remoteConfigDefaults["HEADER_TITLE"]
            remoteConfigDefaults["KEY_UPDATE_URL"]

            remoteConfig.setDefaultsAsync(remoteConfigDefaults)

            //Setting Developer Mode enabled to fast retrieve the values
            remoteConfig.setConfigSettingsAsync(
                FirebaseRemoteConfigSettings.Builder().build()
            )


            remoteConfig.fetch(30) // fetch every minutes
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        remoteConfig.fetchAndActivate()
                        if (remoteConfig.getBoolean("KEY_UPDATE_REQUIRED")) {
                            val currentVersion: String =
                                remoteConfig.getString("KEY_CURRENT_VERSION")
                            val updateUrl: String = remoteConfig.getString("KEY_UPDATE_URL")
                            val priority: String = remoteConfig.getString("PRIORITY_LEVEL")
                            val message: String = remoteConfig.getString("MESSAGE")
                            val title: String = remoteConfig.getString("HEADER_TITLE")

                            Log.d(
                                "database-->",
                                remoteConfig.getBoolean("KEY_UPDATE_REQUIRED").toString()
                            )
                            Log.d("database-->", currentVersion)
                            Log.d("database-->", priority)
                            Log.d("database-->", BuildConfig.VERSION_NAME)
                            Log.d("database-->", updateUrl)
                            Log.d("database-->", message)
                            Log.d("database-->", title)

                            val words = arrayOf("5", "2")

                            if (words.contains(priority) && BuildConfig.VERSION_NAME != currentVersion) {
                                versionUpdateDialog(
                                    activity,
                                    languageCode,
                                    updateUrl,
                                    title,
                                    message,
                                    currentVersion,
                                    priority
                                )
                            } else if (priority == "1") {
                                versionUpdateDialog(
                                    activity,
                                    languageCode,
                                    updateUrl,
                                    title,
                                    message,
                                    currentVersion,
                                    priority
                                )
                            }


                        }
                    } else {
                        Log.d("database-->", "title")
                    }
                }

        }

        fun versionUpdateDialog(
            activity: Activity,
            languageCode: String,
            updateUrl: String,
            title: String,
            message: String,
            currentVersion: String,
            priority: String,
        ) {
            val dialog = AlertDialog.Builder(activity).setCancelable(false)
            val inflater = LayoutInflater.from(activity)
            val reg_layout: View = inflater.inflate(R.layout.apps_version_update_dialog, null)
            val btn_cancel = reg_layout.findViewById<Button>(R.id.btn_cancel)
            val btn_submit = reg_layout.findViewById<Button>(R.id.btn_submit)
            val btn_ok = reg_layout.findViewById<Button>(R.id.btn_ok)
            val menu_icon = reg_layout.findViewById<ImageView>(R.id.menu_icon)
            val menu_item_description =
                reg_layout.findViewById<TextView>(R.id.menu_item_description)
            val menu_item_name = reg_layout.findViewById<TextView>(R.id.menu_item_name)
            val iv_close = reg_layout.findViewById<ImageView>(R.id.iv_close)
            dialog.setView(reg_layout)
            val alertDialog = dialog.create()
            alertDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))



            menu_item_name.text = title
            menu_item_description.text = message

            if (priority == "5" && BuildConfig.VERSION_NAME != currentVersion) {
                iv_close.visibility = View.GONE
                btn_cancel.visibility = View.GONE
            } else if (priority == "2" && BuildConfig.VERSION_NAME != currentVersion) {
                iv_close.visibility = View.VISIBLE
                btn_cancel.visibility = View.VISIBLE
                btn_ok.visibility = View.GONE
                btn_submit.visibility = View.VISIBLE
                iv_close.visibility = View.VISIBLE
                btn_cancel.visibility = View.VISIBLE
            } else if (priority == "1") {
                iv_close.visibility = View.VISIBLE
                btn_cancel.visibility = View.VISIBLE
                btn_ok.visibility = View.VISIBLE
                btn_submit.visibility = View.GONE
            }

            btn_submit.setOnClickListener {
                redirectStore(activity, updateUrl)
            }

            btn_cancel.setOnClickListener { alertDialog.cancel() }
            iv_close.setOnClickListener { alertDialog.cancel() }
            btn_ok.setOnClickListener { alertDialog.cancel() }

            if (TextContants.banglaLanguageCode == languageCode) {
                btn_cancel.setText(R.string.cancel_bangla)
                btn_submit.setText(R.string.update_bangla)
            } else {
                btn_cancel.setText(R.string.cancel)
                btn_submit.setText(R.string.update)
            }
            alertDialog.show()
        }

        private fun redirectStore(activity: Activity, updateUrl: String) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(updateUrl))
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            activity.startActivity(intent)
        }
    }


}