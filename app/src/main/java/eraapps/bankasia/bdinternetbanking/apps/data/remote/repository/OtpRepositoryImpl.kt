package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.OtpRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import javax.inject.Inject

class OtpRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : OtpRepository {

    override suspend fun sentTransactionOtp(
        header: Map<String, String>,
        otpRequestModel: OTPRequestModel
    ): OtpDto {

        return api.sentTransactionOtp(
            otpRequestModel.authorization,
            header,
            otpRequestModel
        )
    }

    override suspend fun doCardOtp(
        header: Map<String, String>,
        otpRequestModel: OTPRequestModel
    ): OtpDto {
        return api.doCardOtp(
            otpRequestModel.authorization,
            header,
            otpRequestModel
        )
    }
}