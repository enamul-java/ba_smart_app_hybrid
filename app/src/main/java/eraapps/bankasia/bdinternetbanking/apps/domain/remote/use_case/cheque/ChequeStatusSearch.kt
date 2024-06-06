package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.cheque

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ChequeRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChequeStatusSearch @Inject constructor(
    private val chequeRepository: ChequeRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            emit(Resource.Loading<CommonProcedureDto>())
            val loginResponse = chequeRepository.chequeStatusSearch(header, accountRequestModel)
            emit(Resource.Success(loginResponse))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }
    }
}