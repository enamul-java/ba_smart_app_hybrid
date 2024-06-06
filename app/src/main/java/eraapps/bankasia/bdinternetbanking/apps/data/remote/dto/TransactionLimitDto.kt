package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class TransactionLimitDto (
    val dailyCount: String,
    val maxAmount: String,
    val minAmount: String,
    val totalLimit: String,
    val menuname: String,
    val limtType: String
)