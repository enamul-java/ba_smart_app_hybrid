package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.other


data class OtherBankUseCase(
    val destinationAccount: DestinationAccount,
    val otherBankdoExecute: OtherBankdoExecute,
    val otherBankAddBeneficiaryExe: OtherBankAddBeneficiaryExe,
    val beneficiaryDeleteExe: BeneficiaryDeleteExe,
    val otherBankBranchSrc: OtherBankBranchSrc,
    val getBankAcLenth: GetBankAcLength,
    val otherBankSrc: OtherBankSrc,
    val destinationAccountList: DestinationAccountList,
    val destinationAccountListTrans: DestinationAccountListTrans,
    val otherBankSrcList: OtherBankSrcList,
    val otherBankBranchSrcList: OtherBanBranchkSrcList,
)
