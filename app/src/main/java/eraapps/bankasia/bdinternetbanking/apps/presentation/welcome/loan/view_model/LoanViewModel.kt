package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.view_model

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.*
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.retrofit.ApiService
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.retrofit.RetrofitErrorMessage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


class LoanViewModel : ViewModel() {

    private val apiService = ApiService()
    private val disposable = CompositeDisposable()

    var loanEmiCalculationResponse2 = MutableLiveData<LoanResponse>()
    var loanEmiCalculationResponse = MutableLiveData<LoanResponse>()
    var scoreUpdateResponse = MutableLiveData<LoanResponse>()
    var disburseExecuteResponse = MutableLiveData<LoanResponse>()
    var cancelLoanResponse = MutableLiveData<LoanResponse>()
    var accountClosingResponse = MutableLiveData<LoanResponse>()
    var slapDataModelResponse = MutableLiveData<SlapDataModel>()
    var resultResponse = MutableLiveData<List<LoanResultDataModel>>()
    var loanListResponse = MutableLiveData<List<LoanListResponseModel>>()
    var loanEligibilityResponse = MutableLiveData<LoanResponse>()
    var reasonResponse = MutableLiveData<List<LoanResponse>>()
    var tenoreResponse = MutableLiveData<List<LoanResponse>>()
    var error_response = MutableLiveData<ErrorResponseModel>()

    fun loanEmiCalculation(header: Map<String, String>,model: LoanModel){
         Log.e("loanEmiCalculation-->","loanEmiCalculation---")
        disposable.add(apiService.loanEmiCalculation(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                        loanEmiCalculationResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("errir-->",e.message.toString())
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    fun loanEmiCalculation2(header: Map<String, String>,model: LoanModel){
        Log.e("loanEmiCalculation-->","loanEmiCalculation---")
        disposable.add(apiService.loanEmiCalculation(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                        loanEmiCalculationResponse2.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    Log.e("errir-->",e.message.toString())
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun getSlap( header: Map<String, String>,model: LoanModel){
        disposable.add(apiService.getSlap(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<SlapDataModel>() {
                override fun onSuccess(model: SlapDataModel) {
                    model.let {
                        slapDataModelResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun loanScoreUpdate(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.loanScoreUpdate(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                        scoreUpdateResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    fun getLoanReason(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.getLoanReason(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<LoanResponse>>() {
                override fun onSuccess(model: List<LoanResponse>) {

                        reasonResponse.value = model

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    fun getTenore(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.getTenore(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<LoanResponse>>() {
                override fun onSuccess(model: List<LoanResponse>) {

                   tenoreResponse.value = model

                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun loanResult(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.loanResult(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<LoanResultDataModel>>() {
                override fun onSuccess(model: List<LoanResultDataModel>) {
                    model.let {
                        resultResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun disburseLoanExecute(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.disburseLoanExecute(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                        disburseExecuteResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    fun cancelLoan(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.cancelLoan(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                       cancelLoanResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun getLoanList(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.getLoanList(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<List<LoanListResponseModel>>() {
                override fun onSuccess(model: List<LoanListResponseModel>) {
                    model.let {
                        loanListResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    fun getCheckEligibility(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.getCheckEligibility(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                        loanEligibilityResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }


    fun accountClosing(header: Map<String, String>,model: LoanModel){

        disposable.add(apiService.accountClosing(header,model)
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeWith(object : DisposableSingleObserver<LoanResponse>() {
                override fun onSuccess(model: LoanResponse) {
                    model.let {
                      accountClosingResponse.value = model
                    }
                }
                override fun onError(e: Throwable) {
                    e.printStackTrace()
                    error_response.value = RetrofitErrorMessage.getLoginErrorMessage(e)

                }

            })
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}