package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.emi_calculation

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.EmiDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.EmiRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.EmiRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.FdrRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class Emi @Inject constructor(
    private val repository: EmiRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        requestModel: EmiRequestModel
    ): Flow<Resource<EmiDto>> = flow {
        try {
            emit(Resource.Loading<EmiDto>())
            val response = repository.getEmiCalculation(header, requestModel)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<EmiDto>(errorHandling.message))
        }
    }

    operator fun invoke(
        header: Map<String, String>,
        requestModel: FdrRequestModel
    ): Flow<Resource<EmiDto>> = flow {
        try {
            emit(Resource.Loading<EmiDto>())
            val response = repository.getFdrCalculation(header, requestModel)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<EmiDto>(errorHandling.message))
        }
    }

}