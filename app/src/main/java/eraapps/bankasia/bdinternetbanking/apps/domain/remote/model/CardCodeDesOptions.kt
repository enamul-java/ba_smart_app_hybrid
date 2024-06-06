package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class CardCodeDesOptions(
    var desc: String? = "",
    var code: String? = "",
    var mobileNumber: String? = "",
    var expireDate: String? = "",
    var cvv2: String? = "",
    var emailId: String? = "",
    var pid: String? = "",
    var cardDesc: String? = "",
    var address: String? = "",
    var zip: String? = "",
    var city: String? = "",
    var cardType: String? = "",
    var regId: String? = "",

    ) {
    override fun toString(): String {
        return desc.toString()
    }
}

