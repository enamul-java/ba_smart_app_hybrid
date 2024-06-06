package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class MfsRequestModel {

    @SerializedName("terminalId")
    var terminalId: String = ""

    @SerializedName("imei")
    var imei: String = ""

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("companyCode")
    var companyCode: String = ""

    @SerializedName("accountNo")
    var accountNo: String = ""

    @SerializedName("operationMode")
    var operationMode: String = ""

    @SerializedName("mfsType")
    var mfsType: String = ""

    @SerializedName("walletNo")
    var walletNo: String = ""

    @SerializedName("walletTitle")
    var walletTitle: String = ""

    @SerializedName("amount")
    var amount: String = ""

    @SerializedName("sourceAc")
    var sourceAc: String = ""

    @SerializedName("sourceAcTitle")
    var sourceAcTitle: String = ""

    @SerializedName("otp")
    var otp: String = ""

    @SerializedName("remarks")
    var remarks: String = ""

    @SerializedName("status")
    var status: String = ""

    @SerializedName("terminalID")
    var terminalID: String? = ""

    @SerializedName("ipimeNO")
    var ipimeNO: String? = ""

    @SerializedName("nickName")
    var nickName: String? = ""

    @SerializedName("emailId")
    var emailId: String? = ""

    @SerializedName("dob")
    var dob: String? = ""

    @SerializedName("gender")
    var gender: String? = ""

    @SerializedName("occupation")
    var occupation: String? = ""

    @SerializedName("fatherName")
    var fatherName: String? = ""

    @SerializedName("motherName")
    var motherName: String? = ""

    @SerializedName("productId")
    var productId: String? = ""

    @SerializedName("productName")
    var productName: String? = ""

    @SerializedName("effectiveDate")
    var effectiveDate: String? = ""

    @SerializedName("expiryDate")
    var expiryDate: String? = ""

    @SerializedName("mfsStatus")
    var mfsStatus: String? = ""

    @SerializedName("resCode")
    var resCode: String? = ""

    @SerializedName("resMsg")
    var resMsg: String? = ""

    @SerializedName("kycreqId")
    var kycreqId: String? = ""

    @SerializedName("mfsCode")
    var mfsCode: String? = ""

    @SerializedName("fromdate")
    var fromdate: String? = ""

    @SerializedName("todate")
    var todate: String? = ""

}