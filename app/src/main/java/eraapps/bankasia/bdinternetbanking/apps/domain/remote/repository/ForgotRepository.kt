package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel

interface ForgotRepository {

    suspend fun forgotPassInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto
  suspend fun forgotUserIDInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto

    suspend fun forgotPassExe(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto

    suspend fun forgotUserIdExe(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ): ForgotModelDto

    suspend fun getCardShadowAcInfo(
        header: Map<String, String>,
        requestModel: ForgotRequestModel
    ): SourceAccountBalanceDto
}
