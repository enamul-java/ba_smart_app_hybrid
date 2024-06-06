package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

import com.google.gson.annotations.SerializedName

data class ShohozResponseDto(
    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?,

    @SerializedName("orderId")
    var orderId: String?,

    @SerializedName("token")
    var token: String?,

    @SerializedName("url")
    var url: String?,

    @SerializedName("transaction_id")
    var transaction_id: String?,

    @SerializedName("transaction_date")
    var transaction_date: String?,

    @SerializedName("utility_category")
    var utility_category: String?,

    @SerializedName("utility_code")
    var utility_code: String?,

    @SerializedName("quantity")
    var quantity: String?,

    @SerializedName("unit_rate")
    var unit_rate: String?,

    @SerializedName("amount")
    var amount: String?,

    @SerializedName("utility_image")
    var utility_image: String?,

    @SerializedName("utility_display_value")
    var utility_display_value: String?,

    @SerializedName("bill_type")
    var bill_type: String?,

    @SerializedName("customer_token")
    var customer_token: String?,

    @SerializedName("particulars")
    var particulars: String?,

    @SerializedName("total_amount")
    var total_amount: String?,

    @SerializedName("commission_amount")
    var commission_amount: String?,

    @SerializedName("amounts_bill_amount")
    var amounts_bill_amount: String?,

    @SerializedName("amounts_vat_amount")
    var amounts_vat_amount: String?,

    @SerializedName("amounts_commission_amount")
    var amounts_commission_amount: String?,

    @SerializedName("amounts_total_amount")
    var amounts_total_amount: String?,

    @SerializedName("details")
    var details: List<ShohozDetailsDto>?,

    @SerializedName("invoice")
    var invoice: List<ShohozInvoiceDto>?,

    @SerializedName("info_trip_number")
    var info_trip_number: String?,

    @SerializedName("info_seat_numbers")
    var info_seat_numbers: String?,

    @SerializedName("info_reserve_valid_till")
    var info_reserve_valid_till: String?,

    @SerializedName("info_journey_date")
    var info_journey_date: String?,

    @SerializedName("info_origin_city")
    var info_origin_city: String?,

    @SerializedName("info_reporting_time")
    var info_reporting_time: String?,

    @SerializedName("info_company_name")
    var info_company_name: String?,

    @SerializedName("info_boarding_point")
    var info_boarding_point: String?,

    @SerializedName("info_mobile_no")
    var info_mobile_no: String?,

    @SerializedName("info_customer_name")
    var info_customer_name: String?,

    @SerializedName("info_destination_city")
    var info_destination_city: String?

){
    constructor() : this("",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        emptyList(),
        emptyList(),
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        ""
    )
}




