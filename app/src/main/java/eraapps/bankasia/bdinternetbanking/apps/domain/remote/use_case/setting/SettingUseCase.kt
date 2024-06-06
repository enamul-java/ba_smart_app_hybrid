package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting

data class SettingUseCase(
    val customerBasicInfo: CustomerBasicInfo,
    val limitInfo: LimitInfo,
    val customerComplain: CustomerComplain,
    val userIdInfoVerify: UserIdInfoVerify,
    val cardInfoVerify: CardInfoVerify,
    val cardPinResetExe: CardPinResetExe,
    val userIdChangeExe: UserIdIChangeExe,
    val userAccessList: UserAccessList,
)