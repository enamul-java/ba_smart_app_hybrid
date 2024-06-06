package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class BranchLocation(
    val sl: String,
    val branchCode: String,
    val branchName: String,
    val branchAddress: String,
    val phone: String,
    val fax: String,
    val logitude: String,
    val latitude: String,
    val opendate: String,
)
