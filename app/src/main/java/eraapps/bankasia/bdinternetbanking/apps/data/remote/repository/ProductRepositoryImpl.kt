package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ProductApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ProductDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ProductRepository
import javax.inject.Inject


class ProductRepositoryImpl @Inject constructor(
    private val api: ProductApi
) : ProductRepository {

    override suspend fun getProductList(
        header: Map<String, String>
    ): List<ProductDto> {
        return api.getProductList(
            header
        )
    }

    override suspend fun getServiceList(
        header: Map<String, String>
    ): List<MenuResponse> {
        return api.getServiceList(
            header
        )
    }
}