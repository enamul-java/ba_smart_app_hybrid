package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case

data class BranchLocaltionLocalUseCase(
    val addBranchLocation: AddBranchLocation,
    val getLocalLocations: GetLocalLocations,
    val branchLocationCount: BranchLocationCount,
)
