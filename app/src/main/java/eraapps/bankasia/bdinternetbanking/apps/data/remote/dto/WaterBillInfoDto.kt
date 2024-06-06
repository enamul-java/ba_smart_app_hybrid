package eraapps.bankasia.bdinternetbanking.apps.data.remote.dto

data class WaterBillInfoDto(
    val outCode: String? = "",
    val outMessage: String? = "",
    val customerCode: String? = "",
    val customerName: String? = "",
    val inspectorID: String? = "",
    val waterBill: String? = "",
    val sewerBill: String? = "",
    val sercharge: String? = "",
    val fixedCharge: String? = "",
    val vatAmount: String? = "",
    val totalBill: String? = "",
    val wasaAccountNo: String? = ""
)
