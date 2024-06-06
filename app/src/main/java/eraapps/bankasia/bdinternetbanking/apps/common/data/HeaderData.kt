package eraapps.bankasia.bdinternetbanking.apps.common.data

import eraapps.bankasia.bdinternetbanking.apps.common.Constants
import eraapps.bankasia.bdinternetbanking.apps.util.GlobalVariable

class HeaderData {

    companion object{

        public fun headerHome( globalVariable: GlobalVariable):  Map<String, String> {
            val header = mapOf(
                "accept" to Constants.accept,
                 "access-auth-request-header" to Constants.access_auth_request_header,
                "device-id" to globalVariable.imei,
                "access-type" to Constants.accessTypePublic,
            )
            return header
        }

        public fun headerWelcome( globalVariable: GlobalVariable):  Map<String, String> {
            val header = mapOf(
                "accept" to Constants.accept,
                "access-auth-request-header" to Constants.access_auth_request_header,
                "device-id" to globalVariable.imei,
                "access-type" to Constants.accessTypePrivate,
            )
            return header
        }


    }
}