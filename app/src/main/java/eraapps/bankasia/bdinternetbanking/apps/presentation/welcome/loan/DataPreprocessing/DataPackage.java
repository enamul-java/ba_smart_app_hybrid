package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing;



import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.SMSModel;


public class DataPackage extends Thread{
    public Double dataPackage(ArrayList<SMSModel> SMSArrayList) {
        String Sender;
        String Msg;
        String FormattedDate;
        String PhoneNumber;
        String Date;

        String mobile_internet_user = null;;
        String prepaid_user = null;;
        String postpaid_user = null;;
        String package_category = null;
        String package_valid_day = null;;
        float package_price = 0;

        float postpaid_bill_paid;
        float internet_package_amount = 0;

        String key = null;

        double Total_prepaid_package_amount = 0;



        HashMap<String, HashMap<Float, String>> DATAPACKAGE = new HashMap<String, HashMap<Float, String>>();
        HashMap<Float, String> prepaid_data_package_list = new HashMap<Float, String>();
        HashMap<Float, String> postpaid_data_package_list = new HashMap<Float, String>();

        try {
            //Log.d("BKASH", "ArrayList Size: " + SMSArrayList.size());
            for (int i = 0; i < SMSArrayList.size(); i++) {
//                Log.d("DataPackage", "Sender: " + SMSArrayList.get(i).getSender());
//                Log.d("DataPackage", "Msg: " + SMSArrayList.get(i).getMsg());
//                Log.d("DataPackage", "FormattedDate: " + SMSArrayList.get(i).getFormattedDate());
//                Log.d("DataPackage", "PhoneNumber: " + SMSArrayList.get(i).getPhoneNumber());

                Sender = SMSArrayList.get(i).getSender().toLowerCase();
                Msg = SMSArrayList.get(i).getMsg().toLowerCase();
                FormattedDate = SMSArrayList.get(i).getFormattedDate();
                PhoneNumber = SMSArrayList.get(i).getPhoneNumber();
                Date = FormattedDate.substring(9,19);

                //Log.d("date",Date.toString());

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

                month = Integer.parseInt(Date.split("/")[1]);
                year = Integer.parseInt(Date.split("/")[2]);



                if((year==targetYear)&&(month==targetMonth)) {
                    //Log.d("date","month: "+month+" year: "+year);
                    //Log.d("date","targetMonth: "+targetMonth+" targetYear: "+targetYear);
                    if((Sender.contains("gpflexiplan")) && (Msg.contains("internet")) && (Msg.contains("started"))
                            && (Msg.contains("successfully")) && (Msg.contains("bundle"))){
                        mobile_internet_user = "true";
                        String[] splits = Msg.split(" ");
                        //Log.d("gpflexiplan", "Msg:"+Msg);

                        for ( String split : splits) {
                            //Log.d("gpflexiplan", "MainMsg:"+split);

                            if (key=="fee") {
                                package_price = Float.parseFloat(split);
                                //Log.d("gpflexiplan","package_price:"+package_price);
                                key = "null";

                            }

                            if(key=="validity") {
                                package_valid_day = split;
                                //Log.d("gpflexiplan","package_valid_day:"+package_valid_day);
                                key = "null";
                                if (Integer.parseInt(split) < 8){
                                    package_category = "Weekly";
                                }
                                else {
                                    package_category = "Monthly";
                                }
                                //Log.d("gpflexiplan","package_category:"+package_category);
                            }

                            if (key=="minute,"){
                                //Log.d("gpflexiplan","Hello");
                                internet_package_amount = Float.parseFloat(split);
                                key = "null";
                                Total_prepaid_package_amount = Total_prepaid_package_amount+internet_package_amount;
//                                Log.d("gpflexiplan","internet_package_amount:"+internet_package_amount);
//                                Log.d("Total_prepaid_package_amount","amount: "+Total_prepaid_package_amount);
                            }

                            if(split.equals("fee"))
                            {
                                key = "fee";
                            }
                            if(split.equals("validity"))
                            {
                                key = "validity";
                            }
                            if(split.equals("minute,"))
                            {
                                key = "minute,";
                            }
                            if(split.equals("gb"))
                            {
                                internet_package_amount = internet_package_amount * 1024;
                            }
                            prepaid_data_package_list.put(internet_package_amount,Date);
                        }
                    }
//                    else if((Sender.contains("banglalink")) && (Msg.contains("internet")) && (Msg.contains("purchased"))){
//
//                        mobile_internet_user = "true";
//                        prepaid_user = "true";
//                        key = "null";
//                        //Log.d("banglalink", "Msg:"+Msg);
//                        String[] splits = Msg.split(" ");
//                        for ( String split : splits) {
//                            if (key=="purchased"){
//                                String[] splits2 = split.split("-");
//                                split = splits2[2];
//                                split = split.substring(0, split.length() - 2);
//                                //Log.d("split","msg:"+split);
//                                package_price=Float.parseFloat(split);
//                                prepaid_data_package_list.put(package_price,Date);
//                                key="null";
//                                break;
//                            }
//                            if(split.equals("purchased")){
//                                key="purchased";
//                            }
//                        }
//                    }
//                    else if(Msg.contains("bill payment successful")){
//                        postpaid_user = "true";
//                        mobile_internet_user = "true";
//                        key = "null";
//                        String[] splits = Msg.split(" ");
//                        for ( String split : splits) {
//                            if (key == "tk"){
//                                postpaid_bill_paid = Float.parseFloat(split);
//                                postpaid_data_package_list.put(postpaid_bill_paid,Date);
//                                key = "null";
//                                break;
//                            }
//                            if (split == "tk"){
//                                key = "tk";
//                            }
//                        }
//                    }
                }
            }



            for(Float packagePrice : prepaid_data_package_list.keySet()) {
                //Log.d("prepaid_data_package_list", "packagePrice: " + packagePrice+ " Date: " + prepaid_data_package_list.get(packagePrice));
            }


            DATAPACKAGE.put("prepaid_data_package_list",prepaid_data_package_list);
            DATAPACKAGE.put("postpaid_data_package_list",postpaid_data_package_list);



        }catch (Exception e){
            e.printStackTrace();
        }
    return Total_prepaid_package_amount;
    }

}
