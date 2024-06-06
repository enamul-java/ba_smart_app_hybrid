package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransactionLimitDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TransactionLimitRequestModel


interface TransactionLimitRepository {
    suspend fun getTransactionLimit(header: Map<String, String>, transactionLimitRequestModel: TransactionLimitRequestModel
    ): List<TransactionLimitDto>
}