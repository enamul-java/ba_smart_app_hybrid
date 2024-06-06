package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ShohozApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentHistoryResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ReRegistrationRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ShohozRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozHistoryRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozTicketCancelRequestModel
import javax.inject.Inject

class ShohozRepositoryImpl  @Inject constructor(
    private val api: ShohozApi
) : ShohozRepository {

    override suspend fun getTokenGenerate(
        header: Map<String, String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto {
        return api.getTokenGenerate(
            shohozRequestModel.authorization,
            header,
            shohozRequestModel
        )
    }

    override suspend fun getTicketInfo(
        header: Map<String, String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto {
        return api.getTicketInfo(
            shohozRequestModel.authorization,
            header,
            shohozRequestModel
        )
    }

    override suspend fun shohozPayment(
        header: Map<String, String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozPaymentResponseDto {
        return api.shohozPayment(
            shohozRequestModel.authorization,
            header,
            shohozRequestModel
        )
    }

    override suspend fun shohozPaymentHistory(
        header: Map<String, String>,
        shohozRequestModel: ShohozHistoryRequestModel
    ): List<ShohozPaymentHistoryResponseDto> {
        return api.shohozPaymentHistory(
            shohozRequestModel.authorization,
            header,
            shohozRequestModel
        )
    }

    override suspend fun shohozTicketCancel(
        header: Map<String, String>,
        shohozRequestModel: ShohozTicketCancelRequestModel
    ): ShohozPaymentResponseDto {
        return api.shohozTicketCancel(
            shohozRequestModel.authorization,
            header,
            shohozRequestModel
        )
    }
}