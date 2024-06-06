package eraapps.bankasia.bdinternetbanking.apps.presentation.model.binimoy

import com.google.gson.annotations.SerializedName

class BinimoyRegReqModel {
    @SerializedName("authorization")
    var authorization:String = ""

    @SerializedName("mailId")
    var mailId:String = ""

    @SerializedName("sessionId")
    var sessionId:String = ""

    @SerializedName("customerCode")
    var customerCode:String = ""

    @SerializedName("binimoyUserId")
    var binimoyUserId:String = ""

    @SerializedName("binimoyUserAlias")
    var binimoyUserAlias:String = ""

    @SerializedName("accountNo")
    var accountNo:String = ""

    @SerializedName("mobileNumber")
    var mobileNumber:String = ""

    @SerializedName("binimoyPin")
    var binimoyPin:String = ""
}

/*
{
  "version": "1",
  "timstamp": "20240427154945001",
  "originatorConversationId": "20240427154945001AABC",
  "binimoyUserId": "xyz@binimoy",
  "binimoyUserAlias": "abc",
  "accountNo": "04934001919",
  "mobileNumber": "01712315172"
}
 */