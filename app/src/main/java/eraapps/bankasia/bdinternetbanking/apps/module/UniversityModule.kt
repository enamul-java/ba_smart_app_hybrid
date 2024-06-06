package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UniversityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.ElectricityRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.GasRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.InsuranceRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.UniversityRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.WaterRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.electricity.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.gas.GasBillIPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.gas.GasBillInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.gas.GasUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.insurance.GetPolicyTypeList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.insurance.InsuranceBillIPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.insurance.InsuranceBillInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.insurance.InsuranceUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs.*
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.university.UniversityBillInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.university.UniversityBillPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.university.UniversityUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterBillIPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterBillInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UniversityModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideUtilityApi(): UniversityApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(UniversityApi::class.java)
    }

    /**
     *  University Bill Pay UseCase
     * */
    @Provides
    @Singleton
    fun providesUniversityUseCase(useCase: UniversityRepositoryImpl): UniversityUseCase {
        return UniversityUseCase(
            universityBillInfo = UniversityBillInfo(useCase),
            universityBillPayment = UniversityBillPayment(useCase),
        )
    }

}