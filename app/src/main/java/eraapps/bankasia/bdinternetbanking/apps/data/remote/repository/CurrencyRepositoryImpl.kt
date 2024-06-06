package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CurrencyApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CurrencyDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CurrencyRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CurrencyRequestModel
import javax.inject.Inject

class CurrencyRepositoryImpl @Inject constructor(
    private val api: CurrencyApi
) : CurrencyRepository {


    override suspend fun getCurrencyRate(
        header: Map<String, String>,
        requestModel: CurrencyRequestModel
    ): List<CurrencyDto> {
        return api.getCurrencyRate(header, requestModel)
    }
}