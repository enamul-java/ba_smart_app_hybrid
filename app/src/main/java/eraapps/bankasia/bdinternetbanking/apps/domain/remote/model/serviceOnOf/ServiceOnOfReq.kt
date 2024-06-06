package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf

import com.google.gson.annotations.SerializedName

class ServiceOnOfReq () {

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("serviceId")
    var serviceId: String = ""

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("compCode")
    var compCode: String = ""

    @SerializedName("actType")
    var actType: String = ""

    @SerializedName("actCode")
    var actCode: String = ""
}