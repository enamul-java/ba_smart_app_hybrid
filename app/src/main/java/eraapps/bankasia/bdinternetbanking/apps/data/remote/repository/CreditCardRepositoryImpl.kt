package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardStatementDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CreditCardOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CreditCardRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OwnBankRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardTypeModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import javax.inject.Inject

class CreditCardRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : CreditCardRepository {

    override suspend fun creditCardSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {

        return api.creditCardSrc(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardDestination(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.creditCardDestination(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardchargeCalculation(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.creditCardchargeCalculation(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun submitCreditCardRegistration(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.submitCreditCardRegistration(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCarddoExecute(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto {
        return api.creditCarddoExecute(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto> {
        return api.creditCardSrcList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardStatementList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardStatementDto> {
        return api.creditCardStatementList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun getCardTypeCheckList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardTypeModel> {
        return api.getCardTypeCheckList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun bankTypeList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto> {
        return api.bankTypeList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun creditCardDestinationList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardOptions> {
        return api.creditCardDestinationList(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun getAccountTypes(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto> {
        return api.getAccountTypes(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}