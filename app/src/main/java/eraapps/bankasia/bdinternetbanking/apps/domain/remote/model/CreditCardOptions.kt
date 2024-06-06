package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class CreditCardOptions(
    var desc: String? = "",
    var bankCode: String? = "",
    var bankName: String? = "",
    var branchCode: String? = "",
    var branch_name: String? = "",
    var accountNo: String? = "",
    var accountTitle: String? = "",
    var status: String? = "",
    var type: String? = "",
    var nickName: String? = ""

) {
    override fun toString(): String {
        return desc.toString()
    }
}

