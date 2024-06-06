package eraapps.bankasia.bdinternetbanking.apps.util.permission

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import eraapps.bankasia.bdinternetbanking.apps.R
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.AfterPermissionGranted
import eraapps.bankasia.bdinternetbanking.apps.util.permission.easyPermission.EasyPermissions

class PermissionUtil {

    private val TAG = "PermissionUtil";
    companion object {

        private const val YES = "Yes"
        private const val NO = "No"
        private const val CAMERA = android.Manifest.permission.CAMERA
        private const val READ_PHONE_STATE = android.Manifest.permission.READ_PHONE_STATE
        private const val ACCESS_NETWORK_STATE = android.Manifest.permission.ACCESS_NETWORK_STATE
        private const val CALL_PHONE = android.Manifest.permission.CALL_PHONE

        private const val CAMERA_REQ = 120
        private const val READ_PHONE_STATE_REQ = 121
        private const val ACCESS_NETWORK_STATE_REQ = 122
        private const val CALL_PHONE_REQ = 123

        const val FROM_QUICK_REACH = "quick_reach"
        const val FROM_LOGIN = "login"
        const val FROM_NANO_LOAN = "nano_loan"
        const val FROM_FORGET_PASS = "forget_password"
        const val FROM_FORGET_USER = "forget_user"
        const val FROM_NEW_USER = "new_user"
        const val FROM_CONTACT = "topup_contact"
        const val FROM_AC_OPEN = "ac_open"

        val ALL_PERMISSIONS =
            arrayOf(
                Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.ACCESS_NETWORK_STATE,
                Manifest.permission.CALL_PHONE
            )

        const val ALL_PERMISSION_PERM = 124

        val ALL_PERMISSIONS_NANO = getNanoLoanReq()
            /*arrayOf(
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.READ_SMS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )*/

        const val ALL_PERMISSION_PERM_NANO = 125

        val ALL_PERMISSIONS_FORGET_PASS =
            getCameraAndStorage()
            /*arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )*/

        const val ALL_PERMISSION_PERM_FORGET_PASS = 126

        val ALL_PERMISSIONS_FORGET_USER =
            getCameraAndStorage()
            /*arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )*/

        const val ALL_PERMISSION_PERM_FORGET_USER = 127

        val ALL_PERMISSIONS_NEW_USER = getCameraAndStorage()
            /*arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.CAMERA
            )*/

        const val ALL_PERMISSION_PERM_NEW_USER = 128

        val ALL_PERMISSIONS_CONTACT =
            arrayOf(
                Manifest.permission.READ_CONTACTS
            )

        const val ALL_PERMISSION_PERM_CONTACT = 129


        private fun getAcOpenReq():Array<String>{


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                return arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                return arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            }else{
                return arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            }
        }

        private fun getNanoLoanReq():Array<String>{


            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                return arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION
                )

            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                return arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

            }else{
                return arrayOf(
                    Manifest.permission.READ_CALENDAR,
                    Manifest.permission.READ_SMS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                    Manifest.permission.READ_EXTERNAL_STORAGE
                )

            }
        }

        private fun getCameraAndStorage():Array<String>{
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
                return arrayOf(
                    Manifest.permission.CAMERA
                )

            }else if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.P){
                return arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )

            }else{
                return arrayOf(
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA
                )

            }
        }

    }



    @AfterPermissionGranted(CAMERA_REQ)
    fun checkCameraPermission(context: Activity):Boolean {
        if (isPermissionGranted(context, CAMERA)) {
            // Have permissions, do the thing!
            //Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
            Log.d(TAG, "All Permission Accepted!");
            return true
        } else {
            // Ask for all permissions
            EasyPermissions.requestPermissions(
                context,
                context.getString(R.string.rationale_asking,"CAMERA"),
                CAMERA_REQ,
                CAMERA
            )
            return false
        }
    }

    @AfterPermissionGranted(CALL_PHONE_REQ)
    fun checkCallPermission(context: Activity):Boolean {
        if (isPermissionGranted(context, CALL_PHONE)) {
            // Have permissions, do the thing!
            //Toast.makeText(this, "TODO: Location and Contacts things", Toast.LENGTH_LONG).show()
            Log.d(TAG, "All Permission Accepted!");
            return true
        } else {
            // Ask for all permissions
            EasyPermissions.requestPermissions(
                context,
                context.getString(R.string.rationale_asking,"CALL_PHONE"),
                CALL_PHONE_REQ,
                CALL_PHONE
            )
            return false
        }
    }

    private fun isPermissionGranted(context:Context,name: String) = ContextCompat.checkSelfPermission(
        context, name
    ) == PackageManager.PERMISSION_GRANTED


    fun rationalTitle(activity: Activity):String{
        return "Access Required for Smart App!"
    }

    fun rationalMessage(activity: Activity, from:String):String{
        var message:String = "This Access Permission Required only for apps functionality! No data shared form device!"
        if(from.equals(FROM_QUICK_REACH)){

        }else if(from.equals(FROM_LOGIN)){

        }else if(from.equals(FROM_NANO_LOAN)){
            message = "* This Access Permission Required only for apps functionality.\n" +
                      "* All Permission needed for Meta Scoring for Micro Finance.\n" +
                      "* No Data shared or saved any where, just for generate score.\n" +
                      "* Please Read all terms and condition carefully."
        }else if(from.equals(FROM_FORGET_PASS)
            || from.equals(FROM_FORGET_USER)
            || from.equals(FROM_NEW_USER)){
            message = "* This Access Permission Required only for apps functionality.\n" +
                    "* All Permission needed for Photo Capture only!."
        }else if(from.equals(FROM_QUICK_REACH)){
            message = "* This Access Permission Required only for apps functionality.\n" +
                    "* Contact Permission needed for Pick Contact No to Topup only!."
        }
        return message
    }

    fun getMessage(activity: Activity, from: String):String{
        var message:String = "Returned from app settings"
        if(from.equals(FROM_QUICK_REACH)){
            message = activity.getString(
            R.string.returned_from_app_settings_to_activity,
            if (isPermissionGranted(activity,Manifest.permission.CAMERA)) YES else NO,
            if (isPermissionGranted(activity,Manifest.permission.READ_PHONE_STATE)) YES else NO,
            if (isPermissionGranted(activity,Manifest.permission.ACCESS_NETWORK_STATE)) YES else NO,
            if (isPermissionGranted(activity,Manifest.permission.CALL_PHONE)) YES else NO)
        }else if(from.equals(FROM_LOGIN)){
            message = activity.getString(
                R.string.returned_from_app_settings_to_activity,
                if (isPermissionGranted(activity,Manifest.permission.CAMERA)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.READ_PHONE_STATE)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.ACCESS_NETWORK_STATE)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.CALL_PHONE)) YES else NO)
        }else if(from.equals(FROM_NANO_LOAN)){
            if(is13orHigh()){
                message = activity.getString(
                    R.string.returned_from_app_settings_to_activity_nano13,
                    if (isPermissionGranted(activity,Manifest.permission.READ_CALENDAR)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.READ_SMS)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.READ_CONTACTS)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.ACCESS_FINE_LOCATION)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.ACCESS_COARSE_LOCATION)) YES else NO
                )
            }else{
                message = activity.getString(
                R.string.returned_from_app_settings_to_activity_nano,
                if (isPermissionGranted(activity,Manifest.permission.READ_CALENDAR)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.READ_SMS)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.READ_CONTACTS)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.ACCESS_FINE_LOCATION)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.ACCESS_COARSE_LOCATION)) YES else NO,
                if (isPermissionGranted(activity,Manifest.permission.READ_EXTERNAL_STORAGE)) YES else NO
                )
            }

        }else if(from.equals(FROM_FORGET_PASS)
            || from.equals(FROM_FORGET_USER)
            || from.equals(FROM_NEW_USER)){
            if(is13orHigh()){
                message = activity.getString(
                    R.string.returned_from_app_settings_to_activity_user_pass_new13,
                    if (isPermissionGranted(activity,Manifest.permission.CAMERA)) YES else NO
                )
            }else{
                message = activity.getString(
                    R.string.returned_from_app_settings_to_activity_user_pass_new,
                    if (isPermissionGranted(activity,Manifest.permission.CAMERA)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.READ_EXTERNAL_STORAGE)) YES else NO,
                    if (isPermissionGranted(activity,Manifest.permission.WRITE_EXTERNAL_STORAGE)) YES else NO
                )
            }
        }else if(from.equals(FROM_CONTACT)){
            message = activity.getString(
                    R.string.returned_from_app_settings_to_activity_contacts,
                    if (isPermissionGranted(activity,Manifest.permission.READ_CONTACTS)) YES else NO
                )
        }
        return message
    }

    fun showAcceptedAccessList(activity: Activity, title:String,from:String){
        val builder = AlertDialog.Builder(activity)
        //set title for alert dialog
        builder.setTitle(title)
        //set message for alert dialog
        builder.setMessage(getMessage(activity, from))
        builder.setIcon(android.R.drawable.ic_dialog_alert)

        //performing positive action
        builder.setPositiveButton("Ok"){dialogInterface, which ->
            dialogInterface.dismiss()
        }

        // Create the AlertDialog
        val alertDialog: AlertDialog = builder.create()
        // Set other dialog properties
        alertDialog.setCancelable(false)
        alertDialog.show()
    }

    private fun is13orHigh():Boolean{
        return Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU
    }

    fun isPermissionGranted(activity: Activity,name: String) = ContextCompat.checkSelfPermission(
        activity, name
    ) == PackageManager.PERMISSION_GRANTED


    fun readRightPermission(){
        //Android 13+ (READ,WIGHT Both Not Allowed)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
        //Android 29+  (WIGHT Not Allowed.Only READ Allowed if Accept)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
        //Android 26+  (WIGHT,READ Allowed if Accept)
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

        }
    }

}