package eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.forgot_pass_user.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import com.cleancode.common.data.LoadingProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ForgotModelDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.ForgotRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ForgotViewModel @Inject constructor(
    private val forgotUseCase: ForgotUseCase
) : ViewModel() {


    val forgotPassInfoVerifyResponse: MutableLiveData<ForgotModelDto> by lazy {
        MutableLiveData<ForgotModelDto>()
    }
    val forgotUserIDInfoVerifyResponse: MutableLiveData<ForgotModelDto> by lazy {
        MutableLiveData<ForgotModelDto>()
    }

    val forgotPassExeResponse: MutableLiveData<ForgotModelDto> by lazy {
        MutableLiveData<ForgotModelDto>()
    }
    val forgotUserIdExeResponse: MutableLiveData<ForgotModelDto> by lazy {
        MutableLiveData<ForgotModelDto>()
    }

    val getCardShadowAcInfoResponse: MutableLiveData<SourceAccountBalanceDto> by lazy {
        MutableLiveData<SourceAccountBalanceDto>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }
    val isLoading: MutableLiveData<LoadingProgressDialog> by lazy {
        MutableLiveData<LoadingProgressDialog>()
    }


    fun forgotPassInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ) {


        try {
            forgotUseCase.forgotPassInfoVerify(header, forgotRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        forgotPassInfoVerifyResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun forgotUserIDInfoVerify(
        header: Map<String, String>,
        forgotRequestModel: ForgotRequestModel
    ) {


        try {
            forgotUseCase.forgotUserIDInfoVerify(header, forgotRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        forgotUserIDInfoVerifyResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun forgotPassExe(header: Map<String, String>, forgotRequestModel: ForgotRequestModel) {


        try {
            forgotUseCase.forgotPassExe(header, forgotRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        forgotPassExeResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun forgotUserIdExe(header: Map<String, String>, forgotRequestModel: ForgotRequestModel) {


        try {
            forgotUseCase.forgotUserIdExe(header, forgotRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        forgotUserIdExeResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }    fun getCardShadowAcInfo(header: Map<String, String>, requestModel: ForgotRequestModel) {

        try {
            forgotUseCase.getCardShadowAcInfo(header, requestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        getCardShadowAcInfoResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        isLoading.postValue(LoadingProgressDialog(isLoading = true))
                    }
                    else -> {}
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

}
