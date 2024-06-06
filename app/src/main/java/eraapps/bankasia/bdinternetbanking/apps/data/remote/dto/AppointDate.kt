package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AppointDate(
    @SerializedName("slotId")
    var slotId: String?,

    @SerializedName("ivacName")
    var ivacName: String?,

    @SerializedName("timeDisplay")
    var timeDisplay: String?,

    @SerializedName("date")
    var date: String?,

    @SerializedName("hour")
    var hour: String?,

    @SerializedName("availableSlot")
    var availableSlot: String?,


    ) {
    override fun toString(): String {
        return date + ":" + timeDisplay
    }
}

