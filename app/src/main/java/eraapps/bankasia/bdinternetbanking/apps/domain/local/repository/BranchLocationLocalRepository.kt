package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity

interface BranchLocationLocalRepository {

    fun getBranchLocations(): List<BranchEntity>
    suspend fun insertBranchLocation(branchLocationEntity: BranchEntity)
    suspend fun branchLocationCount():Int

}