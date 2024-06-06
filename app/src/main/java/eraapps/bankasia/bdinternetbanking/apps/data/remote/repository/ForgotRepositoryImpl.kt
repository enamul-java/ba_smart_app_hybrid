package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ForgotRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import javax.inject.Inject

class ForgotRepositoryImpl @Inject constructor(
    private val api: RegistrationApi
) : ForgotRepository {

    override suspend fun forgotPassInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto {
        return api.forgotPassInfoVerify(header, forgotRequestModel)
    }

    override suspend fun forgotUserIDInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto {
        return api.forgotUserIDInfoVerify(header, forgotRequestModel)

    }

    override suspend fun forgotPassExe(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto {
        return api.forgotPassExe(header, forgotRequestModel)
    }

    override suspend fun forgotUserIdExe(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto {
        return api.forgotUserIdExe(header, forgotRequestModel)
    }

    override suspend fun getCardShadowAcInfo(
        header: Map<String, String>,
        requestModel: ForgotRequestModel
    ): SourceAccountBalanceDto {
        return api.getCardShadowAcInfo(header, requestModel)
    }
}