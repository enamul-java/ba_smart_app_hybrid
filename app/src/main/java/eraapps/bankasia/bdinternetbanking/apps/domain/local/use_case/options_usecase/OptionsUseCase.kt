package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.options_usecase

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.OptionsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import java.util.*
import javax.inject.Inject

class OptionsUseCase @Inject constructor(
    private val repository: OptionsRepositoryImpl
){
    suspend operator fun invoke(optionsModel: OptionsEntity): ResponseDetails {

        return try {
            repository.insertAllOption(optionsModel)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        }catch (e:Exception){
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }

     operator fun invoke(): List<OptionsEntity> {
        return repository.readLanguage()
    }

    companion object {
        suspend operator fun invoke(optionsUseCase: OptionsUseCase, optionsEntity: OptionsEntity): ResponseDetails {

            return try {
                val x = optionsUseCase.repository.updateLanguage(optionsEntity)
                Log.e("SKB_USEC", "invoke: ${optionsEntity.id}", )
                ResponseDetails(0,"Operation Successful.",200, Date())
            }catch (e:Exception){
                ResponseDetails(0,e.message.toString(),400, Date())
            }

        }
    }
}