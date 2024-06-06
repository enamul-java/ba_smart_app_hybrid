package eraapps.bankasia.bdinternetbanking.apps.util;


public class QRTags {


    public enum TagLevel {
        PAYLOAD,
        POINT_OF_INITIATION_METHOD,
        MERCHANT_ACCOUNT_NO,
        MERCHANT_CATAGORY_CODE,
        TRANSACTION_CURRENCY,
        TRANSACTION_AMOUNT,
        COUNTRY_CODE,
        MERCHANT_NAME,
        MERCHANT_CITY,
        POSTAL_CODE,
        CRC
    }

    public static String getTags(TagLevel level) {
        String rValueTag = "";
        switch (level) {
            case PAYLOAD:
                rValueTag = "00";
                break;
            case POINT_OF_INITIATION_METHOD:
                // for static or dynamic (value = 11 for static & value = 12 for dynamic)
                rValueTag = "01";
                break;
            case MERCHANT_ACCOUNT_NO:
                rValueTag = "02";
                break;
            case MERCHANT_CATAGORY_CODE:
                rValueTag = "52";
                break;
            case TRANSACTION_CURRENCY:
                rValueTag = "53";
                break;
            case TRANSACTION_AMOUNT:
                rValueTag = "54";
                break;
            case COUNTRY_CODE:
                rValueTag = "58";
                break;
            case MERCHANT_NAME:
                rValueTag = "59";
                break;
            case MERCHANT_CITY:
                rValueTag = "60";
                break;
            case POSTAL_CODE:
                rValueTag = "61";
                break;
            case CRC:
                rValueTag = "63";
                break;
        }

        return rValueTag;
    }


}
