package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.NotificationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.NotificationRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.notification.Notification
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.notification.NotificationUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NotificationModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideCurrencyApi(): NotificationApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(NotificationApi::class.java)
    }


    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideNotificationRepository(api: NotificationApi): NotificationRepositoryImpl {
        return NotificationRepositoryImpl(api)
    }

    /**
     *  UseCase
     * */
    @Provides
    @Singleton
    fun providesNotificationUseCase(notificationRepositoryImpl: NotificationRepositoryImpl): NotificationUseCase {
        return NotificationUseCase(
            notification = Notification(notificationRepositoryImpl)
        )
    }
}

