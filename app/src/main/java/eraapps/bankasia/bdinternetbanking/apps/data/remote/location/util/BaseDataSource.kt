package eraapps.bankasia.bdinternetbanking.apps.data.remote.location.util

import android.util.Log
import retrofit2.Response

abstract class BaseDataSource {

    protected suspend fun <T> getRemoteResult(call: suspend () -> Response<T>): Resource<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                kotlin.run {  }
                val body = response.body()
                if (body != null) return Resource.success(body)
            }
            return error(" ${response.code()} ${response.message()}", "N")
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), "N")
        }
    }

    private fun <T> error(message: String, flag: String): Resource<T> {
        if (flag.equals("N")) {
            return Resource.error("Network call has failed for a following reason: $message")
        } else {
            return Resource.error("Local Database Error with reason: $message")
        }
    }

    protected fun <T> getLocalResult(databaseQuery: () -> T): Resource<T> {
        try {
            val source = databaseQuery.invoke()
            source.let {
                it?.let {
                    Log.e("Location", "form Base Resource: $it")
                    if(it.toString().equals("[]")) {
                        return error("No Data Found! Please Click Sync.", "L")
                    }else{
                        return Resource.success(it)
                    }
                }?:return error("No Data Found! Please Click Sync.", "L")
            }
        } catch (e: Exception) {
            return error(e.message ?: e.toString(), "L")
        }
    }

}