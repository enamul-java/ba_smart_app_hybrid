package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import javax.inject.Inject

class TopupRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : TopupRepository {
    override suspend fun mobilerechargeLimitCheck(
        header: Map<String, String>,
        topupRequestModel: TopupRequestModel
    ): TopupDto {
        return api.mobilerechargeLimitCheck(
            topupRequestModel.authorization,
            header,
            topupRequestModel
        )
    }

    override suspend fun mobilerechargeExe(
        header: Map<String, String>,
        topupRequestModel: TopupRequestModel
    ): TopupDto {
        return api.mobilerechargeExe(
            topupRequestModel.authorization,
            header,
            topupRequestModel
        )
    }


}