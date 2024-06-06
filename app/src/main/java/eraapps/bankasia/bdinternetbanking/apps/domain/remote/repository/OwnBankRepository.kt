package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel

interface OwnBankRepository {

    public suspend fun fundTransferOwnBankExecute(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    public suspend fun standingInstructionExecute(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    public suspend fun getRequestInfo(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    public suspend fun getRequestInfoList(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>




    public suspend fun addBeneficiary(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    public suspend fun viewBeneficiary(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    public suspend fun getExpireDate(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto

    public suspend fun viewBeneficiaryList(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>

    public suspend fun getInstructionFreqencyList(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>

    public suspend fun viewBeneficiaryAll(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): CommonProcedureDto


    public suspend fun viewBeneficiaryListAll(
        header: Map<String,String>,
        fundTransferOwnBankRequest: OwnBankRequestModel
    ): List<OwnBankViewBeneficiaryDto>

}