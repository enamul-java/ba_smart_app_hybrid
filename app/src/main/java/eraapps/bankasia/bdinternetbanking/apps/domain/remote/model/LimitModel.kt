package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class LimitModel(
    var outCode: String?,
    var outMessage: String?,
    var transType: String?,
    var chargeAmt: String?,
    var minAmt: String?,
    var maxAmt: String?,
    var dailyCount: String?,
    var dailyAmt: String?,
    var monthlyCount: String?,
    var monthlyAmt: String?,
)