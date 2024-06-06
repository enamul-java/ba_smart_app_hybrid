package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationVerifyDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentHistoryResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozHistoryRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozTicketCancelRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ShohozApi {

    @POST("v1/shohoz/token-generate")
    suspend fun getTokenGenerate(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto

    @POST("v1/shohoz/shohoz-ticket-info")
    suspend fun getTicketInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto


    @POST("v1/shohoz/shohoz-payment")
    suspend fun shohozPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body shohozRequestModel: ShohozRequestModel
    ): ShohozPaymentResponseDto

    @POST("v1/shohoz/shohoz-ticket-cancel")
    suspend fun shohozTicketCancel(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body shohozRequestModel: ShohozTicketCancelRequestModel
    ): ShohozPaymentResponseDto

    @POST("v1/shohoz/shohoz-payment-history")
    suspend fun shohozPaymentHistory(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body shohozRequestModel: ShohozHistoryRequestModel
    ): List<ShohozPaymentHistoryResponseDto>

}