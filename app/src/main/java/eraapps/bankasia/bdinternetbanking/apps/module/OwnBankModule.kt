package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.OwnBankApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.OwnBankRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.own.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OwnBankModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideOwnBankApi(): OwnBankApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(OwnBankApi::class.java)
    }

    /**
     *  Own bank fund Transfer UseCase
     * */
    @Provides
    @Singleton
    fun providesFundTransferOwnBankUseCase(useCase: OwnBankRepositoryImpl): OwnBankTransferUseCase {
        return OwnBankTransferUseCase(
            fundTransferExecute = FundTransferExecute(useCase),
            standingInstructionExecute = StandingInstructionExecute(useCase),
            addBeneficiary = AddBeneficiary(useCase),
            viewBeneficiary = ViewBeneficiary(useCase),
            viewBeneficiaryList = ViewBeneficiaryList(useCase),
            viewBeneficiaryAll = ViewBeneficiaryAll(useCase),
            viewBeneficiaryListAll = ViewBeneficiaryListAll(useCase),
            instructionFrequencyList = InstructionFrequencyList(useCase),
            expireDate = ExpireDate(useCase),
            standingInstructionRequestInfo = StandingInstructionRequestInfo(useCase),
            instructionsViewList = InstructionsViewList(useCase),

            )
    }
}