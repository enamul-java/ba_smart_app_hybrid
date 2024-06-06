package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.IvacResponseModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.IvacRequestModel

interface IvacRepository {

    suspend fun getTarnsactionId(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel

    suspend fun ivacBillInfo(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel


    suspend fun ivacBillPayment(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): IvacResponseModel


    suspend fun ivacVisaCenter(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel>

    suspend fun ivacVisaType(
        header: Map<String, String>,
        ivacRequestModel: IvacRequestModel
    ): List<IvacResponseModel>


}