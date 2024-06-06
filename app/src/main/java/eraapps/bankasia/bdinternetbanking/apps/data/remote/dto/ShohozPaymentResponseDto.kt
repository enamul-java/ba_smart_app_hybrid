package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShohozPaymentResponseDto(

    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?,

    @SerializedName("trndocnum")
    var trndocnum: String?,

    @SerializedName("pnr")
    var pnr: String?,

    @SerializedName("shohoz_order_id")
    var shohoz_order_id: String?


)

