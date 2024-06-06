package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.Bkash;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.CreditCard;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;


public class BkashBalanceModel {
    DatabaseHelper db;
    Context context;
    // ArrayList<String> arrayList = new ArrayList<>();

    public BkashBalanceModel(Context context){
        this.context = context;
    }

    private ArrayList<SlabModel> slabModelArrayList;

    public HashMap<String,String> bkashModel(ArrayList<SMSModel> SMSArrayList){

        //Log.d("Hit","bkash model");

        HashMap<String, String> bkash_score = new HashMap<String, String>();

        try{
            HashMap<String, HashMap<String, String>> BKASH = new HashMap<String, HashMap<String, String>>();

            Bkash bkash = new Bkash();
            BKASH = bkash.bkash(SMSArrayList);
            //Log.d("bkashModel", BKASH.toString());

            HashMap<String, String> bkash_payment_list = new HashMap<String, String>();
            HashMap<String, String> bkash_balance_list = new HashMap<String, String>();
            HashMap<String, String> bkash_cashIn_list = new HashMap<String, String>();
            HashMap<String, String> bkash_received_list = new HashMap<String, String>();
            HashMap<String, String> bkash_cashOut_list = new HashMap<String, String>();
            HashMap<String, String> bkash_sendMoney_list = new HashMap<String, String>();



            //Log.d("bkashModel", BKASH.toString());

            for (String ListName : BKASH.keySet()) {
                //Log.d("BKASH", "ListName: " + ListName+ " List: " + BKASH.get(ListName));
                if(ListName.equals("bkash_payment_list")){
                    bkash_payment_list = BKASH.get(ListName);
                }
                else if(ListName.equals("bkash_balance_list")){
                    bkash_balance_list = BKASH.get(ListName);
                }
                else if(ListName.equals("bkash_cashIn_list")){
                    bkash_cashIn_list = BKASH.get(ListName);
                }
                else if(ListName.equals("bkash_received_list")){
                    bkash_received_list = BKASH.get(ListName);
                }
                else if(ListName.equals("bkash_cashOut_list")){
                    bkash_cashOut_list = BKASH.get(ListName);
                }
                else if(ListName.equals("bkash_sendMoney_list")){
                    bkash_sendMoney_list = BKASH.get(ListName);
                }
            }

//            Log.d("bkash_payment_list", bkash_payment_list.toString());
            Log.d("bkash_balance_list", bkash_balance_list.toString());
//            Log.d("bkash_cashIn_list", bkash_cashIn_list.toString());
//            Log.d("bkash_received_list", bkash_received_list.toString());
//            Log.d("bkash_cashOut_list", bkash_cashOut_list.toString());
//            Log.d("bkash_sendMoney_list", bkash_sendMoney_list.toString());

            int compID;
            String compName;
            int varID;
            String varName;
            String valName1 = null;
            String valName2 = null;
            String valName3 = null;
            String valName4 = null;
            String valName5 = null;
            String valName6 = null;
            String valScore1 = null;
            String valScore2 = null;
            String valScore3 = null;
            String valScore4 = null;
            String valScore5 = null;
            String valScore6 = null;

            int lowerSlab1 = 0;
            int upperSlab1 = 0;
            int lowerSlab2 = 0;
            int upperSlab2 = 0;
            int lowerSlab3 = 0;
            int upperSlab3 = 0;
            int lowerSlab4 = 0;
            int upperSlab4 = 0;
            int lowerSlab5 = 0;
            int upperSlab5 = 0;
            int lowerSlab6 = 0;
            int upperSlab6 = 0;

            int AboveFlag = 0;
            int BelowFlag = 0;

            String valScoreFlag = null;

            int valFlag=0;
            int insertdata=0;
            int MaxvalFlag=0;

            db = new DatabaseHelper(this.context);

            List<Component> components = db.getAllComponents();
            List<Variable> variables = db.getAllVariables();
            List<Value> values = db.getAllValues();

            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("asd",compName);
                if(compName.contains("Financial Skills"))
                {
                    Log.d("asd","compNameEquals: "+compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            MaxvalFlag=0;
                            for (Value vl : values) {

//                                Log.d("asd","get_flag: "+vl.get_value_variable_id());
                                if((varID == vl.get_value_variable_id())){
//                                    Log.d("asd","get_value_variable_id: "+vl.get_value_variable_id());
//                                    Log.d("asd","get_flag: "+vl.get_flag());
//                                    Log.d("asd","check: "+vl.get_max_value());
//                                    Log.d("asd","check: "+vl.get_min_value());
                                    valScoreFlag = vl.get_flag();
                                    MaxvalFlag = MaxvalFlag + 1;
                                }
                            }

                            //Log.d("asd","valFlag: "+valScoreFlag);
                            if(valScoreFlag.contains("0")){
                                for (Value vl : values) {
                                    if((varID == vl.get_value_variable_id()) && varName.contains("MFS Balance"))
                                    {
                                        valFlag = valFlag + 1;
                                        Log.d("asd0", "valScoreFlag: "+valScoreFlag);
                                        Log.d("asd0","flag value: " + valFlag);
                                        Log.d("asd0","varID: "+varID);
                                        Log.d("asd0","varName: "+varName);
                                        if(valFlag==1)
                                        {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd","valName1: "+valName1+" valScore1: "+valScore1);

                                        }
                                        if(valFlag==2)
                                        {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }
                                            //Log.d("HEllo","flag2");
                                            Log.d("asd","valName2: "+valName2+" valScore2: "+valScore2);
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            Log.d("asd","valName3: "+valName3+" valScore3: "+valScore3);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }

                                            insertdata =1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                            //Log.d("slabModelArrayList123", "LOG: " + log);
                                        }
                                        if(valFlag == 4)
                                        {
                                            valName4 = vl.get_value_name();
                                            valScore4 = vl.get_value_score();
                                            Log.d("asd","valName4: "+valName4+" valScore4: "+valScore4);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if(valFlag == 5)
                                        {
                                            valName5 = vl.get_value_name();
                                            valScore5 = vl.get_value_score();
                                            Log.d("asd","valName5: "+valName5+" valScore5: "+valScore5);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if(valFlag == 6)
                                        {
                                            valName6 = vl.get_value_name();
                                            valScore6 = vl.get_value_score();
                                            Log.d("asd","valName6: "+valName6+" valScore6: "+valScore6);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                    }
                                }
                            }
                            else if (valScoreFlag.contains("1")){
                                for (Value vl : values) {
                                    if((varID == vl.get_value_variable_id()) && varName.contains("MFS Balance"))
                                    {
                                        valFlag = valFlag + 1;
                                        Log.d("asd1", "valScoreFlag: "+valScoreFlag);
                                        Log.d("asd1","flag value: " + valFlag);
                                        Log.d("asd1","varID: "+varID);
                                        Log.d("asd1","varName: "+varName);
                                        Log.d("asd1","checking flag");
                                        if(valFlag==1)
                                        {
                                            Log.d("asd1"," entered flag1");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab1);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab1 = -1;
                                            }
                                            else{
                                                upperSlab1 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab1"+upperSlab1);
                                            valScore1 = vl.get_value_score();
                                            Log.d("asd1","valScore1"+valScore1);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1","lowerSlab1: "+lowerSlab1+" upperSlab1: "+upperSlab1);
                                            Log.d("asd1","valScore1: "+valScore1);

                                        }
                                        if(valFlag==2)
                                        {
                                            Log.d("asd1"," entered flag2");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab2);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab2 = -1;
                                            }
                                            else{
                                                upperSlab2 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab2"+upperSlab2);
                                            valScore2 = vl.get_value_score();
                                            Log.d("asd1","valScore2"+valScore2);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1","lowerSlab2: "+lowerSlab2+" upperSlab2: "+upperSlab2);
                                            Log.d("asd1","valScore2: "+valScore2);

                                        }
                                        if(valFlag==3)
                                        {
                                            Log.d("asd1"," entered flag3");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab3);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab3 = -1;
                                            }
                                            else{
                                                upperSlab3 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab3"+upperSlab3);
                                            valScore3 = vl.get_value_score();
                                            Log.d("asd1","valScore3"+valScore3);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1","lowerSlab3: "+lowerSlab3+" upperSlab3: "+upperSlab3);
                                            Log.d("asd1","valScore3: "+valScore3);

                                        }
                                        if(valFlag==4)
                                        {
                                            Log.d("asd1"," entered flag4");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab4);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab4 = -1;
                                            }
                                            else{
                                                upperSlab4 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab4"+upperSlab4);
                                            valScore4 = vl.get_value_score();
                                            Log.d("asd1","valScore4"+valScore4);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1","lowerSlab4: "+lowerSlab4+" upperSlab4: "+upperSlab4);
                                            Log.d("asd1","valScore4: "+valScore4);

                                        }
                                        if(valFlag==5)
                                        {
                                            Log.d("asd1"," entered flag5");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab5);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab5 = -1;
                                            }
                                            else{
                                                upperSlab5 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab5"+upperSlab5);
                                            valScore5 = vl.get_value_score();
                                            Log.d("asd1","valScore5"+valScore5);

                                            Log.d("asd1","lowerSlab5: "+lowerSlab5+" upperSlab5: "+upperSlab5);
                                            Log.d("asd1","valScore5: "+valScore5);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }


                                        }
                                        if(valFlag==6)
                                        {
                                            Log.d("asd1"," entered flag6");
                                            Log.d("asd1",vl.get_min_value());
                                            lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1","val"+lowerSlab6);
                                            Log.d("asd1","MaxVal"+MaxvalFlag);
                                            Log.d("asd1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab6 = -1;
                                            }
                                            else{
                                                upperSlab6 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1","upperSlab6"+upperSlab6);
                                            valScore6 = vl.get_value_score();
                                            Log.d("asd1","valScore6"+valScore6);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("asd1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1","lowerSlab6: "+lowerSlab6+" upperSlab6: "+upperSlab6);
                                            Log.d("asd1","valScore6: "+valScore6);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }


//            String str;
//
//            if(valName1 != null){
//                str=valName1;
//                String numberOnly= str.replaceAll("[^0-9]", "");
//                Log.d("asd","valName1: "+numberOnly);
//                if(valName1.contains("Above")){
//                    lowerSlab1 = Integer.parseInt(numberOnly)*1000;
//                    AboveFlag = 1;
//                }
//                Log.d("asd","valoflowerSlab1: "+lowerSlab1);
//            }
//
//            if(valName2 != null){
//
//
//            }


//            String[] valName1splits = valName1.split("-");
//            for( int i = 0; i < valName1splits.length; i++) {
//                if(i==0){
//                    lowerSlab1=Integer.parseInt(valName1splits[i]);
//                }
//                else if(i==1){
//                    upperSlab1=Integer.parseInt(valName1splits[i]);
//                }
//            }
//            String[] valName2splits = valName2.split("-");
//            for( int i = 0; i < valName2splits.length; i++) {
//                if(i==0){
//                    lowerSlab2=Integer.parseInt(valName2splits[i]);
//                }
//                else if(i==1){
//                    upperSlab2=Integer.parseInt(valName2splits[i]);
//                }
//            }
//            String[] valName3splits = valName3.split("-");
//            for( int i = 0; i < valName3splits.length; i++) {
//                if(i==0){
//                    lowerSlab3=Integer.parseInt(valName3splits[i]);
//                }
//                else if(i==1){
//                    upperSlab3=Integer.parseInt(valName3splits[i]);
//                }
//            }

//            String log = "lowerSlab1 : " +lowerSlab1+" ,upperSlab1 : "+upperSlab1+" lowerSlab2 "
//                    +lowerSlab2+" upperSlab2 "+upperSlab2+" lowerSlab3 "+lowerSlab3+" upperSlab3 "+upperSlab3;
//            Log.d("SLABz", "LOG: " + log);

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
//            Log.d("targetMonth", "targetMonth: " + targetMonth);
//            Log.d("targetMonth", "targetYear: " + targetYear);

            //calculating total bkash payment in the recent month :
            double total_bkash_payment = 0;
            double payment_taka = 0;
            String payment_date = null;
            int month;
            int year;

            for (String payment : bkash_payment_list.keySet()) {
                //Getting payment_taka and payment_date from bkash_payment_list:
                payment_taka = Double.parseDouble(payment);
                payment_date = bkash_payment_list.get(payment);
                //Log.d("total_bkash_payment", "payment_taka: " + payment_taka+ " payment_date: " + payment_date);
                //extracting month and year from payment_date
                month = Integer.parseInt(payment_date.split("/")[1]);
                year = Integer.parseInt(payment_date.split("/")[2]);
                //Log.d("total_bkash_payment", "month: " + month+ " year: " + year);
                //calculating total bkash payment in the recent month :
                if((year==targetYear)&&(month==targetMonth)) {
                    total_bkash_payment = total_bkash_payment + payment_taka;
                }
            }
            //Log.d("total_bkash_payment", "total_bkash_payment: " + total_bkash_payment);

            //calculating total bkash received in the recent month :
            double total_bkash_received = 0;
            double received_taka = 0;
            String received_date = null;
            for (String received : bkash_received_list.keySet()) {
                //Log.d("bkash_received_list", "received: " + received+ " Date: " + bkash_received_list.get(received));
                //Getting received_taka and received_date from bkash_received_list dictionaries:
                String received2 = received;
                received = received.replaceAll("[^\\d.]", "");
                Log.d("errorOut","check: "+ received);
                Log.d("errorOut","check: "+ Double.parseDouble(received));
                received_taka = Double.parseDouble(received);
                received_date = bkash_received_list.get(received2);
                Log.d("bkashRecieved", "received_taka: " + received_taka+" received_date: "+received_date);
                //extracting month and year from payment_date
                month = Integer.parseInt(received_date.split("/")[1]);
                year = Integer.parseInt(received_date.split("/")[2]);
                //Log.d("bkashRecieved", "month: " + month+ " year: " + year);
                //calculating total bkash payment in the recent month :
                if((year==targetYear)&&(month==targetMonth)) {
                    total_bkash_received = total_bkash_received + received_taka;
                    //Log.d("received_taka", "received_taka: " + received_taka);
                }
            }
            //Log.d("total_bkash_received", "total_bkash_received: " + total_bkash_received);

            //Calculating Total Bkash CashIn in the recent month :
            double total_bkash_Cash_in = 0;
            double cashIn_taka = 0;
            String cashIn_date = null;
            for (String cashIn : bkash_cashIn_list.keySet()) {
                //Log.d("bkash_cashIn_list", "cashIn_list: " + cashIn+ " Date: " + bkash_cashIn_list.get(cashIn));
                cashIn_taka = Double.parseDouble(cashIn);
                cashIn_date = bkash_cashIn_list.get(cashIn);
                month = Integer.parseInt(cashIn_date.split("/")[1]);
                year = Integer.parseInt(cashIn_date.split("/")[2]);
                //Log.d("bkash_cashIn", "month: " + month+ " year: " + year);
                if((year==targetYear)&&(month==targetMonth)) {
                    total_bkash_Cash_in = total_bkash_Cash_in + cashIn_taka;
                }
            }
            //Log.d("total_bkash_Cash_in", "total_bkash_Cash_in: " + total_bkash_Cash_in);

            //Calculating Total Bkash cashOut in the recent month:
            double total_bkash_cash_out = 0;
            double cashOut_taka = 0;
            String cashOut_date = null;
            for (String cashOut : bkash_cashOut_list.keySet()) {
                //Log.d("bkash_cashOut_list", "cashOut_list: " + cashOut+ " Date: " + bkash_cashOut_list.get(cashOut));
                cashOut_taka = Double.parseDouble(cashOut);
                cashOut_date = bkash_cashOut_list.get(cashOut);
                month = Integer.parseInt(cashOut_date.split("/")[1]);
                year = Integer.parseInt(cashOut_date.split("/")[2]);
                if((year==targetYear)&&(month==targetMonth)) {
                    total_bkash_cash_out = total_bkash_cash_out + cashOut_taka;
                }
            }
            //Log.d("total_bkash_cash_out", "total_bkash_cash_out: " + total_bkash_cash_out);

            //Calculating Total Bkash Send Money in the recent month:
            double total_bkash_send_money = 0;
            double send_money_taka = 0;
            String send_money_date = null;
            for (String sendMoney : bkash_sendMoney_list.keySet()) {
                //Log.d("bkash_sendMoney_list", "sendMoney_list: " + sendMoney+ " Date: " + bkash_sendMoney_list.get(sendMoney));
                send_money_taka = Double.parseDouble(sendMoney);
                send_money_date = bkash_sendMoney_list.get(sendMoney);
                month = Integer.parseInt(send_money_date.split("/")[1]);
                year = Integer.parseInt(send_money_date.split("/")[2]);
                if((year==targetYear)&&(month==targetMonth)) {
                    total_bkash_send_money = total_bkash_send_money + send_money_taka;
                }
            }
            //Log.d("total_bkash_send_money", "total_bkash_send_money: " + total_bkash_send_money);


            double Bkash_total_deposit;
            double Bkash_total_withdraw;
            Bkash_total_deposit = total_bkash_Cash_in + total_bkash_received;
            Bkash_total_withdraw = total_bkash_send_money + total_bkash_cash_out + total_bkash_payment;

            int bkashDepositScore = 0;
            int bkashWithdrawScore = 0;
            if((Bkash_total_deposit>=lowerSlab1)&&(Bkash_total_deposit<upperSlab1)){
                bkashDepositScore = Integer.parseInt(valScore1);
            }
            else if((Bkash_total_deposit>=lowerSlab2)&&(Bkash_total_deposit<upperSlab2)){
                bkashDepositScore = Integer.parseInt(valScore2);
            }
            else if((Bkash_total_deposit>=lowerSlab3)&&(Bkash_total_deposit<upperSlab3)){
                bkashDepositScore = Integer.parseInt(valScore3);
            }

            if((Bkash_total_withdraw>=lowerSlab1)&&(Bkash_total_withdraw<upperSlab1)){
                bkashWithdrawScore = Integer.parseInt(valScore1);
            }
            else if((Bkash_total_withdraw>=lowerSlab2)&&(Bkash_total_withdraw<upperSlab2)){
                bkashWithdrawScore = Integer.parseInt(valScore2);
            }
            else if((Bkash_total_withdraw>=lowerSlab3)&&(Bkash_total_withdraw<upperSlab3)){
                bkashWithdrawScore = Integer.parseInt(valScore3);
            }

            //bkash_score.put("Bkash total deposit:"+ String.valueOf(Bkash_total_deposit) + "  ||  Bkash deposit score : ",String.valueOf(bkashDepositScore));
            //bkash_score.put("Bkash Deposit Score", String.valueOf(bkashDepositScore));

            //bkash_score.put("Bkash total withdraw:"+ String.valueOf(Bkash_total_withdraw) + "  ||  Bkash Withdraw score : ",String.valueOf(bkashWithdrawScore));
            //bkash_score.put("Bkash Withdraw Score", String.valueOf(bkashWithdrawScore));



//            for (Component cn : components) {
//                compID = cn.get_id();
//                compName = cn.get_name();
//                // Log.d("compName",compName);
//                if(compName.equals("Bkash"))
//                {
//                    //Log.d("component",compName);
//                    for (Variable vb : variables) {
//                        if(compID == vb.get_component_id())
//                        {
//                            varID = vb.get_id();
//                            varName = vb.get_name();
//                            if (varName.contains("Bkash balance")){
//                                valFlag=0;
//                                for (Value vl : values) {
//
//                                    if(varID == vl.get_value_variable_id())
//                                    {
//                                        valFlag = valFlag + 1;
//                                        //Log.d("HEllo","flag value: " + valFlag);
//                                        if(valFlag==1)
//                                        {
//                                            //Log.d("HEllo","flag1");
//                                            valName1 = vl.get_value_name();
//                                            valScore1 = vl.get_value_score();
//
//                                        }
//                                        if(valFlag==2)
//                                        {
//                                            valName2 = vl.get_value_name();
//                                            valScore2 = vl.get_value_score();
//                                            //Log.d("HEllo","flag2");
//                                        }
//                                        if(valFlag == 3)
//                                        {
//                                            valName3 = vl.get_value_name();
//                                            valScore3 = vl.get_value_score();
//                                            valFlag = 0;
//                                            insertdata =1;
//                                            //Log.d("HEllo","flag3");
//                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1"
//                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
//                                            Log.d("TotaldepositSlab", "LOG: " + log);
//                                        }
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//            }
//            lowerSlab1 = 0;
//            upperSlab1 = 0;
//            lowerSlab2 = 0;
//            upperSlab2 = 0;
//            lowerSlab3 = 0;
//            upperSlab3 = 0;

//            valName1splits = valName1.split("-");
//            for( int i = 0; i < valName1splits.length; i++) {
//                if(i==0){
//                    lowerSlab1=Integer.parseInt(valName1splits[i]);
//                }
//                else if(i==1){
//                    upperSlab1=Integer.parseInt(valName1splits[i]);
//                }
//            }
//            valName2splits = valName2.split("-");
//            for( int i = 0; i < valName2splits.length; i++) {
//                if(i==0){
//                    lowerSlab2=Integer.parseInt(valName2splits[i]);
//                }
//                else if(i==1){
//                    upperSlab2=Integer.parseInt(valName2splits[i]);
//                }
//            }
//            valName3splits = valName3.split("-");
//            for( int i = 0; i < valName3splits.length; i++) {
//                if(i==0){
//                    lowerSlab3=Integer.parseInt(valName3splits[i]);
//                }
//                else if(i==1){
//                    upperSlab3=Integer.parseInt(valName3splits[i]);
//                }
//            }

            double bkash_balance = 0;
            double balance_taka = 0;
            String balance_date = null;
            int dayFlag = 0;
            int day;
            for (String balance : bkash_balance_list.keySet()) {
                //Getting payment_taka and payment_date from bkash_payment_list:
                balance_taka = Double.parseDouble(balance);
                balance_date = bkash_balance_list.get(balance);
                //Log.d("total_bkash_payment", "payment_taka: " + payment_taka+ " payment_date: " + payment_date);
                //extracting month and year from payment_date
                month = Integer.parseInt(balance_date.split("/")[1]);
                year = Integer.parseInt(balance_date.split("/")[2]);
                day = Integer.parseInt(balance_date.split("/")[0]);
                //Log.d("bkash_balance_list", "day: "+ day +" month: " + month+ " year: " + year);
                //calculating total bkash payment in the recent month :
                if((year==targetYear)&&(month==targetMonth)) {
                    if(day>dayFlag)
                    {
                        bkash_balance = balance_taka;
                        dayFlag = day;
                    }
                }
            }
            Log.d("bkash_balance_list", "bkash_balance_list: " + bkash_balance);

            int bkashBalanceScore = 0;
            Log.d("bkash_balance_list","bkash_balance: "+bkash_balance+" lowerSlab1: "+lowerSlab1
                    +" upperSlab1: "+upperSlab1);

            Log.d("bkash_balance_list","bkash_balance: "+bkash_balance+" lowerSlab5: "+lowerSlab5
                    +" upperSlab5: "+upperSlab5);

            if((bkash_balance>=lowerSlab1)&&(bkash_balance<upperSlab1)){
                Log.d("bkash_balance_list","bkash_balance: "+bkash_balance+" lowerSlab1: "+lowerSlab1
                +" upperSlab1: "+upperSlab1);
                bkashBalanceScore = Integer.parseInt(valScore1);
            }
            else if((bkash_balance>=lowerSlab2)&&(bkash_balance<upperSlab2)){
                Log.d("bkash_balance_list","bkash_balance: "+bkash_balance+" lowerSlab1: "+lowerSlab1
                        +" upperSlab1: "+upperSlab1);
                bkashBalanceScore = Integer.parseInt(valScore2);
            }
            else if((bkash_balance>=lowerSlab3)&&(bkash_balance<upperSlab3)){
                bkashBalanceScore = Integer.parseInt(valScore3);
            }
            else if((bkash_balance>=lowerSlab4)&&(bkash_balance<upperSlab4)){
                bkashBalanceScore = Integer.parseInt(valScore4);
            }
            else if((bkash_balance>=lowerSlab5)&&(bkash_balance<upperSlab5)){
                bkashBalanceScore = Integer.parseInt(valScore5);
            }
            else if((bkash_balance>=lowerSlab6)&&(bkash_balance<upperSlab6)){
                bkashBalanceScore = Integer.parseInt(valScore6);
            }
            Log.d("bkash_balance_list", "bkashBalanceScore: " + bkashBalanceScore);

            bkash_score.put("Bkash balance:  "+ String.valueOf(bkash_balance),String.valueOf(bkashBalanceScore));





        }catch (Exception e){
            e.printStackTrace();
        }

        return bkash_score;
    }

}
