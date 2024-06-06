package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard

data class CreditCardLastStatementDataM(
    val responseCode: String? = "",
    val responseMessage: String? = "",
    val resCodeLStatement: String? = "",
    val resMesLStatement: String? = "",
    val creditLimitBDT: String? = "",
    val creditLimitUSD: String? = "",
    val minPaymentBDTS: String? = "",
    val minPaymentUSDS: String? = "",
    val currentOutstandingBDT: String? = "",
    val currentOutstandingUSD: String? = "",
    val nextStatementDate: String? = "",
    val paymentDueDate: String? = "",
    val url: String? = ""


) {
    override fun toString(): String {
        return "CreditCardLastStatementDataM(responseCode=$responseCode, responseMessage=$responseMessage, resCodeLStatement=$resCodeLStatement, resMesLStatement=$resMesLStatement, creditLimitBDT=$creditLimitBDT, creditLimitUSD=$creditLimitUSD, minPaymentBDTS=$minPaymentBDTS, minPaymentUSDS=$minPaymentUSDS, currentOutstandingBDT=$currentOutstandingBDT, currentOutstandingUSD=$currentOutstandingUSD, nextStatementDate=$nextStatementDate, paymentDueDate=$paymentDueDate, url=$url)"
    }
}


