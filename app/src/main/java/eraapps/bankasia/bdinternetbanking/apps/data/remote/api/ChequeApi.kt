package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ChequeStatusModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ChequeApi {

    @POST("v1/cheque/source-account")
    suspend fun chequeSourceAccount(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/book-request")
    suspend fun chequeBookRequest(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/status-search")
    suspend fun chequeStatusSearch(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/leafs")
    suspend fun chequeLeafs(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/positive-pay-request")
    suspend fun positivePayRequest(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/stop-exe")
    suspend fun stopChequeExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/cheque/source-account-list")
    suspend fun chequeSoureceAccountList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    @POST("v1/cheque/status-search-list")
    suspend fun chequeStatusSearchList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<ChequeStatusModel>

    @POST("v1/cheque/inquiry-types")
    suspend fun chequeInqueryTypes(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>

    @POST("v1/cheque/leafs-list")
    suspend fun chequeLeafsList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>

    @POST("v1/cheque/reason")
    suspend fun chequeLeaveReason(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>


}