package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UniversityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.UniversityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.UniversityRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UniversityRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class UniversityRepositoryImpl @Inject constructor(
    private val api: UniversityApi
) : UniversityRepository {
    override suspend fun universityBillInfo(
        header: Map<String, String>,
        requestModel: UniversityRequestModel
    ): UniversityBillInfoDto {
        return api.universityBillInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun universityBillPayment(
        header: Map<String, String>,
        requestModel: UniversityRequestModel
    ): CommonProcedureDto {
        return api.universityBillPayment(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}