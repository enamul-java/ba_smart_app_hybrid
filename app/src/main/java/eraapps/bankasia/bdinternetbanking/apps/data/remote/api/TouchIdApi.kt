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

interface TouchIdApi {

    @POST("v1/touchid/info-verify")
    suspend fun touchIdInfoVerify(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/touchid/register-flag")
    suspend fun touchIdRegisterFlag(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): CommonProcedureDto


    @POST("v1/touchid/register-execute")
    suspend fun touchIdRegisterExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): CommonProcedureDto

}