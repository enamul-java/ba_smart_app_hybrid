package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsBeneficiaryResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface MfsApi {

    @POST("v1/mfs/view-beneficiary")
    suspend fun mfsViewBeneficiary(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    @POST("v1/mfs/view-beneficiary-trans")
    suspend fun mfsViewBeneficiaryTrans(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    @POST("v1/mfs/add-beneficiary")
    suspend fun mfsAddBeneficiary(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    @POST("v1/mfs/beneficiary-info")
    suspend fun mfsBeneficiaryInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): MfsBeneficiaryResponse

    @POST("v1/mfs/transfer-exe")
    suspend fun mfsTransferExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto
    @POST("v1/mfs/transfer-history")
    suspend fun mfsTransHistory(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    @POST("v1/mfs/view-beneficiary-list")
    suspend fun MfsViewBeneficiaryList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: MfsRequestModel
    ): List<MfsViewResponse>

    @POST("v1/mfs/view-beneficiary-list-trans")
    suspend fun mfsViewBeneficiaryListTrans(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: MfsRequestModel
    ): List<MfsViewResponse>

    @POST("v1/mfs/transfer-history-list")
    suspend fun mfsTransHistoryList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: MfsRequestModel
    ): List<MfsViewResponse>


}