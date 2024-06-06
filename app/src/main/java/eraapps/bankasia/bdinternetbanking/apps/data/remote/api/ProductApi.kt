package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ProductDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST

interface ProductApi {

    @POST("access/v1/products/list")
    suspend fun getProductList(
        @HeaderMap map: Map<String, String>,
    ): List<ProductDto>

    @POST("access/v1/products/service-list")
    suspend fun getServiceList(
        @HeaderMap map: Map<String, String>,
    ): List<MenuResponse>
}