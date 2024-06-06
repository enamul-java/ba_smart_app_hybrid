package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto

class ShohozTicketCancelRequestModel {

    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("orderId")
    var orderId:String = ""

    @SerializedName("pnr")
    var pnr:String = ""

    @SerializedName("otp")
    var otp:String = ""

    @SerializedName("remarks")
    var remarks:String = ""



}