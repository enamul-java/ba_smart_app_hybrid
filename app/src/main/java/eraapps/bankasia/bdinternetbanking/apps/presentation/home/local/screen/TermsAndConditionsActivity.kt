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
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.screen.NewUserRequestActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.dashboard.screen.WelcomeDashboardActivity
import eraapps.bankasia.bdinternetbanking.apps.util.CustomActivityClear
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants

class TermsAndConditionsActivity : CustomAppCompatActivity() {
    private lateinit var globalVariable: GlobalVariable

    private lateinit var toolbar: Toolbar
    private lateinit var toolbar_layout: LinearLayout
    private lateinit var iv_header_back: ImageView
    private lateinit var iv_header_logout: ImageView
    private lateinit var toolbar_title: TextView
    private lateinit var tv_terms_condition: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_terms_and_conditions)
        globalVariable = this.applicationContext as GlobalVariable
        toolbar = findViewById(R.id.toolbar)
        toolbar_layout = findViewById(R.id.toolbar_layout)
        iv_header_back = toolbar.findViewById(R.id.iv_header_back)
        toolbar_title = toolbar.findViewById(R.id.toolbar_title)
        iv_header_logout = toolbar.findViewById(R.id.iv_header_logout)
        tv_terms_condition = findViewById(R.id.tv_terms_condition)

        iv_header_back.setOnClickListener {
            goBack()
        }

        iv_header_logout.visibility = View.INVISIBLE

        this.onBackPressedDispatcher
            .addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    goBack()
                }
            }
            )

        fontSet()
    }

    private fun goBack() {

        val id = intent.getStringExtra("W")
        val idr = intent.getStringExtra("R")
        when {
            id == "W" -> {
                val intent = Intent(this, WelcomeDashboardActivity::class.java)
                intent.putExtra("LAN", "LAN")
                CustomActivityClear.doClearActivity(intent, this)
                startActivity(intent)
            }

            idr == "R" -> {
                val intent = Intent(this, NewUserRequestActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }

            else -> {
                val intent = Intent(this, LoginActivity::class.java)
                CustomActivityClear.doClearActivity(intent, this)
            }
        }

    }

    private fun fontSet() {
        if (TextContants.banglaLanguageCode == globalVariable.languageCode) {
            toolbar_title.setText(R.string.terms_condition_bangla)
            tv_terms_condition.setText(R.string.bangla_terms_conditon)
        } else {
            toolbar_title.setText(R.string.terms_condition_eng)
            tv_terms_condition.setText(R.string.terms_conditions_english)
        }
    }
}