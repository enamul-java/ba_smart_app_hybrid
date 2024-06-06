package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.common.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import com.cleancode.common.data.LoadingProgressDialog
import dagger.hilt.android.lifecycle.HiltViewModel
import eraapps.bankasia.bdinternetbanking.apps.common.Resource2
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.AccountStatementDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TransferHistoryDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.AccountDetailsModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.AccountUseCase
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val accountUseCase: AccountUseCase
) : ViewModel() {


    val sourceAccountResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val sourceAccountBalanceResponse: MutableLiveData<SourceAccountBalanceDto> by lazy {
        MutableLiveData<SourceAccountBalanceDto>()
    }

    val qrCashWithdrawlValidationResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val qrCashWithdrawExeResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val qrCashWithdrawCancelResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val primaryAccountSetResponse: MutableLiveData<SourceAccountBalanceDto> by lazy {
        MutableLiveData<SourceAccountBalanceDto>()
    }

    val sourceAcVerifyResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val sourcAcAddResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val account_balance_casa_response: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val sourceAcforStatement_response: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val accountStatement_response: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val accountStatementReport_response: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val accountStatementReportDownloadResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val accountInfoResponse: MutableLiveData<SourceAccountBalanceDto> by lazy {
        MutableLiveData<SourceAccountBalanceDto>()
    }
    val forigenExchangeRateResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }

    val sourceAccountListResponse: MutableLiveData<List<SourceAccountListDto>> by lazy {
        MutableLiveData<List<SourceAccountListDto>>()
    }

    val sourceAcforStatementListResponse: MutableLiveData<List<SourceAccountListDto>> by lazy {
        MutableLiveData<List<SourceAccountListDto>>()
    }
    val accountStatementListResponse: MutableLiveData<List<AccountStatementDto>> by lazy {
        MutableLiveData<List<AccountStatementDto>>()
    }

    val transHistoryListResponse: MutableLiveData<List<TransferHistoryDto>> by lazy {
        MutableLiveData<List<TransferHistoryDto>>()
    }
    val getAccuntBalanceCasaListResponse: MutableLiveData<List<AccountDetailsModel>> by lazy {
        MutableLiveData<List<AccountDetailsModel>>()
    }

    val transferLimitCheckResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val duplicateCheckResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }
    val transHistoryResponse: MutableLiveData<CommonProcedureDto> by lazy {
        MutableLiveData<CommonProcedureDto>()
    }


    val errorResponse: MutableLiveData<AndroidErrorResponse> by lazy {
        MutableLiveData<AndroidErrorResponse>()
    }
    val isLoading: MutableLiveData<LoadingProgressDialog> by lazy {
        MutableLiveData<LoadingProgressDialog>()
    }

    /*fun sourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAccount(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource.Success -> {
                        Log.e("data----", result.data.toString())
                        sourceAccountResponse.postValue(result.data)
                    }
                    is Resource.Error -> {
                        Log.e("account-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))
                    }

                    else -> {
                        Log.e("else-----", result.message.toString())
                        errorResponse.postValue(AndroidErrorResponse(1, result.message.toString()))

                    }
                }
            }.launchIn(viewModelScope)
        } catch (e: Exception) {
            errorResponse.postValue(AndroidErrorResponse(1, e.message.toString()))
            e.printStackTrace()
        }
    }
    */


    fun sourceAccount(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAccount(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        sourceAccountResponse.postValue(result.data)
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

    fun sourceAccountList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAccountList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("sourceAccountList", result.data.toString())
                        sourceAccountListResponse.postValue(result.data)
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


    fun sourceAcforStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAcforStatement(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        sourceAcforStatement_response.postValue(result.data)
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


    fun sourceAcforStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAcforStatementList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        sourceAcforStatementListResponse.postValue(result.data)
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

    fun soureceAccountBalance(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.soureceAccountBalance(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        sourceAccountBalanceResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun qrCashWithdrawlValidation(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {
        try {
            accountUseCase.qrCashWithdrawlValidation(header, accountRequestModel).onEach { result ->
                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        qrCashWithdrawlValidationResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun qrCashWithdrawExe(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {
        try {
            accountUseCase.qrCashWithdrawExe(header, accountRequestModel).onEach { result ->
                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        qrCashWithdrawExeResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun qrCashWithdrawCancel(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {
        try {
            accountUseCase.qrCashWithdrawCancel(header, accountRequestModel).onEach { result ->
                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        qrCashWithdrawCancelResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun primaryAccountSet(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.primaryAccountSet(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        primaryAccountSetResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun sourceAcVerify(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourceAcVerify(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        sourceAcVerifyResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun sourcAcAdd(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.sourcAcAdd(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        sourcAcAddResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun getAccuntBalanceCasa(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.getAccuntBalanceCasa(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        account_balance_casa_response.postValue(result.data)
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


    fun accountStatement(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.accountStatement(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        accountStatement_response.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun accountStatementReport(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        Log.e("------", "accountStatementReport")
        try {
            accountUseCase.accountStatementReport(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        //Log.e("data----", result.data.toString())
                        accountStatementReport_response.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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


    fun accountInfo(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.accountInfo(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data accountInfo----", result.data.toString())
                        accountInfoResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun forigenExchangeRate(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.forigenExchangeRate(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        // Log.e("data accountInfo----", result.data.toString())
                        forigenExchangeRateResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        // Log.e("error-----", result.message.toString())
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


    fun getAccuntBalanceCasaList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.getAccuntBalanceCasaList(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        getAccuntBalanceCasaListResponse.postValue(result.data)
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


    fun accountStatementList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.accountStatementList(header, accountRequestModel).onEach { result ->
                Log.d("test", "testdata")
                when (result) {
                    is Resource2.Success -> {
                        accountStatementListResponse.postValue(result.data)
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


    fun transferLimitCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.transferLimitCheck(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        transferLimitCheckResponse.postValue(result.data)
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


    fun duplicateCheck(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.duplicateCheck(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        duplicateCheckResponse.postValue(result.data)
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

    fun transHistory(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.transHistory(header, accountRequestModel).onEach { result ->

                when (result) {
                    is Resource2.Success -> {
                        Log.e("data----", result.data.toString())
                        transHistoryResponse.postValue(result.data)
                    }

                    is Resource2.Error -> {
                        Log.e("error-----", result.message.toString())
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

    fun transHistoryList(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ) {

        try {
            accountUseCase.transHistoryList(header, accountRequestModel).onEach { result ->
                Log.d("test", "testdata")
                when (result) {
                    is Resource2.Success -> {
                        transHistoryListResponse.postValue(result.data)
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