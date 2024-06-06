package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.credit


data class CreditCardUseCase(
    val creditCardSrc: CreditCardSrc,
    val creditCardDestination: CreditCardDestination,
    val creditCardchargeCalculation: CreditCardChargeCalculation,
    val submitCreditCardRegistration: SubmitCreditCardRegistration,
    val creditCarddoExecute: CreditCarddoExecute,
    val creditCardSrcList: CreditCardSrcList,
    val creditCardStatementList: CreditCardStatementList,
    val getCardTypeCheckList: GetCardTypeCheckList,
    val bankTypeList: BankTypeList,
    val creditCardDestinationList: CreditCardDestinationList,
    val getAccountTypes: AccountTypesList
)
