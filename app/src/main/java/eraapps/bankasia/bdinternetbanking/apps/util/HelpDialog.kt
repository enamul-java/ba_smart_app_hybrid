package eraapps.bankasia.bdinternetbanking.apps.util

import android.app.Activity
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TextView

import eraapps.bankasia.bdinternetbanking.apps.R


class HelpDialog : AutoLogoutCompatActivity(){
    companion object {

        fun showHelpDialog(activity: Activity, menuid: String) {
            val dialog = Dialog(activity)
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // before
            dialog.setContentView(R.layout.dialog_help)
            dialog.setCancelable(false)
            dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            val lp = WindowManager.LayoutParams()
            lp.copyFrom(dialog.window!!.attributes)
            lp.width = WindowManager.LayoutParams.MATCH_PARENT
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT

            val ivClose = dialog.findViewById(R.id.ivClose) as ImageView
            val tv_menu_name = dialog.findViewById(R.id.tv_menu_name) as TextView
            val tv_menu_details = dialog.findViewById(R.id.tv_menu_details) as TextView

            if (menuid == "OWT") {
                tv_menu_name.text = activity.getText(R.string.own_bank_transfer)
                tv_menu_details.text = activity.getText(R.string.own_bank_help)
            } else if (menuid == "ST") {
                tv_menu_name.text = activity.getText(R.string.standing_ins)
                tv_menu_details.text = activity.getText(R.string.st_ins_help)
            } else if (menuid == "OT") {
                tv_menu_name.text = activity.getText(R.string.other_bank_transfer)
                tv_menu_details.text = activity.getText(R.string.other_bank_help)
            } else if (menuid == "CT") {
                tv_menu_name.text = activity.getText(R.string.credit_card_transfer)
                tv_menu_details.text = activity.getText(R.string.credit_card_fund_transfer_help)
            } else if (menuid == "FTH") {
                tv_menu_name.text = activity.getText(R.string.fund_transfer_history)
                tv_menu_details.text = activity.getText(R.string.transfer_history_help)
            } else if (menuid == "SAS") {
                tv_menu_name.text = activity.getText(R.string.source_ac_add)
                tv_menu_details.text = activity.getText(R.string.source_ac_add_help)
            } else if (menuid == "SCS") {
                tv_menu_name.text = activity.getText(R.string.add_card)
                tv_menu_details.text = activity.getText(R.string.source_card_add_help)
            } else if (menuid == "PAS") {
                tv_menu_name.text = activity.getText(R.string.primary_ac_set)
                tv_menu_details.text = activity.getText(R.string.primary_ac_set_help)
            } else if (menuid == "RTID") {
                tv_menu_name.text = activity.getText(R.string.register_touch_id)
                tv_menu_details.text = activity.getText(R.string.register_id_help)
            } else if (menuid == "MR") {
                tv_menu_name.text = activity.getText(R.string.topup)
                tv_menu_details.text = activity.getText(R.string.mobile_recharge_help)
            } else if (menuid == "MFST") {
                tv_menu_name.text = activity.getText(R.string.wallet_transfer)
                tv_menu_details.text = activity.getText(R.string.wallet_transfer_help)
            } else if (menuid == "MFSTH") {
                tv_menu_name.text = activity.getText(R.string.wallet_transfer_history)
                tv_menu_details.text = activity.getText(R.string.wallet_transfer_history_help)
            } else if (menuid == "EBP") {
                tv_menu_name.text = activity.getText(R.string.electricity_biill_payment)
                tv_menu_details.text = activity.getText(R.string.electricity_help)
            } else if (menuid == "WBP") {
                tv_menu_name.text = activity.getText(R.string.water_bill_payment)
                tv_menu_details.text = activity.getText(R.string.water_bill_payment_help)
            } else if (menuid == "CBP") {
                tv_menu_name.text = activity.getText(R.string.own_bank_card_payment)
                tv_menu_details.text = activity.getText(R.string.credit_card_bill_pay_help)
            } else if (menuid == "FUID") {
                tv_menu_name.text = activity.getText(R.string.forgot_user_id)
                tv_menu_details.text = activity.getText(R.string.forgot_user_id_help)
            } else if (menuid == "FPASS") {
                tv_menu_name.text = activity.getText(R.string.forgot_password)
                tv_menu_details.text = activity.getText(R.string.forgot_password_help)
            } else if (menuid == "NUSUP") {
                tv_menu_name.text = activity.getText(R.string.new_user_request)
                tv_menu_details.text = activity.getText(R.string.new_user_signup_help)
            } else if (menuid == "VQR") {
                tv_menu_name.text = activity.getText(R.string.visa_qr_payment)
                tv_menu_details.text = activity.getText(R.string.visa_qr_help)
            } else if (menuid == "BAQR") {
                tv_menu_name.text = activity.getText(R.string.bangla_qr_payment)
                tv_menu_details.text = activity.getText(R.string.bangla_qr_help)
            } else if (menuid == "SQR") {
                tv_menu_name.text = activity.getText(R.string.static_qr_generate)
                tv_menu_details.text = activity.getText(R.string.static_qr_help)
            } else if (menuid == "DQR") {
                tv_menu_name.text = activity.getText(R.string.dynamic_qr_generate)
                tv_menu_details.text = activity.getText(R.string.dynamic_qr_help)
            } else if (menuid == "UBR") {
                tv_menu_name.text = activity.getText(R.string.bills_report)
                tv_menu_details.text = activity.getText(R.string.utility_report_help)
            } else if (menuid == "OWAB") {
                tv_menu_name.text = activity.getText(R.string.own_bank_add_beneficiary)
                tv_menu_details.text = activity.getText(R.string.own_bank_add_beneficiary_help)
            } else if (menuid == "MAB") {
                tv_menu_name.text = activity.getText(R.string.wallet_add_beneficiary)
                tv_menu_details.text = activity.getText(R.string.wallet_add_beneficiary_help)
            } else if (menuid == "OTAB") {
                tv_menu_name.text = activity.getText(R.string.other_add_beneficiary)
                tv_menu_details.text = activity.getText(R.string.other_add_beneficiary_help)
            } else if (menuid == "EDBR") {
                tv_menu_name.text = activity.getText(R.string.education_bills_report)
                tv_menu_details.text = activity.getText(R.string.tution_fee_report_help)
            } else if (menuid == "INBP") {
                tv_menu_name.text = activity.getText(R.string.insurance_bill_payment)
                tv_menu_details.text = activity.getText(R.string.insurance_bill_payment_help)
            } else if (menuid == "FL") {
                tv_menu_name.text = activity.getText(R.string.finger_login)
                tv_menu_details.text = activity.getText(R.string.finger_login_help)
            } else if (menuid == "INSBR") {
                tv_menu_name.text = activity.getText(R.string.ins_bills_report)
                tv_menu_details.text = activity.getText(R.string.insurance_report_help)
            } else if (menuid == "EDB") {
                tv_menu_name.text = activity.getText(R.string.university_bill_payment)
                tv_menu_details.text = activity.getText(R.string.tution_payment_help)
            } else if (menuid == "QRS") {
                tv_menu_name.text = activity.getText(R.string.qr_scaning)
                tv_menu_details.text = activity.getText(R.string.qr_scaning_help)
            } else if (menuid == "OWQR") {
                tv_menu_name.text = activity.getText(R.string.own_qr_transfer)
                tv_menu_details.text = activity.getText(R.string.own_bank_qr_help)
            } else if (menuid == "MMQR") {
                tv_menu_name.text = activity.getText(R.string.mm_qr_transfer)
                tv_menu_details.text = activity.getText(R.string.mm_qr_help)
            } else if (menuid == NANO_LONAN_DASHBOARD) {
                tv_menu_name.text = activity.getText(R.string.nano_loan_dashboard_help_header)
                tv_menu_details.text = activity.getText(R.string.nano_loan_dashboard_help)
            } else if (menuid == NANO_LONAN_APPLICATION) {
                tv_menu_name.text = activity.getText(R.string.nano_loan_apply_help_header)
                tv_menu_details.text = activity.getText(R.string.nano_loan_apply_help)
            } else if (menuid == SHOHOZ_BUS_TICKET) {
                tv_menu_name.text = activity.getText(R.string.shohoz_bus_ticket_help_header)
                tv_menu_details.text = activity.getText(R.string.shohoz_bus_ticket_help)
            } else if (menuid == CREATE_VIRTUAL_CARD) {
                tv_menu_name.text = activity.getText(R.string.add_card)
                tv_menu_details.text = activity.getText(R.string.source_card_add_help)
            }



            ivClose.setOnClickListener { dialog.dismiss() }

            dialog.show()
            dialog.window!!.attributes = lp
        }


        public final val NANO_LONAN_DASHBOARD:String = "nano loan dashboard"
        public final val NANO_LONAN_APPLICATION:String = "nano loan application"
        public final val SHOHOZ_BUS_TICKET:String = "shohoz bus ticket"

        public final val CREATE_VIRTUAL_CARD:String = "CREATE_VIRTUAL_CARD"
    }

    /*
    Bus Ticket purchase-
•	Select source, destination and departure date.
•	Choose Bus and tap on view seats.
•	Select seat, boarding point and tap on continue button.
•	Write customers details.
•	Read terms & condition and press pay now button.
•	Select your source account.
•	OTP will sent to mobile and email.
•	Submit the OPT to complete transaction.
     */

}