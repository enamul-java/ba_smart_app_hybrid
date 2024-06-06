package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto


data class CreditCardStatementDto(
    var clientId: String?,
    var clientName: String?,
    var statementDate: String?,
    var cardNo: String?,
    var mobileNo: String?,
    var paymentDate: String?,
    var currency: String?,
)

