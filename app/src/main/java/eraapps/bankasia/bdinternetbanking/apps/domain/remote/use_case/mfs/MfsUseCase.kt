package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs

data class MfsUseCase(
    val mfsViewBeneficiary: MfsViewBeneficiary,
    val mfsViewBeneficiaryTrans: MfsViewBeneficiaryTrans,
    val mfsAddBeneficiary: MfsAddBeneficiary,
    val mfsBeneficiaryInfo: MfsBeneficiaryInfo,
    val mfsTransferExe: MfsTransferExe,
    val mfsTransHistory: MfsTransferHistory,
    val mfsViewBeneficiaryList: MfsViewBeneficiaryList,
    val mfsViewBeneficiaryListTrans: MfsViewBeneficiaryListTrans,
    val mfsTransHistoryList: MfsTransHistoryList,
)
