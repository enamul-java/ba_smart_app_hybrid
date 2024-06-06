package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AppointDate

data class IvacResponseModel(
    var outCode: String? = "",
    var outMessage: String? = "",
    var centerId: String? = "",
    var centerName: String? = "",
    var transactionId: String? = "",
    var visaTypeId: String? = "",
    var visaTypeName: String? = "",
    var totalAmount: String? = "",
    var billAmount: String? = "",
    var lid: String? = "",
    var appointList: ArrayList<AppointDate>,
    var date: String? = ""
)