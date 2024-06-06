package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TouchIdApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TouchIdRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import javax.inject.Inject

class TouchIdRepositoryImpl @Inject constructor(
    private val api: TouchIdApi
) : TouchIdRepository {
    override suspend fun touchIdInfoVerify(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.touchIdInfoVerify(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun touchIdRegisterFlag(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.touchIdRegisterFlag(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun touchIdRegisterExe(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.touchIdRegisterExe(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}