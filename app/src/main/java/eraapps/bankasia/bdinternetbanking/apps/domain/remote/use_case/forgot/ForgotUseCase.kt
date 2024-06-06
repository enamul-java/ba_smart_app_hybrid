package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot


data class ForgotUseCase(
    val forgotPassInfoVerify: ForgotPassInfoVerify,
    val forgotUserIDInfoVerify: ForgotUserIdInfoVerify,
    val forgotPassExe: ForgotPassExe,
    val forgotUserIdExe: ForgotUserIdExe,
    val getCardShadowAcInfo: GetCardShadowAcInfo,
)
