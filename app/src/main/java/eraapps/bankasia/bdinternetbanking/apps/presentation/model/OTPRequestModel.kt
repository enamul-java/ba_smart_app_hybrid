package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class OTPRequestModel {

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("actFlg")
    var actFlg: String = ""

    @SerializedName("userId")
    var userId : String = ""

    @SerializedName("mobileNumber")
    var mobileNumber  : String = ""

    @SerializedName("customerCode")
    var customerCode: String = ""

}
