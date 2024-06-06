package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel

interface PasswordChangeRepository {
    suspend fun doExecuteRechange(
        header: Map<String, String>,
        passwordChangeRequestModel: PasswordChangeRequestModel
    ): CommonProcedureDto

}