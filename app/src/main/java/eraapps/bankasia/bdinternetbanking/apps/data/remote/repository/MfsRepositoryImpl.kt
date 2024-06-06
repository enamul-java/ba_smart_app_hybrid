package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MfsApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsBeneficiaryResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.MfsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.QrTransactionRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import javax.inject.Inject

class MfsRepositoryImpl @Inject constructor(
    private val api: MfsApi
) : MfsRepository {
    override suspend fun mfsViewBeneficiary(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto {
        return api.mfsViewBeneficiary(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsViewBeneficiaryTrans(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto {
        return api.mfsViewBeneficiaryTrans(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsAddBeneficiary(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto {
        return api.mfsAddBeneficiary(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsBeneficiaryInfo(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): MfsBeneficiaryResponse {
        return api.mfsBeneficiaryInfo(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsTransferExe(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto {
        return api.mfsTransferExe(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsTransHistory(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): CommonProcedureDto {
        return api.mfsTransHistory(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }


    override suspend fun MfsViewBeneficiaryList(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse> {
        return api.MfsViewBeneficiaryList(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsViewBeneficiaryListTrans(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse> {
        return api.mfsViewBeneficiaryListTrans(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }

    override suspend fun mfsTransHistoryList(
        header: Map<String, String>,
        mfsRequestModel: MfsRequestModel
    ): List<MfsViewResponse> {
        return api.mfsTransHistoryList(
            mfsRequestModel.authorization,
            header,
            mfsRequestModel
        )
    }


}