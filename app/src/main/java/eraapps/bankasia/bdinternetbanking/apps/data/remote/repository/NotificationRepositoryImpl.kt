package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.NotificationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationTokenRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.NotificationRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NotificationTokenRegistrationRequestModel
import javax.inject.Inject

class NotificationRepositoryImpl @Inject constructor(
    private val api: NotificationApi
) : NotificationRepository {

    override suspend fun doNotificationTokenRegistration(
        header: Map<String, String>,
        notificationTokenRegistrationRequestModel: NotificationTokenRegistrationRequestModel
    ): NotificationTokenRegistrationDto {
        return api.doNotificationTokenRegistration(
            header,
            notificationTokenRegistrationRequestModel
        )
    }

    override suspend fun getNotifications(
        header: Map<String, String>
    ): List<NotificationDto> {
        val data = api.getNotifications(header)
        for (i in 0 until data.size) {
            Log.e("Notification", data.get(i).toString())
        }
        return data
    }
}