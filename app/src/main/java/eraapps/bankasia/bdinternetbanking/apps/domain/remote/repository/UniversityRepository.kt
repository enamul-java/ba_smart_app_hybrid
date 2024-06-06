package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.UniversityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UniversityRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel

interface UniversityRepository {

    suspend fun universityBillInfo(
        header: Map<String, String>,
        requestModel: UniversityRequestModel
    ): UniversityBillInfoDto

    suspend fun universityBillPayment(
        header: Map<String, String>,
        requestModel: UniversityRequestModel
    ): CommonProcedureDto

}