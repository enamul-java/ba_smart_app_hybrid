package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants

class AboutUs : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var iv_header_logout: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var about_label: TextView
    private lateinit var lb_about_text: TextView

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        globalVariable = this.applicationContext as GlobalVariable
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        about_label = findViewById(R.id.about_label)
        lb_about_text = findViewById(R.id.lb_about_text)


        iv_header_logout.visibility = View.INVISIBLE



        lb_about_text.text = "Bank Asia SMART App (Version " + BuildConfig.VERSION_NAME + getString(
            R.string.lb_about_text)


        iv_header_back.setOnClickListener {
         goBack()
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
            val intent = Intent(this,WelcomeDashboardActivity::class.java)
            intent.putExtra("LAN", "LAN")
            CustomActivityClear.doClearActivity(intent,this)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

    }

    @SuppressLint("SetTextI18n")
    private fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.setText(R.string.about_us_bangla)
            lb_about_text.text = "স্মার্ট অ্যাপ (সংস্করণ " + BuildConfig.VERSION_NAME + getString(
                R.string.lb_about_text_bangla)
            //lb_about_text.setText(R.string.smart_app_details)
            about_label.setText(R.string.about_us_bangla)
        } else {
            toolbar_title.setText(R.string.about_us)
        }
    }
}