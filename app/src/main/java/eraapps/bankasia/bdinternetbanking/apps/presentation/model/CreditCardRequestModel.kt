package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class CreditCardRequestModel {

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("currentDate")
    var currentDate: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("companyCode")
    var companyCode: String = ""

    @SerializedName("accountNo")
    var accountNo: String = ""

    @SerializedName("bankType")
    var bankType: String = ""

    @SerializedName("destType")
    var destType: String = ""

    @SerializedName("operationMode")
    var operationMode: String = ""

    @SerializedName("accountNumber")
    var accountNumber: String = ""

    @SerializedName("terminalID")
    var terminalID: String = ""

    @SerializedName("ipimeNO")
    var ipimeNO: String = ""

    @SerializedName("sysDate")
    var sysDate: String = ""

    @SerializedName("transType")
    var transType: String = ""

    @SerializedName("actCode")
    var actCode: String = ""

    @SerializedName("otp")
    var otp: String = ""

    @SerializedName("cardNo")
    var cardNo: String = ""

    @SerializedName("cardType")
    var cardType: String = ""

    @SerializedName("amount")
    var amount: String = ""

    @SerializedName("bankCode")
    var bankCode: String = ""

    @SerializedName("branchCode")
    var branchCode: String = ""

    @SerializedName("destAccNo")
    var destAccNo: String = ""

    @SerializedName("destAccTitle")
    var destAccTitle: String = ""

    @SerializedName("remarks")
    var remarks: String = ""

    @SerializedName("debitAmount")
    var debitAmount: String = ""

    @SerializedName("mobileNo")
    var mobileNo: String = ""

    @SerializedName("nickName")
    var nickName: String = ""

    @SerializedName("emailId")
    var emailId: String = ""

    @SerializedName("actFlg")
    var actFlg: String = ""

    @SerializedName("bankName")
    var bankName: String = ""

    @SerializedName("branchName")
    var branchName: String = ""

    @SerializedName("pid")
    var pid: String = ""

    @SerializedName("cuscode")
    var cuscode: String = ""

    @SerializedName("requestCode")
    var requestCode: String = ""

    @SerializedName("url")
    var url: String = ""

}