package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity

interface ContactRepository {

    fun getContactList(): List<ContactRecentEntity>
    suspend fun insertContact(userModel: ContactRecentEntity)
}