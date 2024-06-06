package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.IvacRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface IvacApi {

    @POST("v1/visa/ivac-bill-info")
    suspend fun ivacBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body ivacRequestModel: IvacRequestModel
    ): IvacResponseModel

    @POST("v1/visa/ivac-getTarnsactionId")
    suspend fun getTarnsactionId(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body ivacRequestModel: IvacRequestModel
    ): IvacResponseModel

    @POST("v1/visa/ivac-bill-pay")
    suspend fun ivacBillPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body ivacRequestModel: IvacRequestModel
    ): IvacResponseModel

    @POST("v1/visa/ivac-center")
    suspend fun ivacVisaCenter(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel>

    @POST("v1/visa/ivac-visa-type")
    suspend fun ivacVisaType(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel>


}