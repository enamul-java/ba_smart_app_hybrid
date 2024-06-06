package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class CustomerBasicRequestModel {

    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("userId")
    var userId:String = ""

    @SerializedName("identify")
    var identify:String = ""

    @SerializedName("reqFrom")
    var reqFrom:String = ""

    @SerializedName("subject")
    var subject:String = ""

    @SerializedName("message")
    var message:String = ""

    @SerializedName("attachment")
    var attachment:String = ""

    @SerializedName("imei")
    var imei:String = ""

    @SerializedName("terminalId")
    var terminalId:String = ""

    @SerializedName("categoryCode")
    var categoryCode:String = ""

    @SerializedName("categoryName")
    var categoryName:String = ""


    @SerializedName("transType")
    var transType:String = ""


    @SerializedName("subType")
    var subType:String = ""

}




