package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionLimitApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TransactionValidationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.TransactionLimitRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.TransactionValidationRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TransactionValidationRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_limit.TransactionLimit
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_limit.TransactionLimitUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_validation.ServiceOnOf
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.transaction_validation.TransactionValidationUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object TransactionValidationModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideTransactionValidationApi(): TransactionValidationApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(TransactionValidationApi::class.java)
    }




    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideTransactionValidationRepository(api: TransactionValidationApi): TransactionValidationRepository {
        return TransactionValidationRepositoryImpl(api)
    }

    /**
     *  TransactionValidation UseCase
     * */
    @Provides
    @Singleton
    fun providesTransactionValidationUseCase(
        transactionValidationRepository: TransactionValidationRepository
    ): TransactionValidationUseCase {
        return TransactionValidationUseCase(
            serviceOnOf = ServiceOnOf(transactionValidationRepository)
        )
    }


}