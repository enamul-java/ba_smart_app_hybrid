package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class CurrencyDto(
    @SerializedName("curCode")
    var curCode: String?,

    @SerializedName("valdat")
    var valdat: String?,

    @SerializedName("ttOdRate")
    var ttOdRate: String?,

    @SerializedName("ttCleanRate")
    var ttCleanRate: String?
)
/*
private String curCode = "";
    private String valdat = "";
    private String ttOdRate = "";
    private String ttCleanRate = "";
 */