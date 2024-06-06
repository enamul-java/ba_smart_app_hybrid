package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class AccountStatementDto(
    var slNo : String = "",
    var transactionDate : String = "",
    var checkNo : String = "",
    var remarks : String = "",
    var debtAmount : String = "",
    var creditAmount : String = "",
    var availaleBalance : String = "",
)