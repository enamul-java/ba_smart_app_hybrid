package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ShohozApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.ShohozRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.payoneer.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozPaymentHistory
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozTicketCancel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozTokenGenerate
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozTicketInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.shohoz.ShohozUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ShohozModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideShohozApi(): ShohozApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(ShohozApi::class.java)
    }

    /*
* for remote api
* */
    @Provides
    @Singleton
    fun provideLocationRepository(api: ShohozApi): ShohozRepositoryImpl {
        return ShohozRepositoryImpl(api)
    }

    /**
     * Add Shohozz
     * */
    @Provides
    @Singleton
    fun providesPayoneerUseCase(useCase: ShohozRepositoryImpl): ShohozUseCase {
        return ShohozUseCase(
            shohozTokenGenerate = ShohozTokenGenerate(useCase),
            shohozTicketInfo = ShohozTicketInfo(useCase),
            shohozPayment = ShohozPayment(useCase),
            shohozPaymentHistory = ShohozPaymentHistory(useCase),
            shohozTicketCancel = ShohozTicketCancel(useCase),
        )
    }



}