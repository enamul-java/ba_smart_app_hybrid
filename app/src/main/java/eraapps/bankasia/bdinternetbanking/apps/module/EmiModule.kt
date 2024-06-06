package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.EmiApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.EmiRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.emi_calculation.Emi
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.emi_calculation.EmiUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object EmiModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideEmiApi(): EmiApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(EmiApi::class.java)
    }



    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideEmiRepository(api: EmiApi): EmiRepositoryImpl {
        return EmiRepositoryImpl(api)
    }

    /**
     *  Emi UseCase
     * */
    @Provides
    @Singleton
    fun providesEmiUseCase(emiRepositoryImpl: EmiRepositoryImpl): EmiUseCase {
        return EmiUseCase(
            emi = Emi(emiRepositoryImpl)
        )
    }


}