package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentHistoryResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozPaymentResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ShohozResponseDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozHistoryRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ShohozTicketCancelRequestModel

interface ShohozRepository {

    public suspend fun getTokenGenerate(
        header: Map<String,String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto


    public suspend fun getTicketInfo(
        header: Map<String,String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozResponseDto
    public suspend fun shohozPayment(
        header: Map<String,String>,
        shohozRequestModel: ShohozRequestModel
    ): ShohozPaymentResponseDto

    public suspend fun shohozPaymentHistory(
        header: Map<String,String>,
        shohozRequestModel: ShohozHistoryRequestModel
    ): List<ShohozPaymentHistoryResponseDto>

    public suspend fun shohozTicketCancel(
        header: Map<String,String>,
        shohozRequestModel: ShohozTicketCancelRequestModel
    ): ShohozPaymentResponseDto
}