package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.model

data class SlapDataModel(

    val components: List<Component>,
    val outCode: String,
    val outMessage: String,
    val values: List<Value>,
    val variables: List<Variable>
)