package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class InsuranceBillResponse(
    var outCode: String? = "",
    var outMessage: String? = "",
    var policyTypeCode: String? = "",
    var policyTypeDesc: String? = "",
    var encryptPolicyNo: String? = "",
    var companyCode: String? = "",
    var companyName: String? = "",
    var policyAmount: String? = "",
    var policyPremierAmount: String? = "",
    var premierDate: String? = "",
    var policyStatus: String? = "",
    var instAmount: String? = "",
    var dueDate: String? = "",
    var maturedate: String? = "",
    var tdrstatus: String? = "",
    var loanDate: String? = "",
    var loanAmount: String? = "",
    var onAccountOf: String? = "",
    var poName: String? = "",
    var receiptHeading: String? = "",
    var receiptHeading1: String? = "",
    var aplAmount: String? = "",
    var dueDateNext1: String? = "",
    var dueDateNext2: String? = "",
    var dueDateNext3: String? = "",
    var agentCode: String? = "",
    var takaofCopid: String? = "",
    var branchCode: String? = "",
    var payAmount: String? = "",

    ) {
    override fun toString(): String {
        return policyTypeDesc.toString()
    }
}

