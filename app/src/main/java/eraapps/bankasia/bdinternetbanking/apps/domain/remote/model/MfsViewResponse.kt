package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class MfsViewResponse(
    var regDate: String? = "",
    var mailID: String? = "",
    var mfsAccountNo: String? = "",
    var mfsAccountTitle: String? = "",
    var fatherName: String? = "",
    var motherName: String? = "",
    var dob: String? = "",
    var status: String? = "",
    var codeDesc: String? = "",
    var nickName: String? = "",
    var typeDesc: String? = "",
    var mfsCode: String? = "",
    var emailid: String? = "",
    var slNo: String? = "",
    var amount: String? = "",
    var accountNumber: String? = "",
    var walletNo: String? = "",
    var transDate: String? = "",
    var transactionId: String? = "",
    var mfsType: String? = "",
    var oprMode: String? = "",
) {
    override fun toString(): String {
        return codeDesc.toString()
    }
}

