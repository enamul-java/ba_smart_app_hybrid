package eraapps.bankasia.bdinternetbanking.apps.common

import eraapps.bankasia.bdinternetbanking.apps.common.data.AndroidErrorResponse

/**
 * Sealed classes are used for representing restricted class hierarchies
 * wherein the object or the value can have value only among one of the types
 * */
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class Loading<T>(data: T? = null) : Resource<T>(data)
}

/*
sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: AndroidErrorResponse? = null
) {
    class Success<T>(data: T?) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null, exception: AndroidErrorResponse? = null) :
        Resource<T>(data, message, exception)

    class Loading<T>(data: T? = null) : Resource<T>(data)
}
*/

sealed class Resource2<T>(
    val data: T? = null,
    val message: String? = null,
    val exception: AndroidErrorResponse? = null
) {
    class Success<T>(data: T?) : Resource2<T>(data)
    class Error<T>(message: String, data: T? = null, exception: AndroidErrorResponse? = null) :
        Resource2<T>(data, message, exception)

    class Loading<T>(data: T? = null) : Resource2<T>(data)
}