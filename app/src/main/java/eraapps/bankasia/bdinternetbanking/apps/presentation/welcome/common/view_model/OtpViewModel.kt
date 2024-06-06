package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OtpDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.otp.OtpUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OTPRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class OtpViewModel @Inject constructor(
    private val otpUseCase: OtpUseCase
) : ViewModel() {


    val sentTransOtpResponse: MutableLiveData<OtpDto> by lazy {
        MutableLiveData<OtpDto>()
    }

    val doCardOtpResponse: MutableLiveData<OtpDto> by lazy {
        MutableLiveData<OtpDto>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }

    public fun sendTransOtp(
        header: Map<String, String>,
       otpRequestModel: OTPRequestModel
    ) {

        try {
            otpUseCase.sendTransOtp(header, otpRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        sentTransOtpResponse.postValue(result.data)
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
    public fun doCardOtp(
        header: Map<String, String>,
       otpRequestModel: OTPRequestModel
    ) {

        try {
            otpUseCase.doCardOtp(header, otpRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        doCardOtpResponse.postValue(result.data)
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