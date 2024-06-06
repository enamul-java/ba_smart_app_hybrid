package eraapps.bankasia.bdinternetbanking.apps.data.remote.repository

import eraapps.bankasia.bdinternetbanking.apps.data.remote.api.VCardApi
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardDetailsResDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.vcard.VCardTokenGenDataM
import eraapps.bankasia.bdinternetbanking.apps.domain.remote.repository.VCardRepository
import eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard.VCardTokenGenReqModel
import javax.inject.Inject

class VCardRepositoryImpl @Inject constructor(
    private val api: VCardApi
) : VCardRepository {
    override suspend fun vCardToken(
        header: Map<String, String>,
        requestM: VCardTokenGenReqModel
    ): VCardTokenGenDataM {
        return api.vCardToken(
            requestM.authorization,
            header,
            requestM
        )
    }

    override suspend fun getVCardList(
        header: Map<String, String>,
        requestM: VCardTokenGenReqModel
    ): List<VCardDetailsResDataM> {
        return api.getVCardList(
            requestM.authorization,
            header,
            requestM
        )
    }


}