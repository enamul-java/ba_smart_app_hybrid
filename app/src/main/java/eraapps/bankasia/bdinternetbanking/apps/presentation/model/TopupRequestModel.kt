package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class TopupRequestModel {

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("companyCode")
    var companyCode: String = ""

    @SerializedName("sourceAc")
    var sourceAc: String = ""

    @SerializedName("amount")
    var amount: String = ""

    @SerializedName("mobileOperatorCode")
    var mobileOperatorCode: String = ""

    @SerializedName("prePostCode")
    var prePostCode: String = ""

    @SerializedName("mobileNumber")
    var mobileNumber: String = ""

    @SerializedName("password")
    var password: String = ""

    @SerializedName("remarks")
    var remarks: String = ""


}