package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.menu


data class MenuUseCase(
    val dashboardMenu: DashboardMenu,
    val utilityBillerAllList: UtilityBillerAllList,
    val allUtilityList: AllUtilityList,
    val operatorList: OperatorList,
    val utilityAddBiller: UtilityAddBiller,
    val utilityBillerAll: UtilityBillerAll,

    )
