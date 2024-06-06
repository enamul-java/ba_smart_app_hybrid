package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CreditCardOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel

interface OtherBankRepository {

    suspend fun destinationAccount(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun OtherBankdoExecute(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun otherBankAddBeneficiaryExe(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto
    suspend fun beneficiaryDeleteExe(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun otherBankSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun getBankAcLenth(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): OtherBankOptions

    suspend fun otherBankBranchSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun destinationAccountList(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): List<OtherBankOptions>

    suspend fun destinationAccountListTrans(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): List<OtherBankOptions>

    suspend fun otherBankSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<OtherBankOptions>

    suspend fun otherBankBranchSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<OtherBankOptions>

}