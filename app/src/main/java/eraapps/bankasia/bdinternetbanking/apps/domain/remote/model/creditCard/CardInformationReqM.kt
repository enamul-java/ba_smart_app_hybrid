package eraapps.bankasia.bdinternetbanking.apps.domain.remote.model.creditCard

class CardInformationReqM {
    var authorization: String = ""
    var fullPan: String = ""
    var expaireDate: String = ""
    var cardType: String = ""
    var mailId: String = ""
    var sessionId: String = ""

    override fun toString(): String {
        return "Token length: "+ authorization.length
        //+" fullPan:"+fullPan+" expaireDate:"+expaireDate+" cardType:"+cardType+" mailId:"+mailId+" sessionId:"+sessionId
    }
}