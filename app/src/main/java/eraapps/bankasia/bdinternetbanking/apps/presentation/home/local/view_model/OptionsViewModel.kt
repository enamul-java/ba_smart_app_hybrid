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
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class OptionsViewModel @Inject constructor(
    private val useCase: OptionsUseCase
): ViewModel(){

    val addOptions: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }


    val updateOptions: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }

    val optionsResponse: MutableLiveData<List<OptionsEntity>> by lazy {
        MutableLiveData<List<OptionsEntity>>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }

    fun insertLanguage(optionsEntity: OptionsEntity) {

        GlobalScope.launch {
            try {
                val result = useCase.invoke(optionsEntity)
                addOptions.postValue(result)
            }catch (e:Exception){
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))
            }
        }
    }


    fun updateLanguage(optionsEntity: OptionsEntity) {
        Log.d("optionsEntity", optionsEntity.toString())
        GlobalScope.launch {
            try {
                val result = OptionsUseCase.invoke(useCase,optionsEntity)
                updateOptions.postValue(result)
            }catch (e:Exception){
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))

            }
        }
    }

    fun getLanguage() {

        GlobalScope.launch {
            try {
                val result = useCase.invoke()
                optionsResponse.postValue(result)

            }catch (e:Exception){
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1,e.message.toString()))

            }

        }
    }




}