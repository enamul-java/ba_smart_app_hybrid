package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class CardInfoResponseDto(
    val outCode: String? = "",
    val outMessage: String? = "",
    val nameOnCard: String? = "",
    val address: String? = "",
    val zip: String? = "",
    val expiryDate: String? = "",
    val responseCode: String? = "",
    val emailAddress: String? = "",
    val mobileNo: String? = "",
    val city: String? = "",
    val pan: String? = "",
    val status: String? = "",
    val currencyCode: String? = "",
    val currencyCodeDesc: String? = "",
    val maskCard: String? = "",
    val cardNumber: String? = "",
    val cardDesc: String? = "",
    val cardType: String? = "",
    val regDate: String? = "",
    val regId: String? = "",
    val pid: String? = "",
    val cvv2: String? = "",
    var oprMode: String? = "",
)
