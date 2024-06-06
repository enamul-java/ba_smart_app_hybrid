package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.user

import android.util.Log
import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.OptionsRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.UserRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.OptionsEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserIdEntity
import java.util.*
import javax.inject.Inject

class UserUseCase @Inject constructor(
    private val repository: UserRepositoryImpl
) {
    suspend operator fun invoke(userIdEntity: UserIdEntity): ResponseDetails {

        return try {
            repository.insertUserId(userIdEntity)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }

    suspend operator fun invoke(userId: String): ResponseDetails {

        return try {
            repository.deleteUserId()
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }



    operator fun invoke(): List<UserIdEntity> {
        return repository.getUser()
    }

}