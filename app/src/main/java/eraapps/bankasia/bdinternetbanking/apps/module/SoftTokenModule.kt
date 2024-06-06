package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.OwnBankApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SoftTokenApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.NewUserRequestRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.OwnBankRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.SoftTokenRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.own.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.soft_token.ScanSoftToken
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.soft_token.SoftTokenUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object SoftTokenModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideSoftTokenApi(): SoftTokenApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(SoftTokenApi::class.java)
    }

    /*
   * for remote api
   * */
    @Provides
    @Singleton
    fun provideSoftTokenRepository(api: SoftTokenApi): SoftTokenRepositoryImpl {
        return SoftTokenRepositoryImpl(api)
    }

    /**
     *  Own bank fund Transfer UseCase
     * */
    @Provides
    @Singleton
    fun providesSoftTokenUseCase(useCase: SoftTokenRepositoryImpl): SoftTokenUseCase {
        return SoftTokenUseCase(
            scanSoftToken = ScanSoftToken(useCase),

        )
    }
}