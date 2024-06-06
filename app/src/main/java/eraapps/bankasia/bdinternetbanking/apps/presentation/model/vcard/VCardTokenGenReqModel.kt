package eraapps.bankasia.bdinternetbanking.apps.presentation.model.vcard

import com.google.gson.annotations.SerializedName

class VCardTokenGenReqModel {
    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("pcard")
    var pcard:String = ""

    @SerializedName("operation")
    var operation:String = ""
}

/*
@NotNull(message = "Mail Id  can not be empty.")
private String mailId;
@Encrypted
@NotNull(message = "Session Id can not be empty.")
private String sessionId;
@Encrypted
@NotNull(message = "Session Id can not be empty.")
private String customerCode;
@Encrypted
@NotNull(message = "Card No can not be empty.")
private String pcard;
 */