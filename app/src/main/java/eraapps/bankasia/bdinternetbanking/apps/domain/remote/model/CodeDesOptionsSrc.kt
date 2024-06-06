package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class CodeDesOptionsSrc(
    var desc: String? = "",
    var code: String? = "",
    var pflg: String? = ""

) {
    override fun toString(): String {
        return desc.toString()
    }
}

