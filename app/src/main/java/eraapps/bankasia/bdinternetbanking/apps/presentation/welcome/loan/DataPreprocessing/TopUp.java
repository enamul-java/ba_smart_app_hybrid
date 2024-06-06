package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.SMSModel;


public class TopUp extends Thread{
    public HashMap<String, Integer> topUp(ArrayList<SMSModel> SMSArrayList) {
        String Sender;
        String Msg;
        String FormattedDate;
        String PhoneNumber;
        String Date;
        String key;
        String prepaid_user;
        int total_top_up_last_month = 0;
        HashMap<String, Integer> TOPUP = new HashMap<String, Integer>();
        //HashMap<Float, String> TopUp_list = new HashMap<Float, String>();
        try{
            int targetMonth;
            int targetYear;
            String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
            //Log.d("date", "date: " + date);
            targetMonth = Integer.parseInt(date.split("-")[1]) - 1;
            targetYear = Integer.parseInt(date.split("-")[2]);
            if(targetMonth==0){
                targetMonth = 12;
                targetYear = targetYear - 1;
            }

            int month;
            int year;


            for (int i = 0; i < SMSArrayList.size(); i++) {
//                Log.d("TopUp", "Sender: " + SMSArrayList.get(i).getSender());
//                Log.d("TopUp", "Msg: " + SMSArrayList.get(i).getMsg());
//                Log.d("TopUp", "FormattedDate: " + SMSArrayList.get(i).getFormattedDate());
//                Log.d("TopUp", "PhoneNumber: " + SMSArrayList.get(i).getPhoneNumber());

                Sender = SMSArrayList.get(i).getSender().toLowerCase();
                Msg = SMSArrayList.get(i).getMsg().toLowerCase();
                FormattedDate = SMSArrayList.get(i).getFormattedDate();
                PhoneNumber = SMSArrayList.get(i).getPhoneNumber();
                Date = FormattedDate.substring(9,19);

                month = Integer.parseInt(Date.split("/")[1]);
                year = Integer.parseInt(Date.split("/")[2]);



                if((year==targetYear)&&(month==targetMonth)) {

//                    Log.d("TopUP","month "+month);
//                    Log.d("TopUP","year "+year);
//                    Log.d("TopUP","targetMonth "+targetMonth);
//                    Log.d("TopUP","targetYear "+targetYear);

                    key = "null";
                    //((Sender.contains("itopup")) && (Msg.contains("recharged"))) ||
                    if( ((Sender.contains("flexiload"))
                            && (Msg.contains("refilled")))) {
                        prepaid_user = "true";
                        //Log.d("TopUP","MSG "+Msg);
                        String[] splits = Msg.split(" ");
                        //Log.d("TopUp_list","splits:"+splits[0]);


                        for ( String split : splits) {
                            //Log.d("TopUP","split:"+split);
                            if (key.contains("tk")){
                                if (split.charAt(split.length() - 1) == '.') {
                                    split = split.substring(0, split.length() - 1);
                                }
                               // Log.d("TopUP","split:"+split+"date:"+Date);
                                total_top_up_last_month = total_top_up_last_month + Integer.parseInt(split);
                                key = "null";
                            }
                            if (split.contains("tk") ){
                                key = "tk";
                                //Log.d("TopUP","key:"+key);
                            }
                        }
                    }

                }



            }
            //Log.d("TopUpList",String.valueOf(TopUp_list));
            TOPUP.put("TopUp_list",total_top_up_last_month);
          //  Log.d("TopUpList",TOPUP.toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return TOPUP;
    }
}
