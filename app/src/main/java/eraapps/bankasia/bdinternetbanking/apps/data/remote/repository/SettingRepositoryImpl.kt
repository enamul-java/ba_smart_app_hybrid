package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.SmartAppApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CustomerBasicInfoDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.LoginDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.LimitModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.LoginRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.PasswordChangeRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.SettingRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.CustomerBasicRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.LoginModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.PasswordChangeRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.UserRequestModel
import javax.inject.Inject

class SettingRepositoryImpl @Inject constructor(
    private val api: SmartAppApi
) : SettingRepository {

    override suspend fun customerBasicInfo(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): CustomerBasicInfoDto {
        return api.customerBasicInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun limitInfo(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): LimitModel {
        return api.limitInfo(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun customerComplain(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): CommonProcedureDto {
        return api.customerComplain(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun userIdInfoVerify(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto {
        return api.userIdInfoVerify(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun cardInfoVerify(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto {
        return api.cardInfoVerify(
            requestModel.authorization,
            header,
            requestModel
        )
    }    override suspend fun cardPinResetExe(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto {
        return api.cardPinResetExe(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun userIdChangeExe(
        header: Map<String, String>,
        requestModel: UserRequestModel
    ): CommonProcedureDto {
        return api.userIdChangeExe(
            requestModel.authorization,
            header,
            requestModel
        )
    }

    override suspend fun userAccessList(
        header: Map<String, String>,
        requestModel: CustomerBasicRequestModel
    ): List<CustomerBasicInfoDto> {
        return api.userAccessList(
            requestModel.authorization,
            header,
            requestModel
        )
    }


}