package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserFingerEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity

interface UserFingerRepository {

    fun getUserFingerInfo(userid:String): UserFingerEntity

    suspend fun insertUserFingerInfo( userModel: UserFingerEntity)
    suspend fun updateUserFingerInfo( userModel: UserFingerEntity)
}