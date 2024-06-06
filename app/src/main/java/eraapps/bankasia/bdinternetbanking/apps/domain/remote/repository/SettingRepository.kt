package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CustomerBasicInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.OwnBankViewBeneficiaryDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountBalanceDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.SourceAccountListDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.LimitModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CustomerBasicRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.OwnBankRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UserRequestModel

interface SettingRepository {
    suspend fun customerBasicInfo(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): CustomerBasicInfoDto

    suspend fun limitInfo(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): LimitModel

    suspend fun customerComplain(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): CommonProcedureDto

    suspend fun userIdInfoVerify(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto

    suspend fun cardInfoVerify(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto
 suspend fun cardPinResetExe(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto

    suspend fun userIdChangeExe(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto

    suspend fun userAccessList(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): List<CustomerBasicInfoDto>

}