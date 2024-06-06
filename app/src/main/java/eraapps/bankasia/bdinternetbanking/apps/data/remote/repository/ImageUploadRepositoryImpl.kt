package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ImageUploadRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.*
import javax.inject.Inject

class ImageUploadRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : ImageUploadRepository {


    override suspend fun userImageUpload(
        header: Map<String, String>,
        imageUploadRequestModel: ImageUploadRequestModel
    ): CommonProcedureDto {
        return api.userImageUpload(
            imageUploadRequestModel.authorization,
            header,
            imageUploadRequestModel
        )
    }


}