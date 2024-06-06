package eraapps.bankasia.bdinternetbanking.apps.domain.local.use_case.branch_location_use_case

import eraapps.bankasia.bdinternetbanking.apps.common.data.ResponseDetails
import eraapps.bankasia.bdinternetbanking.apps.domain.local.model.BranchEntity
import eraapps.bankasia.bdinternetbanking.apps.domain.local.repository.BranchLocationLocalRepository
import java.util.*

class AddBranchLocation(private val branchLocationRepository: BranchLocationLocalRepository) {

    suspend operator fun invoke(branchLocationEntity:BranchEntity):ResponseDetails{

        try {
            branchLocationRepository.insertBranchLocation(branchLocationEntity)
            return ResponseDetails(0,"Location Insert Successful.",400, Date())
        }catch (e:Exception){
            return ResponseDetails(0,e.message.toString(),400, Date())
        }

    }

}