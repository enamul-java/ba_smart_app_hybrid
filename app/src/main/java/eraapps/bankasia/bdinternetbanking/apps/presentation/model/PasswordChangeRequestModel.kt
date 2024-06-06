package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName

class PasswordChangeRequestModel {

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("oldPassword")
    var oldPassword: String = ""

    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("newPassword")
    var newPassword: String = ""

    @SerializedName("confirmPassword")
    var confirmPassword: String = ""

    @SerializedName("expiredDays")
    var expiredDays: String = ""


}