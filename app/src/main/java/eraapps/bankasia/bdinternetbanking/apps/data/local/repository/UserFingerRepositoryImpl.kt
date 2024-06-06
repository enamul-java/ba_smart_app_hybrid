package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import androidx.lifecycle.LiveData
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserFingerEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.OptionsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.UserFingerRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.UserRepository
import javax.inject.Inject

class UserFingerRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
) : UserFingerRepository {

    override fun getUserFingerInfo(userid:String): UserFingerEntity {
        return dao.getUserFingerInfo(userid)
    }


    override suspend fun insertUserFingerInfo(userModel: UserFingerEntity) {
        dao.insertUserFingerInfo(userModel)
    }

    override suspend fun updateUserFingerInfo(userModel: UserFingerEntity) {
        dao.updateUserFingerInfo(userModel)
    }

}