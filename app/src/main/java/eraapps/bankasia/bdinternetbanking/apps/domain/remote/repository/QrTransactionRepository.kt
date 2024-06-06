package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CardInfoResponseDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CashWithdrawRequestDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.QrTransactionRequestModel

interface QrTransactionRepository {

    suspend fun addCardInfo(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CardInfoResponseDto

    suspend fun viewCardInfo(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    suspend fun banglaQRValidation(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    suspend fun npsbBanglaQrPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    suspend fun visaQrPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    suspend fun merchantChargeCalculation(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto

    suspend fun mmexecuteCashOut(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto

    suspend fun ownBankQrTransfer(
        header: Map<String, String>,
        cashWithdrawRequestDto: CashWithdrawRequestDto
    ): CommonProcedureDto

    suspend fun viewCardInfoList(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto>

    suspend fun viewCardInfoListPayment(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto>

    suspend fun addCardExe(
        header: Map<String, String>,
        qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    suspend fun qrTransHistory(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    suspend fun qrTransHistoryList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto>


}