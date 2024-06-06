package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.CreditCardOptions
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.LimitModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.*
import retrofit2.http.*

interface SmartAppApi {

    @GET("access/v1/location/atm-location")
    suspend fun getBranchLocation(): List<BranchLocationDto>

    @POST("auth/v1/authenticate")
    suspend fun login(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: LoginModel
    ): LoginDto

    @POST("auth/v1/authenticate-finger")
    suspend fun fingerLogin(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: LoginModel
    ): LoginDto

    @POST("v1/topup/recharge-check")
    suspend fun mobilerechargeLimitCheck(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body topupRequestModel: TopupRequestModel
    ): TopupDto

    @POST("v1/topup/recharge-execute")
    suspend fun mobilerechargeExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body topupRequestModel: TopupRequestModel
    ): TopupDto

    @POST("v1/password/change-execute")
    suspend fun doExecuteRechange(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body passwordChangeRequestModel: PasswordChangeRequestModel
    ): CommonProcedureDto

    @POST("access/v1/image/upload-execute")
    suspend fun userImageUpload(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body imageUploadRequestModel: ImageUploadRequestModel
    ): CommonProcedureDto


    @POST("v1/otp/sent-trans-otp")
    suspend fun sentTransactionOtp(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body otpRequestModel: OTPRequestModel
    ): OtpDto

    @POST("v1/otp/generate-otp-card")
    suspend fun doCardOtp(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body otpRequestModel: OTPRequestModel
    ): OtpDto


    @POST("v1/qr/add-card-info")
    suspend fun addCardInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CardInfoResponseDto

    @POST("v1/qr/add-card-exe")
    suspend fun addCardExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/view-card-info")
    suspend fun viewCardInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/bangla-qr-validation")
    suspend fun banglaQRValidation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/bangla-qr-payment")
    suspend fun npsbBanglaQrPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/visa-qr-payment")
    suspend fun visaQrPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/mm-charge-calculation")
    suspend fun merchantChargeCalculation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: CashWithdrawRequestDto
    ): CommonProcedureDto

    @POST("v1/qr/mm-exe-cashout")
    suspend fun mmexecuteCashOut(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: CashWithdrawRequestDto
    ): CommonProcedureDto

    @POST("v1/qr/own-qr-transfer")
    suspend fun ownBankQrTransfer(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: CashWithdrawRequestDto
    ): CommonProcedureDto

    @POST("v1/qr/view-card-info-list")
    suspend fun viewCardInfoList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto>

    @POST("v1/qr/view-card-info-list-payment")
    suspend fun viewCardInfoListPayment(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body qrTransactionRequestModel: QrTransactionRequestModel
    ): List<CardInfoResponseDto>

    @POST("v1/credit/src-card")
    suspend fun creditCardSrc(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/credit/dest-ac-card")
    suspend fun creditCardDestination(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/credit/charge-calculation")
    suspend fun creditCardchargeCalculation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/credit/submit-credit-card-registration")
    suspend fun submitCreditCardRegistration(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/credit/do-execute")
    suspend fun creditCarddoExecute(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/credit/src-card-list")
    suspend fun creditCardSrcList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

    @POST("v1/credit/bank-type-list")
    suspend fun bankTypeList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

    @POST("v1/credit/card-type-check-list")
    suspend fun getCardTypeCheckList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardTypeModel>

    @POST("v1/credit/card-statement-list")
    suspend fun creditCardStatementList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardStatementDto>

    @POST("v1/credit/dest-ac-card-list")
    suspend fun creditCardDestinationList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardOptions>

    @POST("v1/credit/ac-type-list")
    suspend fun getAccountTypes(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<CreditCardSrcDto>

    @POST("v1/qr/transfer-history")
    suspend fun qrTransHistory(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto

    @POST("v1/qr/transfer-history-list")
    suspend fun qrTransHistoryList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<TransferHistoryDto>

    @POST("v1/customer/basic-info")
    suspend fun customerBasicInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CustomerBasicRequestModel
    ): CustomerBasicInfoDto

    @POST("v1/customer/limit-info")
    suspend fun limitInfo(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CustomerBasicRequestModel
    ): LimitModel

    @POST("v1/customer/complain-request")
    suspend fun customerComplain(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CustomerBasicRequestModel
    ): CommonProcedureDto

    @POST("v1/customer/userid-change-verify")
    suspend fun userIdInfoVerify(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UserRequestModel
    ): CommonProcedureDto

    @POST("v1/customer/card-info-verify")
    suspend fun cardInfoVerify(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UserRequestModel
    ): CommonProcedureDto
  @POST("v1/customer/card-pin-change-exe")
    suspend fun cardPinResetExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UserRequestModel
    ): CommonProcedureDto

    @POST("v1/customer/userid-change-execute")
    suspend fun userIdChangeExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: UserRequestModel
    ): CommonProcedureDto

    @POST("v1/customer/user-access-list")
    suspend fun userAccessList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CustomerBasicRequestModel
    ): List<CustomerBasicInfoDto>

}