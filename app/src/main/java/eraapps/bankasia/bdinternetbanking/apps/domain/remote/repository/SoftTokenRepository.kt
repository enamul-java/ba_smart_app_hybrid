package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.SoftTokenRequestModel

interface SoftTokenRepository {

    public suspend fun scanSoftToken(
        header: Map<String,String>,
        softTokenRequestModel: SoftTokenRequestModel
    ): CommonProcedureDto


}