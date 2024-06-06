package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel

interface OtpRepository {

    suspend fun sentTransactionOtp(
        header: Map<String, String>,
        otpRequestModel: OTPRequestModel
    ): OtpDto

    suspend fun doCardOtp(
        header: Map<String, String>,
        otpRequestModel: OTPRequestModel
    ): OtpDto
}