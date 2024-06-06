package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CardServiceApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.VCardApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.CardServiceRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.VCardRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.VCardRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CardInformation
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CardServiceUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementNew
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementSrc
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementSrcList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.ViewCardLastStatement
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.vcard.GetVCardList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.vcard.VCardToken
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.vcard.VCardUseCase
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object VCardModule {
    /**
     * Api configuration
     */
    @Provides
    @Singleton
    fun provideVCardApi(): VCardApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(VCardApi::class.java)
    }

    /*
     * for remote api
     * */
    @Provides
    @Singleton
    fun provideVCardRepository(api: VCardApi): VCardRepository {
        return VCardRepositoryImpl(api)
    }

    /**
     *  CardService UseCase
     * */
    @Provides
    @Singleton
    fun providesVCardUseCase(repository: VCardRepositoryImpl): VCardUseCase {
        return VCardUseCase(
            vCardToken = VCardToken(repository),
            vCardList = GetVCardList(repository)
        )
    }
}