package eraapps.bankasia.bdinternetbanking.apps.util

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity
import java.util.*

open class AutoLogoutCompatActivity : AppCompatActivity(), View.OnTouchListener,
    LogOutTimerUtil.LogOutListener {

    private var timeset: Long = 50000
    private var noOfClicks: Int = 0
    private var isActive: Boolean = false
    private var isPause: Boolean = false
    private lateinit var pauseTime: Date
    private lateinit var currentTime: Date
    var pasusMillisecon: Long = 0

    private lateinit var globalVariable: GlobalVariable


//    private lateinit var ourDetector: GestureDetectorCompat


    public override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //  ourDetector = GestureDetectorCompat(this, this)
        globalVariable = this.applicationContext as GlobalVariable

    }


    //begin keyboard hide
    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        return false
    }

    override fun dispatchTouchEvent(ev: MotionEvent): Boolean {
        val v = currentFocus
        if (v != null && (ev.action == MotionEvent.ACTION_UP || ev.action == MotionEvent.ACTION_MOVE)
            && v is EditText
            && !v.javaClass.name.startsWith("android.webkit.")
        ) {
            val scrcoords = IntArray(2)
            v.getLocationOnScreen(scrcoords)
            val x = ev.rawX + v.getLeft() - scrcoords[0]
            val y = ev.rawY + v.getTop() - scrcoords[1]
            if (x < v.getLeft() || x > v.getRight() || y < v.getTop() || y > v.getBottom()
            ) hideKeyboard(this)
        }
        return super.dispatchTouchEvent(ev)
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

    /*   override fun onTouchEvent(event: MotionEvent): Boolean {
          return if (ourDetector.onTouchEvent(event)) {
              true
          } else {
              super.onTouchEvent(event)
          }
      }

       override fun onDown(event: MotionEvent): Boolean {
           startDetection()
           return true
       }

       override fun onFling(
           event1: MotionEvent,
           event2: MotionEvent,
           velocityX: Float,
           velocityY: Float
       ): Boolean {
           startDetection()
           return true
       }

       override fun onLongPress(event: MotionEvent) {
           startDetection()
       }

      override fun onScroll(
           event1: MotionEvent,
           event2: MotionEvent,
           distanceX: Float,
           distanceY: Float
       ): Boolean {
           startDetection()
           return true
       }

      override fun onShowPress(event: MotionEvent) {
          startDetection()
      }

      override fun onSingleTapUp(event: MotionEvent): Boolean {
          startDetection()
          return true
      }
   */
    //a method to start detection
    fun startDetection() {
        //set the user's active status to true then increase the number of clicks by 1
        isActive = true
        noOfClicks++

        //dont listen more than once
        //simply do nothing :)
        if (noOfClicks > 1) {

        } else if (noOfClicks == 1) {
            startListener()
        }
    }

    fun startListener() {
        //check the user's activeness after a specified time in milliseconds
        Handler(Looper.getMainLooper()).postDelayed({
            //displayDialog()
            checkActiveness()
        }, timeset)
    }

    fun checkActiveness() {
        /*set the active status to false deliberately
        and wait for 5 seconds to see if it will change to true
        */
        isActive = false
        Handler(Looper.getMainLooper()).postDelayed({

            displayDialog()
        }, 5000)
    }

    fun displayDialog() {
        //if the user is still inactive, show the dialog
        /*reset the number of clicks to zero to start
        the detection  incase the dialog is dismissed*/
        if (!isActive) {
            noOfClicks = 0
            this.runOnUiThread {
                val message = "Your Active Session is Expired! Please login Again."
                if (!(this).isFinishing) {
                    try {
                        CustomActivityClear.forceLogout(this, message)
                    } catch (e: WindowManager.BadTokenException) {
                        e.printStackTrace()
                    }
                }
            }
            // CustomActivityClear.forceLogout(this, "ENG")
        } else {
            /*if active, set the clicks to zero also
            to allow the detection to start as the user clicks/taps the screen
             */
            noOfClicks = 0
        }
    }

    override fun doLogout() {
        this.runOnUiThread {
            val message = "Your Active Session is Expired! Please login Again."
            if (!(this).isFinishing) {
                try {
                    CustomActivityClear.forceLogout(this, message)
                } catch (e: WindowManager.BadTokenException) {
                    e.printStackTrace()
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        LogOutTimerUtil.startLogoutTimer(this, this)

    }


    override fun onUserInteraction() {
        super.onUserInteraction()
        LogOutTimerUtil.startLogoutTimer(this, this)

    }


    override fun onPause() {
        super.onPause()
        isPause = true
        pauseTime = Calendar.getInstance().getTime()
        pasusMillisecon = System.currentTimeMillis()
    }

    override fun onResume() {
        super.onResume()
        if (isPause) {
            isPause = false
            currentTime = Calendar.getInstance().time
            val difm = System.currentTimeMillis() - pasusMillisecon

            val intent = Intent(this, LoginActivity::class.java)

            if (!(this).isFinishing) {
                try {
                    CustomActivityClear.logoutExpireTime(
                        intent,
                        this,
                        difm,
                        LogOutTimerUtil.LOGOUT_TIME
                    )
                } catch (e: WindowManager.BadTokenException) {
                    e.printStackTrace()
                }
            }

        }

    }

    fun showErrorMessage(engMessage:String,bngMessage:String){
        CustomAlert.showErrorMessage(
            this,
            engMessage,
            bngMessage,
            globalVariable.languageCode
        )
    }


}