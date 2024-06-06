package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.Bkash;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.CreditCard;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;


public class BkashAccountHolderModel {
    DatabaseHelper db;
    Context context;

    public BkashAccountHolderModel(Context context){
        this.context = context;
    }

    private ArrayList<SlabModel> slabModelArrayList;


    public HashMap<String,String> bkashAccountHolderModel(ArrayList<SMSModel> SMSArrayList) {

        HashMap<String, String> bkashAccountHolder_score = new HashMap<String, String>();

        try {
            HashMap<String, HashMap<String, String>> BKASH = new HashMap<String, HashMap<String, String>>();

            Bkash bkash = new Bkash();
            BKASH = bkash.bkash(SMSArrayList);
            //Log.d("bkashModel", BKASH.toString());

            HashMap<String, String> bkash_account_holder_list = new HashMap<String, String>();


            //Log.d("bkashModel", BKASH.toString());

            for (String ListName : BKASH.keySet()) {
                Log.d("BKASH", "ListName: " + ListName+ " List: " + BKASH.get(ListName));
                if (ListName.equals("bkash_account_holder")) {
                    bkash_account_holder_list = BKASH.get(ListName);
                    Log.d("bkashAccountHolder_sc",bkash_account_holder_list.toString());
                }
            }
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

            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("asd",compName);
                if (compName.contains("Financial Skills")) {
                    Log.d("asd", "compNameEquals: " + compName);
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
                                    if ((varID == vl.get_value_variable_id()) && varName.contains("MFS Account holder")) {
                                        valFlag = valFlag + 1;
                                        Log.d("asd0", "valScoreFlag: " + valScoreFlag);
                                        Log.d("asd0", "flag value: " + valFlag);
                                        Log.d("asd0", "varID: " + varID);
                                        Log.d("asd0", "varName: " + varName);
                                        if (valFlag == 1) {
                                            //Log.d("HEllo","flag1");
                                            valName1 = vl.get_value_name();
                                            valScore1 = vl.get_value_score();
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd", "valName1: " + valName1 + " valScore1: " + valScore1);

                                        }
                                        if (valFlag == 2) {
                                            valName2 = vl.get_value_name();
                                            valScore2 = vl.get_value_score();
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                            //Log.d("HEllo","flag2");
                                            Log.d("asd", "valName2: " + valName2 + " valScore2: " + valScore2);
                                        }
                                        if (valFlag == 3) {
                                            valName3 = vl.get_value_name();
                                            valScore3 = vl.get_value_score();
                                            Log.d("asd", "valName3: " + valName3 + " valScore3: " + valScore3);
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
                                            Log.d("asd", "valName4: " + valName4 + " valScore4: " + valScore4);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if (valFlag == 5) {
                                            valName5 = vl.get_value_name();
                                            valScore5 = vl.get_value_score();
                                            Log.d("asd", "valName5: " + valName5 + " valScore5: " + valScore5);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                        if (valFlag == 6) {
                                            valName6 = vl.get_value_name();
                                            valScore6 = vl.get_value_score();
                                            Log.d("asd", "valName6: " + valName6 + " valScore6: " + valScore6);
                                            if (valFlag == MaxvalFlag) {
                                                valFlag = 0;
                                                break;
                                            }
                                        }
                                    }
                                }
                            } else if (valScoreFlag.contains("1")) {
                                for (Value vl : values) {
                                    if ((varID == vl.get_value_variable_id()) && varName.contains("MFS Account holder")) {
                                        valFlag = valFlag + 1;
                                        Log.d("asd1", "valScoreFlag: " + valScoreFlag);
                                        Log.d("asd1", "flag value: " + valFlag);
                                        Log.d("asd1", "varID: " + varID);
                                        Log.d("asd1", "varName: " + varName);
                                        Log.d("asd1", "checking flag");
                                        if (valFlag == 1) {
                                            Log.d("asd1", " entered flag1");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab1);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab1 = -1;
                                            } else {
                                                upperSlab1 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab1" + upperSlab1);
                                            valScore1 = vl.get_value_score();
                                            Log.d("asd1", "valScore1" + valScore1);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1", "lowerSlab1: " + lowerSlab1 + " upperSlab1: " + upperSlab1);
                                            Log.d("asd1", "valScore1: " + valScore1);

                                        }
                                        if (valFlag == 2) {
                                            Log.d("asd1", " entered flag2");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab2);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab2 = -1;
                                            } else {
                                                upperSlab2 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab2" + upperSlab2);
                                            valScore2 = vl.get_value_score();
                                            Log.d("asd1", "valScore2" + valScore2);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1", "lowerSlab2: " + lowerSlab2 + " upperSlab2: " + upperSlab2);
                                            Log.d("asd1", "valScore2: " + valScore2);

                                        }
                                        if (valFlag == 3) {
                                            Log.d("asd1", " entered flag3");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab3);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab3 = -1;
                                            } else {
                                                upperSlab3 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab3" + upperSlab3);
                                            valScore3 = vl.get_value_score();
                                            Log.d("asd1", "valScore3" + valScore3);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1", "lowerSlab3: " + lowerSlab3 + " upperSlab3: " + upperSlab3);
                                            Log.d("asd1", "valScore3: " + valScore3);

                                        }
                                        if (valFlag == 4) {
                                            Log.d("asd1", " entered flag4");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab4);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab4 = -1;
                                            } else {
                                                upperSlab4 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab4" + upperSlab4);
                                            valScore4 = vl.get_value_score();
                                            Log.d("asd1", "valScore4" + valScore4);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1", "lowerSlab4: " + lowerSlab4 + " upperSlab4: " + upperSlab4);
                                            Log.d("asd1", "valScore4: " + valScore4);

                                        }
                                        if (valFlag == 5) {
                                            Log.d("asd1", " entered flag5");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab5);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab5 = -1;
                                            } else {
                                                upperSlab5 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab5" + upperSlab5);
                                            valScore5 = vl.get_value_score();
                                            Log.d("asd1", "valScore5" + valScore5);

                                            Log.d("asd1", "lowerSlab5: " + lowerSlab5 + " upperSlab5: " + upperSlab5);
                                            Log.d("asd1", "valScore5: " + valScore5);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }


                                        }
                                        if (valFlag == 6) {
                                            Log.d("asd1", " entered flag6");
                                            Log.d("asd1", vl.get_min_value());
                                            lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                            Log.d("asd1", "val" + lowerSlab6);
                                            Log.d("asd1", "MaxVal" + MaxvalFlag);
                                            Log.d("asd1", vl.get_max_value());
                                            if (vl.get_max_value().contains("null")) {
                                                Log.d("asd1", "null value");
                                                upperSlab6 = -1;
                                            } else {
                                                upperSlab6 = Integer.parseInt(vl.get_max_value());
                                            }


                                            Log.d("asd1", "upperSlab6" + upperSlab6);
                                            valScore6 = vl.get_value_score();
                                            Log.d("asd1", "valScore6" + valScore6);
                                            if (valFlag == MaxvalFlag) {
                                                Log.d("asd1", "break");
                                                valFlag = 0;
                                                break;
                                            }
                                            Log.d("asd1", "lowerSlab6: " + lowerSlab6 + " upperSlab6: " + upperSlab6);
                                            Log.d("asd1", "valScore6: " + valScore6);

                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
            String checking_acount_holder = "";
            int bkash_acount_holder_Score = 0;
            for (String cashIn : bkash_account_holder_list.keySet()) {
                Log.d("bkash_account_hold", "bkash_account_holder_list: " + cashIn + " What?: " + bkash_account_holder_list.get(cashIn));
                checking_acount_holder = bkash_account_holder_list.get(cashIn);
                if(checking_acount_holder.equals("Yes")){
                    break;
                }
            }

            if (checking_acount_holder.contains(valName1)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore1);
            } else if (checking_acount_holder.contains(valName2)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore2);
            } else if (checking_acount_holder.equals(valName3)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore3);
            } else if (checking_acount_holder.contains(valName4)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore4);
            } else if (checking_acount_holder.contains(valName5)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore5);
            } else if (checking_acount_holder.contains(valName6)) {
                bkash_acount_holder_Score = Integer.parseInt(valScore6);
            }

            bkashAccountHolder_score.put("Bkash acount holder:  "+ String.valueOf(checking_acount_holder),String.valueOf(bkash_acount_holder_Score));

            Log.d("bkashAccountHolder_",bkashAccountHolder_score.toString());

        }catch (Exception e){
            e.printStackTrace();
        }
        return bkashAccountHolder_score;
    }
}
