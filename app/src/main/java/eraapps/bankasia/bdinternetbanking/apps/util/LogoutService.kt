package eraapps.bankasia.bdinternetbanking.apps.util

import android.app.Service
import android.content.Intent
import android.os.CountDownTimer
import android.os.IBinder
import android.util.Log


class LogoutService : Service() {
     var timer: CountDownTimer?=null


    override fun onCreate() {
        super.onCreate()
        timer = object : CountDownTimer(1 * 60 * 1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                //Some code
                Log.v("Autologout-->", "Service Started")
            }

            override fun onFinish() {
                Log.v("Autologout-->", "Call Logout by Service")
                // Code for Logout
                stopSelf()
            }
        }
    }


    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
}