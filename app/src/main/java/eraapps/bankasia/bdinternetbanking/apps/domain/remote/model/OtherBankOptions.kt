package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class OtherBankOptions(
    var desc: String? = "",
    var bankCode: String? = "",
    var bankName: String? = "",
    var branchCode: String? = "",
    var branch_name: String? = "",
    var accountNo: String? = "",
    var accountTitle: String? = "",
    var status: String? = "",
    var type: String? = "",
    var nickName: String? = "",
    var emailId: String? = "",
    var mobileNo: String? = "",
    var acMinLenth: String? = "",
    var acMaxLenth: String? = "",
    var pid: String? = "",
    var network: String? = "",
    var oprMode: String? = "",

    ) {
    override fun toString(): String {
        return desc.toString()
    }
}

