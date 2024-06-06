package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtpRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SendTransOtp@Inject constructor(
    private val otpRepository: OtpRepository
) {
    operator fun invoke( header: Map<String,String>,otpRequestModel: OTPRequestModel): Flow<Resource2<OtpDto>> = flow {
        try {
            val loginResponse = otpRepository.sentTransactionOtp(header,otpRequestModel)
            emit(Resource2.Success(loginResponse))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }
    }
}