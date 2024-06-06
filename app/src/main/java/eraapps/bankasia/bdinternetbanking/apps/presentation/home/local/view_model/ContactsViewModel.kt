package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.contact.ContactUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.options_usecase.OptionsUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.user.UserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactsViewModel @Inject constructor(
    private val contactUseCase: ContactUseCase
) : ViewModel() {


    val addUseridResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }


    val recentContactResponse: MutableLiveData<List<ContactRecentEntity>> by lazy {
        MutableLiveData<List<ContactRecentEntity>>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }


    fun insertContact(userIdEntity: ContactRecentEntity) {

        GlobalScope.launch {
            try {
                val result = contactUseCase.invoke(userIdEntity)
                addUseridResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }


    fun getContactList() {

        GlobalScope.launch {
            try {
                val result = contactUseCase.invoke()
                recentContactResponse.postValue(result)

            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))

            }

        }
    }


}