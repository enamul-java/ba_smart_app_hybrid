package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.RecentTransferEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity

interface RecentTransferRepository {

    fun getrecentTransferList(type: String, sourceAc: String): List<RecentTransferEntity>

    suspend fun insertRecentTransfer(recentTransferEntity: RecentTransferEntity)
}