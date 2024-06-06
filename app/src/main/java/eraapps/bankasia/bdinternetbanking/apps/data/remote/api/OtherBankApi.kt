package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.OtherBankOptions
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CreditCardRequestModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface OtherBankApi {

    @POST("v1/other/destination-account")
    suspend fun destinationAccount(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): CommonProcedureDto


    @POST("v1/other/do-execute")
    suspend fun OtherBankdoExecute(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/other/add-beneficiary-exe")
    suspend fun otherBankAddBeneficiaryExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/beneficiary/delete-exe")
    suspend fun beneficiaryDeleteExe(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/other/bank-src-exe")
    suspend fun otherBankSrc(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/other/bank-ac-length")
    suspend fun getBankAcLenth(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): OtherBankOptions

    @POST("v1/other/branch-src-exe")
    suspend fun otherBankBranchSrc(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): CommonProcedureDto

    @POST("v1/other/destination-account-list")
    suspend fun destinationAccountList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<OtherBankOptions>

    @POST("v1/other/destination-account-list-trans")
    suspend fun destinationAccountListTrans(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body accountRequestModel: AccountRequestModel
    ): List<OtherBankOptions>

    @POST("v1/other/bank-src-list")
    suspend fun otherBankSrcList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<OtherBankOptions>

    @POST("v1/other/branch-src-list")
    suspend fun otherBankBranchSrcList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: CreditCardRequestModel
    ): List<OtherBankOptions>


}