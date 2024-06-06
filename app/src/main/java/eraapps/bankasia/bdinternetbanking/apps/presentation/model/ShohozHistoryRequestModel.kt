package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto

class ShohozHistoryRequestModel {

    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("custCode")
    var custCode:String = ""

    @SerializedName("fromDate")
    var fromDate:String = ""

    @SerializedName("toDate")
    var toDate:String = ""

}