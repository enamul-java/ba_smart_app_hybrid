package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShohozInvoiceDto(

    @SerializedName("amount")
    var amount: String?,

    @SerializedName("seat_no")
    var seat_no: String?,

    @SerializedName("ticket_id")
    var ticket_id: String?

)

