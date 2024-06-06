package eraapps.bankasia.bdinternetbanking.apps.presentation.model

class ChequeStatusModel(
    slNo: String,
    leafNo:String,
    leafType:String,
    transactionDate:String,
    transactionType:String,
    amount:String,
    accountNumber:String,
    accountType:String,
    accountTitle:String,
    issueDate:String
) {
    var slNo : String? = ""
    var leafNo : String? = ""
    var leafType : String? = ""
    var transactionDate : String? = ""
    var transactionType : String? = ""
    var amount : String? = ""
    var accountNumber : String? = ""
    var accountType : String? = ""
    var accountTitle : String? = ""
    var issueDate : String? = ""

    init {
        this.slNo = slNo
        this.leafNo = leafNo
        this.leafType = leafType
        this.transactionDate = transactionDate
        this.transactionType = transactionType
        this.amount = amount
        this.accountNumber = accountNumber
        this.accountType = accountType
        this.accountTitle = accountTitle
        this.issueDate = issueDate

    }
}
