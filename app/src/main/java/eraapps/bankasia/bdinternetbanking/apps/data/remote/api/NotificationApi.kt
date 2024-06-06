package eraapps.bankasia.bdinternetbanking.apps.data.remote.api

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationTokenRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NotificationTokenRegistrationRequestModel
import retrofit2.http.Body
import retrofit2.http.HeaderMap
import retrofit2.http.POST


interface NotificationApi {

    @POST("access/v1/notification/registration")
    suspend fun doNotificationTokenRegistration(
        @HeaderMap map: Map<String, String>,
        @Body notificationRequestModel: NotificationTokenRegistrationRequestModel
    ): NotificationTokenRegistrationDto


    @POST("access/v1/notification/messages")
    suspend fun getNotifications(
        @HeaderMap map: Map<String, String>
    ): List<NotificationDto>
}