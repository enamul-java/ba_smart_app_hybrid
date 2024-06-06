package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.currency_rate

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CurrencyDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CurrencyRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CurrencyRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CurrencyRate @Inject constructor(
    private val repository: CurrencyRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        requestModel: CurrencyRequestModel
    ): Flow<Resource<List<CurrencyDto>>> = flow {
        try {
            emit(Resource.Loading<List<CurrencyDto>>())
            val response = repository.getCurrencyRate(header, requestModel)
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
            emit(Resource.Error<List<CurrencyDto>>(errorHandling.message))
        }
    }



}