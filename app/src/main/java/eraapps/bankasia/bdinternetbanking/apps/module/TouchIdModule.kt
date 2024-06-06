package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.IvacApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MfsApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.TouchIdApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.IvacRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.MfsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.TouchIdRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.touch.TouchIdInfoVerify
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.touch.TouchIdRegisterExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.touch.TouchIdRegisterFlag
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.touch.TouchIdUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object TouchIdModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideTouchIdApi(): TouchIdApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(TouchIdApi::class.java)
    }

    /**
     *  TouchId UseCase
     * */
    @Provides
    @Singleton
    fun providesTouchIdUseCase(useCase: TouchIdRepositoryImpl): TouchIdUseCase {
        return TouchIdUseCase(
            touchIdRegisterFlag = TouchIdRegisterFlag(useCase),
            touchIdInfoVerify = TouchIdInfoVerify(useCase),
            touchIdRegisterExe = TouchIdRegisterExe(useCase)
        )
    }
}