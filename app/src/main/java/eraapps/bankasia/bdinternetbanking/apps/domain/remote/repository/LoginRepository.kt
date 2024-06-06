package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel

interface LoginRepository {

    suspend fun login(header: Map<String, String>, loginModel: LoginModel): LoginDto
    suspend fun fingerLogin(header: Map<String, String>, loginModel: LoginModel): LoginDto
}
