package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class NotificationDto(

    @SerializedName("title")
    var title: String? = "",

    @SerializedName("message")
    var message: String? = "",

    @SerializedName("imageid")
    var imageid: String? = "",

    @SerializedName("imageflg")
    var imageflg: String? = "",

    @SerializedName("programedate")
    var programedate: String? = ""
)
