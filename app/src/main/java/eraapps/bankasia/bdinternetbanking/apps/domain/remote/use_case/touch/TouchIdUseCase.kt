package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.touch

data class TouchIdUseCase(
    val touchIdRegisterFlag: TouchIdRegisterFlag,
    val touchIdInfoVerify: TouchIdInfoVerify,
    val touchIdRegisterExe: TouchIdRegisterExe,
)