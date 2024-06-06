package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.cheque


data class ChequeUseCase(
    val chequeSourceAccount: ChequeSourceAccount,
    val chequeBookRequest: ChequeBookRequest,
    val chequeStatusSearch: ChequeStatusSearch,
    val chequeLeafs: ChequeLeafs,
    val positivePayRequest: PositivePayRequest,
    val stopChequeExe: StopChequeExe,
    val chequeSoureceAccountList: ChequeSourceAccountList,
    val chequeInqueryTypes: ChequeInqueryTypes,
    val chequeStatusSearchList: ChequeStatusSearchList,
    val chequeLeafsList: ChequeLeafsList,
    val chequeLeaveReason: ChequeLeaveReason,
)
