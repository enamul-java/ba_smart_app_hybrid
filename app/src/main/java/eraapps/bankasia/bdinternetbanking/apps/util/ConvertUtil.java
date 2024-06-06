package eraapps.bankasia.bdinternetbanking.apps.util;

public class ConvertUtil {
    public static String emptyCheck(String input){
        if(null == input){
            return "";
        }else if(input.equals("null")){
            return "";
        }else{
            return input;
        }
    }
    public static String emptyCheckBalance(String input){
        if(null == input){
            return "0.0";
        }else if(input.equals("null")){
            return "0.0";
        }else if(input.equals("")){
            return "0.0";
        }else{
            return input;
        }
    }
}
