package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.UtilityApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.ElectricityRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.GasRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.InsuranceRepositoryImpl
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
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterBillIPayment
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterBillInfo
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water.WaterUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilityModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideUtilityApi(): UtilityApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(UtilityApi::class.java)
    }

    /**
     *  Electricity Bill Pay UseCase
     * */
    @Provides
    @Singleton
    fun providesElectricityBillPayUseCase(useCase: ElectricityRepositoryImpl): ElectricityUseCase {
        return ElectricityUseCase(
            electricityBillInfo = ElectricityBillInfo(useCase),
            billsReport = BillsReport(useCase),
            electricityBillPaymet = ElectricityBillIPayment(useCase),
            billsReportList = BillsReportList(useCase)
        )
    }

    /**
     *  Water Bill Pay UseCase
     * */
    @Provides
    @Singleton
    fun providesWaterBillPayUseCase(useCase: WaterRepositoryImpl): WaterUseCase {
        return WaterUseCase(
            waterBillInfo = WaterBillInfo(useCase),
            waterBillPayment = WaterBillIPayment(useCase)
        )
    }

    /**
     *  Insurance Bill Pay UseCase
     * */
    @Provides
    @Singleton
    fun providesInsuranceBillPayUseCase(useCase: InsuranceRepositoryImpl): InsuranceUseCase {
        return InsuranceUseCase(
            getPolicyTypeList = GetPolicyTypeList(useCase),
            insuranceBillInfo = InsuranceBillInfo(useCase),
            insuranceBillPayment = InsuranceBillIPayment(useCase)
        )
    }

    /**
     *  Gas Bill Pay UseCase
     * */
    @Provides
    @Singleton
    fun providesGasBillPayUseCase(useCase: GasRepositoryImpl): GasUseCase {
        return GasUseCase(
            gasBillInfo = GasBillInfo(useCase),
            gasBillIPayment = GasBillIPayment(useCase)
        )
    }
}