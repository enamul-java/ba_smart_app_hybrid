package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.screen

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.emon.raihan.dynamicutility.util.CustomDailog
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import eraapps.bankasia.bdinternetbanking.apps.util.ValidationUtil

class ContactUsActivity : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var iv_header_logout: ImageView
    private lateinit var toolbar_title: TextView

    private lateinit var tv_address: TextView
    private lateinit var tv_email: TextView
    private lateinit var tv_email_label: TextView
    private lateinit var tv_contact_16205: TextView
    private lateinit var tv_contact_oversease: TextView
    private lateinit var tv_customer_care: TextView

    private lateinit var iv_facebook: ImageView
    private lateinit var iv_instagram: ImageView
    private lateinit var iv_linkedin: ImageView
    private lateinit var iv_youtube: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contact_us)
        globalVariable = this.applicationContext as GlobalVariable
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        tv_address = findViewById(R.id.tv_address)
        tv_email = findViewById(R.id.tv_email)
        tv_customer_care = findViewById(R.id.tv_customer_care)
        tv_contact_16205 = findViewById(R.id.tv_contact_16205)
        tv_contact_oversease = findViewById(R.id.tv_contact_oversease)
        tv_email_label = findViewById(R.id.tv_email_label)
        iv_facebook = findViewById(R.id.iv_facebook)
        iv_instagram = findViewById(R.id.iv_instagram)
        iv_linkedin = findViewById(R.id.iv_linkedin)
        iv_youtube = findViewById(R.id.iv_youtube)

        iv_header_logout.visibility = View.INVISIBLE

        iv_header_back.setOnClickListener {
            goBack()
        }

        tv_email.setOnClickListener {
            val emailIntent = Intent(
                Intent.ACTION_SENDTO, Uri.fromParts(
                    "mailto", "contact.center@bankasia-bd.com", null
                )
            )
            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject")
            emailIntent.putExtra(Intent.EXTRA_TEXT, "Body")
            emailIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
            if (emailIntent.resolveActivity(this.packageManager) != null) {
                this.startActivity(Intent.createChooser(emailIntent, "Send email..."))
            }

        }
        tv_contact_16205.setOnClickListener {
            if (!checkCallPermission()) {
                getCallPermission()
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + "16205")
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(callIntent)
            }
        }
        tv_contact_oversease.setOnClickListener {
            if (!checkCallPermission()) {
                getCallPermission()
            } else {
                val callIntent = Intent(Intent.ACTION_CALL)
                callIntent.data = Uri.parse("tel:" + "8809617016205")
                callIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
                startActivity(callIntent)
            }
        }

        iv_facebook.setOnClickListener {
            ValidationUtil.goToUrl(this, "https://www.facebook.com/BankAsiaLimited")
        }
        iv_instagram.setOnClickListener { }
        iv_linkedin.setOnClickListener { }
        iv_youtube.setOnClickListener {
            ValidationUtil.goToUrl(this, "https://www.youtube.com/@BankAsiaLimitedOfficial")
        }

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goBack()
                }
            }
            )

        fontset()

    }

    private fun goBack() {
        val id = intent.getStringExtra("W")
        if (id == "W") {
            val intent = Intent(this, WelcomeDashboardActivity::class.java)
            intent.putExtra("LAN", "LAN")
            CustomActivityClear.doClearActivity(intent, this)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

    }

    private fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.setText(R.string.about_us_bangla)
            tv_address.setText(R.string.address_bangla)
            tv_email_label.setText(R.string.email_bangla)
            tv_customer_care.setText(R.string.customer_care_bangla)
        } else {
            toolbar_title.setText(R.string.contact_us)
            tv_address.setText(R.string.address)
            tv_email_label.setText(R.string.email)
            tv_customer_care.setText(R.string.customer_care)
        }
    }

    fun getCallPermission() {

        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val READ_CONTACTS = ContextCompat.checkSelfPermission(
                    this, Manifest.permission.CALL_PHONE
                )

                if (READ_CONTACTS != PackageManager.PERMISSION_GRANTED) {
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

    private fun checkCallPermission(): Boolean {
        val result =
            ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
        return result == PackageManager.PERMISSION_GRANTED
    }
}