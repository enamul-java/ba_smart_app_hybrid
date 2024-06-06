package eraapps.bankasia.bdinternetbanking.apps.module


import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.RegistrationApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.ForgotRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.GetCardShadowAcInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotPassExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotPassInfoVerify
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotUserIdExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.forgot.ForgotUserIdInfoVerify
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.registration.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RegistrationModule {

    @Provides
    @Singleton
    fun provideRegistrationApi(): RegistrationApi {
        return RetrofitBuilder
            .getRetrofitNid()
            .create(RegistrationApi::class.java)
    }


    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideNewUserRequestRepository(api: RegistrationApi): NewUserRequestRepositoryImpl {
        return NewUserRequestRepositoryImpl(api)
    }

    /**
     *  NewUserRequest UseCase
     * */
    @Provides
    @Singleton
    fun providesNewUserRequestUseCase(newUserRequestRepositoryImpl: NewUserRequestRepositoryImpl): NewUserRequestUseCase {
        return NewUserRequestUseCase(
            newUserRequest = NewUserRequest(newUserRequestRepositoryImpl)
        )
    }

    /*
    * for remote api
    * */
    @Provides
    @Singleton
    fun provideReRegistrationRepository(api: RegistrationApi): ReRegistrationRepositoryImpl {
        return ReRegistrationRepositoryImpl(api)
    }

    /*
* for remote api
* */
    @Provides
    @Singleton
    fun provideForgotRepository(api: RegistrationApi): ForgotRepository {
        return ForgotRepositoryImpl(api)
    }


    /**
     *  ReRegistration UseCase
     * */
    @Provides
    @Singleton
    fun providesReRegistrationUseCase(reRegistrationRepositoryImpl: ReRegistrationRepositoryImpl): ReRegistrationUseCase {
        return ReRegistrationUseCase(
            reRegistration = ReRegistration(reRegistrationRepositoryImpl)
        )
    }

    /**
     *  Forgot UseCase
     * */
    @Provides
    @Singleton
    fun providesForgotUseCase(forgotUseCase: ForgotRepositoryImpl): ForgotUseCase {
        return ForgotUseCase(
            forgotPassInfoVerify = ForgotPassInfoVerify(forgotUseCase),
            forgotUserIDInfoVerify = ForgotUserIdInfoVerify(forgotUseCase),
            forgotPassExe = ForgotPassExe(forgotUseCase),
            forgotUserIdExe = ForgotUserIdExe(forgotUseCase),
            getCardShadowAcInfo = GetCardShadowAcInfo(forgotUseCase)
        )
    }


}