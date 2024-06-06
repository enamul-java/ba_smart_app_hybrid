package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MenuResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.MenuRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class AllUtilityList @Inject constructor(
    private val menuRepository: MenuRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        accountRequestModel: AccountRequestModel
    ): Flow<Resource<List<MenuResponse>>> = flow {
        try {
            emit(Resource.Loading())
            val response = menuRepository.allUtilityList(header, accountRequestModel)
            emit(Resource.Success(response))
        } catch (e: HttpException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: IOException) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        } catch (e: Exception) {
            val error = ErrorHandling.exception(e)
            emit(Resource.Error(error.message))
        }
    }
}