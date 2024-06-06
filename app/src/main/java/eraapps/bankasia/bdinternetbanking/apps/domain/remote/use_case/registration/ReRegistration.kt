package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.registration

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ReRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ReRegistrationRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ReRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ReRegistration @Inject constructor(
    private val repository: ReRegistrationRepository
) {

    operator fun invoke(header: Map<String, String>, requestModel: OTPRequestModel): Flow<Resource<OtpDto>> = flow {
        try {
            emit(Resource.Loading<OtpDto>())
            val response = repository.sentDeviceReRegistrationOtp(header,requestModel)
            emit(Resource.Success(response))
        }catch ( e: HttpException){
            val errorHandling =  ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }catch (e: IOException){
            val errorHandling =   ErrorHandling.exception(e)
            emit(Resource.Error<OtpDto>(errorHandling.message))
        }
    }

    operator fun invoke(header: Map<String, String>, requestModel: ReRegistrationRequestModel): Flow<Resource<ReRegistrationDto>> = flow {
        try {
            emit(Resource.Loading<ReRegistrationDto>())
            val response = repository.deviceReRegistrationRequest(header,requestModel)
            emit(Resource.Success(response))
        }catch ( e: HttpException){
            val errorHandling =  ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }catch (e: IOException){
            val errorHandling =   ErrorHandling.exception(e)
            emit(Resource.Error<ReRegistrationDto>(errorHandling.message))
        }
    }
}