package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.retrofit

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model.ErrorResponseModel


class RetrofitErrorMessage {

    companion object {

        fun getLoginErrorMessage(exception: Throwable): ErrorResponseModel {
            if (exception is RetrofitExceptionHandle) {
                try {
                    when (exception.getKind()) {


                        RetrofitExceptionHandle.Kind.HTTP_422_WITH_DATA ->
                            return ErrorResponseModel("1",
                                exception.getErrorData()!!.message.toString())

                        RetrofitExceptionHandle.Kind.HTTP ->
                            // Connection Time Out Error! Please Try Again.
                            return ErrorResponseModel("1",
                                "Check Your Internet Connection! Please try Again.")

                        RetrofitExceptionHandle.Kind.NETWORK ->

                            return ErrorResponseModel("1",
                                "Check Your Internet Connection! Please try Again..")

                        RetrofitExceptionHandle.Kind.UNEXPECTED ->

                            /*return ErrorResponseModel("1",
                                "The server can not find the requested resource! Please try again later...")*/

                            return ErrorResponseModel("1",
                                exception.getErrorData()!!.message.toString())
                    }
                } catch (e: Exception) {
                    return ErrorResponseModel("1", e.message.toString())

                }


            }

            //return ErrorResponseModel("1", "Server Is Unreachable. Please try again later....")
            return ErrorResponseModel("1",
                "The server can not find the requested resource! Check Your Internet Connection! Please try again later...")

        }
    }
}