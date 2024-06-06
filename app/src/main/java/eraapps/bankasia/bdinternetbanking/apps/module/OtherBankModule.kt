package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.OtherBankApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.OtherBankRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.cheque.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.other.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OtherBankModule {
    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideOtherBankApi(): OtherBankApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(OtherBankApi::class.java)
    }

    /*
 * for remote api
 * */
    @Provides
    @Singleton
    fun provideOtherBankRepository(api: OtherBankApi): OtherBankRepositoryImpl {
        return OtherBankRepositoryImpl(api)
    }

    /**
     *  Other Bank UseCase
     * */
    @Provides
    @Singleton
    fun providesOtherBankUseCase(useCase: OtherBankRepositoryImpl): OtherBankUseCase {
        return OtherBankUseCase(
            destinationAccount = DestinationAccount(useCase),
            otherBankdoExecute = OtherBankdoExecute(useCase),
            otherBankAddBeneficiaryExe = OtherBankAddBeneficiaryExe(useCase),
            beneficiaryDeleteExe = BeneficiaryDeleteExe(useCase),
            otherBankBranchSrc = OtherBankBranchSrc(useCase),
            getBankAcLenth = GetBankAcLength(useCase),
            otherBankSrc = OtherBankSrc(useCase),
            destinationAccountList = DestinationAccountList(useCase),
            destinationAccountListTrans = DestinationAccountListTrans(useCase),
            otherBankSrcList = OtherBankSrcList(useCase),
            otherBankBranchSrcList = OtherBanBranchkSrcList(useCase),
        )
    }
}