package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class ShohozPaymentHistoryDto(
    var SHOHOZREF: String = "",
    var CUSCOD: String = "",
    var AMOUNT: String = "",
    var TOTAL_AMOUNT: String = "",
    var SHOHOZPAYSTATUS: String = "",
    var LASTCANCELDATE: String = "",
    var PNR: String = "",
    var REMARKS: String = "",
    var TIMSTAMP: String = "",
    var COMPANY_NAME: String = "",
    var SEAT_NO: String = "",
    var JOURNEY_FROM: String = "",
    var JOURNEY_TO: String = "",
    var JOURNEY_DATE: String = "",
    var BORDING_POINT: String = "",
    var BORDING_TIME: String = "",
    var CONTACT_NO: String = "",
    var CANCEL_FLG: String = ""

    //SHOHOZREF,CUSCOD,AMOUNT,DEBITAMOUNT TOTAL_AMOUNT,SHOHOZPAYSTATUS,LASTCANCELDATE,
    // PNR,REMARKS,TIMSTAMP,COMPANY_NAME,SEAT_NO,JOURNEY_FROM,JOURNEY_TO,
    // JOURNEY_DATE,BORDING_POINT,BORDING_TIME,CONTACT_NO

    )