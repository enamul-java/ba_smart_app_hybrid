package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionLimitApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransactionLimitDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TransactionLimitRepository

import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TransactionLimitRequestModel
import javax.inject.Inject


class TransactionLimitRepositoryImpl @Inject constructor(
    private val api: TransactionLimitApi
) : TransactionLimitRepository {


    override suspend fun getTransactionLimit(
        header: Map<String, String>,
        transactionLimitRequestModel: TransactionLimitRequestModel
    ): List<TransactionLimitDto> {
        return api.getTransactionLimit(header, transactionLimitRequestModel)
    }
}