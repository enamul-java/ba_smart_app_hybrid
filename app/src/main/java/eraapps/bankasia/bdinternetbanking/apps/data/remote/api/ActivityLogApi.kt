package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import retrofit2.http.*

interface ActivityLogApi {

    /**
     * Navigation to activity before login
     */
    @POST("v1/activity/user-activity-log")
    suspend fun userActivityWithoutLoginLog(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    /**
     * Navigation to activity after login
     */
    @POST("v1/activity/user-activity-log")
    suspend fun userActivityLog(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    /**
     * Action Performed In Activity
     */
    @POST("v1/activity/user-action-log")
    suspend fun userActionLog(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

}