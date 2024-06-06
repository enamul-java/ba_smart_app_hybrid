package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel

interface AccountRepository {

    suspend fun sourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun forigenExchangeRate(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun SourceAccountBalance(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto

    suspend fun primaryAccountSet(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto
    suspend fun sourceAcVerify(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
   suspend fun sourcAcAdd(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun getAccuntBalanceCasaResponse(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun sourceAcforStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun accountStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun accountStatementReport(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun AccountInfo(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto

    suspend fun sourceAccountList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    suspend fun sourceAcforStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    suspend fun accountStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<AccountStatementDto>

    suspend fun getAccuntBalanceCasaList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<AccountDetailsModel>

    suspend fun transferLimitCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun duplicateAccountCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun transHistory(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
    suspend fun qrCashWithdrawlValidation(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
    suspend fun qrCashWithdrawExe(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun qrCashWithdrawCancel(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
    suspend fun transHistoryList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto>

}