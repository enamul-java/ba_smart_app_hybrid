package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel

interface TouchIdRepository {

    suspend fun touchIdInfoVerify(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun touchIdRegisterFlag(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun touchIdRegisterExe(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto

}