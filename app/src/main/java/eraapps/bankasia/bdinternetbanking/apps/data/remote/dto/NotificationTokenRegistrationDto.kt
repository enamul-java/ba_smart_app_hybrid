package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NotificationTokenRegistrationDto (
    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?)
