package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ChequeApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ChequeResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ChequeRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ChequeStatusModel
import javax.inject.Inject

class ChequeRepositoryImpl @Inject constructor(
    private val api: ChequeApi
) : ChequeRepository {

    override suspend fun chequeSourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.chequeSourceAccount(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeBookRequest(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.chequeBookRequest(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeStatusSearch(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.chequeStatusSearch(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeLeafs(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.chequeLeafs(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun positivePayRequest(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.positivePayRequest(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun stopChequeExe(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.stopChequeExe(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeSoureceAccountList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto> {
        return api.chequeSoureceAccountList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeStatusSearchList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeStatusModel> {
        return api.chequeStatusSearchList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeInqueryTypes(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto> {
        return api.chequeInqueryTypes(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeLeafsList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto> {
        return api.chequeLeafsList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun chequeLeaveReason(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto> {
        return api.chequeLeaveReason(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }


}