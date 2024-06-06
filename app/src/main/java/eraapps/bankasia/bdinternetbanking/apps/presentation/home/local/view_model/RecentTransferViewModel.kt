package eraapps.bankasia.bdinternetbanking.apps.presentation.home.local.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.ContactRecentEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.RecentTransferEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.contact.ContactUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.options_usecase.OptionsUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.transfer.RecentTransferUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.user.UserUseCase
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecentTransferViewModel @Inject constructor(
    private val useCase: RecentTransferUseCase
) : ViewModel() {


    val addRecentTransferResponse: MutableLiveData<ResponseDetails> by lazy {
        MutableLiveData<ResponseDetails>()
    }


    val recentTransferResponse: MutableLiveData<List<RecentTransferEntity>> by lazy {
        MutableLiveData<List<RecentTransferEntity>>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }


    fun insertRecentTransfer(recentTransferEntity: RecentTransferEntity) {

        GlobalScope.launch {
            try {
                val result = useCase.invoke(recentTransferEntity)
                addRecentTransferResponse.postValue(result)
            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            }
        }
    }


    fun getrecentTransferList(type:String,sourceAc:String) {

        GlobalScope.launch {
            try {
                val result = useCase.invoke(type,sourceAc)
                recentTransferResponse.postValue(result)

            } catch (e: Exception) {
                e.printStackTrace()
                errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))

            }

        }
    }


}