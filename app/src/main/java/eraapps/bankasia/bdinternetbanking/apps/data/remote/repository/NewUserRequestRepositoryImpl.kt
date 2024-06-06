package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationVerifyDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.NewUserRequestRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import okhttp3.MultipartBody
import javax.inject.Inject

class NewUserRequestRepositoryImpl @Inject constructor(
    private val api: RegistrationApi
) : NewUserRequestRepository {

    override suspend fun newUserRegReqVerify(
        header: Map<String, String>,
        newUserRequestVerifyRequestModel: NewUserRequestVerifyRequestModel
    ): NewUserRequestRegistrationVerifyDto {
        return api.newUserRegReqVerify(header,newUserRequestVerifyRequestModel)
    }
    override suspend fun newUserRegistrationRequest(
        header: Map<String, String>,
        newUserRequestRegistrationRequestModel: NewUserRequestRegistrationRequestModel
    ): NewUserRequestRegistrationDto {
        return api.newUserRegistrationRequest(header,newUserRequestRegistrationRequestModel
        )

    }
}