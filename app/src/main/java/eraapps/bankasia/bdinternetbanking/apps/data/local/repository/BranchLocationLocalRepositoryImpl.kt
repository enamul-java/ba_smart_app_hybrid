package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.BranchLocationLocalRepository
import javax.inject.Inject
class BranchLocationLocalRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
    ) :BranchLocationLocalRepository {

    override fun getBranchLocations(): List<BranchEntity> {
        return dao.getBranchLocations()
    }

    override suspend fun insertBranchLocation(branchLocationEntity: BranchEntity) {
        dao.insertBranchLocaition(branchLocationEntity)
    }

    override suspend fun branchLocationCount(): Int {
        return dao.branchLocationCount()
    }
}