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

interface UtilityApiBackup {

    @POST("v1/electricity/bill-info")
    suspend fun electricityBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto

    @POST("v1/utility/bills-report")
    suspend fun billsReport(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto

    @POST("v1/utility/bills-report-list")
    suspend fun billsReportList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): List<UtilityDetailsDto>

    @POST("v1/electricity/bill-payment")
    suspend fun electricityBillPaymet(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto


    @POST("v1/water/bill-info")
    suspend fun waterBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): WaterBillInfoDto

    @POST("v1/water/bill-payment")
    suspend fun waterBillPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto

    @POST("v1/insurance/bill-info")
    suspend fun insuranceBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): InsuranceBillResponse

    @POST("v1/insurance/bill-payment")
    suspend fun insuranceBillPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto

    @POST("v1/gas/bill-info")
    suspend fun gasBillInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): TopupDto

    @POST("v1/gas/bill-payment")
    suspend fun gasBillPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto

    @POST("v1/insurance/policy-type-list")
    suspend fun getPolicyTypeList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): List<InsuranceBillResponse>


}