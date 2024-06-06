package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class OwnBankRequestModel {

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

    @SerializedName("requestDate")
    var requestDate:String = ""

    @SerializedName("sourceAccount")
    var sourceAccount:String = ""

    @SerializedName("amount")
    var amount:String = ""

    @SerializedName("destinationAccount")
    var destinationAccount:String = ""

    @SerializedName("destinationAccountTitle")
    var destinationAccountTitle :String = ""

    @SerializedName("productCode")
    var productCode:String = ""

    @SerializedName("otp")
    var otp :String = ""

    @SerializedName("remarks")
    var remarks:String = ""

    @SerializedName("transferType")
    var transferType:String = ""

    @SerializedName("operationMode")
    var operationMode :String = ""

    @SerializedName("status")
    var status:String = ""

    @SerializedName("nickName")
    var nickName:String = ""

    @SerializedName("mobileNumber")
    var mobileNumber:String = ""

    @SerializedName("email")
    var email :String = ""

    @SerializedName("code")
    var code :String = ""

    @SerializedName("instType")
    var instType :String = ""

    @SerializedName("numberofInstruction")
    var numberofInstruction :String = ""


    @SerializedName("referenceNo")
    var referenceNo :String = ""

    @SerializedName("expireDate")
    var expireDate :String = ""


}




