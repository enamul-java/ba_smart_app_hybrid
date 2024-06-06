package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

import com.google.gson.annotations.SerializedName


data class LoanResponse(

    @SerializedName("emi")
    var emi: String?,

    @SerializedName("totalPayment")
    var totalPayment: String?,

    @SerializedName("totalInterest")
    var totalInterest: String?,

    @SerializedName("outCode")
    var outCode: String?,

    @SerializedName("outMessage")
    var outMessage: String?,


    @SerializedName("amount")
    var amount: String?,

    @SerializedName("month")
    var month: String?,

    @SerializedName("customerCode")
    var customerCode: String?,

    @SerializedName("applied_for")
    var applied_for: String?,

    @SerializedName("customer_name")
    var customer_name: String?,

    @SerializedName("applied_amount")
    var applied_amount:Int?,

    @SerializedName("customer_id")
    var customer_id:Int?,


    @SerializedName("contact_number")
     var contact_number: String?,

    @SerializedName("applied_date")
    var applied_date: String?,

    @SerializedName("result")
    var result: String?,

    @SerializedName("code")
    var code: String?,

    @SerializedName("desc")
    var desc: String?,

    @SerializedName("firstInstallmentDate")
    var firstInstallmentDate: String?,

    @SerializedName("debitAmount")
    var debitAmount: String?,

    @SerializedName("creditAmount")
    var creditAmount: String?,

    @SerializedName("chargeAmount")
    var chargeAmount: String?,

    @SerializedName("vatAmount")
    var vatAmount: String?,

    @SerializedName("sanctionDate")
    var sanctionDate: String?,

    @SerializedName("interestRate")
    var interestRate: String?,

    @SerializedName("chargeWithVat")
    var chargeWithVat: String?,

    @SerializedName("accountType")
    var accountType: String?,

    @SerializedName("branchCode")
    var branchCode: String?,

    @SerializedName("account_number")
    var account_number: String?,

    @SerializedName("status")
    var status: String?,


    @SerializedName("sourceAcs")
    var sourceAcs: String?,


)


