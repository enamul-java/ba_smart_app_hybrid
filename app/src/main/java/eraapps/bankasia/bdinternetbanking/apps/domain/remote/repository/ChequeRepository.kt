package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ChequeStatusModel

interface ChequeRepository {

    suspend fun chequeSourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun chequeBookRequest(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun chequeStatusSearch(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun chequeLeafs(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
 suspend fun positivePayRequest(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun stopChequeExe(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto


    suspend fun chequeSoureceAccountList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    suspend fun chequeStatusSearchList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeStatusModel>

    suspend fun chequeInqueryTypes(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>

    suspend fun chequeLeafsList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>

    suspend fun chequeLeaveReason(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<ChequeResponseDto>


}