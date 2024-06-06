package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CurrencyDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CurrencyRequestModel

interface CurrencyRepository {
    suspend fun getCurrencyRate(header: Map<String, String>, requestModel: CurrencyRequestModel
    ): List<CurrencyDto>
}