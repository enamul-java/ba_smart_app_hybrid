package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class CustomerBasicInfoDto(
    val outCode: String? = "",
    val outMessage: String? = "",
    val customerName: String? = "",
    val customerDob: String? = "",
    val customerAddress: String? = "",
    val customerMobile: String? = "",
    val customerEmail: String? = "",
    val customerGender: String? = "",
    val customerNid: String? = "",
    val customerStatus: String? = "",
    val loginFrom: String? = "",
    val imei: String? = "",
    val appVersion: String? = "",
    val deviceInfo: String? = "",
    val loginDate: String? = "",

)
