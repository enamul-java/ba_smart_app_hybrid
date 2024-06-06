package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CustomerBasicInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SettingRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CustomerBasicRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UserAccessList @Inject constructor(
    private val settingRepository: SettingRepository
) {
    operator fun invoke( header: Map<String,String>,  requestModel: CustomerBasicRequestModel): Flow<Resource2<List<CustomerBasicInfoDto>>> = flow {
        try {
            val response = settingRepository.userAccessList(header,requestModel)
             emit(Resource2.Success(response))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<List<CustomerBasicInfoDto>>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<List<CustomerBasicInfoDto>>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<List<CustomerBasicInfoDto>>(message = error.message, exception = error))
        }
    }
}