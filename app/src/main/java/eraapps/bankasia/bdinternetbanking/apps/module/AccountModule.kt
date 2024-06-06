package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.AccountApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.AccountRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AccountModule {
    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideAccountApi(): AccountApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(AccountApi::class.java)
    }

    /*
 * for remote api
 * */
    @Provides
    @Singleton
    fun provideAccountRepository(api: AccountApi): AccountRepositoryImpl {
        return AccountRepositoryImpl(api)
    }

    /**
     *  Account UseCase
     * */
    @Provides
    @Singleton
    fun providesAccountUseCase(useCase: AccountRepositoryImpl): AccountUseCase {
        return AccountUseCase(
            sourceAccount = SourceAccount(useCase),
            soureceAccountBalance = SourceAccountBalance(useCase),
            primaryAccountSet = PrimaryAccountSet(useCase),
            sourceAcVerify = SourceAcVerify(useCase),
            sourcAcAdd = SourceAcAdd(useCase),
            getAccuntBalanceCasa = AccountBalanceCasa(useCase),
            sourceAcforStatement = SourceAcForStatement(useCase),
            accountStatement = AccountStatement(useCase),
            accountStatementReport = AccountStatementReport(useCase),
            accountInfo = AccountInfo(useCase),
            forigenExchangeRate = ForigenExchangeRate(useCase),
            sourceAccountList = SourceAccountList(useCase),
            sourceAcforStatementList = SourceAcForStatementList(useCase),
            accountStatementList = AccountStatementList(useCase),
            getAccuntBalanceCasaList = AccountCasaList(useCase),
            transferLimitCheck = TransferLimitCheck(useCase),
            duplicateCheck = DuplicateCheck(useCase),
            transHistory = TransferHistory(useCase),
            transHistoryList = TransferHistoryList(useCase),
            qrCashWithdrawlValidation = QrCashWithdrawalValidation(useCase),
            qrCashWithdrawExe = QrCashWithdrawaExe(useCase),
            qrCashWithdrawCancel = QrCashWithdrawaCancel(useCase)
        )
    }
}