package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.AccountRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtpRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CardOtp@Inject constructor(
    private val otpRepository: OtpRepository
) {
    operator fun invoke( header: Map<String,String>,otpRequestModel: OTPRequestModel): Flow<Resource2<OtpDto>> = flow {
        try {
            val cardOtpResponse = otpRepository.doCardOtp(header,otpRequestModel)
            emit(Resource2.Success(cardOtpResponse))
        }catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<OtpDto>(message = error.message, exception = error))
        }
    }
}