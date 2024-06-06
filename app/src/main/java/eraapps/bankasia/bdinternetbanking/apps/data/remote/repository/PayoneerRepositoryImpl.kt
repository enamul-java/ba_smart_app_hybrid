package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.PayoneerApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.PayoneerDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PayoneerRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PayoneerRequestModel
import javax.inject.Inject

class PayoneerRepositoryImpl @Inject constructor(
    private val api: PayoneerApi
) :PayoneerRepository {


    override suspend fun getPayeeUrlInfo(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.getPayeeUrlInfo(
            payoneerRequestModel.authorization,
            header
        )
    }

    override suspend fun getCardInfo(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.getCardInfo(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun getPayeeGetAccessTotken(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.getPayeeGetAccessTotken(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun getPayoneerBalanceInfo(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.getPayoneerBalanceInfo(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun transfer(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.transfer(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun history(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.history(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun historyList(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): List<PayoneerDto> {
        return api.historyList(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }

    override suspend fun refresh(
        header: Map<String, String>,
        payoneerRequestModel: PayoneerRequestModel
    ): PayoneerDto {
        return api.refresh(
            payoneerRequestModel.authorization,
            header,
            payoneerRequestModel
        )
    }


}