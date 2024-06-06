package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MfsApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.MfsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MfsModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideMfsApi(): MfsApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(MfsApi::class.java)
    }

    /**
     *  MFS Transfer UseCase
     * */
    @Provides
    @Singleton
    fun providesMfsTransferUseCase(useCase: MfsRepositoryImpl): MfsUseCase {
        return MfsUseCase(
            mfsViewBeneficiary = MfsViewBeneficiary(useCase),
            mfsViewBeneficiaryTrans = MfsViewBeneficiaryTrans(useCase),
            mfsAddBeneficiary = MfsAddBeneficiary(useCase),
            mfsBeneficiaryInfo = MfsBeneficiaryInfo(useCase),
            mfsTransferExe = MfsTransferExe(useCase),
            mfsTransHistory = MfsTransferHistory(useCase),
            mfsViewBeneficiaryList = MfsViewBeneficiaryList(useCase),
            mfsViewBeneficiaryListTrans = MfsViewBeneficiaryListTrans(useCase),
            mfsTransHistoryList = MfsTransHistoryList(useCase),
        )
    }
}