package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import retrofit2.http.*

interface AccountApi {

    @POST("v1/account/source-account")
    suspend fun sourceAccount(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/source-account-balance")
    suspend fun soureceAccountBalance(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto

    @POST("v1/account/primary-account-set")
    suspend fun primaryAccountSet(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto

    @POST("v1/account/source-account-verify")
    suspend fun sourceAcVerify(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/source-account-add")
    suspend fun sourcAcAdd(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/account-info")
    suspend fun accountInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto

    @POST("v1/account/forigen-exchange-rate")
    suspend fun forigenExchangeRate(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/account-info-all")
    suspend fun getAccuntBalanceCasa(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/source-account-statement")
    suspend fun sourceAcforStatement(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/account-statement")
    suspend fun accountStatement(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("access/v1/report/account-statement-exe")
    suspend fun accountStatementReport(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/source-account-list")
    suspend fun sourceAccountList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    @POST("v1/account/source-account-statement-list")
    suspend fun sourceAcforStatementList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto>

    @POST("v1/account/account-statement-list")
    suspend fun accountStatementList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<AccountStatementDto>

    @POST("v1/account/account-casa-list")
    suspend fun getAccuntBalanceCasaList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<AccountDetailsModel>


    @POST("v1/account/transfer-limit-check")
    suspend fun transferLimitCheck(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/account/duplicate-check")
    suspend fun duplicateAccountCheck(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/fund/transfer-history")
    suspend fun transHistory(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto


    @POST("v1/fund/transfer-history-list")
    suspend fun transHistoryList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto>

    @POST("v1/qr/cash-withdrawl-validation")
    suspend fun qrCashWithdrawlValidation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/cash-withdraw-exe")
    suspend fun qrCashWithdrawExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/cash-withdraw-cancel")
    suspend fun qrCashWithdrawCancel(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto
}