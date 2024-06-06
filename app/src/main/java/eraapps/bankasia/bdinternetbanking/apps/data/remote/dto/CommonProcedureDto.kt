package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class CommonProcedureDto(
    val currentDate: String? = "",
    val outCode: String? = "",
    val outMessage: String? = "",
    val referenceNo: String? = "",
    val qid: String? = "",
    val transactionId: String? = "",
    val rechargeToken: String? = "",
    val date: String? = "",
    val otpFlag: String? = "",
    val chargeAmt: String? = "",
    val chargeAmount: String? = "",
    val chargeId: String? = "",
    val totalAmount: String? = "",
    val vatAmt: String? = "",
    val debitAmount: String? = "",
    val status: String? = "",
    val currencyRate: String? = "",
    val clientId: String? = "",
)
