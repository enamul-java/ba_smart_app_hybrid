package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard


data class CreditCardStatementNewDataM(
    val responseMessage: String? = "",
    val responseCode: String? = "",
    val data: ArrayList<CreditCardStatementObjectDto>? = ArrayList(),


) {
    override fun toString(): String {
        return "CreditCardStatementNewDataM(data=$data)"
    }
}

data class CreditCardStatementObjectDto(
    val responseCode: String? = "",
    val termSICName: String? = "",
    val tranCode: String? = "",
    val amount: String? = "",
    val termLocation: String? = "",
    val currencyAcct: String? = "",
    val termCountryName: String? = "",
    val approvalCode: String? = "",
    val amountAcct: String? = "",
    val particulars: String? = "",
    val termName: String? = "",
    val currency: String? = "",
    val termCity: String? = "",
    val tranNumber: String? = "",
    val pAN: String? = "",
    val tranTime: String? = "",
    val termOwner: String? = ""

)


