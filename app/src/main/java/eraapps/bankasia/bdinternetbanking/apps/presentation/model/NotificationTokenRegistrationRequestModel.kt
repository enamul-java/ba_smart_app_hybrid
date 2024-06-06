package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class NotificationTokenRegistrationRequestModel {
    @SerializedName("customerCode")
    var customerCode: String = ""

    @SerializedName("deviceId")
    var deviceId: String = ""

    @SerializedName("token")
    var token: String = ""

    @SerializedName("tokenFrom")
    var tokenFrom: String = ""
}
