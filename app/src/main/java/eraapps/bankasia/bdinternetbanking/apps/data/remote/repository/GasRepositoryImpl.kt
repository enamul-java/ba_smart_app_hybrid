package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import javax.inject.Inject

class GasRepositoryImpl @Inject constructor(
    private val api: UtilityApi
) : GasRepository {
    override suspend fun gasBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): TopupDto {
        return api.gasBillInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun gasBillPaymet(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto {
        return api.gasBillPayment(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}