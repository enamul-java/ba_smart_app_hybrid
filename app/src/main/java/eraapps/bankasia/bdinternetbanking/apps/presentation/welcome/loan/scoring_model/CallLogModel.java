package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class CallLogModel {

    DatabaseHelper db;
    Context context;

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

    HashMap<String, String> CallLogUniqueListScore = new HashMap<String, String>();
    HashMap<String, String> CallLogUniqueList = new HashMap<String, String>();
    List<String> GreaterThan4mins = new ArrayList<>();

    public CallLogModel(Context context){
        this.context = context ;
    }
    public HashMap<String,String> callLogModel(HashMap<String,String> CallLogUniqueList, List<String> GreaterThan4mins) {
        try{
            CallLogUniqueList = CallLogUniqueList;
            GreaterThan4mins = GreaterThan4mins;


            db = new DatabaseHelper(this.context);
            List<Component> components = db.getAllComponents();
            List<Variable> variables = db.getAllVariables();
            List<Value> values = db.getAllValues();

            //FOR Unique calls
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                // Log.d("compName",compName);
                if(compName.equals("Call History"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Unique numbers") ){
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
                                            Log.d("CallLogModelSlab", "LOG: " + log);
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

            Log.d("CallLogList",String.valueOf(CallLogUniqueList.size()));
            Log.d("CallLogList",String.valueOf(GreaterThan4mins.size()));

            int Unique_call_score = 0;

            if((CallLogUniqueList.size()>=lowerSlab1)&&(CallLogUniqueList.size()<upperSlab1)){
                Unique_call_score = Integer.parseInt(valScore1);
            }
            else if((CallLogUniqueList.size()>=lowerSlab2)&&(CallLogUniqueList.size()<upperSlab2)){
                Unique_call_score = Integer.parseInt(valScore2);
            }
            else if((CallLogUniqueList.size()>=lowerSlab3)){
                Unique_call_score = Integer.parseInt(valScore3);
            }
            //Log.d("Unique_call_score", "Unique_call_score: " + Unique_call_score + " "+ CallLogUniqueList.size());

            CallLogUniqueListScore.put("Unique_call_score",String.valueOf(Unique_call_score));



            //------------------------------------------------------------------------------------
            //FOR greate than 4 mins
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                // Log.d("compName",compName);
                if(compName.equals("Call History"))
                {
                    //Log.d("component",compName);
                    for (Variable vb : variables) {
                        if(compID == vb.get_component_id())
                        {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            if (varName.contains("Call duration")){
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


            int GreaterThan4mins_score = 0;

            if((GreaterThan4mins.size()>=lowerSlab1)&&(GreaterThan4mins.size()<upperSlab1)){
                GreaterThan4mins_score = Integer.parseInt(valScore1);
            }
            else if((GreaterThan4mins.size()>=lowerSlab2)&&(GreaterThan4mins.size()<upperSlab2)){
                GreaterThan4mins_score = Integer.parseInt(valScore2);
            }
            else if((GreaterThan4mins.size()>=lowerSlab3)){
                GreaterThan4mins_score = Integer.parseInt(valScore3);
            }
            //Log.d("Unique_call_score", "Unique_call_score: " + Unique_call_score + " "+ CallLogUniqueList.size());

            CallLogUniqueListScore.put("GreaterThan4mins_score",String.valueOf(GreaterThan4mins_score));



        }catch (Exception e){
            e.printStackTrace();
        }


        return CallLogUniqueListScore;
    }
}
