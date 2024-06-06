package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ReRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ReRegistrationRequestModel

interface ReRegistrationRepository {
    suspend fun deviceReRegistrationRequest(header: Map<String, String>, reRegistrationRequestModel: ReRegistrationRequestModel
    ): ReRegistrationDto

    suspend fun sentDeviceReRegistrationOtp(
    header: Map<String, String>,
    otpRequestModel: OTPRequestModel
    ): OtpDto
}