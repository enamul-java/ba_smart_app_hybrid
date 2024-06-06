package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfReq
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfRes
import retrofit2.http.*

interface TransactionValidationApi {

    @POST("v1/transaction_validation/check_service")
    suspend fun sourceAccount(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body serviceOnOfRes: ServiceOnOfReq
    ): ServiceOnOfRes
}