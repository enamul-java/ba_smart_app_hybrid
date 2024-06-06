package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import androidx.lifecycle.LiveData
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.OptionsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.UserRepository
import javax.inject.Inject

class UserRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
) : UserRepository {

    override fun getUser(): List<UserIdEntity> {
        return dao.getUser()
    }

    override suspend fun insertUserId(userModel: UserIdEntity) {
        dao.insertUserId(userModel)
    }

    suspend fun deleteUserId( ) {
        dao.deleteAllUserId()
    }


}