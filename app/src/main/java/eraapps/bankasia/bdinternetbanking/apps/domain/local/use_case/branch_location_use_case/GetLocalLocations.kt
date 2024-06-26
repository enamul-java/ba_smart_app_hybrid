package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.BranchLocationLocalRepository

class GetLocalLocations(private val branchLocationRepository: BranchLocationLocalRepository) {

    suspend operator fun invoke(): List<BranchEntity> {
        return branchLocationRepository.getBranchLocations()
    }
}