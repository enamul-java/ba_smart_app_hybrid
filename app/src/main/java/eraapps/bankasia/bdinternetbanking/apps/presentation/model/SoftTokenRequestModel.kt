package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class SoftTokenRequestModel {
    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("userId")
    var userId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("softToken")
    var softToken:String = ""

    @SerializedName("referenceNo")
    var referenceNo:String = ""

    @SerializedName("email")
    var email:String = ""


}


