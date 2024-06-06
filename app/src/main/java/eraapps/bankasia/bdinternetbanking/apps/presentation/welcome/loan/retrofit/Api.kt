package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.retrofit

import com.google.gson.JsonObject
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanListResponseModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResponse
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.LoanResultDataModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.SlapDataModel
import io.reactivex.Single
import retrofit2.http.*

interface Api {

    @POST("v1/loan/emi")
    fun loanEmiCalculation(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>




    @POST("v1/loan/slap-info")
    fun getSlap(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<SlapDataModel>

    @POST("v1/loan/apply-loan")
    fun loanScoreUpdate(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>

    @POST("v1/loan/loan-reason")
    fun getLoanReason(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>
    ): Single<List<LoanResponse>>


    @POST("v1/loan/tenore")
    fun getTenore(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>
    ): Single<List<LoanResponse>>



    @POST("v1/loan/loan-result")
    fun loanResult(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<List<LoanResultDataModel>>


    @POST("v1/loan/execute-disburse")
    fun disburseLoanExecute(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>

    @POST("v1/loan/cancel-loan")
    fun cancelLoan(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>


    @POST("v1/loan/loan-list")
    fun getLoanList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<List<LoanListResponseModel>>

    @POST("v1/loan/account-closing")
    fun accountClosing(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>

    @POST("v1/loan/check-eligibility")
    fun getCheckEligibility(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body loanModel: LoanModel
    ): Single<LoanResponse>
}