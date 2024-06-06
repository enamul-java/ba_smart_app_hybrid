package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.WaterBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ElectricityRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.WaterRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class WaterRepositoryImpl @Inject constructor(
    private val api: UtilityApi
) : WaterRepository {
    override suspend fun waterBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): WaterBillInfoDto {
        return api.waterBillInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun waterBillPayment(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.waterBillPayment(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}