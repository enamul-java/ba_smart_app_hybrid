package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardListDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardSourceCardReqM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CreditCardStatementSrcList @Inject constructor(
    private val creditCardRepository: CardServiceRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        requestModel: CreditCardSourceCardReqM
    ): Flow<Resource<List<CreditCardSourceCardListDataM>>> = flow {
        try {
            val response =
                creditCardRepository.creditCardSrcList(header, requestModel)
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