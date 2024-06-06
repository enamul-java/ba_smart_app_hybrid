package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.IvacRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.IvacRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IvacBillInfo @Inject constructor(
    private val ivacRepository: IvacRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: IvacRequestModel
    ): Flow<Resource<IvacResponseModel>> = flow {
        try {
            val response = ivacRepository.ivacBillInfo(header, requestModel)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(message = error.message))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}