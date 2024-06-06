package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.OwnBankApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import javax.inject.Inject

class OwnBankRepositoryImpl @Inject constructor(
    private val api: OwnBankApi
) :OwnBankRepository{

    override suspend fun fundTransferOwnBankExecute(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {

        return api.fundTransferOwnBankExecute(
           fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun standingInstructionExecute(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {
        return api.standingInstructionExecute(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun getRequestInfo(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {
        return api.getRequestInfo(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun getRequestInfoList(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto> {
        return api.getRequestInfoList(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }


    override suspend fun addBeneficiary(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {

        return api.addBeneficiary(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )

    }

    override suspend fun viewBeneficiary(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {
        return api.viewBeneficiary(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }


    override suspend fun viewBeneficiaryList(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto> {
        return api.viewBeneficiaryList(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun getInstructionFreqencyList(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto> {

        return api.getInstructionFreqencyList(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )

    }

    override suspend fun viewBeneficiaryAll(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {
        return api.viewBeneficiaryAll(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun viewBeneficiaryListAll(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto> {
        return api.viewBeneficiaryListAll(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

    override suspend fun getExpireDate(
        header: Map<String, String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto {
        return api.getExpireDate(
            fundTransferOwnBankRequest.authorization,
            header,
            fundTransferOwnBankRequest
        )
    }

}