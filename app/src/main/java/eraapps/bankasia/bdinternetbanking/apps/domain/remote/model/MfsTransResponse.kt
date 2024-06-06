package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class MfsTransResponse(
    var slNo: String? = "",
    var amount: String? = "",
    var accountNumber: String? = "",
    var walletNo: String? = "",
    var transDate: String? = "",
    var transactionId: String? = "",
    var mfsType: String? = "",
    var status: String? = "",
) {
    override fun toString(): String {
        return mfsType.toString()
    }
}

