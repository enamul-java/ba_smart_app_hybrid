package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SoftTokenApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SoftTokenRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.SoftTokenRequestModel
import javax.inject.Inject

class SoftTokenRepositoryImpl@Inject constructor(
    private val api: SoftTokenApi
) : SoftTokenRepository {

    override suspend fun scanSoftToken(
        header: Map<String, String>,
        softTokenRequestModel: SoftTokenRequestModel
    ): CommonProcedureDto {
      return api.scanSoftToken(softTokenRequestModel.authorization,header,softTokenRequestModel)
    }
}