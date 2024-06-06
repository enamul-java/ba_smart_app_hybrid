package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local

import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.util.BaseDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import javax.inject.Inject

class LocationLocalDataSource@Inject constructor(
    private val locationDatabase: LocationDatabase
): BaseDataSource() {

    //Branch
    suspend fun getBranchLocation() = getLocalResult { locationDatabase.branchLocationDao.getBranchLocations() }

    suspend fun getBranchDataCount():Int{
        return locationDatabase.branchLocationDao.branchLocationCount()
    }

    suspend fun saveBranchData(data:BranchLocationDto):Long{
        return locationDatabase.branchLocationDao.insertBranchLocation(data)
    }

    suspend fun saveAllBranchData(list:List<BranchLocationDto>){
        locationDatabase.branchLocationDao.insertAllBranchLocation(list)
    }

    suspend fun deleteAllBranch(){
        locationDatabase.branchLocationDao.branchLocationDelete()
    }

    //ATM
    suspend fun getAtmLocation() = getLocalResult { locationDatabase.aTMLocationDao.getAtmLocations() }

    suspend fun getAtmDataCount():Int{
        return locationDatabase.aTMLocationDao.atmLocationCount()
    }

    suspend fun saveAtmData(data:ATMLocationDto):Long{
        return locationDatabase.aTMLocationDao.insertAtmLocation(data)
    }

    suspend fun saveAllAtmData(list:List<ATMLocationDto>){
        locationDatabase.aTMLocationDao.insertAllAtmLocation(list)
    }

    suspend fun deleteAllAtm(){
        locationDatabase.aTMLocationDao.atmLocationDelete()
    }
}