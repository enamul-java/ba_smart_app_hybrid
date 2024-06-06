package eraapps.bankasia.bdinternetbanking.apps.domain.remote.location.usecase


import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.repository.LocationRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.remote.location.util.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.ATMLocationDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.BranchLocationDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class LocationUseCase @Inject constructor(
    private val locationRepository: LocationRepositoryImpl
) {

    private val TAG:String = "Location"

    suspend fun  getBranchLocationLocalData(map: Map<String, String>):Flow<Resource<List<BranchLocationDto>>> = flow{

        emit(Resource.loading(null))

        val local = locationRepository.getLocalBranchData()
        local.let {
            try {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        emit(it)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(TAG, "-----From Local Error..${it.message}")
                        syncBranchLocationData(map).collect{
                            emit(it)
                        }
                    }
                    Resource.Status.LOADING ->
                        emit(it)
                }
            }catch (e:Exception){
                syncBranchLocationData(map).collect{
                    Log.e(TAG, "-----From Local Exception..")
                    emit(it)
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun  syncBranchLocationData(map: Map<String, String>):Flow<Resource<List<BranchLocationDto>>> = flow{

        emit(Resource.loading(null))

        val remoteData = locationRepository.getRemoteBranchData(map)
        remoteData.let {

            when(it.status){
                Resource.Status.SUCCESS -> {

                    it.data?.let {
                        emit(remoteData)
                        try {
                            locationRepository.deleteAllBranch()
                        }catch (e:Exception){
                            Log.e(TAG, e.toString())
                        }
                        //locationRepository.saveAllBranchData(it)
                        for (i in 0 until it.size){
                            Log.e("Location", "----------Data Saving $i is ${it.get(i).toString()}")
                            Log.w("Location", "Insert Res: ${locationRepository.saveBranchData(it.get(i))}")
                        }
                    } ?:emit(Resource.error(message = "No Data Found!" , data = null))
                }
                Resource.Status.ERROR -> {
                    emit(it)
                }
                Resource.Status.LOADING ->
                    emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun  getLiveBranchLocationData(map: Map<String, String>):Flow<Resource<List<BranchLocationDto>>> = flow{

        emit(Resource.loading(null))

        val remoteData = locationRepository.getRemoteBranchData(map)
        remoteData.let {

            when(it.status){
                Resource.Status.SUCCESS -> {

                    it.data?.let {
                        emit(remoteData)
                    } ?:emit(Resource.error(message = "No Data Found!" , data = null))
                }
                Resource.Status.ERROR -> {
                    emit(it)
                }
                Resource.Status.LOADING ->
                    emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)


    suspend fun  getAtmLocationLocalData(map: Map<String, String>):Flow<Resource<List<ATMLocationDto>>> = flow{

        emit(Resource.loading(null))

        val local = locationRepository.getLocalAtmData()
        local.let {
            try {
                when(it.status){
                    Resource.Status.SUCCESS -> {
                        emit(it)
                    }
                    Resource.Status.ERROR -> {
                        Log.e(TAG, "-----From Local Error..${it.message}")
                        syncAtmLocationData(map).collect{
                            emit(it)
                        }
                    }
                    Resource.Status.LOADING ->
                        emit(it)
                }
            }catch (e:Exception){
                syncAtmLocationData(map).collect{
                    Log.e(TAG, "-----From Local Exception..")
                    emit(it)
                }
            }
        }
    }.flowOn(Dispatchers.IO)

    suspend fun  syncAtmLocationData(map: Map<String, String>):Flow<Resource<List<ATMLocationDto>>> = flow{

        emit(Resource.loading(null))

        val remoteData = locationRepository.getRemoteAtmData(map)
        remoteData.let {

            when(it.status){
                Resource.Status.SUCCESS -> {

                    it.data?.let {
                        emit(remoteData)
                        try {
                            locationRepository.deleteAllAtm()
                        }catch (e:Exception){
                            Log.e(TAG, e.toString())
                        }
                        //locationRepository.saveAllBranchData(it)
                        for (i in 0 until it.size){
                            Log.e("Location", "----------Data Saving $i is ${it.get(i).toString()}")
                            Log.w("Location", "Insert Res: ${locationRepository.saveBranchData(it.get(i))}")
                        }
                    } ?:emit(Resource.error(message = "No Data Found!" , data = null))
                }
                Resource.Status.ERROR -> {
                    emit(it)
                }
                Resource.Status.LOADING ->
                    emit(it)
            }
        }
    }.flowOn(Dispatchers.IO)


    /*
    public suspend fun <T> getBranchLocationLocalData(map: Map<String, String>):Flow<T> {
        var local = locationRepository.getLocalBranchData()
        local.let {
            try {
                //If Local Data Base Data Not Available
                it.data?.let {
                    return flow {
                        it
                    }
                }
            }catch (e:Exception){
               return syncBranchLocationData(map)
            }
        }

        return syncBranchLocationData(map)
    }

    public suspend fun <T> syncBranchLocationData(map: Map<String, String>):Flow<T>{
        var remoteData = locationRepository.getRemoteBranchData(map)
        remoteData.let {
            it.data?.let {
                try {
                    locationRepository.deleteAllBranch()
                }catch (e:Exception){

                }

                locationRepository.saveBranchData(it)
            }
            return flow { remoteData }
        }
    }
    */
}