package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import androidx.lifecycle.LiveData
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.RecentTransferEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.ContactRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.OptionsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.RecentTransferRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.UserRepository
import javax.inject.Inject

class RecentTransferRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
) : RecentTransferRepository {

    override fun getrecentTransferList(type: String,sourceAc:String): List<RecentTransferEntity> {
        return dao.getrecentTransferList(type,sourceAc)
    }

    override suspend fun insertRecentTransfer(recentTransferEntity: RecentTransferEntity) {
        dao.insertRecentTransfer(recentTransferEntity)
    }

}