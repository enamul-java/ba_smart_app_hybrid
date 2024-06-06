package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CardServiceApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardLastStatementDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardLastStatementReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardListDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewReqM
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import javax.inject.Inject


class CardServiceRepositoryImpl @Inject constructor(
    private val api: CardServiceApi
) : CardServiceRepository {

    override suspend fun creditCardSrc(
        header: Map<String, String>,
        requestModel: CreditCardSourceCardReqM
    ): CommonProcedureDto {
        return api.creditCardSrc(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardSrcList(
        header: Map<String, String>,
        requestModel: CreditCardSourceCardReqM
    ): List<CreditCardSourceCardListDataM> {
        return api.creditCardSrcList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    /*
    override suspend fun creditCardSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {

        return api.creditCardSrc(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSourceCardListDataM> {
        return api.creditCardSrcList(
            requestModel.authorization,
            header,
            requestModel
        )
    }*/




    override suspend fun creditCardInformation(
        header: Map<String, String>,
        requestM: CardInformationReqM
    ): CardInformationDataM {
        Log.e("Card", "Card Info called"+ requestM.toString())
        return api.creditCardInformation(
            requestM.authorization,
             header,
            requestM
        )
    }

    override suspend fun creditCardLastStatement(
        header: Map<String, String>,
        requestM: CreditCardLastStatementReqM
    ): CreditCardLastStatementDataM {
        Log.e("Card", "Card LastStatement called"+ requestM.toString())
        return api.creditCardLastStatement(
            requestM.authorization,
             header,
            requestM
        )
    }

    override suspend fun creditCardStatement(
        header: Map<String, String>,
        requestM: CreditCardStatementNewReqM
    ): CreditCardStatementNewDataM {
        Log.e("Card", "Card CardStatement called"+ requestM.toString())
        return api.creditCardStatement(
            requestM.authorization,
             header,
            requestM
        )
    }
}