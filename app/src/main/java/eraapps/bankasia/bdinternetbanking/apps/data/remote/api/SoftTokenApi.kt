package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.SoftTokenRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface SoftTokenApi {

    @POST("v1/soft-token/scan-token")
    suspend fun scanSoftToken(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body softTokenRequestModel: SoftTokenRequestModel
    ): CommonProcedureDto



}