package eraapps.bankasia.bdinternetbanking.apps.presentation.model

import com.google.gson.annotations.SerializedName
import okhttp3.MultipartBody
import okhttp3.RequestBody

class ImageUploadRequestModel {


    @SerializedName("authorization")
    var authorization: String = ""

    @SerializedName("mailId")
    var mailId: String = ""

    @SerializedName("sessionId")
    var sessionId: String = ""

    @SerializedName("userPhoto")
    var userPhoto: String = ""

    @SerializedName("fileSize")
    var fileSize: String = ""

    @SerializedName("fileExtn")
    var fileExtn: String = ""



}