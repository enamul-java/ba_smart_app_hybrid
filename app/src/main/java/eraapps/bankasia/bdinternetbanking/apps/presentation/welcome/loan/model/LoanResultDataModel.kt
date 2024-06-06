package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

import com.google.gson.annotations.SerializedName

data class LoanResultDataModel(

    val applied_amount: Int,
    val applied_date: String,
    val applied_for: String,
    val contact_number: String,
    val customer_id: Int,
    val customer_name: String,
    val result: String,
    val resultflag: String,
    var approved_amount: Int = 0,
    val tenor: String,
    val account_number: String,

)