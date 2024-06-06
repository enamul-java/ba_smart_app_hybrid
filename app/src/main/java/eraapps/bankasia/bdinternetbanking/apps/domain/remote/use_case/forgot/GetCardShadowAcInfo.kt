package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ForgotRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetCardShadowAcInfo @Inject constructor(
    private val forgotRepository: ForgotRepository
) {

    operator fun invoke(header: Map<String, String>, forgotRequestModel: ForgotRequestModel): Flow<Resource<SourceAccountBalanceDto>> = flow {
        try {
            emit(Resource.Loading<SourceAccountBalanceDto>())
            val accountInfoesponse = forgotRepository.getCardShadowAcInfo(header,forgotRequestModel)
            emit(Resource.Success(accountInfoesponse))
        }catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<SourceAccountBalanceDto>(error.message))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<SourceAccountBalanceDto>(error.message))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<SourceAccountBalanceDto>(error.message))
        }
    }
}