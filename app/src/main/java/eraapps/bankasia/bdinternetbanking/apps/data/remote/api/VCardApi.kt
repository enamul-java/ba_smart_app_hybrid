package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardTokenGenDataM
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface VCardApi {
    @POST("v1/virtual-card/token-generate")
    suspend fun vCardToken(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: VCardTokenGenReqModel
    ): VCardTokenGenDataM

    @POST("v1/virtual-card/virtual-card-list")
    suspend fun getVCardList(
        @Header("Authorization") authorization: String,
        @HeaderMap map: Map<String, String>,
        @Body requestModel: VCardTokenGenReqModel
    ): List<VCardDetailsResDataM>


}