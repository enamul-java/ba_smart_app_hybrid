package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model

data class MenuResponse(
    var menuTitleE: String? = "",
    var menuTitleB: String? = "",
    var itemid: String? = "",
    var softCode: String? = ""


) {
    override fun toString(): String {
        return menuTitleE.toString()
    }
}

