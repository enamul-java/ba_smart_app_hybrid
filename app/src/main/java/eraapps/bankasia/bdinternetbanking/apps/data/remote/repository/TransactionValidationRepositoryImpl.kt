package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository


import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionValidationApi
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfReq
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfRes
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TransactionValidationRepository
import javax.inject.Inject

class TransactionValidationRepositoryImpl @Inject constructor(
    private val api: TransactionValidationApi
    ):TransactionValidationRepository{

    override suspend fun checkServiceStatus(
        header: Map<String, String>,
        serviceOnOfReq: ServiceOnOfReq
    ): ServiceOnOfRes {
        Log.e("ServiceOnOff", "Called checkServiceStatus in TransactionValidationRepositoryImpl")
        return api.sourceAccount(serviceOnOfReq.authorization,header,serviceOnOfReq)
    }
}