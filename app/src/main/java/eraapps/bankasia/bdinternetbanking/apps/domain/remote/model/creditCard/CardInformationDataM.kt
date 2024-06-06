package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard

data class CardInformationDataM(
    val resCode: String? = "",
    val resMessage: String? = "",

    /*val lastTxnTime: String? = "",
    val accountNo: String? = "",
    val holdAmount: String? = "",
    val balance: String? = "",
    val currencyID: String? = "",*/

    val accountNoBDT: String? = "",
    val lastTxnTimeBDT: String? = "",
    val holdAmountBDT: String? = "",
    val balanceBDT: String? = "",
    val currencyIDBDT: String? = "",

    val accountNoUSD: String? = "",
    val lastTxnTimeUSD: String? = "",
    val holdAmountUSD: String? = "",
    val balanceUSD: String? = "",
    val currencyIDUSD: String? = "",


    val resCodeDue: String? = "",
    val resMesDue: String? = "",
    val minPaymentBDT: String? = "",
    val minPaymentUSD: String? = "",
    val paymentDueDate: String? = "",

    val resCodeOutStanding: String? = "",
    val resMesOutStanding: String? = "",
    val cardNumber: String? = "",
    val cardStatus: String? = "",
    val cardState: String? = "",
    val outstandingBDT: String? = "",
    val outstandingUSD: String? = "",
)


