package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfReq
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfRes
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel

interface TransactionValidationRepository {

    /**
     *
     */
    suspend fun checkServiceStatus(
        header: Map<String, String>,
        serviceOnOfReq: ServiceOnOfReq
    ): ServiceOnOfRes
}