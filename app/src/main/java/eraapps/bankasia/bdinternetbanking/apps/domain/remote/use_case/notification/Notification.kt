package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.notification

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationTokenRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.NotificationRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NotificationTokenRegistrationRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject


class Notification @Inject constructor(
    private val repository: NotificationRepository
) {

    operator fun invoke(
        header: Map<String, String>,
        requestModel: NotificationTokenRegistrationRequestModel
    ): Flow<Resource<NotificationTokenRegistrationDto>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.doNotificationTokenRegistration(header, requestModel)

            emit(Resource.Success(response))

        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<NotificationTokenRegistrationDto>(errorHandling.message))
        }
    }


    operator fun invoke(
        header: Map<String, String>
    ): Flow<Resource<List<NotificationDto>>> = flow {
        try {
            emit(Resource.Loading<List<NotificationDto>>())
            val response = repository.getNotifications(header)
            if (response.isNotEmpty()) {
                emit(Resource.Success(response))
            } else {
                emit(Resource.Error("You have no notifications"))
            }
        } catch (e: HttpException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error(errorHandling.message))
        } catch (e: IOException) {
            val errorHandling = ErrorHandling.exception(e)
            emit(Resource.Error<List<NotificationDto>>(errorHandling.message))
        }
    }


}