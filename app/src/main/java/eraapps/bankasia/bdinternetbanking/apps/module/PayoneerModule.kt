package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.PayoneerApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.PayoneerRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.payoneer.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PayoneerModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun providePayoneerApi(): PayoneerApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(PayoneerApi::class.java)
    }

    /*
* for remote api
* */
    @Provides
    @Singleton
    fun provideLocationRepository(api: PayoneerApi): PayoneerRepositoryImpl {
        return PayoneerRepositoryImpl(api)
    }

    /**
     * Add LocalBracnh Location
     * */
    @Provides
    @Singleton
    fun providesPayoneerUseCase(useCase: PayoneerRepositoryImpl): PayoneerUseCase {
        return PayoneerUseCase(
            getPayoneerUrlInfo = GetPayoneerUrlInfo(useCase),
            getPayoneerCardInfo = GetPayoneerCardInfo(useCase),
            getPayoneerAccessToken = GetPayoneerAccessToken(useCase),
            getPayoneerBalanceInfo = GetPayoneerBalanceInfo(useCase),
            transferPayoneer = TransferPayoneer(useCase),
           payoneerHistory = PayoneerHistory(useCase),
           payoneerHistoryList = PayoneerHistoryList(useCase),
            payoneerPaymentRefresh = PayoneerPaymentRefresh(useCase),
        )
    }



}