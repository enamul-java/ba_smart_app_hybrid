package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import javax.inject.Inject

class PasswordChangeRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : PasswordChangeRepository {


    override suspend fun doExecuteRechange(
        header: Map<String, String>,
        passwordChangeRequestModel: PasswordChangeRequestModel
    ): CommonProcedureDto {
        return api.doExecuteRechange(
            passwordChangeRequestModel.authorization,
            header,
            passwordChangeRequestModel
        )
    }


}