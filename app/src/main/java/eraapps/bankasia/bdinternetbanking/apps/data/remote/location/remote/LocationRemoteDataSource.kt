package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.remote

import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.util.BaseDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import javax.inject.Inject

class LocationRemoteDataSource @Inject constructor(
    private val locationApi: LocationApi
): BaseDataSource() {
    suspend fun getBranchLocation(map: Map<String, String>) = getRemoteResult { locationApi.getBranchLocation(map) }
    suspend fun getAtmLocation(map: Map<String, String>) = getRemoteResult { locationApi.getAtmLocation(map) }
}
