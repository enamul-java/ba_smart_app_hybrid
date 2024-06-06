package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.InsuranceBillResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.*
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface UniversityApi {

    @POST("v1/university/bill-info")
    suspend fun universityBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UniversityRequestModel
    ): UniversityBillInfoDto

    @POST("v1/university/bill-payment")
    suspend fun universityBillPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UniversityRequestModel
    ): CommonProcedureDto

}