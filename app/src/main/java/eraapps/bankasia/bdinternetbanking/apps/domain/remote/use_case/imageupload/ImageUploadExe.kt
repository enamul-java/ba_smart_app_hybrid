package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.imageupload

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ImageUploadRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ImageUploadRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ImageUploadExe @Inject constructor(
    private val imageUploadRepository: ImageUploadRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        imageUploadRequestModel: ImageUploadRequestModel
    ): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            emit(Resource.Loading<CommonProcedureDto>())
            val topupResponse =
                imageUploadRepository.userImageUpload(header, imageUploadRequestModel)
            emit(Resource.Success(topupResponse))
        } catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error<CommonProcedureDto>(error.message))
        }
    }
}