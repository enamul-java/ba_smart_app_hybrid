package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface TransactionLimitApi {

    @POST("access/v1/transaction_limit/get-transaction-limits")
    suspend fun getTransactionLimit(
        @HeaderMap map: Map<String, String>,
        @Body transactionLimitRequestModel: TransactionLimitRequestModel
    ): List<TransactionLimitDto>
}