package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class TransactionValidationModel(

    var accountNumber: String? = "",
    var balance: String? = "",
    var accountTypeDesc: String? = "",
    var status: String? = "",
    var accountTitle: String? = "",
    var companyCode: String? = "",
    var currency: String? = ""

)