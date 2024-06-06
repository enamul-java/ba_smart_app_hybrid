package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.SMSModel;


public class Bkash extends Thread{

    public HashMap<String, HashMap<String, String>> bkash(ArrayList<SMSModel> SMSArrayList)
    {
        String Sender;
        String Msg;
        String FormattedDate;
        String PhoneNumber;
        String Date;
        int smart_phone_user_score = 25;
        int bkash_score = 0;
        int flag = 0;
        int taka_flag = 0;
        int bkash_account_holder = 0;
        String key = "null";
        String link_with_mobile_wallet = "null";
        HashMap<String, HashMap<String, String>> BKASH = new HashMap<String, HashMap<String, String>>();
        HashMap<String, String> bkash_payment_list = new HashMap<String, String>();
        HashMap<String, String> bkash_balance_list = new HashMap<String, String>();
        HashMap<String, String> bkash_cashIn_list = new HashMap<String, String>();
        HashMap<String, String> bkash_received_list = new HashMap<String, String>();
        HashMap<String, String> bkash_cashOut_list = new HashMap<String, String>();
        HashMap<String, String> bkash_sendMoney_list = new HashMap<String, String>();
        HashMap<String, String> bkash_account_holder_list = new HashMap<String, String>();
        //Data extraction
        try {
            //Log.d("BKASH", "ArrayList Size: " + SMSArrayList.size());
            for (int i = 0; i < SMSArrayList.size(); i++) {
//                Log.d("Bkash", "Sender: " + SMSArrayList.get(i).getSender());
//                Log.d("Bkash", "Msg: " + SMSArrayList.get(i).getMsg());
//                Log.d("Bkash", "FormattedDate: " + SMSArrayList.get(i).getFormattedDate());
//                Log.d("Bkash", "PhoneNumber: " + SMSArrayList.get(i).getPhoneNumber());
                Sender = SMSArrayList.get(i).getSender().toLowerCase();
                Msg = SMSArrayList.get(i).getMsg().toLowerCase();
                FormattedDate = SMSArrayList.get(i).getFormattedDate();
                PhoneNumber = SMSArrayList.get(i).getPhoneNumber();
                Date = FormattedDate.substring(9,19);
                //Log.d("FormattedDate", "FormattedDate:"+FormattedDate);
                if((Sender.contains("bkash")) && (Msg.contains("payment")) && (Msg.contains("successful"))){
                    link_with_mobile_wallet = "true";
                    bkash_score = 25;
                    bkash_account_holder = 1;
                    //Log.d("Msg", "MainMsg:"+Msg);
                    String[] splits = Msg.split(" ");
                    for ( String split : splits) {
                        //Log.d("Msg", "MsgSplit:"+split);
                        if (flag == 1)
                        {
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            flag = 0;
                            key = "null";
                            split = split.replace(",","");
                            bkash_payment_list.put(split, Date);
                        }
                        if (flag == 2)
                        {
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            flag = 0;
                            key = "null";
                            split = split.replace(",","");
                            //Log.d("payment_balance","balance: "+split+" Date:"+Date);
                            bkash_balance_list.put(split, Date);
                        }
                        if((key.equals("payment")) && (split.equals("tk")))
                        {
                            flag = 1;
                        }
                        if((key.equals("balance")) && (split.equals("tk")))
                        {
                            flag = 2;
                        }
                        if(split.equals("payment"))
                        {
                            key = "payment";
                        }
                        if(split.equals("balance"))
                        {
                            key = "balance";
                        }
                    }
                }
                else if((Sender.contains("bkash")) && (Msg.contains("received")))
                {
                    bkash_account_holder = 1;
                    link_with_mobile_wallet = "true";
                    bkash_score = 25;
                    //Log.d("receivedMsg", "Body:"+Msg);
                    String[] splits = Msg.split(" ");
                    for ( String split : splits) {
                        //balance
                        if ((flag == 1))
                        {
                            // removing fullstop (.) after value of taka
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            flag = 0;
                            key = "null";
                            split = split.replace(",","");
                            bkash_balance_list.put(split,Date);
                        }

                        //received
                        if (flag == 2)
                        {
                            // removing fullstop (.) after value of taka
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            flag = 0;
                            key = "null";
                            split = split.replace(",","");
                            //Log.d("received_balance","balance: "+split+" Date:"+Date);
                            bkash_received_list.put(split,Date);
                        }

                        if ((key.equals("balance")) && (split.equals("tk")))
                        {
                            flag = 1;
                        }
                        if ((key.equals("received")) && (split.equals("tk")))
                        {
                            flag = 2;
                        }
                        if (split.equals("balance"))
                        {
                            key = "balance";
                        }
                        if (split.equals("received"))
                        {
                            key = "received";
                        }
                    }
                }
                else if((Sender.contains("bkash")) && (Msg.contains("cash in")))
                {
                    bkash_account_holder = 1;
                    Log.d("cashincheck",Msg.toString());
                    int balance_flag = 0;
                    flag = 0;
                    link_with_mobile_wallet = "true";
                    String[] splits = Msg.split(" ");
                    for ( String split : splits) {
                        if (flag == 1)
                        {
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            split = split.replace(",","");
                            bkash_cashIn_list.put(split,Date);
                            key = "null";
                            flag =0;

                        }
                        if (balance_flag ==2){

                            balance_flag = 0;
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            split = split.replace(",","");
                            Log.d("testingteam",split.toString());
                            bkash_balance_list.put(split,Date);
                            break;
                        }

                        if (split.equals("tk"))
                        {
                            key = "tk";
                            flag = 1;
                        }
                        if (split.equals("balance"))
                        {
                            balance_flag = 1;
                            Log.d("testingteam","balance_flag = 1");
                        }
                        if (split.equals("tk") && balance_flag == 1)
                        {
                            balance_flag = 2;
                            Log.d("testingteam","balance_flag = 2");
                        }

                    }

                }
                else if((Sender.contains("bkash")) && (Msg.contains("cash out")))
                {
                    bkash_account_holder = 1;
                    link_with_mobile_wallet = "true";
                    bkash_score = 25;
                    String[] splits = Msg.split(" ");
                    for ( String split : splits) {

                        if (key.equals("tk"))
                        {
                            if(split.charAt(split.length() - 1)=='.')
                            {
                                split = split.substring(0, split.length() - 1);
                            }
                            split = split.replace(",","");
                            bkash_cashOut_list.put(split,Date);
                            key = "null";
                            break ;
                        }
                        if (split.equals("tk"))
                        {
                            key = "tk";
                        }
                    }
                }
                else if((Sender.contains("bkash")) && (Msg.contains("send money")))
                {
                    bkash_account_holder = 1;
                    link_with_mobile_wallet = "true";
                    bkash_score = 25;
                    //Log.d("sendMoney", "Body:"+Msg);
                    String[] splits = Msg.split(" ");
                    for ( String split : splits) {
                        if (key.equals("tk")) {
                            if (split.charAt(split.length() - 1) == '.') {
                                split = split.substring(0, split.length() - 1);
                            }
                            split = split.replace(",", "");
                            //Log.d("bkash_sendMoney_list","sendMoney: "+split+" Date: "+Date);
                            bkash_sendMoney_list.put(split, Date);
                            key = "null";
                            break;
                        }
                        if (split.equals("tk")) {
                            key = "tk";
                        }
                    }

                }




            }
//            for (String payment : bkash_payment_list.keySet()) {
//                Log.d("bkash_payment_list", "Payment: " + payment+ " Date: " + bkash_payment_list.get(payment));
//            }
            for (String balance : bkash_balance_list.keySet()) {
                Log.d("bkash_balance_list", "Balance: " + balance+ " Date: " + bkash_balance_list.get(balance));
            }
//            for (String received : bkash_received_list.keySet()) {
//                Log.d("bkash_received_list", "received: " + received+ " Date: " + bkash_received_list.get(received));
//            }
            for (String cashIn : bkash_cashIn_list.keySet()) {
                Log.d("bkash_cashIn_list", "cashIn_list: " + cashIn+ " Date: " + bkash_cashIn_list.get(cashIn));
            }
//            for (String cashOut : bkash_cashOut_list.keySet()) {
//                Log.d("bkash_cashOut_list", "cashOut_list: " + cashOut+ " Date: " + bkash_cashOut_list.get(cashOut));
//            }
//            for (String sendMoney : bkash_sendMoney_list.keySet()) {
//                Log.d("bkash_sendMoney_list", "sendMoney_list: " + sendMoney+ " Date: " + bkash_sendMoney_list.get(sendMoney));
//            }

//            Log.d("bkash_received_list", bkash_received_list.toString());
//            Log.d("bkash_cashIn_list", bkash_cashIn_list.toString());
//            BkashModel bkashModel = new BkashModel();
//            bkashModel.bkashModel(bkash_payment_list);
            BKASH.put("bkash_payment_list",bkash_payment_list);
            BKASH.put("bkash_balance_list",bkash_balance_list);
            BKASH.put("bkash_cashIn_list",bkash_cashIn_list);
            BKASH.put("bkash_received_list",bkash_received_list);
            BKASH.put("bkash_cashOut_list",bkash_cashOut_list);
            BKASH.put("bkash_sendMoney_list",bkash_sendMoney_list);

            //Log.d("bkash_balance_list", bkash_balance_list.toString());
            if(bkash_account_holder == 1){
                bkash_account_holder_list.put("account_holder","Yes");
            }
            else{
                bkash_account_holder_list.put("account_holder","No");
            }
            BKASH.put("bkash_account_holder",bkash_account_holder_list);
            Log.d("bkashAccountHolder_score1",bkash_account_holder_list.toString());







        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return BKASH;
    }
}
