package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class CashWithdrawRequestDto {

     @SerializedName("terminalId")
     var terminalId:String = ""

     @SerializedName("imei")
     var imei:String = ""

     @SerializedName("mailId")
     var mailId:String = ""

     @SerializedName("sessionId")
     var sessionId:String = ""

     @SerializedName("authorization")
     var authorization:String = ""

     @SerializedName("companyCode")
     var companyCode:String = ""

     @SerializedName("sourceAc")
     var sourceAc:String = ""

    @SerializedName("destAc")
    var destAc:String = ""

     @SerializedName("amount")
     var amount:String = ""

    @SerializedName("otp")
     var otp:String = ""

    @SerializedName("remarks")
     var remarks:String = ""

   @SerializedName("serviceId")
     var serviceId:String = ""

  @SerializedName("chargeAmount")
     var chargeAmount:String = ""

    @SerializedName("chargeId")
     var chargeId:String = ""

 }