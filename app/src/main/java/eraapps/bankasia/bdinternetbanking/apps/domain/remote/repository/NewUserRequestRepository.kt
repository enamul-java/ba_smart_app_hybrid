package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationVerifyDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import okhttp3.MultipartBody

interface NewUserRequestRepository {

    suspend fun newUserRegReqVerify(
        header: Map<String, String>,
        newUserRequestVerifyRequestModel: NewUserRequestVerifyRequestModel
    ): NewUserRequestRegistrationVerifyDto

    suspend fun newUserRegistrationRequest(
        header: Map<String, String>,
        newUserRequestRegistrationRequestModel: NewUserRequestRegistrationRequestModel
    ): NewUserRequestRegistrationDto
}