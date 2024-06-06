package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.repository

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.local.LocationLocalDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.remote.LocationRemoteDataSource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.location.repository.LocationRepository
import javax.inject.Inject

class LocationRepositoryImpl @Inject constructor(
    private val localDataSource: LocationLocalDataSource,
    private val remoteDataSource: LocationRemoteDataSource
): LocationRepository {
    //Branch
    public suspend fun getLocalBranchData() = localDataSource.getBranchLocation()
    public suspend fun getLocalBranchDataCount() = localDataSource.getBranchDataCount()
    public suspend fun getRemoteBranchData(map: Map<String, String>) = remoteDataSource.getBranchLocation(map)
    suspend fun saveAllBranchData(list:List<BranchLocationDto>){
        Log.e("Location", "-----------Data Deleting...")
        localDataSource.saveAllBranchData(list)
    }
    suspend fun saveBranchData(data:BranchLocationDto):Long{
        Log.e("Location", "-----------Data Deleting...")
        return localDataSource.saveBranchData(data)
    }
    suspend fun deleteAllBranch(){
        Log.e("Location", "-----------Data Deleting...")
        localDataSource.deleteAllBranch()
    }

    //ATM
    public suspend fun getLocalAtmData() = localDataSource.getAtmLocation()
    public suspend fun getLocalAtmDataCount() = localDataSource.getAtmDataCount()
    public suspend fun getRemoteAtmData(map: Map<String, String>) = remoteDataSource.getAtmLocation(map)
    suspend fun saveAllAtmData(list:List<ATMLocationDto>){
        Log.e("Location", "-----------Data Deleting...")
        localDataSource.saveAllAtmData(list)
    }
    suspend fun saveBranchData(data:ATMLocationDto):Long{
        Log.e("Location", "-----------Data Deleting...")
        return localDataSource.saveAtmData(data)
    }
    suspend fun deleteAllAtm(){
        Log.e("Location", "-----------Data Deleting...")
        localDataSource.deleteAllAtm()
    }
}