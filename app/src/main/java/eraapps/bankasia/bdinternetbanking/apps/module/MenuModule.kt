package eraapps.bankasia.bdinternetbanking.apps.module

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MenuApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.MfsApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.MenuRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.repository.MfsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.AllUtilityList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.DashboardMenu
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.MenuUseCase
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.OperatorList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.UtilityAddBiller
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.UtilityBillerAll
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu.UtilityBillerAllList
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.mfs.*
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MenuModule {

    /**
     * Api configuration
     */

    @Provides
    @Singleton
    fun provideMenuApi(): MenuApi {
        return RetrofitBuilder
            .getRetrofit()
            .create(MenuApi::class.java)
    }

    /**
     *  Menu UseCase
     * */
    @Provides
    @Singleton
    fun providesMenuUseCase(useCase: MenuRepositoryImpl): MenuUseCase {
        return MenuUseCase(
            dashboardMenu = DashboardMenu(useCase),
            utilityBillerAllList = UtilityBillerAllList(useCase),
            allUtilityList = AllUtilityList(useCase),
            operatorList = OperatorList(useCase),
            utilityAddBiller = UtilityAddBiller(useCase),
            utilityBillerAll = UtilityBillerAll(useCase),
        )
    }
}