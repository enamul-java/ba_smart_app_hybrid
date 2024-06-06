package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_limit

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransactionLimitDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TransactionLimitRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TransactionLimitRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class TransactionLimit @Inject constructor(
    private val repository: TransactionLimitRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        requestModel: TransactionLimitRequestModel
    ): Flow<Resource<List<TransactionLimitDto>>> = flow {
        try {
            emit(Resource.Loading<List<TransactionLimitDto>>())
            val response = repository.getTransactionLimit(header, requestModel)
            if(response.isNotEmpty()){
                emit(Resource.Success(response))
            }
            else{
                emit(Resource.Error(TextContants.no_data_found_english))
            }
        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<List<TransactionLimitDto>>(errorHandling.message))
        }
    }

}