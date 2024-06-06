package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ReRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ReRegistrationRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ReRegistrationRequestModel
import javax.inject.Inject


class ReRegistrationRepositoryImpl @Inject constructor(
    private val api: RegistrationApi
) : ReRegistrationRepository {

    override suspend fun deviceReRegistrationRequest(
        header: Map<String, String>,
        reRegistrationRequestModel: ReRegistrationRequestModel
    ): ReRegistrationDto {
        return api.deviceReRegistrationRequest(reRegistrationRequestModel.authorization,header,reRegistrationRequestModel)
    }

    override suspend fun sentDeviceReRegistrationOtp(
        header: Map<String, String>,
        otpRequestModel: OTPRequestModel
    ): OtpDto {
        return api.sentDeviceReRegistrationOtp(otpRequestModel.authorization,header,otpRequestModel)
    }
}