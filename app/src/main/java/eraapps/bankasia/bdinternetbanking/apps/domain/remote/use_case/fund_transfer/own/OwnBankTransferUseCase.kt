package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.fund_transfer.own


data class OwnBankTransferUseCase(
    val fundTransferExecute: FundTransferExecute,
    val standingInstructionExecute: StandingInstructionExecute,
    val addBeneficiary: AddBeneficiary,
    val viewBeneficiary: ViewBeneficiary,
    val viewBeneficiaryList: ViewBeneficiaryList,
    val viewBeneficiaryAll: ViewBeneficiaryAll,
    val viewBeneficiaryListAll: ViewBeneficiaryListAll,
    val instructionFrequencyList: InstructionFrequencyList,
    val expireDate: ExpireDate,
    val standingInstructionRequestInfo: StandingInstructionRequestInfo,
    val instructionsViewList: InstructionsViewList,


)
