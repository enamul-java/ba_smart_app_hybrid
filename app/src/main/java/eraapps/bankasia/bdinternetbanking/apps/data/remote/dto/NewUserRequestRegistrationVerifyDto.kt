package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NewUserRequestRegistrationVerifyDto(
    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?,

    @SerializedName("sessionid")
    var sessionid: String?,

    @SerializedName("accessflg")
    var accessflg: String?,

    @SerializedName("sessionId")
    var sessionId: String?
)
