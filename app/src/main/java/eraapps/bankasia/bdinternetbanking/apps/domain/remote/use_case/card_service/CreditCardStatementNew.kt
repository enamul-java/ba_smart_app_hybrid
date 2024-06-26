package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CreditCardStatementNewReqM
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CreditCardStatementNew @Inject constructor(
    private val repository: CardServiceRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: CreditCardStatementNewReqM
    ): Flow<Resource<CreditCardStatementNewDataM>> = flow {
        try {
            val response = repository.creditCardStatement(
                header,
                requestModel
            )
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