package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class CreditCardSrcOptions(
    var acType: String? = "",
    var desc: String? = "",
    var flag: String? = ""

) {
    override fun toString(): String {
        return desc.toString()
    }
}

