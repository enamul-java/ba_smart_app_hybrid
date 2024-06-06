package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.payoneer

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PayoneerRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PayoneerRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PayoneerPaymentRefresh @Inject constructor(
    private val payoneerRepository: PayoneerRepository
) {
    operator fun invoke( header: Map<String,String>,payoneerRequestModel: PayoneerRequestModel): Flow<Resource2<PayoneerDto>> = flow {
        try {
            val cardOtpResponse = payoneerRepository.refresh(header,payoneerRequestModel)
            emit(Resource2.Success(cardOtpResponse))
        }catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<PayoneerDto>(message = error.message, exception = error))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<PayoneerDto>(message = error.message, exception = error))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<PayoneerDto>(message = error.message, exception = error))
        }
    }
}