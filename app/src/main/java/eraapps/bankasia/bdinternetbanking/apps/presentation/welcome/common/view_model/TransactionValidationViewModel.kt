package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfReq
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.serviceOnOf.ServiceOnOfRes
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_validation.TransactionValidationUseCase
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class TransactionValidationViewModel @Inject constructor(
    private val tranValUseCase: TransactionValidationUseCase
) : ViewModel() {

    val serviceOnOfResponse: MutableLiveData<ServiceOnOfRes> by lazy {
        MutableLiveData<ServiceOnOfRes>()
    }
    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }

    fun serviceOnOf(
        header: Map<String, String>,
        serviceOnOfReq: ServiceOnOfReq
    ) {
        try {
            tranValUseCase.serviceOnOf(header, serviceOnOfReq).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        serviceOnOfResponse.postValue(result.data)
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