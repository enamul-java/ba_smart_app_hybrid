package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ProductDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse

interface ProductRepository {
    suspend fun getProductList(
        header: Map<String, String>
    ): List<ProductDto>

    suspend fun getServiceList(
        header: Map<String, String>
    ): List<MenuResponse>
}