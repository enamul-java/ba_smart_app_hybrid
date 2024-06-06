package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.InsuranceBillResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel

interface InsuranceRepository {

    suspend fun insuranceBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): InsuranceBillResponse

    suspend fun getPolicyTypeList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): List<InsuranceBillResponse>

    suspend fun insuranceBillPayment(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto

}