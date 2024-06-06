package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ProductApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.ProductRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.product.GetServiceList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.product.Product
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.product.ProductUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {


    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideProductApi(): ProductApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(ProductApi::class.java)
    }


    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideProductRepository(api: ProductApi): ProductRepositoryImpl {
        return ProductRepositoryImpl(api)
    }

    /**
     *  Product UseCase
     * */
    @Provides
    @Singleton
    fun providesProductUseCase(productRepositoryImpl: ProductRepositoryImpl): ProductUseCase {
        return ProductUseCase(
            product = Product(productRepositoryImpl),
            getServiceList = GetServiceList(productRepositoryImpl)
        )
    }


}