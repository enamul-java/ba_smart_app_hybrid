package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*

interface RegistrationApi {

    @POST("access/v1/user-registration/registration-verify")
    suspend fun newUserRegReqVerify(
        @HeaderMap map: Map<String, String>,
        @Body newUserRequestVerifyRequestModel: NewUserRequestVerifyRequestModel
    ): NewUserRequestRegistrationVerifyDto

    @POST("access/v1/user-registration/registration")
    suspend fun newUserRegistrationRequest(
        @HeaderMap map: Map<String, String>,
        @Body newUserRequestRegistrationRequestModel: NewUserRequestRegistrationRequestModel
    ): NewUserRequestRegistrationDto

    @POST("v1/user-re-registration/re-registration")
    suspend fun deviceReRegistrationRequest(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body reRegistrationRequestModel: ReRegistrationRequestModel
    ): ReRegistrationDto


    @POST("v1/otp/generate-otp-registration")
    suspend fun sentDeviceReRegistrationOtp(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body otpRequestModel: OTPRequestModel
    ): OtpDto

    @POST("access/v1/forgot/password-verify")
    suspend fun forgotPassInfoVerify(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: ForgotRequestModel
    ): ForgotModelDto

    @POST("access/v1/forgot/userid-verify")
    suspend fun forgotUserIDInfoVerify(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: ForgotRequestModel
    ): ForgotModelDto

    @POST("access/v1/forgot/password-execute")
    suspend fun forgotPassExe(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: ForgotRequestModel
    ): ForgotModelDto

    @POST("access/v1/forgot/userid-execute")
    suspend fun forgotUserIdExe(
        @HeaderMap header: Map<String, String>,
        @Body loginModel: ForgotRequestModel
    ): ForgotModelDto

    @POST("access/v1/forgot/card-shadow-account")
    suspend fun getCardShadowAcInfo(
        @HeaderMap header: Map<String, String>,
        @Body requestModel: ForgotRequestModel
    ): SourceAccountBalanceDto


}