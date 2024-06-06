package eraapps.bankasia.bdinternetbanking.apps.util

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse
import retrofit2.HttpException
import java.io.IOException

class ErrorHandling {

    companion object {
        fun exception(e: Exception): AndroidErrorResponse {

            Log.e("source rrr-->", "-----------")
            val rValue = AndroidErrorResponse(0, e.message.toString())

            if (e is HttpException) {

                val exception: HttpException = e as HttpException

                when (exception.code()) {
                    400 -> {
                        return AndroidErrorResponse(400, "Validation Error-400")
                    }
                    401 -> {
                        // return AndroidErrorResponse(401, "Invalid Token! Permission denied")
                        return AndroidErrorResponse(
                            401,
                            "Your session has expired. Please login again-401"
                        )
                    }
                    500 -> {
                        return AndroidErrorResponse(500, "Internal Server Error-500")
                    }
                    403 -> {
                        return AndroidErrorResponse(403, "Forbidden! Access Denied-403")
                    }
                    else -> {
                        return AndroidErrorResponse(401, exception.message.toString() + "-401")
                    }
                }
            } else if (e is IOException) {
                val exception: IOException = e as IOException
                return AndroidErrorResponse(
                    1,
                    exception.message.toString() + " Couldn't not reach server. Please check your internet"
                )
            } else if (e is IOException) {
                return AndroidErrorResponse(1, e.message.toString())
            }
            return rValue
        }

    }


}