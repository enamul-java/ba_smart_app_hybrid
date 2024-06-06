package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_validation

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionValidationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.TransactionValidationRepositoryImpl
import javax.inject.Inject

data class TransactionValidationUseCase @Inject constructor(
    val serviceOnOf: ServiceOnOf
)