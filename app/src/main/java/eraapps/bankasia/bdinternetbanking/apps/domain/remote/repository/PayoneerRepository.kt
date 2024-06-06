package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PayoneerRequestModel

interface PayoneerRepository {

    public suspend fun getPayeeUrlInfo  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto

    public suspend fun getCardInfo  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto

    public suspend fun getPayeeGetAccessTotken  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto

    public suspend fun getPayoneerBalanceInfo  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto
    public suspend fun transfer  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto

    public suspend fun history  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto


    public suspend fun historyList  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): List<PayoneerDto>

    public suspend fun refresh  (
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto

}