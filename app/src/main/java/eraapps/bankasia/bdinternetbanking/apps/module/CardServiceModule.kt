package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CardServiceApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.CardServiceRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CardInformation
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CardServiceUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementNew
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementSrc
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.CreditCardStatementSrcList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.card_service.ViewCardLastStatement
import javax.inject.Singleton



@Module
@InstallIn(SingletonComponent::class)
object CardServiceModule {
    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideCardServiceApi(): CardServiceApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(CardServiceApi::class.java)
    }

    /*
 * for remote api
 * */
    @Provides
    @Singleton
    fun provideCardServiceRepository(api: CardServiceApi): CardServiceRepository {
        return CardServiceRepositoryImpl(api)
    }

    /**
     *  CardService UseCase
     * */
    @Provides
    @Singleton
    fun providesCardServiceUseCase(useCase: CardServiceRepositoryImpl): CardServiceUseCase {
        return CardServiceUseCase(
            creditCardSrc = CreditCardStatementSrc(useCase),
            creditCardSrcList = CreditCardStatementSrcList(useCase),
            cardInformation = CardInformation(useCase),
            viewCardLastStatement = ViewCardLastStatement(useCase),
            creditCardStatementNew = CreditCardStatementNew(useCase),
        )
    }
}