package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.BinimoyApi
import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.VCardApi
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.binimoy.BinimoyRegDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardTokenGenDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.BinimoyRepository
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.VCardRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.binimoy.BinimoyRegReqModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel
import javax.inject.Inject

class BinimoyRepositoryImpl @Inject constructor(
    private val api: BinimoyApi
) : BinimoyRepository {

    override suspend fun binimoyRegistration(
        header: Map<String, String>,
        requestM: BinimoyRegReqModel
    ): BinimoyRegDataM {
        return api.binimoyRegistration(
            requestM.authorization,
            header,
            requestM
        )
    }


}