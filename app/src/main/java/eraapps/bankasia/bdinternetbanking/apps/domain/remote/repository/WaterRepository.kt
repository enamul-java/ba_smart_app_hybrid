package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel

interface WaterRepository {

    suspend fun waterBillInfo(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): WaterBillInfoDto

    suspend fun waterBillPayment(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): CommonProcedureDto

}