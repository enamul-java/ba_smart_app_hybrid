package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PayoneerRequestModel
import retrofit2.http.*

interface PayoneerApi {

    @GET("v1/payoneer/url-info")
    suspend fun getPayeeUrlInfo
                (
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
    ): PayoneerDto

    @POST("v1/payoneer/card-info")
    suspend fun getCardInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto

    @POST("v1/payoneer/access-token")
    suspend fun getPayeeGetAccessTotken(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto

    @POST("v1/payoneer/get-balance")
    suspend fun getPayoneerBalanceInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto

    @POST("v1/payoneer/transfer")
    suspend fun transfer(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto

    @POST("v1/payoneer/transaction-history")
    suspend fun history(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto

    @POST("v1/payoneer/transaction-history_list")
    suspend fun historyList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): List<PayoneerDto>


    @POST("v1/payoneer/refresh")
    suspend fun refresh(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: PayoneerRequestModel
    ): PayoneerDto
}


