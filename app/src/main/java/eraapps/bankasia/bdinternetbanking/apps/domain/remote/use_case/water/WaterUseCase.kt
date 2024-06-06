package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.water

import eraapps.bankasia.bdinternetbanking.apps.common.Resource
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.CommonProcedureDto
import eraapps.bankasia.bdinternetbanking.apps.data.remote.dto.TopupDto
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.AccountRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.TopupRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.account.SourceAccount
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup.MobilerechargeExe
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.topup.MobilerechargeLimitCheck
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.AccountRequestModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.TopupRequestModel
import eraapps.bankasia.bdinternetbanking.apps.util.ErrorHandling
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

data class WaterUseCase(
    val waterBillInfo: WaterBillInfo,
    val waterBillPayment: WaterBillIPayment
)