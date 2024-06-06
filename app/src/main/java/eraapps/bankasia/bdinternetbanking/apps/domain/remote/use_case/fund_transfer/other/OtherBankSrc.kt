package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.other

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CreditCardRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtherBankRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class OtherBankSrc @Inject constructor(
    private val otherBankRepository: OtherBankRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            val response = otherBankRepository.otherBankSrc(header, requestModel)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}