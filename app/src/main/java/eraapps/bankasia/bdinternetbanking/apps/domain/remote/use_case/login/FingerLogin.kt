package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.login

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.toLogin
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class FingerLogin @Inject constructor(
    private val loginRepository: LoginRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        loginModel: LoginModel
    ): Flow<Resource2<Login>> = flow {
        try {

            val loginResponse = loginRepository.fingerLogin(header, loginModel).toLogin()
            emit(Resource2.Success(loginResponse))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error<Login>(message = error.message, exception = error))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error<Login>(message = error.message, exception = error))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error<Login>(message = error.message, exception = error))
        }
    }
}