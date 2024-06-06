package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.cleancode.common.data.LoadingProgressDialog
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ElectricityBillInfoDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.MenuUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UtilityRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MenuViewModel @Inject constructor(
    private val menuUseCase: MenuUseCase
) : ViewModel() {


    val dashboardMenuResponse: MutableLiveData<List<MenuResponse>> by lazy {
        MutableLiveData<List<MenuResponse>>()
    }

    val utilityBillerAllListResponse: MutableLiveData<List<ElectricityBillInfoDto>> by lazy {
        MutableLiveData<List<ElectricityBillInfoDto>>()
    }

    val allUtilityListResponse: MutableLiveData<List<MenuResponse>> by lazy {
        MutableLiveData<List<MenuResponse>>()
    }
    val instituteTypeListResponse: MutableLiveData<List<MenuResponse>> by lazy {
        MutableLiveData<List<MenuResponse>>()
    }

    val operatorListResponse: MutableLiveData<List<MenuResponse>> by lazy {
        MutableLiveData<List<MenuResponse>>()
    }
    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }

    val utilityAddBillerResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val utilityBillerAllResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }


    fun dashboardMenu(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            menuUseCase.dashboardMenu(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        dashboardMenuResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        //  errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun utilityBillerAllList(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ) {

        try {
            menuUseCase.utilityBillerAllList(header, requestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        utilityBillerAllListResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        //  errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun allUtilityList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            menuUseCase.allUtilityList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        allUtilityListResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        //  errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }
  fun instituteTypeList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            menuUseCase.allUtilityList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        instituteTypeListResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        //  errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun operatorList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            menuUseCase.operatorList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        operatorListResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        //  errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun utilityAddBiller(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ) {


        try {
            menuUseCase.utilityAddBiller(header, requestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        utilityAddBillerResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }

                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        //  isLoading.postValue(LoadingProgressDialog(isLoading = true))
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }

    fun utilityBillerAll(
        header: Map<String, String>,
        requestModel: UtilityRequestModel
    ) {


        try {
            menuUseCase.utilityBillerAll(header, requestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        utilityBillerAllResponse.postValue(result.data)
                    }

                    is Resource.Error -> {
                        Log.e("error-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }

                    is Resource.Loading -> {
                        Log.e("loading-----", result.message.toString())
                        //  isLoading.postValue(LoadingProgressDialog(isLoading = true))
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }


}