package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz

data class ShohozUseCase(
    val shohozTokenGenerate: ShohozTokenGenerate,
    val shohozTicketInfo: ShohozTicketInfo,
    val shohozPayment: ShohozPayment,
    val shohozPaymentHistory: ShohozPaymentHistory,
    val shohozTicketCancel: ShohozTicketCancel,
)
