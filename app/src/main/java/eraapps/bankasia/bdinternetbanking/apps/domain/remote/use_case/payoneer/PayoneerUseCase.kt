package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.payoneer

data class PayoneerUseCase(
    val getPayoneerUrlInfo: GetPayoneerUrlInfo,
    val getPayoneerCardInfo: GetPayoneerCardInfo,
    val getPayoneerAccessToken: GetPayoneerAccessToken,
    val getPayoneerBalanceInfo: GetPayoneerBalanceInfo,
    val transferPayoneer: TransferPayoneer,
    val payoneerHistory: PayoneerHistory,
    val payoneerHistoryList: PayoneerHistoryList,
    val payoneerPaymentRefresh: PayoneerPaymentRefresh,
)
