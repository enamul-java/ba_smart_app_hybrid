package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.own

import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExpireDate @Inject constructor(

    private val fundTransferOwnBankRepository: OwnBankRepository
) {
    operator fun invoke( header: Map<String,String>, fundTransferOwnBankRequest: OwnBankRequestModel): Flow<Resource2<CommonProcedureDto>> = flow {
        try {
            val response = fundTransferOwnBankRepository.getExpireDate(header,fundTransferOwnBankRequest)
             emit(Resource2.Success(response))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<CommonProcedureDto>(message = error.message, exception = error))
        }
    }
}