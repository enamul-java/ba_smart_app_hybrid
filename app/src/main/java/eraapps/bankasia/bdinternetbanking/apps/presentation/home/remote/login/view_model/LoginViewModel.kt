package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import com.cleancode.common.data.LoadingProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.login.LoginUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {


    val loginResponse: MutableLiveData<Login> by lazy {
        MutableLiveData<Login>()
    }

    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }
    val isLoading: MutableLiveData<LoadingProgressDialog> by lazy {
        MutableLiveData<LoadingProgressDialog>()
    }


    fun login(header: Map<String, String>, loginModel: LoginModel) {


        try {
            loginUseCase.login(header, loginModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {

                        loginResponse.postValue(result.data)
                    }
                    is Resource2.Error -> {
                        errorResponse.postValue(result.exception)
                    }

                    else -> {
                        errorResponse.postValue(result.exception)
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun fingerLogin(header: Map<String, String>, loginModel: LoginModel) {

        try {
            loginUseCase.fingerLogin(header, loginModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {

                        loginResponse.postValue(result.data)
                    }
                    is Resource2.Error -> {
                        errorResponse.postValue(result.exception)
                    }

                    else -> {
                        errorResponse.postValue(result.exception)
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

}