package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ShohozRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ShohozTicketInfo @Inject constructor(
    private val shohozRepository: ShohozRepository
) {
    operator fun invoke( header: Map<String,String>, shohozRequestModel: ShohozRequestModel): Flow<Resource2<ShohozResponseDto>> = flow {
        try {
            val response = shohozRepository.getTicketInfo(header,shohozRequestModel)
             emit(Resource2.Success(response))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ShohozResponseDto>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ShohozResponseDto>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ShohozResponseDto>(message = error.message, exception = error))
        }
    }
}




