package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class EmiDto(
    @SerializedName("emi")
    var emi: String?,

    @SerializedName("totalPayment")
    var totalPayment: String?,

    @SerializedName("totalInterest")
    var totalInterest: String?,

    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?
)