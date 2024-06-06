package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model


data class Login(
    val outCode: String? = "",
    val outMessage: String? = "",
    val mailId: String? = "",
    val sessionId: String? = "",
    val cusomerCode: String? = "",
    val mobileNo: String? = "",
    val userName: String? = "",
    val address: String? = "",
    val nidNo: String? = "",
    val identifyBanking: String? = "",
    val token: String? = "",
    val emailId: String? = "",
   // val userEmail: String? = "",
)