package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class MobilerechargeLimitCheck @Inject constructor(
    private val topupRepository: TopupRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        topupRequestModel: TopupRequestModel
    ): Flow<Resource<TopupDto>> = flow {
        try {
            val topupResponse = topupRepository.mobilerechargeLimitCheck(header, topupRequestModel)
            emit(Resource.Success(topupResponse))
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