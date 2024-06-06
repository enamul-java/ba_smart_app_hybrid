package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.BinimoyApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.CardServiceApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.VCardApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.BinimoyRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.CardServiceRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.VCardRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.BinimoyRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.CardServiceRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.VCardRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.binimoy.BinimoyReg
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.binimoy.BinimoyUseCase
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
object BinimoyModule {
    /**
     * Api configuration
     */
    @Provides
    @Singleton
    fun provideBinimoyApi(): BinimoyApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(BinimoyApi::class.java)
    }

    /*
     * for remote api
     * */
    @Provides
    @Singleton
    fun provideBinimoyRepository(api: BinimoyApi): BinimoyRepository {
        return BinimoyRepositoryImpl(api)
    }

    /**
     *  CardService UseCase
     * */
    @Provides
    @Singleton
    fun providesBinimoyUseCase(repository: BinimoyRepositoryImpl): BinimoyUseCase {
        return BinimoyUseCase(
            binimoyReg = BinimoyReg(repository)
        )
    }
}