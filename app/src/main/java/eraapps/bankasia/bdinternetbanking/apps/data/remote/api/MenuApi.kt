package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface MenuApi {

    @POST("access/v1/menu/dashboard")
    suspend fun dashboardMenu(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): List<MenuResponse>
    @POST("access/v1/menu/utility-biller-all-list")
    suspend fun utilityBillerAllList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): List<ElectricityBillInfoDto>

    @POST("access/v1/menu/utility-bill-list-all")
    suspend fun allUtilityList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): List<MenuResponse>

    @POST("access/v1/menu/operator-list")
    suspend fun operatorList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: AccountRequestModel
    ): List<MenuResponse>

    @POST("access/v1/menu/utility-add-biller")
    suspend fun utilityAddBiller(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto

    @POST("access/v1/menu/utility-biller-all")
    suspend fun utilityBillerAll(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UtilityRequestModel
    ): CommonProcedureDto

}