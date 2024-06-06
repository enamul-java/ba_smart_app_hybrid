package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.location

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.getBrnachLocation
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BranchLocation
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.BranchLocationRepository
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetLocations @Inject constructor(
    private val branchLocationRepository: BranchLocationRepository
) {

    operator fun invoke(): Flow<Resource<List<BranchLocation>>> = flow {
        try {
            emit(Resource.Loading<List<BranchLocation>>())
            branchLocationRepository.getBranchLocation()
            val locatios =  branchLocationRepository.getBranchLocation().map {
                it.getBrnachLocation()
            }
            emit(Resource.Success<List<BranchLocation>>(locatios))
        }catch ( e: HttpException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<List<BranchLocation>>(error.message))
        }catch (e: IOException){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<List<BranchLocation>>(error.message))
        }catch (e:Exception){
            var error =  ErrorHandling.exception(e)
            emit(Resource.Error<List<BranchLocation>>(error.message))
        }
    }
}