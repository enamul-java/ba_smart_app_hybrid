package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.screen

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.widget.Toolbar
import eraapps.bankasia.bdinternetbanking.apps.util.CustomAppCompatActivity
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants

class SafetyTipsActivity : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var iv_header_logout: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var tv_safety_tips: TextView
    private lateinit var safety_tips: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_safety_tips)

        globalVariable = this.applicationContext as GlobalVariable
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        tv_safety_tips = findViewById(R.id.tv_safety_tips)
        safety_tips = findViewById(R.id.safety_tips)

        iv_header_logout.visibility = View.INVISIBLE


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
            val intent = Intent(this, WelcomeDashboardActivity::class.java)
            intent.putExtra("LAN", "LAN")
            CustomActivityClear.doClearActivity(intent,this)
            startActivity(intent)
        } else {
            val intent = Intent(this, LoginActivity::class.java)
            CustomActivityClear.doClearActivity(intent, this)
        }

    }

    private fun fontset() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.setText(R.string.lb_safety_tips_bangla)
            tv_safety_tips.setText(R.string.lb_safety_tips_bangla)
            safety_tips.setText(R.string.lb_safety_tips_details_text_bangla)
        } else {
            toolbar_title.setText(R.string.lb_safety_tips)
            tv_safety_tips.setText(R.string.lb_safety_tips)
            safety_tips.setText(R.string.lb_safety_tips_details_text)
        }
    }
}