package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.EmiDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.EmiRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.FdrRequestModel
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface EmiApi {

    @POST("access/v1/calculation/emi")
    suspend fun getEmiCalculation(
        @HeaderMap map: Map<String, String>,
        @Body requestModel: EmiRequestModel
    ): EmiDto

    @POST("access/v1/calculation/fdr")
    suspend fun getFdrCalculation(
        @HeaderMap map: Map<String, String>,
        @Body requestModel: FdrRequestModel
    ): EmiDto

}