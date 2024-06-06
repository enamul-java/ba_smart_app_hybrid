package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.setting

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CustomerBasicInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SettingRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CustomerBasicRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CustomerBasicInfo @Inject constructor(
    private val settingRepository: SettingRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): Flow<Resource2<CustomerBasicInfoDto>> = flow {
        try {

            val sourceAccountBalanceResponse = settingRepository.customerBasicInfo(header,requestModel)
            emit(Resource2.Success(sourceAccountBalanceResponse))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CustomerBasicInfoDto>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CustomerBasicInfoDto>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CustomerBasicInfoDto>(message = error.message, exception = error))
        }
    }
}