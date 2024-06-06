package eraapps.bankasia.bdinternetbanking.apps.domain.local.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity

interface OptionsRepository {

    fun readLanguage(): List<OptionsEntity>
    suspend fun insertAllOption( optionsModel: OptionsEntity)
    suspend fun updateLanguage( optionsModel: OptionsEntity)

}