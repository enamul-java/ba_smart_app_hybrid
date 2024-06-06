package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

data class Value(
    val flag: Int,
    val max_value: Int,
    val min_value: Int,
    val value_id: Int,
    val value_name: String,
    val value_score: Int,
    val variable_id: Int
)