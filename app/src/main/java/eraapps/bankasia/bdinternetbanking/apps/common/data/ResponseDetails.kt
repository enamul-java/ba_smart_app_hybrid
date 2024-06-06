package eraapps.bankasia.bdinternetbanking.apps.common.data

import java.util.*

data class ResponseDetails(
    var errorCode:Int,
    var message:String,
    var statusCode:Int,
    var date: Date
)
