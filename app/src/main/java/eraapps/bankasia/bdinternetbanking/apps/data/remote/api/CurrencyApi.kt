package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CurrencyDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CurrencyRequestModel
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface CurrencyApi {
    @POST("access/v1/calculation/currency-rate")
    suspend fun getCurrencyRate(
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CurrencyRequestModel
    ): List<CurrencyDto>
}