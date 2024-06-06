package eraapps.bankasia.bdinternetbanking.apps.module

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.local.data_source.SmartAppDatabase
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local.LocationDatabase
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local.LocationLocalDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.remote.LocationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.remote.LocationRemoteDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.repository.LocationRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.location.repository.LocationRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.location.usecase.LocationUseCase
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocationModule {

    //Dependency for LocationRemoteDataSource
    @Provides
    @Singleton
    fun provideLocationApi(): LocationApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(LocationApi::class.java)
    }

    //Dependency for LocationRepositoryImpl
    @Provides
    @Singleton
    fun provideLocationRemoteDataSource(locationApi: LocationApi): LocationRemoteDataSource {
        return LocationRemoteDataSource(locationApi)
    }

    //Dependency for LocationLocalDataSource
    @Provides
    @Singleton
    fun provideLocationDatabase(app: Application): LocationDatabase {
        return Room.databaseBuilder(
            app,
            LocationDatabase::class.java,
            LocationDatabase.DATABASE_NAME
        ).fallbackToDestructiveMigration()
         .build()
    }

    //Dependency for LocationRepositoryImpl
    @Provides
    @Singleton
    fun provideLocationLocalDataSource(locationDatabase: LocationDatabase): LocationLocalDataSource {
        return LocationLocalDataSource(locationDatabase)
    }

    //Dependency for LocationUseCase
    @Provides
    @Singleton
    fun provideLocationRepositoryImpl(
        local: LocationLocalDataSource,
        remote: LocationRemoteDataSource
    ): LocationRepositoryImpl {
        return LocationRepositoryImpl(local,remote)
    }

    //Dependency for LocationViewModel
    @Provides
    @Singleton
    fun provideLocationUseCase(repository: LocationRepositoryImpl): LocationUseCase {
        return LocationUseCase(repository)
    }
}