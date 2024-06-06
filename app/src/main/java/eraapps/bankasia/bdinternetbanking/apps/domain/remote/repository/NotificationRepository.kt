package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.NotificationTokenRegistrationDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.NotificationTokenRegistrationRequestModel


interface NotificationRepository {
    suspend fun doNotificationTokenRegistration(
        header: Map<String, String>,
        notificationTokenRegistrationRequestModel: NotificationTokenRegistrationRequestModel
    ): NotificationTokenRegistrationDto

    suspend fun getNotifications(
        header: Map<String, String>
    ): List<NotificationDto>
}
