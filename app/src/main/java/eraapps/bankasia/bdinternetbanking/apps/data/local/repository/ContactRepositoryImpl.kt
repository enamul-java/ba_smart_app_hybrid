package eraapps.bankasia.bdinternetbanking.apps.data.local.repository

import android.util.Log
import androidx.lifecycle.LiveData
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDao
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.ContactRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.OptionsRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.UserRepository
import javax.inject.Inject

class ContactRepositoryImpl @Inject constructor(
    private val dao: SmartAppDao
) : ContactRepository {

    override fun getContactList(): List<ContactRecentEntity> {
        return dao.getContactList()
    }


    override suspend fun insertContact(userModel: ContactRecentEntity) {

      var i =   dao.checkExistionContact(userModel.phone);
        Log.e("Recent contact = ",i.toString())
        if(i>0){
            Log.e("Recent contact already",i.toString())
        }else{
            dao.insertContact(userModel)
        }


    }

}