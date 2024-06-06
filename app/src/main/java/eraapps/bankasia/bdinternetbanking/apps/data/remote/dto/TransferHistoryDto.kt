package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class TransferHistoryDto(
    var slNo: String = "",
    var sourceAcNo: String = "",
    var destAcNo: String = "",
    var destAcTitle: String = "",
    var amount: String = "",
    var status: String = "",
    var transDate: String = "",
    var transId: String = "",
    var transType: String = "",

    )