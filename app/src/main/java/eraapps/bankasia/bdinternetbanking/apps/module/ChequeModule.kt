package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.AccountApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.ChequeApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.AccountRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.ChequeRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.cheque.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ChequeModule {
    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideChequeApi(): ChequeApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(ChequeApi::class.java)
    }

    /*
 * for remote api
 * */
    @Provides
    @Singleton
    fun provideChequeRepository(api: ChequeApi): ChequeRepositoryImpl {
        return ChequeRepositoryImpl(api)
    }

    /**
     *  Cheque UseCase
     * */
    @Provides
    @Singleton
    fun providesChequeUseCase(useCase: ChequeRepositoryImpl): ChequeUseCase {
        return ChequeUseCase(
            chequeSourceAccount = ChequeSourceAccount(useCase),
            chequeBookRequest = ChequeBookRequest(useCase),
            chequeStatusSearch = ChequeStatusSearch(useCase),
            chequeLeafs = ChequeLeafs(useCase),
            positivePayRequest = PositivePayRequest(useCase),
            stopChequeExe = StopChequeExe(useCase),
            chequeSoureceAccountList = ChequeSourceAccountList(useCase),
            chequeInqueryTypes = ChequeInqueryTypes(useCase),
            chequeStatusSearchList = ChequeStatusSearchList(useCase),
            chequeLeafsList = ChequeLeafsList(useCase),
            chequeLeaveReason = ChequeLeaveReason(useCase)
        )
    }
}