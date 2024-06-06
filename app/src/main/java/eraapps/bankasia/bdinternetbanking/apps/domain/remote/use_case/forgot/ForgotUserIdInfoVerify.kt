package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ForgotRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ForgotUserIdInfoVerify @Inject constructor(
    private val forgotRepository: ForgotRepository
) {

    operator fun invoke(header: Map<String, String>, forgotRequestModel: ForgotRequestModel): Flow<Resource<ForgotModelDto>> = flow {
        try {
            emit(Resource.Loading())
            val loginResponse = forgotRepository.forgotUserIDInfoVerify(header,forgotRequestModel)
            emit(Resource.Success(loginResponse))
        }catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}