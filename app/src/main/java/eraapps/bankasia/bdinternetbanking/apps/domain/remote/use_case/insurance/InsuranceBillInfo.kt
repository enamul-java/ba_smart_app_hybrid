package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.insurance

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.InsuranceBillResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ElectricityRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.InsuranceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class InsuranceBillInfo @Inject constructor(
    private val insuranceRepository: InsuranceRepository
) {
    operator fun invoke(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ): Flow<Resource<InsuranceBillResponse>> = flow {
        try {
            val response = insuranceRepository.insuranceBillInfo(header, requestModel)
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