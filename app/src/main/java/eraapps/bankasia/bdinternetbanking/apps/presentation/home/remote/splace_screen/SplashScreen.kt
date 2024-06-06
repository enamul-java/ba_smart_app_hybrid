package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.splace_screen

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.BuildConfig
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model.OptionsViewModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable
import java.text.SimpleDateFormat
import java.util.*


@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    private lateinit var handler: Handler
    private lateinit var runnable: Runnable
    private lateinit var timer: Timer
    private lateinit var iv_logo: ImageView
    private lateinit var tv_version_name: TextView


    private lateinit var globalVariable: GlobalVariable

    val optionsViewModel: OptionsViewModel by viewModels()
    private lateinit var optionsEntities: ArrayList<OptionsEntity>


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        iv_logo = findViewById(R.id.iv_logo)
        // iv_logo.animate().rotation(180f).setDuration(2000).start()
        tv_version_name = findViewById(R.id.tv_version_name)
        tv_version_name.text = "Version:- " + BuildConfig.VERSION_NAME

        globalVariable = this.applicationContext as GlobalVariable

        val zoomAnimation: Animation =
            AnimationUtils.loadAnimation(baseContext, R.anim.zoom)
        iv_logo.startAnimation(zoomAnimation)


        optionsViewModel.getLanguage()
        observeViewModel()


        handler = Handler(Looper.getMainLooper())
        runnable = Runnable {
            timer.cancel()
            val intent = Intent(this@SplashScreen, LoginActivity::class.java)
            startActivity(intent)
            finish()
        }

        timer = Timer()
        timer.schedule(object : TimerTask() {
            override fun run() {
                handler.post(runnable)
            }
        }, 2000, 2000)


    }

    @SuppressLint("SuspiciousIndentation")
    private fun observeViewModel() {

        optionsViewModel.optionsResponse
            .observe(this) {

                optionsEntities = it as ArrayList<OptionsEntity>
                            if (optionsEntities.size == 0) {
                                dataInsert()

                            } else {
                                for (i in optionsEntities) {
                                    globalVariable.languageCode = i.code.toString()

                                }

                            }

            }

        optionsViewModel.errorResponse
            .observe(this) {
               // pDialog.dismiss()
                //CustomAlert.showErrorMessage(requireActivity(), it.message, globalVariable.languageCode)

                Log.e("QUICK", it.message )
            }
    }

    private fun dataInsert() {
        val sdf = SimpleDateFormat("dd/MM/yyyy")
        val currentDate = sdf.format(Date())

        val optionsEntity =
            OptionsEntity(0, "ENG", "English", "LAN", "Y", currentDate)
        optionsViewModel.insertLanguage(optionsEntity)
        if (optionsEntities.size != 0) {
            globalVariable.languageCode = optionsEntity.code.toString()
        }
    }

}