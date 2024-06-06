package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel

interface MenuRepository {
    suspend fun dashboardMenu(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse>
    suspend fun utilityBillerAllList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<ElectricityBillInfoDto>

    suspend fun allUtilityList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse>

    suspend fun operatorList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse>

    suspend fun utilityAddBiller(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto
    suspend fun utilityBillerAll(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto

}