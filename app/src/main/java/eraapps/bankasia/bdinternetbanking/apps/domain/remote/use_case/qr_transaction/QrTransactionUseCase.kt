package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.qr_transaction

data class QrTransactionUseCase(
    val addCardInfo: AddCardInfo,
    val viewCardInfo: ViewCardInfo,
    val banglaQRValidation: BanglaQrValidation,
    val npsbBanglaQrPayment: NpsbBanglaQrPayment,
    val visaQrPayment: VisaQrPayment,
    val merchantChargeCalculation: MerchantChargeCalculation,
    val mmexecuteCashOut: MmExeCashout,
    val ownBankQrTransfer: OwnBankQrTransfer,
    val viewCardInfoList: ViewCardInfoList,
    val viewCardInfoListPayment: ViewCardInfoListPayment,
    val addCardExe: AddCardExe,
    val qrTransHistory: QrTransHistory,
    val qrTransHistoryList: QrTransHistoryList,
)
