package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.CreditCard;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;
public class CreditCardMinimumDue {DatabaseHelper db;
    Context context;

    public CreditCardMinimumDue(Context context){
        this.context = context ;
    }

    public HashMap<String,String> creditCardMinimumDue(ArrayList<SMSModel> SMSArrayList) {

        HashMap<String, String> CREDIT_DEBIT_score = new HashMap<String, String>();

        try {
            HashMap<String, HashMap<String, String>> CREDIT_DEBIT = new HashMap<String, HashMap<String, String>>();
            CreditCard creditCard = new CreditCard();
            CREDIT_DEBIT = creditCard.creditCard(SMSArrayList);

            HashMap<String, String> minimum_due_list = new HashMap<String, String>();

            for (String ListName : CREDIT_DEBIT.keySet()) {
                //Log.d("CREDIT_DEBIT", "ListName: " + ListName+ " List: " + CREDIT_DEBIT.get(ListName));

                if(ListName.equals("minimum_due_list")){
                    minimum_due_list = CREDIT_DEBIT.get(ListName);
                }
            }
//            Log.d("credited_amount_list", credited_amount_list.toString());
//            Log.d("card_balance_list", card_balance_list.toString());
//            Log.d("salary_list", salary_list.toString());
//            Log.d("closing_balance_list", closing_balance_list.toString());
            Log.d("minimum_due_list", minimum_due_list.toString());
            //Log.d("debited_amount_list", debited_amount_list.toString());

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


            int month;
            int year;

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


            //MINIMUM DUE
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("asd",compName);
                if(compName.contains("Employment"))
                {
                    Log.d("bkashDepositModel","compNameEquals: "+compName);
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Credit Card Due"))
                                    {
                                        valFlag = valFlag + 1;
                                        Log.d("bkashDepositModel0", "valScoreFlag: "+valScoreFlag);
                                        Log.d("bkashDepositModel0","flag value: " + valFlag);
                                        Log.d("bkashDepositModel0","varID: "+varID);
                                        Log.d("bkashDepositModel0","varName: "+varName);
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
                                            Log.d("bkashDepositModel","valName1: "+valName1+" valScore1: "+valScore1);

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
                                            Log.d("bkashDepositModel","valName2: "+valName2+" valScore2: "+valScore2);
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            Log.d("bkashDepositModel","valName3: "+valName3+" valScore3: "+valScore3);
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
                                            Log.d("bkashDepositModel","valName4: "+valName4+" valScore4: "+valScore4);
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
                                            Log.d("bkashDepositModel","valName5: "+valName5+" valScore5: "+valScore5);
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
                                            Log.d("bkashDepositModel","valName6: "+valName6+" valScore6: "+valScore6);
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Credit Card Due"))
                                    {
                                        valFlag = valFlag + 1;
                                        Log.d("CreditCardDue", "valScoreFlag: "+valScoreFlag);
                                        Log.d("CreditCardDue","flag value: " + valFlag);
                                        Log.d("CreditCardDue","varID: "+varID);
                                        Log.d("CreditCardDue","varName: "+varName);
                                        Log.d("CreditCardDue","checking flag");
                                        if(valFlag==1)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag1");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab1);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("bkashDepositModel1","null value");
                                                upperSlab1 = -1;
                                            }
                                            else{
                                                upperSlab1 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab1"+upperSlab1);
                                            valScore1 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore1"+valScore1);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1","lowerSlab1: "+lowerSlab1+" upperSlab1: "+upperSlab1);
                                            Log.d("bkashDepositModel1","valScore1: "+valScore1);

                                        }
                                        if(valFlag==2)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag2");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab2);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("bkashDepositModel1","null value");
                                                upperSlab2 = -1;
                                            }
                                            else{
                                                upperSlab2 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab2"+upperSlab2);
                                            valScore2 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore2"+valScore2);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1","lowerSlab2: "+lowerSlab2+" upperSlab2: "+upperSlab2);
                                            Log.d("bkashDepositModel1","valScore2: "+valScore2);

                                        }
                                        if(valFlag==3)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag3");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab3);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("bkashDepositModel1","null value");
                                                upperSlab3 = -1;
                                            }
                                            else{
                                                upperSlab3 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab3"+upperSlab3);
                                            valScore3 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore3"+valScore3);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1","lowerSlab3: "+lowerSlab3+" upperSlab3: "+upperSlab3);
                                            Log.d("bkashDepositModel1","valScore3: "+valScore3);

                                        }
                                        if(valFlag==4)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag4");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab4);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("bkashDepositModel1","null value");
                                                upperSlab4 = -1;
                                            }
                                            else{
                                                upperSlab4 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab4"+upperSlab4);
                                            valScore4 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore4"+valScore4);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1","lowerSlab4: "+lowerSlab4+" upperSlab4: "+upperSlab4);
                                            Log.d("bkashDepositModel1","valScore4: "+valScore4);

                                        }
                                        if(valFlag==5)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag5");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab5);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("bkashDepositModel1","null value");
                                                upperSlab5 = -1;
                                            }
                                            else{
                                                upperSlab5 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab5"+upperSlab5);
                                            valScore5 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore5"+valScore5);

                                            Log.d("bkashDepositModel1","lowerSlab5: "+lowerSlab5+" upperSlab5: "+upperSlab5);
                                            Log.d("bkashDepositModel1","valScore5: "+valScore5);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }


                                        }
                                        if(valFlag==6)
                                        {
                                            Log.d("bkashDepositModel1"," entered flag6");
                                            Log.d("bkashDepositModel1",vl.get_min_value());
                                            lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1","val"+lowerSlab6);
                                            Log.d("bkashDepositModel1","MaxVal"+MaxvalFlag);
                                            Log.d("bkashDepositModel1",vl.get_max_value());
                                            if(vl.get_max_value().contains("null")){
                                                Log.d("asd1","null value");
                                                upperSlab6 = -1;
                                            }
                                            else{
                                                upperSlab6 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1","upperSlab6"+upperSlab6);
                                            valScore6 = vl.get_value_score();
                                            Log.d("bkashDepositModel1","valScore6"+valScore6);
                                            if(valFlag == MaxvalFlag)
                                            {
                                                Log.d("bkashDepositModel1","break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1","lowerSlab6: "+lowerSlab6+" upperSlab6: "+upperSlab6);
                                            Log.d("bkashDepositModel1","valScore6: "+valScore6);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }




            //Card minimum due in the recent month:
            double minimum_due = 0;
            double minimum_due_taka = 0;
            String minimum_due_date = null;
            int minimum_due_score = 0;
            for (String minimumDue : minimum_due_list.keySet()) {
                minimum_due_taka = Double.parseDouble(minimumDue);
                minimum_due_date = minimum_due_list.get(minimumDue);

                month = Integer.parseInt(minimum_due_date.split("/")[1]);
                year = Integer.parseInt(minimum_due_date.split("/")[2]);

                if ((year == targetYear) && (month == targetMonth)) {
                    minimum_due = minimum_due_taka;
                }
                else if((year == 2021) && (month == 12)) {
                    minimum_due = minimum_due_taka;
                }
            }
            Log.d("minimum_due",String.valueOf(minimum_due));

            int creditCardDueScore = 0;
            if((minimum_due>=lowerSlab1)&&(minimum_due<upperSlab1)){
                creditCardDueScore = Integer.parseInt(valScore1);
            }
            else if((minimum_due>=lowerSlab2)&&(minimum_due<upperSlab2)){
                creditCardDueScore = Integer.parseInt(valScore2);
            }
            else if((minimum_due>=lowerSlab3)&&(minimum_due<upperSlab3)){
                creditCardDueScore = Integer.parseInt(valScore3);
            }
            else if((minimum_due>=lowerSlab4)&&(minimum_due<upperSlab4)){
                creditCardDueScore = Integer.parseInt(valScore4);
            }
            else if((minimum_due>=lowerSlab5)&&(minimum_due<upperSlab5)){
                creditCardDueScore = Integer.parseInt(valScore5);
            }
            else if((minimum_due>=lowerSlab6)&&(minimum_due<upperSlab6)){
                creditCardDueScore = Integer.parseInt(valScore6);
            }

//            CREDIT_DEBIT_score.put("Credit Card Due : "+ String.valueOf(minimum_due) ,String.valueOf(creditCardDueScore));
            CREDIT_DEBIT_score.put("Credit Card monthly expense : "+ String.valueOf(minimum_due) ,String.valueOf(creditCardDueScore));
            Log.d("CreditCardDue",String.valueOf(minimum_due));
            Log.d("CreditCardDue",String.valueOf(creditCardDueScore));

        }catch (Exception e){
            e.printStackTrace();
        }
        return CREDIT_DEBIT_score;
    }
}
