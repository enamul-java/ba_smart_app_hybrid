package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

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
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface CardServiceApi {

    //Form SmartAppApi.kt
    @POST("v1/credit-statement/src-card")
    suspend fun creditCardSrc(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardSourceCardReqM
    ): CommonProcedureDto

    @POST("v1/credit-statement/src-card-list")
    suspend fun creditCardSrcList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardSourceCardReqM
    ): List<CreditCardSourceCardListDataM>
    //========================

    @POST("v1/credit-statement/card-info")
    suspend fun creditCardInformation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CardInformationReqM
    ): CardInformationDataM

    @POST("v1/credit-statement/last-statement")
    suspend fun creditCardLastStatement(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardLastStatementReqM
    ): CreditCardLastStatementDataM

    @POST("v1/credit-statement/statement")
    suspend fun creditCardStatement(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardStatementNewReqM
    ): CreditCardStatementNewDataM

}