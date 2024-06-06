package eraapps.bankasia.bdinternetbanking.apps.util

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.view.TextureView
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatButton
import dagger.hilt.android.AndroidEntryPoint
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.LimitModel

@AndroidEntryPoint
class LimitDialog : AutoLogoutCompatActivity() {

    companion object {
        @SuppressLint("SetTextI18n")
        fun showLimitDialog(activity: Activity, menuid: String, limitModel: LimitModel) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.check_limit_layout)
            dialog.setCancelable(true)
            // dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val btn_ok = dialog.findViewById(R.id.btn_ok) as AppCompatButton
            val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
            val tv_trans_type = dialog.findViewById(R.id.tv_trans_type) as TextView
            val tv_trans_count_amt_value =
                dialog.findViewById(R.id.tv_trans_count_amt_value) as TextView
            val tv_per_trans_count_amt_value =
                dialog.findViewById(R.id.tv_per_trans_count_amt_value) as TextView

            tv_trans_type.text = limitModel.transType
            tv_trans_count_amt_value.text = limitModel.dailyCount + " | " + limitModel.dailyAmt
            tv_per_trans_count_amt_value.text = limitModel.minAmt + " | " + limitModel.maxAmt


            btn_ok.setOnClickListener { dialog.dismiss() }
            ivClose.setOnClickListener { dialog.dismiss() }

            dialog.show()
            dialog.window!!.attributes = lp
        }


    }

}