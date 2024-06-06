package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.OtherBankApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CreditCardOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CreditCardRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtherBankRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import javax.inject.Inject

class OtherBankRepositoryImpl @Inject constructor(
    private val api: OtherBankApi
) : OtherBankRepository {

    override suspend fun destinationAccount(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.destinationAccount(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun OtherBankdoExecute(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.OtherBankdoExecute(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun otherBankAddBeneficiaryExe(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.otherBankAddBeneficiaryExe(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun beneficiaryDeleteExe(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.beneficiaryDeleteExe(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun otherBankSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.otherBankSrc(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun getBankAcLenth(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): OtherBankOptions {
        return api.getBankAcLenth(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun otherBankBranchSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.otherBankBranchSrc(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun destinationAccountList(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): List<OtherBankOptions> {
        return api.destinationAccountList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun destinationAccountListTrans(
        header: Map<String, String>,
        requestModel: AccountRequestModel
    ): List<OtherBankOptions> {
        return api.destinationAccountListTrans(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun otherBankSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<OtherBankOptions> {
        return api.otherBankSrcList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun otherBankBranchSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<OtherBankOptions> {
        return api.otherBankBranchSrcList(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}