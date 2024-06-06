package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class CurrencyRequestModel {
    @SerializedName("mailid")
    var mailid: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("companyid")
    var companyid: String = ""

    @SerializedName("branchid")
    var branchid: String = ""
}

