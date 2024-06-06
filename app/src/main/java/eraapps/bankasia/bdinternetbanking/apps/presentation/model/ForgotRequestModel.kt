package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class ForgotRequestModel {

    @SerializedName("terminalId")
    var terminalId:String = ""

    @SerializedName("imei")
    var imei:String = ""

    @SerializedName("userId")
    var userId:String = ""

    @SerializedName("mobileNumber")
    var mobileNumber:String = ""

    @SerializedName("nid")
    var nid:String = ""

    @SerializedName("dob")
    var dob:String = ""

   @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("otp")
    var otp:String = ""

    @SerializedName("operationMode")
    var operationMode:String = ""

    @SerializedName("accountNo")
    var accountNo:String = ""

    @SerializedName("image")
    var image:String = ""

  @SerializedName("mailId")
    var mailId:String = ""

  @SerializedName("companyCode")
    var companyCode:String = ""

 @SerializedName("currentDate")
    var currentDate:String = ""

@SerializedName("destType")
    var destType:String = ""

@SerializedName("bankType")
    var bankType:String = ""


}