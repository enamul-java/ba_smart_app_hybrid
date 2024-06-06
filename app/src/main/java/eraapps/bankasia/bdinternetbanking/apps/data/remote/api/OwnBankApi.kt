package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface OwnBankApi {

    @POST("v1/own-bank-transfer/transfer")
    suspend fun fundTransferOwnBankExecute(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    @POST("v1/standing-ins/standing-ins-transfer")
    suspend fun standingInstructionExecute(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    @POST("v1/standing-ins/request-info")
    suspend fun getRequestInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    @POST("v1/standing-ins/request-info-list")
    suspend fun getRequestInfoList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>



    @POST("v1/own-bank-transfer/add-beneficiary")
    suspend fun addBeneficiary(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    @POST("v1/own-bank-transfer/view-beneficiary")
    suspend fun viewBeneficiary(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    @POST("v1/own-bank-transfer/view-beneficiary-list")
    suspend fun viewBeneficiaryList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>


    @POST("v1/standing-ins/instruction-frequency-list")
    suspend fun getInstructionFreqencyList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>




    @POST("v1/standing-ins/instruction-expire")
    suspend fun getExpireDate(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    @POST("v1/own-bank-transfer/view-beneficiary-all")
    suspend fun viewBeneficiaryAll(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    @POST("v1/own-bank-transfer/view-beneficiary-all-list")
    suspend fun viewBeneficiaryListAll(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>

}