package eraapps.bankasia.bdinternetbanking.apps.util

import android.content.Context
import android.util.DisplayMetrics
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity

class ScreenUtil {

    companion object{
         fun getScreenHeight(context: Context): Int {
            val wm = context.getSystemService(AppCompatActivity.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val metrics = DisplayMetrics()
            display.getMetrics(metrics)
            val height = metrics.heightPixels
            return height
        }
         fun getScreenWidth(context: Context): Int {
            val wm = context.getSystemService(AppCompatActivity.WINDOW_SERVICE) as WindowManager
            val display = wm.defaultDisplay
            val metrics = DisplayMetrics()
            display.getMetrics(metrics)
            val width = metrics.widthPixels
            return width
        }

        fun getScreenDiagonally(activity: AppCompatActivity):String {
            val metrics = DisplayMetrics()
            var str =""
            activity.windowManager.defaultDisplay.getMetrics(metrics)

            val yInches = metrics.heightPixels / metrics.ydpi
            val xInches = metrics.widthPixels / metrics.xdpi
            val diagonalInches = Math.sqrt((xInches * xInches + yInches * yInches).toDouble())
            if (diagonalInches > 6.7) {
                str = "BIG"
            }
            else if ((diagonalInches > 5.5) and (diagonalInches <=  6.7)) {
                str =  "MEDIUM"
            }
            else{
                str = "SMALL"
            }

            return  str
        }

    }

}