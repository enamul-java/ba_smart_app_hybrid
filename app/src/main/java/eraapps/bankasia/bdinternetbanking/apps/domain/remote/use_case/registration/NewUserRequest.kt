package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.registration

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationVerifyDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.NewUserRequestRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.MultipartBody
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class NewUserRequest @Inject constructor(
    private val repository: NewUserRequestRepository
) {

    operator fun invoke(header: Map<String, String>, requestModel: NewUserRequestVerifyRequestModel): Flow<Resource<NewUserRequestRegistrationVerifyDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.newUserRegReqVerify(header,requestModel)
            emit(Resource.Success(response))
        }catch ( e: HttpException){
            val errorHandling =  ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }catch (e: IOException){
            val errorHandling =   ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }
    }

    operator fun invoke(header: Map<String, String>, requestModel: NewUserRequestRegistrationRequestModel): Flow<Resource<NewUserRequestRegistrationDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.newUserRegistrationRequest(header,requestModel)
            emit(Resource.Success(response))
        }catch ( e: HttpException){
            val errorHandling =  ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }catch (e: IOException){
            val errorHandling =   ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        }
    }
}