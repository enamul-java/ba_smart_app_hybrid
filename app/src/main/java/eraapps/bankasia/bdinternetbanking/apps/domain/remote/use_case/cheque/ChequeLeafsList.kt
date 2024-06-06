package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.cheque

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ChequeResponseDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ChequeRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ChequeLeafsList @Inject constructor(
    private val chequeRepository: ChequeRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): Flow<Resource<List<ChequeResponseDto>>> = flow {
        try {
            emit(Resource.Loading())
            val response = chequeRepository.chequeLeafsList(header, accountRequestModel)
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