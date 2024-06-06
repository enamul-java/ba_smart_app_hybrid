package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class AccountStatementModel(
    var slNo: String,
    var transactionDate: String,
    var checkNo: String,
    var remarks: String,
    var debtAmount: String,
    var creditAmount: String,
    var availaleBalance: String
)

