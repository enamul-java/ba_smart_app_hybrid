package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class PayoneerDto(
    val outCode: String? = "",
    val outMessage: String? = "",
    val payoneerclientId: String= "",

    val response_type: String= "",
    val authUrl: String= "",
    val shadinCardClientId: String = "",
    val redirectUrl: String= "",
    val responseType: String= "",
    val clientSecret: String= "",
    val info_url: String= "",
    val account_id: String= "",
    val moreInformation: String= "",
    val httpMessage: String= "",
    val error_description: String= "",
    val httpCode: String= "",
    val shadinCardNumber: String= "",
    val shadinCardFcAccount: String= "",
    val chargeAmount: String= "",
    val id_token: String= "",
    val token_type: String= "",
    val access_token: String= "",
    val scope: String= "",
    val id: String= "",
    val type: String= "",
    val currency: String= "",
    val status: String= "",
    val status_name: String= "",
    val available_balance: Double= 0.00,
    val update_time: String= "",
    val transactinId: String= "",
    val transactiondate: String= "",
    val payment_id: String= "",
    val trn_ref: String= "",
    val totalBDTAmount:Double = 0.0,
    val usdAmountPortion:Double = 0.0,
    val bdtAmountPortion:Double = 0.0,
    val amount:Double = 0.0,
    val trans_complete_flg:String = "",
    val cbsStatusCode:String = "",
    val referenceId:String = "",
    val remarks:String = "",
    val midrate:String = "",



)


/*(outCode=null, outMessage=null, payoneer_url=null, payoneerclientId=null, scope=null, response_type=null, authUrl=, redirectUrl=,
responseType=, clientSecret=, info_url=, account_id=, moreInformation=, httpMessage=, error_description=, httpCode=,
shadinCardNumber=541173******8203, shadinCardFcAccount=5411730100848203, chargeAmount=, shadinCardClientId=,
token_type=, access_token=, expires_in=0, consented_on=0, refresh_token=, refresh_token_expires_in=0, id_token=,
error=, code=, id=, type=, currency=USD, status=, status_name=, available_balance=0.0, update_time=,
totalBDTAmount=67.27, usdAmountPortion=0.35, bdtAmountPortion=0.65, trn_ref=, statuscode=, currentdate=null,
midrate=Y, midrateStatus=, midRateMessage=, requestFrom=, requestType=, requestResponse=, payment_id=,
slNo=1, transactiondate=FEB 16, 2023, trans_complete_flg=Y, CBSStatusCode=Y, referenceId=PAYO0000639230,
amount=1, remarks=4017960-fff, commit_id=)
        */