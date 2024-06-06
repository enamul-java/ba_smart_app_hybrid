package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardLastStatementDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardLastStatementReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardListDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewReqM
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel

interface CardServiceRepository {

    suspend fun creditCardSrc(
        header: Map<String, String>,
        requestModel: CreditCardSourceCardReqM
    ): CommonProcedureDto

    suspend fun creditCardSrcList(
        header: Map<String, String>,
        requestModel: CreditCardSourceCardReqM
    ): List<CreditCardSourceCardListDataM>
    suspend fun creditCardInformation(
        header: Map<String, String>,
        requestM: CardInformationReqM
    ): CardInformationDataM

    suspend fun creditCardLastStatement(
        header: Map<String, String>,
        requestM: CreditCardLastStatementReqM
    ): CreditCardLastStatementDataM
    suspend fun creditCardStatement(
        header: Map<String, String>,
        requestM: CreditCardStatementNewReqM
    ): CreditCardStatementNewDataM

}