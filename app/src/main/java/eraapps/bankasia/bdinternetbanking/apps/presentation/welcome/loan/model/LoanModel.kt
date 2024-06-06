package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName


class LoanModel {

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("actStateFlag")
    var actStateFlag:String = ""

    @SerializedName("amount")
    var amount:String = ""

    @SerializedName("month")
    var month:String = ""

    @SerializedName("requestCode")
    var requestCode:String = ""

//    @SerializedName("jsonObject")
//    var jsonObject: JsonObject? = null

    @SerializedName("jsonObject")
    var jsonObject: String = ""

    @SerializedName("creditScoreId")
    var creditScoreId: String = ""

    @SerializedName("receiverAccountNo")
    var receiverAccountNo: String = ""

    @SerializedName("emi")
    var emi: String = ""

    @SerializedName("loanId")
    var loanId: String = ""

    @SerializedName("remarks")
    var remarks: String = ""

    @SerializedName("sanctionDate")
    var sanctionDate: String = ""

    @SerializedName("imei")
    var imei: String = ""

    @SerializedName("applied_amount")
    var applied_amount: Int = 0

    @SerializedName("approved_amount")
    var approved_amount: Int = 0

    @SerializedName("deviceInfo")
    var deviceInfo: String = ""

    @SerializedName("versionCode")
    var versionCode: String = ""

    @SerializedName("mobileNumber")
    var mobileNumber: String = ""

    @SerializedName("loanAccount")
    var loanAccount: String = ""

    @SerializedName("accountType")
    var accountType: String = ""

    @SerializedName("branchCode")
    var branchCode: String = ""

    @SerializedName("settlementFlag")
    var settlementFlag: String = ""


    @SerializedName("sourceAcs")
    var sourceAcs: String = ""

    @SerializedName("p1")
    var p1: String = ""

    @SerializedName("p2")
    var p2: String = ""

    @SerializedName("p3")
    var p3: String = ""

}
