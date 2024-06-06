package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShohozDetailsDto(
    @SerializedName("label")
    var label: String?,

    @SerializedName("value")
    var value: String?
)

