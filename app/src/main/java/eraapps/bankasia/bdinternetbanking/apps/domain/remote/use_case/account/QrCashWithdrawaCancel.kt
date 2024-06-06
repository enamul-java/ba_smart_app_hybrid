package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.AccountRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class QrCashWithdrawaCancel @Inject constructor(
    private val accountRepository: AccountRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): Flow<Resource2<CommonProcedureDto>> = flow {
        try {
            val response = accountRepository.qrCashWithdrawCancel(header, accountRequestModel)
            emit(Resource2.Success(response))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error(message = error.message, exception = error))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error(message = error.message, exception = error))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource2.Error(message = error.message, exception = error))
        }
    }
}