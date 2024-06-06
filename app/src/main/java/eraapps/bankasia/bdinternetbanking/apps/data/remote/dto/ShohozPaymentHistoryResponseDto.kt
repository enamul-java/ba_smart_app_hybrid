package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShohozPaymentHistoryResponseDto(

    @SerializedName("shohozref")
    var SHOHOZREF: String?,

    @SerializedName("cuscod")
    var CUSCOD: String?,

    @SerializedName("amount")
    var AMOUNT: String?,

    @SerializedName("total_amount")
    var TOTAL_AMOUNT: String?,

    @SerializedName("shohozpaystatus")
    var SHOHOZPAYSTATUS: String?,

    @SerializedName("lastcanceldate")
    var LASTCANCELDATE: String?,

    @SerializedName("pnr")
    var PNR: String?,

    @SerializedName("remarks")
    var REMARKS: String?,

    @SerializedName("timstamp")
    var TIMSTAMP: String?,

    @SerializedName("company_name")
    var COMPANY_NAME: String?,

    @SerializedName("seat_no")
    var SEAT_NO: String?,

    @SerializedName("journey_from")
    var JOURNEY_FROM: String?,

    @SerializedName("journey_to")
    var JOURNEY_TO: String?,

    @SerializedName("journey_date")
    var JOURNEY_DATE: String?,

    @SerializedName("bording_point")
    var BORDING_POINT: String?,

    @SerializedName("bording_time")
    var BORDING_TIME: String?,

    @SerializedName("contact_no")
    var CONTACT_NO: String?,

    @SerializedName("cancel_flag")
    var CANCEL_FLAG: String?


    //shohozref,cuscod,amount,debitamount total_amount,shohozpaystatus,lastcanceldate,
    // pnr,remarks,timstamp,company_name,seat_no,journey_from,journey_to,
    // journey_date,bording_point,bording_time,contact_no





)

