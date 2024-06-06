package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.InsuranceBillResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class InsuranceRepositoryImpl @Inject constructor(
    private val api: UtilityApi
) : InsuranceRepository {
    override suspend fun insuranceBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): InsuranceBillResponse {
        return api.insuranceBillInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun getPolicyTypeList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<InsuranceBillResponse> {
        return api.getPolicyTypeList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun insuranceBillPayment(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.insuranceBillPayment(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}