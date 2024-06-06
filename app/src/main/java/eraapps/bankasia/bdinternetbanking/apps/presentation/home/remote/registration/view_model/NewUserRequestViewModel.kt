package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleancode.common.data.LoadingProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NewUserRequestRegistrationVerifyDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.registration.NewUserRequestUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NewUserRequestVerifyRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class NewUserRequestViewModel  @Inject constructor(
    private val useCase: NewUserRequestUseCase
)  : ViewModel() {


    val newUserRequestRegistrationVerifyResponse: MutableLiveData<NewUserRequestRegistrationVerifyDto> by lazy {
        MutableLiveData<NewUserRequestRegistrationVerifyDto>()
    }
    val newUserRequestRegistrationResponse: MutableLiveData<NewUserRequestRegistrationDto> by lazy {
        MutableLiveData<NewUserRequestRegistrationDto>()
    }

    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }
    val isLoading: MutableLiveData<LoadingProgressDialog> by lazy {
        MutableLiveData<LoadingProgressDialog>()
    }



     fun newUserRegReqVerify(header: Map<String, String>,requestModel: NewUserRequestVerifyRequestModel){


        try {
            useCase.newUserRequest(header,requestModel).onEach { result ->

                when(result){
                    is Resource.Success ->{
                        Log.e("data----",result.data.toString())
                        newUserRequestRegistrationVerifyResponse.postValue(result.data)
                    }
                    is Resource.Error ->{
                        Log.e("error-----",result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1,result.message.toString()))

                    }
                    is Resource.Loading ->{
                        Log.e("loading-----",result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                }
            }.launchIn(viewModelScope)
        }catch (e:Exception){
            errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))
            e.printStackTrace()
        }
    }


     fun newUserRegistrationRequest(header: Map<String, String>,requestModel: NewUserRequestRegistrationRequestModel){
        try {
            useCase.newUserRequest(header,requestModel).onEach { result ->

                when(result){
                    is Resource.Success ->{
                        Log.e("data----",result.data.toString())
                        newUserRequestRegistrationResponse.postValue(result.data)
                    }
                    is Resource.Error ->{
                        Log.e("error-----",result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1,result.message.toString()))

                    }
                    is Resource.Loading ->{
                        Log.e("loading-----",result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                }
            }.launchIn(viewModelScope)
        }catch (e:Exception){
            errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))
            e.printStackTrace()
        }
    }

}