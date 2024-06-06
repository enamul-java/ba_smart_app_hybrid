package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class ReRegistrationRequestModel {

    var authorization: String = ""

    @SerializedName("imei")
    var imei: String = ""

    @SerializedName("userId")
    var userId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("otp")
    var otp: String = ""

    @SerializedName("operationMode")
    var operationMode: String = ""
}