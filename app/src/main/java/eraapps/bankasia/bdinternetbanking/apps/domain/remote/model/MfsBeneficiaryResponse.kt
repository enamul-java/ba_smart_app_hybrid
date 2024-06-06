package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class MfsBeneficiaryResponse(
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
    var outCode: String? = "",
    var outMessage: String? = "",
    var statusDesc: String? = "",
    var productId: String? = "",
    var walletTitle: String? = "",
    var walletNo: String? = "",
    var dateOfBirth: String? = "",
    var gender: String? = "",
    var email: String? = "",
    var occupation: String? = "",
    var productDescription: String? = "",
    var effectiveDate: String? = "",
    var expireDate: String? = "",
    var bkashStatus: String? = "",
    var resultCode: String? = "",
    var resultDescription: String? = "",
    var conversationId: String? = "",

    ) {
    override fun toString(): String {
        return codeDesc.toString()
    }


}

