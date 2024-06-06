package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.contact

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.ContactRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.OptionsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.UserRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import java.util.*
import javax.inject.Inject

class ContactUseCase @Inject constructor(
    private val repository: ContactRepositoryImpl
) {
    suspend operator fun invoke(userIdEntity: ContactRecentEntity): ResponseDetails {

        return try {
            repository.insertContact(userIdEntity)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }

    operator fun invoke(): List<ContactRecentEntity> {
        return repository.getContactList()
    }

}