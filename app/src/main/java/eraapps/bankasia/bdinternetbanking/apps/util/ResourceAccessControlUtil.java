package eraapps.bankasia.bdinternetbanking.apps.util;

import android.app.Activity;

import eraapps.bankasia.bdinternetbanking.apps.BuildConfig;

public class ResourceAccessControlUtil {

    /**
     * Start Develop on 28 Dec 2023
     * @param activity
     * @return
     */
    public static boolean otpPerferenceAccess(Activity activity){
        if (!BuildConfig.BUILD_TYPE.equals("release")) {
            return false;
        }
        return true;
    }

    public static boolean creditCardStatementAccessControl(Activity activity){
        if (BuildConfig.BUILD_TYPE.equals("release")) {
            return false;
        }
        return true;
    }


    public static boolean accountOpeningAccessControl(Activity activity){
        return true;//live on 18 April, 2024
        /*
        if (!BuildConfig.FLAVOR.equals("live") || BuildConfig.BUILD_TYPE.equals("debug")) {
            return true;
        }
        return false;
        */
    }

    public static boolean serverOnOffAccessControl(Activity activity){
        if (!BuildConfig.FLAVOR.equals("live")) {
            return true;
        }
        return false;
    }

}
