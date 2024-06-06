package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.CreditCard;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class CreditCardModel {
    DatabaseHelper db;
    Context context;

    public CreditCardModel(Context context){
        this.context = context ;
    }

    public HashMap<String,String> creditCardModel(ArrayList<SMSModel> SMSArrayList) {

        HashMap<String, String> CREDIT_DEBIT_score = new HashMap<String, String>();

        try {
            HashMap<String, HashMap<String, String>> CREDIT_DEBIT = new HashMap<String, HashMap<String, String>>();
            CreditCard creditCard = new CreditCard();
            CREDIT_DEBIT = creditCard.creditCard(SMSArrayList);

            HashMap<String, String> credited_amount_list = new HashMap<String, String>();
            HashMap<String, String> card_balance_list = new HashMap<String, String>();
            HashMap<String, String> salary_list = new HashMap<String, String>();
            HashMap<String, String> closing_balance_list = new HashMap<String, String>();
            HashMap<String, String> minimum_due_list = new HashMap<String, String>();
            HashMap<String, String> debited_amount_list = new HashMap<String, String>();

            for (String ListName : CREDIT_DEBIT.keySet()) {
                //Log.d("CREDIT_DEBIT", "ListName: " + ListName+ " List: " + CREDIT_DEBIT.get(ListName));
                if(ListName.equals("credited_amount_list")){
                    credited_amount_list = CREDIT_DEBIT.get(ListName);
                }
                else if(ListName.equals("card_balance_list")){
                    card_balance_list = CREDIT_DEBIT.get(ListName);
                }
                else if(ListName.equals("salary_list")){
                    salary_list = CREDIT_DEBIT.get(ListName);
                }
                else if(ListName.equals("closing_balance_list")){
                    closing_balance_list = CREDIT_DEBIT.get(ListName);
                }
                else if(ListName.equals("minimum_due_list")){
                    minimum_due_list = CREDIT_DEBIT.get(ListName);
                }
                else if(ListName.equals("debited_amount_list")){
                    debited_amount_list = CREDIT_DEBIT.get(ListName);
                }
            }
//            Log.d("credited_amount_list", credited_amount_list.toString());
//            Log.d("card_balance_list", card_balance_list.toString());
//            Log.d("salary_list", salary_list.toString());
//            Log.d("closing_balance_list", closing_balance_list.toString());
//            Log.d("minimum_due_list", minimum_due_list.toString());
            //Log.d("debited_amount_list", debited_amount_list.toString());

            int compID;
            String compName;
            int varID;
            String varName;
            String valName1 = null;
            String valName2 = null;
            String valName3 = null;
            String valScore1 = null;
            String valScore2 = null;
            String valScore3 = null;

            int valFlag=0;
            int insertdata=0;

            db = new DatabaseHelper(this.context);

            List<Component> components = db.getAllComponents();
            List<Variable> variables = db.getAllVariables();
            List<Value> values = db.getAllValues();

            //FOR SALARY
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
               // Log.d("compName",compName);
                if(compName.equals("Bank account"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Salary") ){
                                valFlag=0;
                                for (Value vl : values) {

                                    if(varID == vl.get_value_variable_id())
                                    {
                                        valFlag = valFlag + 1;
                                        //Log.d("HEllo","flag value: " + valFlag);
                                        if(valFlag==1)
                                        {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();

                                        }
                                        if(valFlag==2)
                                        {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            //Log.d("HEllo","flag2");
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            valFlag = 0;
                                            insertdata =1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1 "
                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                            Log.d("SalarySlab", "LOG: " + log);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            int lowerSlab1 = 0;
            int upperSlab1 = 0;
            int lowerSlab2 = 0;
            int upperSlab2 = 0;
            int lowerSlab3 = 0;
            int upperSlab3 = 0;


            String[] valName1splits = valName1.split("-");
            for( int i = 0; i < valName1splits.length; i++) {
                if(i==0){
                    lowerSlab1=Integer.parseInt(valName1splits[i]);
                }
                else if(i==1){
                    upperSlab1=Integer.parseInt(valName1splits[i]);
                }
            }

            String[] valName2splits = valName2.split("-");
            for( int i = 0; i < valName2splits.length; i++) {
                if(i==0){
                    lowerSlab2=Integer.parseInt(valName2splits[i]);
                }
                else if(i==1){
                    upperSlab2=Integer.parseInt(valName2splits[i]);
                }
            }
            String[] valName3splits = valName3.split("-");
            for( int i = 0; i < valName3splits.length; i++) {
                if(i==0){
                    lowerSlab3=Integer.parseInt(valName3splits[i]);
                }
                else if(i==1){
                    upperSlab3=Integer.parseInt(valName3splits[i]);
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

            //Salary in the recent month:
            double salary_amount = 0;
            int salary_score = 0;
            double salary_taka = 0;
            String salary_date = null;

            int month;
            int year;

            Iterator myVeryOwnIterator = salary_list.keySet().iterator();
            while(myVeryOwnIterator.hasNext()) {
                String key=(String)myVeryOwnIterator.next();
                String value=(String)salary_list.get(key);
                //Toast.makeText(ctx, "Key: "+key+" Value: "+value, Toast.LENGTH_LONG).show();

                salary_taka = Double.parseDouble(value);
                salary_date = key;
//                Log.d("salary_list", "salary_date: " + salary_date);
//                Log.d("salary_list", "salary_taka: " + salary_taka);
                //extracting month and year from payment_date
                month = Integer.parseInt(salary_date.split("/")[1]);
                year = Integer.parseInt(salary_date.split("/")[2]);

                //calculating salary amount in recent month:
                if((year==targetYear)&&(month==targetMonth)) {
                    salary_amount = salary_taka;
                }
            }
//            Log.d("salary_list", "targetYear: " + targetYear);
//            Log.d("salary_list", "targetMonth: " + targetMonth);
            //Log.d("salary_list", "salary_amount: " + salary_amount);

            if((salary_amount>=lowerSlab1)&&(salary_amount<upperSlab1)){
                salary_score = Integer.parseInt(valScore1);
            }
            else if((salary_amount>=lowerSlab2)&&(salary_amount<upperSlab2)){
                salary_score = Integer.parseInt(valScore2);
            }
            else if((salary_amount>=lowerSlab3)){
                salary_score = Integer.parseInt(valScore3);
            }
            //Log.d("salary_score", "salary_score: " + salary_score + " "+ salary_taka);




            //FOR Total credited amount
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                // Log.d("compName",compName);
                if(compName.equals("Bank account"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Total deposit")){
                                valFlag=0;
                                for (Value vl : values) {

                                    if(varID == vl.get_value_variable_id())
                                    {
                                        valFlag = valFlag + 1;
                                        //Log.d("HEllo","flag value: " + valFlag);
                                        if(valFlag==1)
                                        {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();

                                        }
                                        if(valFlag==2)
                                        {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            //Log.d("HEllo","flag2");
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            valFlag = 0;
                                            insertdata =1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                            Log.d("TotaldepositSlab", "LOG: " + log);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            lowerSlab1 = 0;
            upperSlab1 = 0;
            lowerSlab2 = 0;
            upperSlab2 = 0;
            lowerSlab3 = 0;
            upperSlab3 = 0;

            valName1splits = valName1.split("-");
            for( int i = 0; i < valName1splits.length; i++) {
                if(i==0){
                    lowerSlab1=Integer.parseInt(valName1splits[i]);
                }
                else if(i==1){
                    upperSlab1=Integer.parseInt(valName1splits[i]);
                }
            }
            valName2splits = valName2.split("-");
            for( int i = 0; i < valName2splits.length; i++) {
                if(i==0){
                    lowerSlab2=Integer.parseInt(valName2splits[i]);
                }
                else if(i==1){
                    upperSlab2=Integer.parseInt(valName2splits[i]);
                }
            }
            valName3splits = valName3.split("-");
            for( int i = 0; i < valName3splits.length; i++) {
                if(i==0){
                    lowerSlab3=Integer.parseInt(valName3splits[i]);
                }
                else if(i==1){
                    upperSlab3=Integer.parseInt(valName3splits[i]);
                }
            }






            //Total credited amount in the recent month:

            double Total_credited_amount = 0;
            int Credit_score = 0;
            double credited_amount_taka = 0;
            String credited_amount_date = null;

            for (String credited_amount : credited_amount_list.keySet()) {
                credited_amount_taka = Double.parseDouble(credited_amount);
                credited_amount_date = credited_amount_list.get(credited_amount);

                //extracting month and year from payment_date
                month = Integer.parseInt(credited_amount_date.split("/")[1]);
                year = Integer.parseInt(credited_amount_date.split("/")[2]);

                if((year==targetYear)&&(month==targetMonth)) {
                    Total_credited_amount = Total_credited_amount+credited_amount_taka;
                }
            }
            //Log.d("Total_credited_amount", "Total_credited_amount: " + Total_credited_amount);

            if((Total_credited_amount>=lowerSlab1)&&(Total_credited_amount<upperSlab1)){
                Credit_score = Integer.parseInt(valScore1);
            }
            else if((Total_credited_amount>=lowerSlab2)&&(Total_credited_amount<upperSlab2)){
                Credit_score = Integer.parseInt(valScore2);
            }
            else if((Total_credited_amount>=lowerSlab3)){
                Credit_score = Integer.parseInt(valScore3);
            }
            //Log.d("Credit_score", "Credit_score: " + Credit_score);



            //bank balance from last message in the recent
            double card_balance = 0;
            double card_balance_taka = 0;
            String card_balance_date = null;
            int day;
            int last_day_in_december;

            Set<Integer> day_list = new HashSet<Integer>();
            for (String cardBalance : card_balance_list.keySet()) {
                card_balance_taka = Double.parseDouble(cardBalance);
                card_balance_date = card_balance_list.get(cardBalance);

                day = Integer.parseInt(card_balance_date.split("/")[0]);
                month = Integer.parseInt(card_balance_date.split("/")[1]);
                year = Integer.parseInt(card_balance_date.split("/")[2]);

                if ((year == targetYear) && (month == targetMonth)) {
                    day_list.add(day);
                }
            }

                if(!day_list.isEmpty()){
                    //Log.d("card_balance_taka",day_list.toString());
                    //Log.d("card_balance_taka", Collections.max(day_list).toString());
                    last_day_in_december = Collections.max(day_list);

                    for (String cardBalance : card_balance_list.keySet()) {
                        card_balance_taka = Double.parseDouble(cardBalance);
                        card_balance_date = card_balance_list.get(cardBalance);

                        day = Integer.parseInt(card_balance_date.split("/")[0]);
                        month = Integer.parseInt(card_balance_date.split("/")[1]);
                        year = Integer.parseInt(card_balance_date.split("/")[2]);

                        if ((year == targetYear) && (month == targetMonth) && (day == last_day_in_december)) {
                            card_balance = card_balance_taka;
                        }
                    }
                    //Log.d("card_balance",String.valueOf(card_balance));
                }






            //MINIMUM DUE
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                // Log.d("compName",compName);
                if(compName.contains("Credit Card"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Minimum due")){
                                valFlag=0;
                                for (Value vl : values) {

                                    if(varID == vl.get_value_variable_id())
                                    {
                                        valFlag = valFlag + 1;
                                        //Log.d("HEllo","flag value: " + valFlag);
                                        if(valFlag==1)
                                        {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();

                                        }
                                        if(valFlag==2)
                                        {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            //Log.d("HEllo","flag2");
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            valFlag = 0;
                                            insertdata =1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                            Log.d("minimumdueSlab", "LOG: " + log);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            lowerSlab1 = 0;
            upperSlab1 = 0;
            lowerSlab2 = 0;
            upperSlab2 = 0;
            lowerSlab3 = 0;
            upperSlab3 = 0;

            valName1splits = valName1.split("-");
            for( int i = 0; i < valName1splits.length; i++) {
                if(i==0){
                    lowerSlab1=Integer.parseInt(valName1splits[i]);
                }
                else if(i==1){
                    upperSlab1=Integer.parseInt(valName1splits[i]);
                }
            }
            valName2splits = valName2.split("-");
            for( int i = 0; i < valName2splits.length; i++) {
                if(i==0){
                    lowerSlab2=Integer.parseInt(valName2splits[i]);
                }
                else if(i==1){
                    upperSlab2=Integer.parseInt(valName2splits[i]);
                }
            }
            valName3splits = valName3.split("-");
            for( int i = 0; i < valName3splits.length; i++) {
                if(i==0){
                    lowerSlab3=Integer.parseInt(valName3splits[i]);
                }
                else if(i==1){
                    upperSlab3=Integer.parseInt(valName3splits[i]);
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
            }
            //Log.d("minimum_due",String.valueOf(minimum_due));

            if(minimum_due>=lowerSlab1){
                minimum_due_score = Integer.parseInt(valScore1);
            }
            else if((minimum_due>=lowerSlab2)&&(minimum_due<upperSlab2)){
                minimum_due_score = Integer.parseInt(valScore2);
            }
            else if((minimum_due>=lowerSlab3)&&(minimum_due<upperSlab3)){
                minimum_due_score = Integer.parseInt(valScore3);
            }
            //Log.d("minimum_due_score", "minimum_due_score: " + String.valueOf(minimum_due_score));





            //CLOSING BALANCE
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                // Log.d("compName",compName);
                if(compName.contains("Credit Card"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Closing balance")){
                                valFlag=0;
                                for (Value vl : values) {

                                    if(varID == vl.get_value_variable_id())
                                    {
                                        valFlag = valFlag + 1;
                                        //Log.d("HEllo","flag value: " + valFlag);
                                        if(valFlag==1)
                                        {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();

                                        }
                                        if(valFlag==2)
                                        {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            //Log.d("HEllo","flag2");
                                        }
                                        if(valFlag == 3)
                                        {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            valFlag = 0;
                                            insertdata =1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                    +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                            Log.d("closingSlab", "LOG: " + log);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
            lowerSlab1 = 0;
            upperSlab1 = 0;
            lowerSlab2 = 0;
            upperSlab2 = 0;
            lowerSlab3 = 0;
            upperSlab3 = 0;

            valName1splits = valName1.split("-");
            for( int i = 0; i < valName1splits.length; i++) {
                if(i==0){
                    lowerSlab1=Integer.parseInt(valName1splits[i]);
                }
                else if(i==1){
                    upperSlab1=Integer.parseInt(valName1splits[i]);
                }
            }
            valName2splits = valName2.split("-");
            for( int i = 0; i < valName2splits.length; i++) {
                if(i==0){
                    lowerSlab2=Integer.parseInt(valName2splits[i]);
                }
                else if(i==1){
                    upperSlab2=Integer.parseInt(valName2splits[i]);
                }
            }
            valName3splits = valName3.split("-");
            for( int i = 0; i < valName3splits.length; i++) {
                if(i==0){
                    lowerSlab3=Integer.parseInt(valName3splits[i]);
                }
                else if(i==1){
                    upperSlab3=Integer.parseInt(valName3splits[i]);
                }
            }



            //closingbalance
            double closing_balance = 0;
            double closing_balance_taka = 0;
            String closing_balance_date = null;
            int closing_balance_score = 0;
            for (String closing : closing_balance_list.keySet()) {
                closing_balance_taka = Double.parseDouble(closing);
                closing_balance_date = closing_balance_list.get(closing);

                Log.d("closing_balance_list",String.valueOf(closing_balance_taka));
                Log.d("closing_balance_list",String.valueOf(closing_balance_date));

                month = Integer.parseInt(closing_balance_date.split("/")[1]);
                year = Integer.parseInt(closing_balance_date.split("/")[2]);

                if ((year == targetYear) && (month == targetMonth)) {
                    closing_balance = closing_balance_taka;
                }
                else if ((year == 2021) && (month == 12)) {
                    closing_balance = closing_balance_taka;
                }
            }
            Log.d("closing_balance_list",String.valueOf(closing_balance));

            if(closing_balance>=lowerSlab1){
                closing_balance_score = Integer.parseInt(valScore1);
            }
            else if((closing_balance>=lowerSlab2)&&(closing_balance<upperSlab2)){
                closing_balance_score = Integer.parseInt(valScore2);
            }
            else if((closing_balance>=lowerSlab3)&&(closing_balance<upperSlab3)){
                closing_balance_score = Integer.parseInt(valScore3);
            }


            //FOR DEBIT
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("compName",compName);
                if(compName.equals("Debit"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            valFlag=0;
                            for (Value vl : values) {

                                if(varID == vl.get_value_variable_id())
                                {
                                    valFlag = valFlag + 1;
                                    //Log.d("HEllo","flag value: " + valFlag);
                                    if(valFlag==1)
                                    {
                                        //Log.d("HEllo","flag1");
                                        valName1 = vl.get_value_name();
                                        valScore1 = vl.get_value_score();

                                    }
                                    if(valFlag==2)
                                    {
                                        valName2 = vl.get_value_name();
                                        valScore2 = vl.get_value_score();
                                        //Log.d("HEllo","flag2");
                                    }
                                    if(valFlag == 3)
                                    {
                                        valName3 = vl.get_value_name();
                                        valScore3 = vl.get_value_score();
                                        valFlag = 0;
                                        insertdata =1;
                                        //Log.d("HEllo","flag3");
                                        String log = "compName: " +compName+" ,varName: "+varName+" valName1"
                                                +valName1+" valName2 "+valName2+" valName3 "+valName3+" valScore1 "+valScore1+" valScore2 "+valScore2+" valScore3 "+valScore3;
                                        //Log.d("slabModelArrayList123", "LOG: " + log);
                                    }
                                }
                            }
                        }
                    }
                }
            }

            valName1splits = valName1.split("-");
            for( int i = 0; i < valName1splits.length; i++) {
                if(i==0){
                    lowerSlab1=Integer.parseInt(valName1splits[i]);
                }
                else if(i==1){
                    upperSlab1=Integer.parseInt(valName1splits[i]);
                }
            }
            valName2splits = valName2.split("-");
            for( int i = 0; i < valName2splits.length; i++) {
                if(i==0){
                    lowerSlab2=Integer.parseInt(valName2splits[i]);
                }
                else if(i==1){
                    upperSlab2=Integer.parseInt(valName2splits[i]);
                }
            }
            valName3splits = valName3.split("-");
            for( int i = 0; i < valName3splits.length; i++) {
                if(i==0){
                    lowerSlab3=Integer.parseInt(valName3splits[i]);
                }
                else if(i==1){
                    upperSlab3=Integer.parseInt(valName3splits[i]);
                }
            }


            double Total_debited_amount = 0;
            double debited_amount_taka = 0;
            String debited_amount_date = null;
            int debit_score = 0;

            for (String debited : debited_amount_list.keySet()) {
                debited_amount_taka = Double.parseDouble(debited);
                debited_amount_date = debited_amount_list.get(debited);

                month = Integer.parseInt(debited_amount_date.split("/")[1]);
                year = Integer.parseInt(debited_amount_date.split("/")[2]);

                if ((year == targetYear) && (month == targetMonth)) {
                    Total_debited_amount = Total_debited_amount + debited_amount_taka;
                }
            }

            //Log.d("Total_debited_amount",String.valueOf(Total_debited_amount));

            if((Total_debited_amount>=lowerSlab1)&&(Total_debited_amount<upperSlab1)){
                debit_score = Integer.parseInt(valScore1);
            }
            else if((Total_debited_amount>=lowerSlab2)&&(Total_debited_amount<upperSlab2)){
                debit_score = Integer.parseInt(valScore2);
            }
            else if(Total_debited_amount>=lowerSlab3){
                debit_score = Integer.parseInt(valScore3);
            }
            //Log.d("debit_score", "debit_score: " + String.valueOf(debit_score));


            int balance_score=0;
            if((card_balance>=0)&&(Total_debited_amount<50000)){
                balance_score = 5;
            }
            else if((Total_debited_amount>=50000)&&(Total_debited_amount<100000)){
                balance_score = 10;
            }
            else if((Total_debited_amount>=100000)&&(Total_debited_amount<200000)){
                balance_score = 15;
            }


            CREDIT_DEBIT_score.put("salary_amount: "+ String.valueOf(salary_amount) + "  ||  Salary score : ",String.valueOf(salary_score));
            //CREDIT_DEBIT_score.put("salary_score", String.valueOf(salary_score));
            CREDIT_DEBIT_score.put("Total_credited_amount: "+ String.valueOf(Total_credited_amount) + "  ||  Credit score : ",String.valueOf(Credit_score));
            //CREDIT_DEBIT_score.put("Credit_score", String.valueOf(Credit_score));
            CREDIT_DEBIT_score.put("minimum_due: "+ String.valueOf(minimum_due) + "  ||  Minimum due score : ",String.valueOf(minimum_due_score));
            //CREDIT_DEBIT_score.put("minimum_due_score", String.valueOf(minimum_due_score));
            CREDIT_DEBIT_score.put("Total debited amount: "+ String.valueOf(Total_debited_amount) + "  ||  Debit score : ",String.valueOf(debit_score));
            //CREDIT_DEBIT_score.put("debit_score", String.valueOf(debit_score));
            //CREDIT_DEBIT_score.put("Bank_balance: "+ String.valueOf(card_balance) + "  ||  Balance score : ",String.valueOf(balance_score));
            CREDIT_DEBIT_score.put("closing_balance: "+ String.valueOf(closing_balance) + "  ||  closing_balance score : ",String.valueOf(closing_balance_score));








        }catch (Exception e){
            e.printStackTrace();
        }
        return CREDIT_DEBIT_score;
    }
}
