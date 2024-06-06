package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class EmiRequestModel {
    @SerializedName("rate")
    var rate: String = ""

    @SerializedName("time")
    var time: String = ""

    @SerializedName("principal")
    var principal: String = ""

}