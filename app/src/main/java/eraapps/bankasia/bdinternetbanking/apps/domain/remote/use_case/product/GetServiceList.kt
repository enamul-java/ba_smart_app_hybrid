package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.product

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ProductDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BillModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ProductRepository
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import eraapps.bankasia.bdinternetbanking.apps.util.TextContants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class GetServiceList @Inject constructor(
    private val repository: ProductRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        //requestModel: ProductRequestModel
    ): Flow<Resource<List<MenuResponse>>> = flow {
        try {
            emit(Resource.Loading<List<MenuResponse>>())
            val response = repository.getServiceList(
                header
                // , requestModel
            )
            if (response.isNotEmpty()) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error(TextContants.no_data_found_english))
            }
        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<List<MenuResponse>>(errorHandling.message))
        }
    }

}