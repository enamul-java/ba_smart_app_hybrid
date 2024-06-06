package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service


data class CardServiceUseCase(
    val creditCardSrc: CreditCardStatementSrc,
    val creditCardSrcList: CreditCardStatementSrcList,
    val cardInformation: CardInformation,
    val viewCardLastStatement: ViewCardLastStatement,
    val creditCardStatementNew: CreditCardStatementNew
)
