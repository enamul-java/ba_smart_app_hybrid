package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.other

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtherBankRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class DestinationAccountList @Inject constructor(

    private val otherBankRepository: OtherBankRepository
) {
    operator fun invoke( header: Map<String,String>,requestModel: AccountRequestModel): Flow<Resource2<List<OtherBankOptions>>> = flow {
        try {
            val response = otherBankRepository.destinationAccountList(header,requestModel)
            Log.e("response usecase-->",response.toString())
             emit(Resource2.Success(response))
        }catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            //emit(Resource.Error(error.message))
            emit(Resource2.Error<List<OtherBankOptions>>(message = error.message, exception = error))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<List<OtherBankOptions>>(message = error.message, exception = error))
            //emit(Resource.Error(error.message))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource2.Error<List<OtherBankOptions>>(message = error.message, exception = error))
            //emit(Resource.Error(error.message))
        }
    }
}