package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

import com.google.gson.annotations.SerializedName

data class LoanListResponseModel (

    @SerializedName("amount")
    var amount: String?,

    @SerializedName("status")
    var status: String?,

    @SerializedName("compCode")
    var compCode: String?,

    @SerializedName("branchCode")
    var branchCode: String?,

    @SerializedName("accountType")
    var accountType: String?,

    @SerializedName("accountNo")
    var accountNo: String?,

    @SerializedName("accountTitle")
    var accountTitle: String?,

    @SerializedName("openDate")
    var openDate: String?,

    @SerializedName("accountStatus")
    var accountStatus: String?,

    @SerializedName("cusCode")
    var cusCode: String?,

    @SerializedName("comFlg")
    var comFlg: String?,

    @SerializedName("comCat")
    var comCat: String?,

    @SerializedName("schemaName")
    var schemaName: String?,

    @SerializedName("lonCon")
    var lonCon: String?,

    @SerializedName("codDsc")
    var codDsc: String?,

    @SerializedName("curCed")
    var curCed: String?,

    @SerializedName("curBal")
    var curBal: String?,

    @SerializedName("blAmt")
    var blAmt: String?,

    @SerializedName("lmtAmt")
    var lmtAmt: String?,

    @SerializedName("allTrn")
    var allTrn: String?,

    @SerializedName("crdNum")
    var crdNum: String?,

    @SerializedName("oprIns")
    var oprIns: String?,

    @SerializedName("intAmount")
    var intAmount: String?,

    @SerializedName("sourceAcNo")
    var sourceAcNo: String?,

    @SerializedName("expiryDate")
    var expiryDate: String?,

    @SerializedName("sourceAcBalance")
    var sourceAcBalance: String?

    /*
    sourceAcNo,
    expiryDate,
    sourceAcBalance;*/
)