package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardSrcDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CreditCardStatementDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CreditCardOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardTypeModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel

interface CreditCardRepository {

    suspend fun creditCardSrc(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun creditCardDestination(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun creditCardchargeCalculation(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun submitCreditCardRegistration(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun creditCarddoExecute(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    suspend fun creditCardSrcList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

    suspend fun creditCardStatementList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardStatementDto>

    suspend fun getCardTypeCheckList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardTypeModel>

    suspend fun bankTypeList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

    suspend fun creditCardDestinationList(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardOptions>

    suspend fun getAccountTypes(
        header: Map<String, String>,
        requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

}