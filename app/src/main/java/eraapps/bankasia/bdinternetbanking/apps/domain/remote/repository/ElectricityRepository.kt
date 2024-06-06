package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel

interface ElectricityRepository {

    suspend fun electricityBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto
    suspend fun billsReport(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): ElectricityBillInfoDto

    suspend fun electricityBillPaymet(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto

    suspend fun billsReportList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<UtilityDetailsDto>

}