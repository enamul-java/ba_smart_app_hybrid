package eraapps.bankasia.bdinternetbanking.apps.util;


import android.content.Intent;
import android.util.Log;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;


/**
 * Created by ehaque on 1/4/18.
 */

public class LanguageDetection {

    private static String alphabet = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    private static int initialRandom = 5;
    private static int actualRandom = 0;

    private static int trueCount = 0;
    private static int falseCount = 0;

    static public String languageCode [] = {"en","bn"};
    static private Map<String, String> language= new HashMap<String, String>();
    public static void detectLanguage(String text, ResultHandler rh){

        try{
            //Return English is get the text
            //null or text length less then 1
            if((text==null) || (text.length()<1)){return;};

            //Length of the text
            int length = text.length();
            //get the ration form the length
            float ratio = length/initialRandom;
            //multiply ration wiht 2 times
            actualRandom = (int) ratio*2;
            actualRandom = length<5?1:actualRandom;
            actualRandom = actualRandom>10?10:actualRandom;
            System.out.println("Random: "+ actualRandom);

            String temp = "";
            int tempRandom = 0;
            Random rand = new Random();
            Set<Integer> generated = new LinkedHashSet<>();

            for (int i = 0; i < actualRandom; i++) {
                generated.add(rand.nextInt(actualRandom) + 1);
            }

            Iterator<Integer> setIterator = generated.iterator();
            while(setIterator.hasNext()){
                tempRandom = setIterator.next();
                temp = text.substring(tempRandom-1, tempRandom);
                temp = temp.trim();
                if(!temp.equals("")){
                    Log.e("Detection: == >","temp =-" + temp+ "-tempRandom = " + tempRandom+ isEng(temp));

                    if(isEng(temp)){
                        trueCount +=1;
                    }else{
                        falseCount += 1;
                    }
                }

            }

            String result = trueCount>falseCount?languageCode[0].toString():languageCode[1].toString();
            trueCount = 0;
            falseCount = 0;

            rh.success(result);
        }catch(Exception exception){
            rh.failed(languageCode[0].toString(), "Failed With: "+exception+"");
        }

    }

    static boolean isEng(String s){
        return alphabet.contains(s);
    }

}