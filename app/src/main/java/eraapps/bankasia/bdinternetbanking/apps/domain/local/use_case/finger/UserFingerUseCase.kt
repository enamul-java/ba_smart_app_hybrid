package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.finger

import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.data.local.repository.UserFingerRepositoryImpl
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.UserFingerEntity
import java.util.*
import javax.inject.Inject

class UserFingerUseCase @Inject constructor(
    private val repository: UserFingerRepositoryImpl
) {
    suspend fun insertUserFingerInfo(userIdEntity: UserFingerEntity): ResponseDetails {

        return try {
            repository.insertUserFingerInfo(userIdEntity)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }
    suspend fun updateUserFingerInfo(userIdEntity: UserFingerEntity): ResponseDetails {

        return try {
            repository.updateUserFingerInfo(userIdEntity)
            ResponseDetails(0, "Operation Successful.", 200, Date())
        } catch (e: Exception) {
            ResponseDetails(0, e.message.toString(), 400, Date())
        }

    }

     fun getUserFingerInfo(userid:String): UserFingerEntity {
        return repository.getUserFingerInfo(userid)
    }

}