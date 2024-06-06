package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.vcard

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.VCardRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetVCardList @Inject constructor(
    private val repository: VCardRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: VCardTokenGenReqModel
    ): Flow<Resource<List<VCardDetailsResDataM>>> = flow {
        try {

            val response = repository.getVCardList(
                header,
                requestModel
            )
            for (i in 0 until response.size){
                Log.e("response", response.get(i).toString())
            }
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