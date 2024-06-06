package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class CodeDesOptions(
    var desc: String? = "",
    var code: String? = ""

){
    override fun toString(): String {
        return desc.toString()
    }
}

