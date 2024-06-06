package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity

interface UserRepository {

    fun getUser(): List<UserIdEntity>
    suspend fun insertUserId( userModel: UserIdEntity)
}