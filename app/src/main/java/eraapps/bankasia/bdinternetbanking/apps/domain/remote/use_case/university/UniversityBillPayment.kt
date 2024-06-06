package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.university

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.UniversityRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UniversityRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class UniversityBillPayment @Inject constructor(
    private val universityRepository: UniversityRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: UniversityRequestModel
    ): Flow<Resource<CommonProcedureDto>> = flow {
        try {
            val response = universityRepository.universityBillPayment(header, requestModel)
            emit(Resource.Success(response))
        } catch ( e: HttpException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }catch (e: IOException){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }catch (e:Exception){
            val error =  ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}