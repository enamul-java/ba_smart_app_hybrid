package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.EmiApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.EmiDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.EmiRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.EmiRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.FdrRequestModel
import javax.inject.Inject


class EmiRepositoryImpl @Inject constructor(
    private val api: EmiApi
) : EmiRepository {


    override suspend fun getEmiCalculation(
        header: Map<String, String>,
        requestModel: EmiRequestModel
    ): EmiDto {
        return api.getEmiCalculation(header, requestModel)
    }

    override suspend fun getFdrCalculation(
        header: Map<String, String>,
        requestModel: FdrRequestModel
    ): EmiDto {
        return api.getFdrCalculation(header, requestModel)
    }
}