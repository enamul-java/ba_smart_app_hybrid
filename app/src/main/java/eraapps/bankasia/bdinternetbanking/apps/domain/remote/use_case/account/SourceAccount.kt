package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.AccountRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject
/*
class SourceAccount@Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke( header: Map<String,String>,accountRequestModel: AccountRequestModel): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            emit(Resource.Loading<CommonProcedureDto>())
            val loginResponse = accountRepository.sourceAccount(header,accountRequestModel)
            emit(Resource.Success(loginResponse))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))

        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
           // emit(Resource.Error<CommonProcedureDto>(error.message))
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
           // emit(Resource.Error<CommonProcedureDto>(error.message))
        }
    }
}
*/

class SourceAccount@Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke( header: Map<String,String>,accountRequestModel: AccountRequestModel): Flow<Resource2<CommonProcedureDto>> = flow {
        try {

            val loginResponse = accountRepository.sourceAccount(header,accountRequestModel)
            emit(Resource2.Success(loginResponse))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error(message = error.message, exception = error))

        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            // emit(Resource.Error<CommonProcedureDto>(error.message))
            emit(Resource2.Error(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error(message = error.message, exception = error))
            // emit(Resource.Error<CommonProcedureDto>(error.message))
        }
    }
}