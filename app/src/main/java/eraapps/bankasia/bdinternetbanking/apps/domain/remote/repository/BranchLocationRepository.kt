package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto

interface BranchLocationRepository {

    public suspend fun getBranchLocation(): List<BranchLocationDto>
}