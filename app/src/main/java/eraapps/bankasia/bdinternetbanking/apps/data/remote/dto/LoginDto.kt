package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.BranchLocation
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.Login

data class LoginDto(
    val outCode: String,
    val outMessage: String,
    val mailId: String,
    val sessionId: String,
    val cusomerCode: String,
    val mobileNo: String,
    val userName: String,
    val address: String,
    val identifyBanking: String,
    val token: String,
    val emailId: String? = ""

)

/*
* bind only required data to value from the api response. Remove extra data from full repose
* */

fun LoginDto.toLogin(): Login {

    return Login(
        outCode = outCode,
        outMessage=outMessage,
        mailId=mailId,
        sessionId=sessionId,
        cusomerCode=cusomerCode,
        mobileNo=mobileNo,
        userName=userName,
        address=address,
        identifyBanking=identifyBanking,
        token=token,
        emailId = emailId
    )
}