package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MenuApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.MenuRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class MenuRepositoryImpl @Inject constructor(
    private val api: MenuApi
) : MenuRepository {
    override suspend fun dashboardMenu(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse> {
        return api.dashboardMenu(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun utilityBillerAllList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<ElectricityBillInfoDto> {
        return api.utilityBillerAllList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun allUtilityList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse> {
        return api.allUtilityList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun operatorList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<MenuResponse> {
        return api.operatorList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun utilityAddBiller(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.utilityAddBiller(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun utilityBillerAll(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.utilityBillerAll(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}