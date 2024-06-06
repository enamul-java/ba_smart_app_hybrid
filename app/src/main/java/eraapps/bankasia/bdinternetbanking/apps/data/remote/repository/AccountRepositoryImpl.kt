package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.AccountApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.AccountRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import javax.inject.Inject

class AccountRepositoryImpl @Inject constructor(
    private val api: AccountApi
) : AccountRepository {


    override suspend fun sourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {


        return api.sourceAccount(
            accountRequestModel.authorization,
            //  "",
            header,
            accountRequestModel
        )
    }

    override suspend fun forigenExchangeRate(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.forigenExchangeRate(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun AccountInfo(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto {
        return api.accountInfo(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }


    override suspend fun SourceAccountBalance(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto {
        return api.soureceAccountBalance(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun primaryAccountSet(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): SourceAccountBalanceDto {
        return api.primaryAccountSet(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun sourceAcVerify(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.sourceAcVerify(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun sourcAcAdd(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.sourcAcAdd(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun getAccuntBalanceCasaResponse(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.getAccuntBalanceCasa(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun sourceAcforStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.sourceAcforStatement(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun accountStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.accountStatement(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun accountStatementReport(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.accountStatementReport(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun sourceAccountList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto> {
        return api.sourceAccountList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun sourceAcforStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<SourceAccountListDto> {
        return api.sourceAcforStatementList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun accountStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<AccountStatementDto> {
        return api.accountStatementList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun getAccuntBalanceCasaList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<AccountDetailsModel> {
        return api.getAccuntBalanceCasaList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun transferLimitCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.transferLimitCheck(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun duplicateAccountCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.duplicateAccountCheck(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun transHistory(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.transHistory(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun qrCashWithdrawlValidation(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.qrCashWithdrawlValidation(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }
    override suspend fun qrCashWithdrawExe(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.qrCashWithdrawExe(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun qrCashWithdrawCancel(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.qrCashWithdrawCancel(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun transHistoryList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto> {
        return api.transHistoryList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

}