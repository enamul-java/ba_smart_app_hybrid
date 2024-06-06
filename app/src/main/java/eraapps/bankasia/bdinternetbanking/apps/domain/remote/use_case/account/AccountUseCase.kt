package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account

data class AccountUseCase(
    val sourceAccount: SourceAccount,
    val soureceAccountBalance: SourceAccountBalance,
    val primaryAccountSet: PrimaryAccountSet,
    val sourceAcVerify: SourceAcVerify,
    val sourcAcAdd: SourceAcAdd,
    val getAccuntBalanceCasa: AccountBalanceCasa,
    val sourceAcforStatement: SourceAcForStatement,
    val accountStatement: AccountStatement,
    val accountStatementReport: AccountStatementReport,
    val accountInfo: AccountInfo,
    val forigenExchangeRate: ForigenExchangeRate,
    val sourceAccountList: SourceAccountList,
    val sourceAcforStatementList: SourceAcForStatementList,
    val accountStatementList: AccountStatementList,
    val getAccuntBalanceCasaList: AccountCasaList,
    val transferLimitCheck: TransferLimitCheck,
    val duplicateCheck: DuplicateCheck,
    val transHistory: TransferHistory,
    val transHistoryList: TransferHistoryList,
    val qrCashWithdrawlValidation: QrCashWithdrawalValidation,
    val qrCashWithdrawExe: QrCashWithdrawaExe,
    val qrCashWithdrawCancel: QrCashWithdrawaCancel
)
