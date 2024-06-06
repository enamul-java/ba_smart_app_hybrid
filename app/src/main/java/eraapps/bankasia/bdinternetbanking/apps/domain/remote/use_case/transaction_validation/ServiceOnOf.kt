package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_validation

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfReq
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfRes
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TransactionValidationRepository
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class ServiceOnOf @Inject constructor(
    private val repository: TransactionValidationRepository
) {

    operator fun invoke( header: Map<String,String>,serviceOnOfReq: ServiceOnOfReq):
            Flow<Resource2<ServiceOnOfRes>> = flow {
        try {
            Log.e("ServiceOnOff", "Called ServiceOnOf")
            if(serviceOnOfReq.serviceId.isNullOrEmpty()){
                var error =  ErrorHandling.exception(NullPointerException("Service ID Can Not Be Null or Empty!"))
                emit(Resource2.Error<ServiceOnOfRes>(message = error.message, exception = error))
            }else {
                //Setting Service ActType and ActCode as Service ID
                /*serviceOnOfReq.serviceId = Encript_Parameter.getRsa_encrypt(serviceOnOfReq.serviceId)
                serviceOnOfReq.actType = ServiceOnOffUtil.getActType(serviceOnOfReq.serviceId)
                serviceOnOfReq.actCode = ServiceOnOffUtil.getActCode(serviceOnOfReq.serviceId)*/

                val response = repository.checkServiceStatus(header, serviceOnOfReq)
                emit(Resource2.Success(response))
            }
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ServiceOnOfRes>(message = error.message, exception = error))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ServiceOnOfRes>(message = error.message, exception = error))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource2.Error<ServiceOnOfRes>(message = error.message, exception = error))
        }
    }

}