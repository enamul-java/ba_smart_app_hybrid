package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserFingerEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.finger.UserFingerUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.options_usecase.OptionsUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.user.UserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserFingerViewModel @Inject constructor(
    private val useCase: UserFingerUseCase
) : ViewModel() {


    private val insertUserFingerInfoResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }

    private val updateUserFingerInfoResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }

    val userFingerInfoResponse: MutableLiveData<UserFingerEntity> by lazy {
        MutableLiveData<UserFingerEntity>()
    }

    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }


    fun insertUserFingerInfo(userFingerEntity: UserFingerEntity) {

        GlobalScope.launch {
            try {
                val result = useCase.insertUserFingerInfo(userFingerEntity)
                insertUserFingerInfoResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }

    fun updateUserFingerInfo(userFingerEntity: UserFingerEntity) {

        GlobalScope.launch {
            try {
                val result = useCase.updateUserFingerInfo(userFingerEntity)
                updateUserFingerInfoResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }


    fun getUserFingerInfo(userid: String) {

        GlobalScope.launch {
            try {
                val result = useCase.getUserFingerInfo(userid)
                userFingerInfoResponse.postValue(result)

            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))

            }

        }
    }


}