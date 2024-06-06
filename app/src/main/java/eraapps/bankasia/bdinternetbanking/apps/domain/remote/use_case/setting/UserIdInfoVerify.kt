package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SettingRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CustomerBasicRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UserRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserIdInfoVerify @Inject constructor(
    private val settingRepository: SettingRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): Flow<Resource2<CommonProcedureDto>> = flow {
        try {

            val customerComplainResponse = settingRepository.userIdInfoVerify(header,requestModel)
            emit(Resource2.Success(customerComplainResponse))
        }catch (e: HttpException){
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