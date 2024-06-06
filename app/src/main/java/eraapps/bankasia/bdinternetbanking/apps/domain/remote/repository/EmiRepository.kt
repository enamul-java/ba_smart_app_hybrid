package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.EmiDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.EmiRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.FdrRequestModel


interface EmiRepository {
    suspend fun getEmiCalculation(header: Map<String, String>, requestModel: EmiRequestModel
    ): EmiDto
    suspend fun getFdrCalculation(header: Map<String, String>, requestModel: FdrRequestModel
    ): EmiDto
}