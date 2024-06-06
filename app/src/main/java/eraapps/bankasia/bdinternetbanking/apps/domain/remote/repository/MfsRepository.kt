package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsBeneficiaryResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel

interface MfsRepository {

    suspend fun mfsViewBeneficiary(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    suspend fun mfsViewBeneficiaryTrans(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto
    suspend fun mfsAddBeneficiary(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    suspend fun mfsBeneficiaryInfo(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): MfsBeneficiaryResponse

    suspend fun mfsTransferExe(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    suspend fun mfsTransHistory(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto

    suspend fun MfsViewBeneficiaryList(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse>

  suspend fun mfsViewBeneficiaryListTrans(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse>
  suspend fun mfsTransHistoryList(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse>


}