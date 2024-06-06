package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.binimoy.BinimoyRegDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardTokenGenDataM
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.binimoy.BinimoyRegReqModel
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel

interface BinimoyRepository {
    suspend fun binimoyRegistration(
        header: Map<String, String>,
        requestM: BinimoyRegReqModel
    ): BinimoyRegDataM
}