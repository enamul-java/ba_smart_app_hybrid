package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class DeviceTypeUnderIncomeModel {
    DatabaseHelper db;
    Context context;

    public DeviceTypeUnderIncomeModel(Context context){
        this.context = context ;
    }
    public Integer deviceTypeModel() {
        int deviceTypeScore = 0;
        try {
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

            int valFlag = 0;
            int insertdata = 0;
            int MaxvalFlag = 0;

            db = new DatabaseHelper(this.context);

            List<Component> components = db.getAllComponents();
            List<Variable> variables = db.getAllVariables();
            List<Value> values = db.getAllValues();

            //FOR SALARY
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("asd",compName);
                if (compName.contains("Income ")) {
                    Log.d("bkashDepositModel", "compNameEquals: " + compName);
                    for (Variable vb : variables) {
                        if (compID == vb.get_component_id()) {
                            varID = vb.get_id();
                            varName = vb.get_name();
                            MaxvalFlag = 0;
                            for (Value vl : values) {

//                                Log.d("asd","get_flag: "+vl.get_value_variable_id());
                                if ((varID == vl.get_value_variable_id())) {
//                                    Log.d("asd","get_value_variable_id: "+vl.get_value_variable_id());
//                                    Log.d("asd","get_flag: "+vl.get_flag());
//                                    Log.d("asd","check: "+vl.get_max_value());
//                                    Log.d("asd","check: "+vl.get_min_value());
                                    valScoreFlag = vl.get_flag();
                                    MaxvalFlag = MaxvalFlag + 1;
                                }
                            }

                            //Log.d("asd","valFlag: "+valScoreFlag);
                            if (valScoreFlag.contains("0")) {
                                for (Value vl : values) {
                                    if ((varID == vl.get_value_variable_id()) && varName.contains("Device Type")) {
                                        valFlag = valFlag + 1;
                                        Log.d("bkashDepositModel0", "valScoreFlag: " + valScoreFlag);
                                        Log.d("bkashDepositModel0", "flag value: " + valFlag);
                                        Log.d("bkashDepositModel0", "varID: " + varID);
                                        Log.d("bkashDepositModel0", "varName: " + varName);
                                        if (valFlag == 1) {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel", "valName1: " + valName1 + " valScore1: " + valScore1);

                                        }
                                        if (valFlag == 2) {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                            //Log.d("HEllo","flag2");
                                            Log.d("bkashDepositModel", "valName2: " + valName2 + " valScore2: " + valScore2);
                                        }
                                        if (valFlag == 3) {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            Log.d("bkashDepositModel", "valName3: " + valName3 + " valScore3: " + valScore3);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }

                                            insertdata = 1;
                                            //Log.d("HEllo","flag3");
                                            String log = "compName: " + compName + " ,varName: " + varName + " valName1"
                                                    + valName1 + " valName2 " + valName2 + " valName3 " + valName3 + " valScore1 " + valScore1 + " valScore2 " + valScore2 + " valScore3 " + valScore3;
                                            //Log.d("slabModelArrayList123", "LOG: " + log);
                                        }
                                        if (valFlag == 4) {
                                            valName4 = vl.get_value_name();
                                            valScore4 = vl.get_value_score();
                                            Log.d("bkashDepositModel", "valName4: " + valName4 + " valScore4: " + valScore4);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if (valFlag == 5) {
                                            valName5 = vl.get_value_name();
                                            valScore5 = vl.get_value_score();
                                            Log.d("bkashDepositModel", "valName5: " + valName5 + " valScore5: " + valScore5);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if (valFlag == 6) {
                                            valName6 = vl.get_value_name();
                                            valScore6 = vl.get_value_score();
                                            Log.d("bkashDepositModel", "valName6: " + valName6 + " valScore6: " + valScore6);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (valScoreFlag.contains("1")) {
                                for (Value vl : values) {
                                    if ((varID == vl.get_value_variable_id()) && varName.contains("Device Type")) {
                                        valFlag = valFlag + 1;
                                        Log.d("bkashDepositModel1", "valScoreFlag: " + valScoreFlag);
                                        Log.d("bkashDepositModel1", "flag value: " + valFlag);
                                        Log.d("bkashDepositModel1", "varID: " + varID);
                                        Log.d("bkashDepositModel1", "varName: " + varName);
                                        Log.d("bkashDepositModel1", "checking flag");
                                        if (valFlag == 1) {
                                            Log.d("bkashDepositModel1", " entered flag1");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab1);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("bkashDepositModel1", "null value");
                                                upperSlab1 = -1;
                                            } else {
                                                upperSlab1 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab1" + upperSlab1);
                                            valScore1 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore1" + valScore1);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1", "lowerSlab1: " + lowerSlab1 + " upperSlab1: " + upperSlab1);
                                            Log.d("bkashDepositModel1", "valScore1: " + valScore1);

                                        }
                                        if (valFlag == 2) {
                                            Log.d("bkashDepositModel1", " entered flag2");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab2);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("bkashDepositModel1", "null value");
                                                upperSlab2 = -1;
                                            } else {
                                                upperSlab2 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab2" + upperSlab2);
                                            valScore2 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore2" + valScore2);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1", "lowerSlab2: " + lowerSlab2 + " upperSlab2: " + upperSlab2);
                                            Log.d("bkashDepositModel1", "valScore2: " + valScore2);

                                        }
                                        if (valFlag == 3) {
                                            Log.d("bkashDepositModel1", " entered flag3");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab3);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("bkashDepositModel1", "null value");
                                                upperSlab3 = -1;
                                            } else {
                                                upperSlab3 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab3" + upperSlab3);
                                            valScore3 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore3" + valScore3);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1", "lowerSlab3: " + lowerSlab3 + " upperSlab3: " + upperSlab3);
                                            Log.d("bkashDepositModel1", "valScore3: " + valScore3);

                                        }
                                        if (valFlag == 4) {
                                            Log.d("bkashDepositModel1", " entered flag4");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab4);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("bkashDepositModel1", "null value");
                                                upperSlab4 = -1;
                                            } else {
                                                upperSlab4 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab4" + upperSlab4);
                                            valScore4 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore4" + valScore4);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1", "lowerSlab4: " + lowerSlab4 + " upperSlab4: " + upperSlab4);
                                            Log.d("bkashDepositModel1", "valScore4: " + valScore4);

                                        }
                                        if (valFlag == 5) {
                                            Log.d("bkashDepositModel1", " entered flag5");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab5);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("bkashDepositModel1", "null value");
                                                upperSlab5 = -1;
                                            } else {
                                                upperSlab5 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab5" + upperSlab5);
                                            valScore5 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore5" + valScore5);

                                            Log.d("bkashDepositModel1", "lowerSlab5: " + lowerSlab5 + " upperSlab5: " + upperSlab5);
                                            Log.d("bkashDepositModel1", "valScore5: " + valScore5);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }


                                        }
                                        if (valFlag == 6) {
                                            Log.d("bkashDepositModel1", " entered flag6");
                                            Log.d("bkashDepositModel1", vl.get_min_value());
                                            lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                            Log.d("bkashDepositModel1", "val" + lowerSlab6);
                                            Log.d("bkashDepositModel1", "MaxVal" + MaxvalFlag);
                                            Log.d("bkashDepositModel1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab6 = -1;
                                            } else {
                                                upperSlab6 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("bkashDepositModel1", "upperSlab6" + upperSlab6);
                                            valScore6 = vl.get_value_score();
                                            Log.d("bkashDepositModel1", "valScore6" + valScore6);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("bkashDepositModel1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("bkashDepositModel1", "lowerSlab6: " + lowerSlab6 + " upperSlab6: " + upperSlab6);
                                            Log.d("bkashDepositModel1", "valScore6: " + valScore6);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }

            String deviceType = "Smartphone";

            if (deviceType.contains(valName1) ){
                deviceTypeScore = Integer.parseInt(valScore1);
            }
            else if (deviceType.contains(valName2) ){
                deviceTypeScore = Integer.parseInt(valScore2);
            }
            else if (deviceType.equals(valName3)){
                deviceTypeScore = Integer.parseInt(valScore3);
            }
            else if (deviceType.contains(valName4)){
                deviceTypeScore = Integer.parseInt(valScore4);
            }
            else if (deviceType.contains(valName5) ){
                deviceTypeScore = Integer.parseInt(valScore5);
            }
            else if (deviceType.contains(valName6) ){
                deviceTypeScore = Integer.parseInt(valScore6);
            }
//            else{
//                deviceOSscore = Integer.parseInt(valScore6);
//            }

            Log.d("deviceTypeScore","deviceTypeScore: " +deviceTypeScore);


        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return deviceTypeScore;
    }
}
