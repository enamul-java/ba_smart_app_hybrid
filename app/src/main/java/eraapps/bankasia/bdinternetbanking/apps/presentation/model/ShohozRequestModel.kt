package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto

class ShohozRequestModel {

    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("terminalId")
    var terminalId:String = ""

    @SerializedName("imei")
    var imei:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("companyCode")
    var companyCode:String = ""

    @SerializedName("sourceAccount")
    var sourceAccount:String = ""

    @SerializedName("amount")
    var amount:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("orderId")
    var orderId:String = ""

    @SerializedName("otp")
    var otp:String = ""

    @SerializedName("remarks")
    var remarks:String = ""

    @SerializedName("commission")
    var commission:String = ""

    @SerializedName("operationMode")
    var operationMood:String = ""

    @SerializedName("shohozToken")
    var shohozToken:String = ""

    @SerializedName("purchaseDetails")
    var purchaseDetails:ShohozResponseDto = ShohozResponseDto()

}