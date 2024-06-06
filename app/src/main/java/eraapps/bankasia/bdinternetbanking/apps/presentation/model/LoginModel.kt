package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class LoginModel {

    @SerializedName("userId")
    var userId:String = ""

    @SerializedName("passowrd")
    var passowrd:String = ""

    @SerializedName("appVersion")
    var appVersion:String = ""

    @SerializedName("serverMac")
    var serverMac:String = ""

    @SerializedName("clientMac")
    var clientMac:String = ""

    @SerializedName("authUser")
    var authUser:String = ""

    @SerializedName("authPassword")
    var authPassword:String = ""

    @SerializedName("os")
    var os:String = ""

    @SerializedName("imei")
    var imei:String = ""

    @SerializedName("deviceInfo")
    var deviceInfo:String = ""

    @SerializedName("requestFrom")
    var requestFrom:String = ""

    @SerializedName("requestType")
    var requestType:String = ""

    @SerializedName("versionCode")
    var versionCode:String = ""

    @SerializedName("fingerflg")
    var fingerflg:String = ""



}