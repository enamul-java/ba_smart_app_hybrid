package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.retrofit

import com.google.gson.JsonObject
import eraapps.bankasia.bdinternetbanking.apps.module.RetrofitBuilder
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanListResponseModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.SlapDataModel
import io.reactivex.Single
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

class ApiService {


    private val api = RetrofitBuilder.getRetrofitNid()
        .create(Api::class.java);

    /*private val api = Retrofit.Builder()
        .baseUrl(baseurl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(okHttpClient)
        .build()
        .create(Api::class.java)
    */

    fun loanEmiCalculation(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.loanEmiCalculation(
            model.authorization,
            header,
            model
        )
    }

    fun getSlap(header: Map<String, String>,model: LoanModel): Single<SlapDataModel> {
        return api.getSlap(
            model.authorization,
            header,
            model
        )
    }

    fun loanScoreUpdate(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.loanScoreUpdate(
            model.authorization,
            header,
            model
        )
    }

    fun getLoanReason(header: Map<String, String>,model: LoanModel): Single<List<LoanResponse>> {
        return api.getLoanReason(
            model.authorization,
            header
        )
    }
  fun getTenore(header: Map<String, String>,model: LoanModel): Single<List<LoanResponse>> {
        return api.getTenore(
            model.authorization,
            header
        )
    }



    fun loanResult(header: Map<String, String>,model: LoanModel): Single<List<LoanResultDataModel>> {
        return api.loanResult(
            model.authorization,
            header,
            model
        )
    }

    fun disburseLoanExecute(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.disburseLoanExecute(
            model.authorization,
            header,
            model
        )
    }

    fun cancelLoan(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.cancelLoan(
            model.authorization,
            header,
            model
        )
    }

    fun getLoanList(header: Map<String, String>,model: LoanModel): Single<List<LoanListResponseModel>> {
        return api.getLoanList(
            model.authorization,
            header,
            model
        )
    }

    fun accountClosing(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.accountClosing(
            model.authorization,
            header,
            model
        )
    }

    fun getCheckEligibility(header: Map<String, String>,model: LoanModel): Single<LoanResponse> {
        return api.getCheckEligibility(
            model.authorization,
            header,
            model
        )
    }
}