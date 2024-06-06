package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.remote

import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local.ATMLocationDao
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.HeaderMap

interface LocationApi {

    @GET("access/v1/location/branch-location")
    suspend fun getBranchLocation(
        @HeaderMap map: Map<String, String>
    ): Response<List<BranchLocationDto>>

    @GET("access/v1/location/atm-location")
    suspend fun getAtmLocation(
        @HeaderMap map: Map<String, String>
    ): Response<List<ATMLocationDto>>
}