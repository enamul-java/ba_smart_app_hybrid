package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.options_usecase.OptionsUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.user.UserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserViewModel @Inject constructor(
    private val useCase: UserUseCase
) : ViewModel() {


    val addUseridResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }

    val deleteUseridResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }


    val userIdResponse: MutableLiveData<List<UserIdEntity>> by lazy {
        MutableLiveData<List<UserIdEntity>>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }


    fun insertUserId(userIdEntity: UserIdEntity) {

        GlobalScope.launch {
            try {
                val result = useCase.invoke(userIdEntity)
                addUseridResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }

    fun deleteUserId(userid: String) {

        GlobalScope.launch {
            try {
                val result = useCase.invoke(userid)
                deleteUseridResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }



    fun getUserId() {

        GlobalScope.launch {
            try {
                val result = useCase.invoke()
                userIdResponse.postValue(result)

            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))

            }

        }
    }


}