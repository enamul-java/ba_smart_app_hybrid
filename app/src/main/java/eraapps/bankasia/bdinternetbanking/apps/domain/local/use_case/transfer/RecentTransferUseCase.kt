package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.transfer

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.ContactRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.OptionsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.RecentTransferRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.UserRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.*
import java.util.*
import javax.inject.Inject

class RecentTransferUseCase @Inject constructor(
    private val repository: RecentTransferRepositoryImpl
) {
    suspend operator fun invoke(recentTransferEntity: RecentTransferEntity): ResponseDetails {

        return try {
            repository.insertRecentTransfer(recentTransferEntity)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }

    operator fun invoke(type:String,sourceAc:String): List<RecentTransferEntity> {
        return repository.getrecentTransferList(type,sourceAc)
    }

}