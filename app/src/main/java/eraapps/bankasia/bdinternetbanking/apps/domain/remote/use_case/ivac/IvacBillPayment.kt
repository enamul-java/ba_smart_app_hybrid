package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.IvacRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.MfsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.QrTransactionRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.IvacRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class IvacBillPayment @Inject constructor(
    private val ivacRepository: IvacRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: IvacRequestModel
    ): Flow<Resource<IvacResponseModel>> = flow {
        try {
            val response = ivacRepository.ivacBillPayment(header, requestModel)
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