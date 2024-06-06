package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.soft_token

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SoftTokenRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.SoftTokenRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ScanSoftToken @Inject constructor(
    private val softTokenRepository: SoftTokenRepository
) {
    operator fun invoke( header: Map<String,String>, softTokenRequestModel: SoftTokenRequestModel): Flow<Resource2<CommonProcedureDto>> = flow {
        try {
            val response = softTokenRepository.scanSoftToken(header,softTokenRequestModel)
             emit(Resource2.Success(response))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }
    }
}