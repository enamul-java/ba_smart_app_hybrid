package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class PayoneerRequestModel {

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("cusCode")
    var cusCode:String = ""

    @SerializedName("requestDate")
    var requestDate:String = ""



    @SerializedName("operationMode")
    var operationMode:String = ""

    @SerializedName("code")
    var code:String = ""

    @SerializedName("account_id")
    var account_id:String = ""

    @SerializedName("access_token")
    var access_token:String = ""

    @SerializedName("shadinCardFcAccount")
    var shadinCardFcAccount:String = ""

    @SerializedName("shadinCardclientId")
    var shadinCardclientId:String = ""

    @SerializedName("shadhinCardNumber")
    var shadhinCardNumber:String = ""

    @SerializedName("amount")
    var amount:String = ""

    @SerializedName("remarks")
    var remarks:String = ""

    @SerializedName("password")
    var password:String = ""

    @SerializedName("terimsCondition")
    var terimsCondition:String = ""

    @SerializedName("payoneer_account_id")
    var payoneer_account_id:String = ""

    @SerializedName("balanceId")
    var balanceId:String = ""

    @SerializedName("refernceId")
    var refernceId:String = ""


}

