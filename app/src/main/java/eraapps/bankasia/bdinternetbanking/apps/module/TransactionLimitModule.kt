package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionLimitApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.TransactionLimitRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_limit.TransactionLimit
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_limit.TransactionLimitUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TransactionLimitModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideTransactionLimitApi(): TransactionLimitApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(TransactionLimitApi::class.java)
    }




    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideTransactionLimitRepository(api: TransactionLimitApi): TransactionLimitRepositoryImpl {
        return TransactionLimitRepositoryImpl(api)
    }

    /**
     *  TransactionLimit UseCase
     * */
    @Provides
    @Singleton
    fun providesTransactionLimitUseCase(transactionLimitRepositoryImpl: TransactionLimitRepositoryImpl): TransactionLimitUseCase {
        return TransactionLimitUseCase(
            transactionLimit = TransactionLimit(transactionLimitRepositoryImpl)
        )
    }


}