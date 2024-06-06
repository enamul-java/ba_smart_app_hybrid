package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.qr_transaction

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.QrTransactionRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ViewCardInfoListPayment @Inject constructor(
    private val qrTransactionRepository: QrTransactionRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): Flow<Resource<List<CardInfoResponseDto>>> = flow {
        try {
            val response =
                qrTransactionRepository.viewCardInfoListPayment(header, qrTransactionRequestModel)
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