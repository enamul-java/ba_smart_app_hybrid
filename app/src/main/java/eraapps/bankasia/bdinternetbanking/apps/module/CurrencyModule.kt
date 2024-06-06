package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CurrencyApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.CurrencyRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.currency_rate.CurrencyRate
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.currency_rate.CurrencyUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object CurrencyModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideCurrencyApi(): CurrencyApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(CurrencyApi::class.java)
    }



    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideCurrencyRepository(api: CurrencyApi): CurrencyRepositoryImpl {
        return CurrencyRepositoryImpl(api)
    }

    /**
     *  UseCase
     * */
    @Provides
    @Singleton
    fun providesCurrencyUseCase(currencyRepositoryImpl: CurrencyRepositoryImpl): CurrencyUseCase {
        return CurrencyUseCase(
            currency = CurrencyRate(currencyRepositoryImpl)
        )
    }


}