package eraapps.bankasia.bdinternetbanking.apps.domain.remote.use_case.vcard

data class VCardUseCase(
    val vCardToken: VCardToken,
    val vCardList: GetVCardList
)
