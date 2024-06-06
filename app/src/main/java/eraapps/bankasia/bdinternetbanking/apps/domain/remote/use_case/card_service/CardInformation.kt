package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard.CardInformationReqM
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class CardInformation @Inject constructor(
    private val repository: CardServiceRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: CardInformationReqM
    ): Flow<Resource<CardInformationDataM>> = flow {
        try {

            val response = repository.creditCardInformation(
                header,
                requestModel
            )
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            Log.e("response",e.response().toString())
            Log.e("token", requestModel.authorization)
            emit(Resource.Error(error.message+"HttpException"+requestModel.toString()))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}