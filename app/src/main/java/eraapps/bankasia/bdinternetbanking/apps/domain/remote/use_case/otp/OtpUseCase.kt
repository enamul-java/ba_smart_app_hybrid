package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp

data class OtpUseCase(
    val sendTransOtp: SendTransOtp,
    val doCardOtp: CardOtp,
)
