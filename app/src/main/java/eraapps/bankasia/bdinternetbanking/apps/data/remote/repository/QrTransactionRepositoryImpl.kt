package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.QrTransactionRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CashWithdrawRequestDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel
import javax.inject.Inject

class QrTransactionRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : QrTransactionRepository {
    override suspend fun addCardInfo(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CardInfoResponseDto {
        return api.addCardInfo(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun viewCardInfo(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto {
        return api.viewCardInfo(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun banglaQRValidation(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto {
        return api.banglaQRValidation(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun npsbBanglaQrPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto {
        return api.npsbBanglaQrPayment(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun visaQrPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto {
        return api.visaQrPayment(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun merchantChargeCalculation(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto {
        return api.merchantChargeCalculation(
            cashWithdrawRequestDto.authorization,
            header,
            cashWithdrawRequestDto
        )
    }

    override suspend fun mmexecuteCashOut(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto {
        return api.mmexecuteCashOut(
            cashWithdrawRequestDto.authorization,
            header,
            cashWithdrawRequestDto
        )
    }

    override suspend fun ownBankQrTransfer(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto {
        return api.ownBankQrTransfer(
            cashWithdrawRequestDto.authorization,
            header,
            cashWithdrawRequestDto
        )
    }

    override suspend fun viewCardInfoList(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto> {
        return api.viewCardInfoList(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun viewCardInfoListPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto> {
        return api.viewCardInfoListPayment(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun addCardExe(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto {
        return api.addCardExe(
            qrTransactionRequestModel.authorization,
            header,
            qrTransactionRequestModel
        )
    }

    override suspend fun qrTransHistory(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto {
        return api.qrTransHistory(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }

    override suspend fun qrTransHistoryList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto> {
        return api.qrTransHistoryList(
            accountRequestModel.authorization,
            header,
            accountRequestModel
        )
    }


}