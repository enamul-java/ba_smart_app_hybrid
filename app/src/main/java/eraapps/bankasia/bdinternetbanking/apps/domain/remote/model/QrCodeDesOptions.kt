package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class QrCodeDesOptions(
    var desc: String? = "",
    var code: String? = "",
    var label: String? = ""

){
    override fun toString(): String {
        return desc.toString()
    }
}

