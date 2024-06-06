package eraapps.bankasia.bdinternetbanking.apps.util.download;

import eraapps.bankasia.bdinternetbanking.apps.BuildConfig;
import eraapps.bankasia.bdinternetbanking.apps.common.Constants;

public class DownloadUrl {

    /**
     *@author "Md Enamul Haque"
     *@since
     *@see -Statement Api url generator
     */
    public static String getBaseUrl(String accountNo){

        String api = "AccountStatementDownloadApi?";
        /**
         *@author "Md Enamul Haque"
         *@since
         *@see Agent Banking Statement Api
         */
        /*if(accountNo.startsWith("108") || accountNo.startsWith("508")){
            api = "AbsAccountStatementDownloadApi?";
        }*/
        String baseUrl = Constants.BASE_URL;
        if(BuildConfig.FLAVOR == "luat"){
            return "http://202.40.191.226:8084/Bank-asia-smart-app/"+api;
        }else if(BuildConfig.BUILD_TYPE == "debug"){
            //baseUrl = "http://202.40.191.226:8084/Bank-asia-smart-app/"+api;
            baseUrl = baseUrl.substring(0, baseUrl.length() - 14)+"Bank-asia-smart-app/"+api;
        }else if(BuildConfig.BUILD_TYPE == "release"){
            baseUrl = baseUrl.substring(0, baseUrl.length() - 14)+"Bank-asia-smart-app/"+api;
        }else{
            baseUrl = baseUrl.substring(0, baseUrl.length() - 14)+"Bank-asia-smart-app/"+api;
        }
        return baseUrl;
    }
}
