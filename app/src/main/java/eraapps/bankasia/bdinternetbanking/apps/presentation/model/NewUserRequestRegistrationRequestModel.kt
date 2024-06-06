package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

class NewUserRequestRegistrationRequestModel {

        @SerializedName("terminalid")
        var terminalid: String = ""

        @SerializedName("ipimeino")
        var ipimeino: String = ""

        @SerializedName("sessionid")
        var sessionid: String = ""

        @SerializedName("otp")
        var otp: String = ""

        @SerializedName("actnum")
        var actnum: String = ""

        @SerializedName("mobileno")
        var mobileno: String = ""

        @SerializedName("nid")
        var nid: String = ""


        @SerializedName("web")
        var web: String = ""

        @SerializedName("app")
        var app: String = ""

        @SerializedName("confirmflg")
        var confirmflg: String = ""

        @SerializedName("dob")
        var dob: String = ""

        @SerializedName("mailid")
        var mailid: String = ""

        @SerializedName("userid")
        var userid: String = ""

        @SerializedName("image")
        var image: String = ""

}