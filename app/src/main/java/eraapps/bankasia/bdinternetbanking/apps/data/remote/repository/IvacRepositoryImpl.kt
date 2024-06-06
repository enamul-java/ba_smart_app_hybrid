package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.IvacApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.MfsViewResponse
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.IvacRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.ivac.IvacBillInfo
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.IvacRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.MfsRequestModel
import javax.inject.Inject

class IvacRepositoryImpl @Inject constructor(
    private val api: IvacApi
) : IvacRepository {
    override suspend fun getTarnsactionId(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel {
        return api.getTarnsactionId(
            ivacRequestModel.authorization,
            header,
            ivacRequestModel
        )
    }

    override suspend fun ivacBillInfo(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel {
        return api.ivacBillInfo(
            ivacRequestModel.authorization,
            header,
            ivacRequestModel
        )
    }

    override suspend fun ivacBillPayment(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel {
        return api.ivacBillPayment(
            ivacRequestModel.authorization,
            header,
            ivacRequestModel
        )
    }

    override suspend fun ivacVisaCenter(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel> {
        return api.ivacVisaCenter(
            ivacRequestModel.authorization,
            header,
            ivacRequestModel
        )
    }
    override suspend fun ivacVisaType(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel> {
        return api.ivacVisaType(
            ivacRequestModel.authorization,
            header,
            ivacRequestModel
        )
    }


}