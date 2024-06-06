package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.UtilityDetailsDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ElectricityRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class ElectricityRepositoryImpl @Inject constructor(
    private val api: UtilityApi
) : ElectricityRepository {
    override suspend fun electricityBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto {
        return api.electricityBillInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun billsReport(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto {
        return api.billsReport(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun electricityBillPaymet(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.electricityBillPaymet(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun billsReportList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<UtilityDetailsDto> {
        return api.billsReportList(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}