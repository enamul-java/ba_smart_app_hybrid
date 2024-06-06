package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case

import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.BranchLocationLocalRepository

class BranchLocationCount(private val branchLocationRepository: BranchLocationLocalRepository) {

    suspend operator fun invoke(): Int {
        return branchLocationRepository.branchLocationCount()
    }
}