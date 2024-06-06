package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.registration.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import com.cleancode.common.data.LoadingProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ReRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.registration.ReRegistrationUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ReRegistrationRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ReRegistrationViewModel  @Inject constructor(
    private val useCase: ReRegistrationUseCase
)  : ViewModel() {


    val reRegistrationOtpResponse: MutableLiveData<OtpDto> by lazy {
        MutableLiveData<OtpDto>()
    }
    val reRegistrationRequestResponse: MutableLiveData<ReRegistrationDto> by lazy {
        MutableLiveData<ReRegistrationDto>()
    }

    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }
    val isLoading: MutableLiveData<LoadingProgressDialog> by lazy {
        MutableLiveData<LoadingProgressDialog>()
    }


    fun sentDeviceReRegistrationOtp(header: Map<String, String>,requestModel: OTPRequestModel){

        try {
            useCase.reRegistration(header,requestModel).onEach { result ->

                when(result){
                    is Resource.Success ->{
                        Log.e("data----",result.data.toString())
                        reRegistrationOtpResponse.postValue(result.data)
                    }
                    is Resource.Error ->{
                        Log.e("error-----",result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1,result.message.toString()))

                    }
                    is Resource.Loading ->{
                        Log.e("loading-----",result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }catch (e:Exception){
            errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))
            e.printStackTrace()
        }
    }


    fun deviceReRegistrationRequest(header: Map<String, String>,requestModel: ReRegistrationRequestModel){

        try {
            useCase.reRegistration(header,requestModel).onEach { result ->

                when(result){
                    is Resource.Success ->{
                        Log.e("data----",result.data.toString())
                        reRegistrationRequestResponse.postValue(result.data)
                    }
                    is Resource.Error ->{
                        Log.e("error-----",result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1,result.message.toString()))

                    }
                    is Resource.Loading ->{
                        Log.e("loading-----",result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        }catch (e:Exception){
            errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))
            e.printStackTrace()
        }
    }

}