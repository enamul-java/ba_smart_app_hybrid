package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import androidx.lifecycle.LiveData
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.OptionsRepository
import javax.inject.Inject

class OptionsRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
) : OptionsRepository {
    override fun readLanguage(): List<OptionsEntity> {
        return dao.readLanguage()
    }


    override suspend fun insertAllOption(optionsModel: OptionsEntity) {
        dao.insertAllOption(optionsModel)
    }

    override suspend fun updateLanguage(optionsModel: OptionsEntity) {
        dao.updateLanguage(optionsModel)
    }

}