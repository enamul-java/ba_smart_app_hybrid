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


public class BkashPaymentModel {

  DatabaseHelper db;
    Context context;

    public BkashPaymentModel(Context context){
        this.context = context;
    }

    private ArrayList<SlabModel> slabModelArrayList;

    public HashMap<String,String> bkashPaymentModel(ArrayList<SMSModel> SMSArrayList){

        Log.d("Hit","bkashPaymentModel hit");

        HashMap<String, String> bkash_score = new HashMap<String, String>();

        try{
            HashMap<String, HashMap<String, String>> BKASH = new HashMap<String, HashMap<String, String>>();

            Bkash bkash = new Bkash();
            BKASH = bkash.bkash(SMSArrayList);
            //Log.d("bkashModel", BKASH.toString());

            HashMap<String, String> bkash_payment_list = new HashMap<String, String>();
            //Log.d("bkashModel", BKASH.toString());

            for (String ListName : BKASH.keySet()) {
                //Log.d("BKASH", "ListName: " + ListName+ " List: " + BKASH.get(ListName));
                if(ListName.equals("bkash_payment_list")){
                    bkash_payment_list = BKASH.get(ListName);
                }
            }

//            Log.d("bkash_payment_list", bkash_payment_list.toString());
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
                //Log.d("bkashWithdrawModel","compName: "+compName);
                if(compName.contains("Financial Skills") || compName.contains("Financial"))
                {
                    Log.d("bkashWithdrawModel","Gotit");
                    Log.d("bkashWithdrawModel","compNameEquals: "+compName);
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Bkash Payment"))
                                    {
                                        valFlag = valFlag + 1;
//                                        Log.d("asd0", "valScoreFlag: "+valScoreFlag);
//                                        Log.d("asd0","flag value: " + valFlag);
//                                        Log.d("asd0","varID: "+varID);
//                                        Log.d("asd0","varName: "+varName);
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
//                                            Log.d("asd","valName1: "+valName1+" valScore1: "+valScore1);

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
//                                            Log.d("asd","valName2: "+valName2+" valScore2: "+valScore2);
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
//                                            Log.d("asd","valName3: "+valName3+" valScore3: "+valScore3);
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
//                                            Log.d("asd","valName4: "+valName4+" valScore4: "+valScore4);
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
//                                            Log.d("asd","valName5: "+valName5+" valScore5: "+valScore5);
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
//                                            Log.d("asd","valName6: "+valName6+" valScore6: "+valScore6);
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Bkash Payment"))
                                    {
                                        valFlag = valFlag + 1;
//                                        Log.d("bkashWithdrawModel1", "valScoreFlag: "+valScoreFlag);
//                                        Log.d("bkashWithdrawModel1","flag value: " + valFlag);
//                                        Log.d("bkashWithdrawModel1","varID: "+varID);
//                                        Log.d("bkashWithdrawModel1","varName: "+varName);
//                                        Log.d("bkashWithdrawModel1","checking flag");
                                        if(valFlag==1)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag1");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab1 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab1);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab1 = -1;
                                            }
                                            else{
                                                upperSlab1 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab1"+upperSlab1);
                                            valScore1 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore1"+valScore1);
                                            if(valFlag == MaxvalFlag)
                                            {
//                                                Log.d("bkashWithdrawModel","break");
                                                valFlag = 0;
                                                break;
                                            }
//                                            Log.d("bkashWithdrawModel1","lowerSlab1: "+lowerSlab1+" upperSlab1: "+upperSlab1);
//                                            Log.d("bkashWithdrawModel1","valScore1: "+valScore1);

                                        }
                                        if(valFlag==2)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag2");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab2 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab2);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab2 = -1;
                                            }
                                            else{
                                                upperSlab2 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab2"+upperSlab2);
                                            valScore2 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore2"+valScore2);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashWithdrawModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
//                                            Log.d("bkashWithdrawModel1","lowerSlab2: "+lowerSlab2+" upperSlab2: "+upperSlab2);
//                                            Log.d("bkashWithdrawModel1","valScore2: "+valScore2);

                                        }
                                        if(valFlag==3)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag3");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab3 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab3);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab3 = -1;
                                            }
                                            else{
                                                upperSlab3 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab3"+upperSlab3);
                                            valScore3 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore3"+valScore3);
                                            if(valFlag == MaxvalFlag)
                                            {
//                                                Log.d("bkashWithdrawModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
//                                            Log.d("bkashWithdrawModel1","lowerSlab3: "+lowerSlab3+" upperSlab3: "+upperSlab3);
//                                            Log.d("bkashWithdrawModel1","valScore3: "+valScore3);

                                        }
                                        if(valFlag==4)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag4");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab4 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab4);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab4 = -1;
                                            }
                                            else{
                                                upperSlab4 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab4"+upperSlab4);
                                            valScore4 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore4"+valScore4);
                                            if(valFlag == MaxvalFlag)
                                            {
//                                                Log.d("bkashWithdrawModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
//                                            Log.d("bkashWithdrawModel1","lowerSlab4: "+lowerSlab4+" upperSlab4: "+upperSlab4);
//                                            Log.d("bkashWithdrawModel1","valScore4: "+valScore4);

                                        }
                                        if(valFlag==5)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag5");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab5 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab5);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab5 = -1;
                                            }
                                            else{
                                                upperSlab5 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab5"+upperSlab5);
                                            valScore5 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore5"+valScore5);

//                                            Log.d("bkashWithdrawModel1","lowerSlab5: "+lowerSlab5+" upperSlab5: "+upperSlab5);
//                                            Log.d("bkashWithdrawModel1","valScore5: "+valScore5);
                                            if(valFlag == MaxvalFlag)
                                            {
//                                                Log.d("bkashWithdrawModel1","break");
                                                valFlag = 0;
                                                break;
                                            }


                                        }
                                        if(valFlag==6)
                                        {
//                                            Log.d("bkashWithdrawModel1"," entered flag6");
//                                            Log.d("bkashWithdrawModel1",vl.get_min_value());
                                            lowerSlab6 = Integer.parseInt(vl.get_min_value());
//                                            Log.d("bkashWithdrawModel1","val"+lowerSlab6);
//                                            Log.d("bkashWithdrawModel1","MaxVal"+MaxvalFlag);
//                                            Log.d("bkashWithdrawModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
//                                                Log.d("bkashWithdrawModel1","null value");
                                                upperSlab6 = -1;
                                            }
                                            else{
                                                upperSlab6 = Integer.parseInt(vl.get_max_value());
                                            }


//                                            Log.d("bkashWithdrawModel1","upperSlab6"+upperSlab6);
                                            valScore6 = vl.get_value_score();
//                                            Log.d("bkashWithdrawModel1","valScore6"+valScore6);
                                            if(valFlag == MaxvalFlag)
                                            {
//                                                Log.d("bkashWithdrawModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
//                                            Log.d("bkashWithdrawModel1","lowerSlab6: "+lowerSlab6+" upperSlab6: "+upperSlab6);
//                                            Log.d("bkashWithdrawModel1","valScore6: "+valScore6);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }

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
            int month;
            int year;

            //calculating total bkash payment in the recent month :
            double total_bkash_payment = 0;
            double payment_taka = 0;
            String payment_date = null;

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
            Log.d("bkashPayment_score", "total_bkash_payment: " + total_bkash_payment);




            int bkashPaymentScore = 0;
//            Log.d("bkashPaymentScore","Bkash_total_withdraw: "+Bkash_total_withdraw+" lowerSlab1: "+lowerSlab1
//                    +" upperSlab1: "+upperSlab1);
//
//            Log.d("bkash_balance_list","Bkash_total_withdraw: "+Bkash_total_withdraw+" lowerSlab5: "+lowerSlab5
//                    +" upperSlab5: "+upperSlab5);

            if((total_bkash_payment>=lowerSlab1)&&(total_bkash_payment<upperSlab1)){
//                Log.d("bkash_balance_list","bkash_balance: "+total_bkash_payment+" lowerSlab1: "+lowerSlab1
//                        +" upperSlab1: "+upperSlab1);
                bkashPaymentScore = Integer.parseInt(valScore1);
            }
            else if((total_bkash_payment>=lowerSlab2)&&(total_bkash_payment<upperSlab2)){
//                Log.d("bkash_balance_list","bkash_balance: "+total_bkash_payment+" lowerSlab1: "+lowerSlab1
//                        +" upperSlab1: "+upperSlab1);
                bkashPaymentScore = Integer.parseInt(valScore2);
            }
            else if((total_bkash_payment>=lowerSlab3)&&(total_bkash_payment<upperSlab3)){
                bkashPaymentScore = Integer.parseInt(valScore3);
            }
            else if((total_bkash_payment>=lowerSlab4)&&(total_bkash_payment<upperSlab4)){
                bkashPaymentScore = Integer.parseInt(valScore4);
            }
            else if((total_bkash_payment>=lowerSlab5)&&(total_bkash_payment<upperSlab5)){
                bkashPaymentScore = Integer.parseInt(valScore5);
            }
            else if((total_bkash_payment>=lowerSlab6)&&(total_bkash_payment<upperSlab6)){
                bkashPaymentScore = Integer.parseInt(valScore6);
            }
            Log.d("bkashPayment_score", "bkashPayment_score: " + bkashPaymentScore);

            bkash_score.put("Bkash Payment: "+ String.valueOf(total_bkash_payment) ,String.valueOf(bkashPaymentScore));

        }catch (Exception e){
            e.printStackTrace();
        }

        return bkash_score;
    }
}
