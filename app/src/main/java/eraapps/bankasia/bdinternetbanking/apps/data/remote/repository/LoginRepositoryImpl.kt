package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : LoginRepository {

    override suspend fun login(header: Map<String, String>, loginModel: LoginModel): LoginDto {
        return api.login(header, loginModel)
    }

    override suspend fun fingerLogin(
        header: Map<String, String>,
        loginModel: LoginModel
    ): LoginDto {
        return api.fingerLogin(header, loginModel)
    }
}