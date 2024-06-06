package eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardTokenGenDataM
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel

interface VCardRepository {
    suspend fun vCardToken(
        header: Map<String, String>,
        requestM: VCardTokenGenReqModel
    ): VCardTokenGenDataM

    suspend fun getVCardList(
        header: Map<String, String>,
        requestM: VCardTokenGenReqModel
    ): List<VCardDetailsResDataM>
}