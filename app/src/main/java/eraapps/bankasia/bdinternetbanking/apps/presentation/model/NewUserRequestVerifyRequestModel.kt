package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody

class NewUserRequestVerifyRequestModel {
    @SerializedName("terminalid")
    var terminalid: String = ""

    @SerializedName("ipimeino")
    var ipimeino: String = ""

    @SerializedName("sessionid")
    var sessionid: String = ""

    @SerializedName("actnum")
    var actnum: String = ""

    @SerializedName("mobileno")
    var mobileno: String = ""

    @SerializedName("nid")
    var nid: String = ""

    @SerializedName("dob")
    var dob: String = ""

    @SerializedName("userid")
    var userid: String = ""

    @SerializedName("image")
    var image: String = ""

}