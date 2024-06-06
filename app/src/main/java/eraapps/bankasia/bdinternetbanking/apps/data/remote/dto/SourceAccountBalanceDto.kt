package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class SourceAccountBalanceDto(
    val outCode: String? = "",
    val outMessage: String? = "",
    val accountTitle: String? = "",
    val availaleBalance: String? = "",
    val currencyCode: String? = "",
    val customerCode: String? = "",
    val bankCode: String? = "",
    val bankName: String? = "",
    val branchCode: String? = "",
    val branchName: String? = "",
    val shadowAc: String? = "",

)