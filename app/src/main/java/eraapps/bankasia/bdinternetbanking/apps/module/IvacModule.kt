package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.IvacApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MfsApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.IvacRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.MfsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object IvacModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideIvacApi(): IvacApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(IvacApi::class.java)
    }

    /**
     *  Ivac UseCase
     * */
    @Provides
    @Singleton
    fun providesIvacUseCase(useCase: IvacRepositoryImpl): IvacUseCase {
        return IvacUseCase(
            ivacVisaCenter = IvacVisaCenter(useCase),
            ivacVisaType = IvacVisaType(useCase),
            ivacBillInfo = IvacBillInfo(useCase),
            getTarnsactionId = GetTransactionId(useCase),
            ivacBillPayment = IvacBillPayment(useCase),
        )
    }
}