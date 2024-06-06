package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac

data class IvacUseCase(
    val ivacVisaCenter: IvacVisaCenter,
    val ivacVisaType: IvacVisaType,
    val ivacBillInfo: IvacBillInfo,
    val getTarnsactionId: GetTransactionId,
    val ivacBillPayment: IvacBillPayment,
)
