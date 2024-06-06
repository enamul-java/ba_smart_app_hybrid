package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class UserRequestModel {

    @SerializedName("terminalId")
    var terminalId: String = ""

    @SerializedName("imei")
    var imei: String = ""

    @SerializedName("otp")
    var otp: String = ""

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("password")
    var password: String = ""

    @SerializedName("confirmFlg")
    var confirmFlg: String = ""

    @SerializedName("olduserId")
    var olduserId: String = ""

    @SerializedName("newuserId")
    var newuserId: String = ""

    @SerializedName("confirmuserId")
    var confirmuserId: String = ""

    @SerializedName("customerCode")
    var customerCode: String = ""

    @SerializedName("reqFrom")
    var reqFrom: String = ""

    @SerializedName("cardNo")
    var cardNo: String = ""

    @SerializedName("nameOnCard")
    var nameOnCard: String = ""

    @SerializedName("address")
    var address: String = ""

    @SerializedName("expiryDate")
    var expiryDate: String = ""

    @SerializedName("mobileNo")
    var mobileNo: String = ""

    @SerializedName("newPin")
    var newPin: String = ""

    @SerializedName("confirmpin")
    var confirmpin: String = ""

    @SerializedName("maskedPan")
    var maskedPan: String = ""

    @SerializedName("sourceAc")
    var sourceAc: String = ""

    @SerializedName("clientId")
    var clientId: String = ""


}