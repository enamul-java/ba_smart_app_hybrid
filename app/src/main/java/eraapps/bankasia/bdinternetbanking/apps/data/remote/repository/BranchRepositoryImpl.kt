package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.BranchLocationRepository
import javax.inject.Inject

class BranchRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : BranchLocationRepository {

    //    override suspend fun getBranchLocation(): List<BranchLocationDto> {
//        return api.getBranchLocation()
//    }
    override suspend fun getBranchLocation(): List<BranchLocationDto> {
        TODO("Not yet implemented")
    }

}