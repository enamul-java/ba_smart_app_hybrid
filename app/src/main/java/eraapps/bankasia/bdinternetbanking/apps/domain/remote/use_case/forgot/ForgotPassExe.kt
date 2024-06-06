package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.toLogin
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ForgotRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ForgotPassExe @Inject constructor(
    private val forgotRepository: ForgotRepository
) {

    operator fun invoke(header: Map<String, String>, forgotRequestModel: ForgotRequestModel): Flow<Resource<ForgotModelDto>> = flow {
        try {
            emit(Resource.Loading<ForgotModelDto>())
            val forgotPassExeResponse = forgotRepository.forgotPassExe(header,forgotRequestModel)
            emit(Resource.Success(forgotPassExeResponse))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<ForgotModelDto>(error.message))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<ForgotModelDto>(error.message))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<ForgotModelDto>(error.message))
        }
    }
}