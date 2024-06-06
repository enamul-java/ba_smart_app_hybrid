package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.password_change

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class PasswordChangeExe @Inject constructor(
    private val passwordChangeRepository: PasswordChangeRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        passwordChangeRequestModel: PasswordChangeRequestModel
    ): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            emit(Resource.Loading<CommonProcedureDto>())
            val topupResponse =
                passwordChangeRepository.doExecuteRechange(header, passwordChangeRequestModel)
            emit(Resource.Success(topupResponse))
        } catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }
    }
}