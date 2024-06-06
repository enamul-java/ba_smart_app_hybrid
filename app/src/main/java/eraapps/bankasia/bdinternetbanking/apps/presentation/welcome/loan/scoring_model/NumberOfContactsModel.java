package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class NumberOfContactsModel {
    DatabaseHelper db;
    Context context;
    int Number_of_contacts = 0;

    public NumberOfContactsModel(Context context){
        this.context = context ;
    }
    public Integer numberOfContactsModel() {

        Cursor cursor = null;
        try {
            cursor = context.getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null, null, null, null);
//            int contactIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone._ID);
//            int nameIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME);
//            int phoneNumberIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);
//            int photoIdIdx = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_ID);
            cursor.moveToFirst();
            do {
                Number_of_contacts++;
//                String idContact = cursor.getString(contactIdIdx);
//                String name = cursor.getString(nameIdx);
//                String phoneNumber = cursor.getString(phoneNumberIdx);
//                Log.d("yes", idContact);
//                Log.d("yes", name);
//                Log.d("yes", phoneNumber);

                //...
            } while (cursor.moveToNext());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
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
            if(compName.contains("Social Captial"))
            {
                Log.d("bankDepositModel","compNameEquals: "+compName);
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
                                if((varID == vl.get_value_variable_id()) && varName.contains("No.of Contacts "))
                                {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel", "valScoreFlag: "+valScoreFlag);
                                    Log.d("bankDepositModel","flag value: " + valFlag);
                                    Log.d("bankDepositModel","varID: "+varID);
                                    Log.d("bankDepositModel","varName: "+varName);
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
                                        Log.d("bankDepositModel","valName1: "+valName1+" valScore1: "+valScore1);

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
                                        Log.d("bankDepositModel","valName2: "+valName2+" valScore2: "+valScore2);
                                    }
                                    if(valFlag == 3)
                                    {
                                        valName3 = vl.get_value_name();
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel","valName3: "+valName3+" valScore3: "+valScore3);
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
                                        Log.d("bankDepositModel","valName4: "+valName4+" valScore4: "+valScore4);
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
                                        Log.d("bankDepositModel","valName5: "+valName5+" valScore5: "+valScore5);
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
                                        Log.d("bankDepositModel","valName6: "+valName6+" valScore6: "+valScore6);
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
                                if((varID == vl.get_value_variable_id()) && varName.contains("No.of Contacts "))
                                {
                                    valFlag = valFlag + 1;
                                    Log.d("bankDepositModel1", "valScoreFlag: "+valScoreFlag);
                                    Log.d("bankDepositModel1","flag value: " + valFlag);
                                    Log.d("bankDepositModel1","varID: "+varID);
                                    Log.d("bankDepositModel1","varName: "+varName);
                                    Log.d("bankDepositModel1","checking flag");
                                    if(valFlag==1)
                                    {
                                        Log.d("bankDepositModel1"," entered flag1");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab1 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab1);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab1 = -1;
                                        }
                                        else{
                                            upperSlab1 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab1"+upperSlab1);
                                        valScore1 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore1"+valScore1);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab1: "+lowerSlab1+" upperSlab1: "+upperSlab1);
                                        Log.d("bankDepositModel1","valScore1: "+valScore1);

                                    }
                                    if(valFlag==2)
                                    {
                                        Log.d("bankDepositModel1"," entered flag2");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab2 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab2);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab2 = -1;
                                        }
                                        else{
                                            upperSlab2 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab2"+upperSlab2);
                                        valScore2 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore2"+valScore2);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab2: "+lowerSlab2+" upperSlab2: "+upperSlab2);
                                        Log.d("bankDepositModel1","valScore2: "+valScore2);

                                    }
                                    if(valFlag==3)
                                    {
                                        Log.d("bankDepositModel1"," entered flag3");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab3 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab3);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab3 = -1;
                                        }
                                        else{
                                            upperSlab3 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab3"+upperSlab3);
                                        valScore3 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore3"+valScore3);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab3: "+lowerSlab3+" upperSlab3: "+upperSlab3);
                                        Log.d("bankDepositModel1","valScore3: "+valScore3);

                                    }
                                    if(valFlag==4)
                                    {
                                        Log.d("bankDepositModel1"," entered flag4");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab4 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab4);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab4 = -1;
                                        }
                                        else{
                                            upperSlab4 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab4"+upperSlab4);
                                        valScore4 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore4"+valScore4);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab4: "+lowerSlab4+" upperSlab4: "+upperSlab4);
                                        Log.d("bankDepositModel1","valScore4: "+valScore4);

                                    }
                                    if(valFlag==5)
                                    {
                                        Log.d("bankDepositModel1"," entered flag5");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab5 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab5);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab5 = -1;
                                        }
                                        else{
                                            upperSlab5 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab5"+upperSlab5);
                                        valScore5 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore5"+valScore5);

                                        Log.d("bankDepositModel1","lowerSlab5: "+lowerSlab5+" upperSlab5: "+upperSlab5);
                                        Log.d("bankDepositModel1","valScore5: "+valScore5);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }


                                    }
                                    if(valFlag==6)
                                    {
                                        Log.d("bankDepositModel1"," entered flag6");
                                        Log.d("bankDepositModel1",vl.get_min_value());
                                        lowerSlab6 = Integer.parseInt(vl.get_min_value());
                                        Log.d("bankDepositModel1","val"+lowerSlab6);
                                        Log.d("bankDepositModel1","MaxVal"+MaxvalFlag);
                                        Log.d("bankDepositModel1",vl.get_max_value());
                                        if(vl.get_max_value().contains("null")){
                                            Log.d("bankDepositModel1","null value");
                                            upperSlab6 = -1;
                                        }
                                        else{
                                            upperSlab6 = Integer.parseInt(vl.get_max_value());
                                        }


                                        Log.d("bankDepositModel1","upperSlab6"+upperSlab6);
                                        valScore6 = vl.get_value_score();
                                        Log.d("bankDepositModel1","valScore6"+valScore6);
                                        if(valFlag == MaxvalFlag)
                                        {
                                            Log.d("bankDepositModel1","break");
                                            valFlag = 0;
                                            break;
                                        }
                                        Log.d("bankDepositModel1","lowerSlab6: "+lowerSlab6+" upperSlab6: "+upperSlab6);
                                        Log.d("bankDepositModel1","valScore6: "+valScore6);

                                    }
                                }
                            }
                        }

                    }
                }
            }
        }

        Log.d("ok","lowerSlab1 "+lowerSlab1);
        Log.d("ok","upperSlab1 "+upperSlab1);
        Log.d("ok","lowerSlab2 "+lowerSlab2);
        Log.d("ok","upperSlab2 "+upperSlab2);
        int number_of_contacts_score = 0;
        if((Number_of_contacts>=lowerSlab1)){
            if (valScore1 != null) {
                number_of_contacts_score = Integer.parseInt(valScore1);
            }
        }
        else if((Number_of_contacts>=lowerSlab2)&&(Number_of_contacts<upperSlab2)){
            number_of_contacts_score = Integer.parseInt(valScore2);
        }
        else if((Number_of_contacts>=lowerSlab3)&&(Number_of_contacts<upperSlab3)){
            number_of_contacts_score = Integer.parseInt(valScore3);
        }
        else if((Number_of_contacts>=lowerSlab4)&&(Number_of_contacts<upperSlab4)){
            number_of_contacts_score = Integer.parseInt(valScore4);
        }
        else if((Number_of_contacts>=lowerSlab5)&&(Number_of_contacts<upperSlab5)){
            number_of_contacts_score = Integer.parseInt(valScore5);
        }
        else if((Number_of_contacts>=lowerSlab6)&&(Number_of_contacts<upperSlab6)){
            number_of_contacts_score = Integer.parseInt(valScore6);
        }


        return number_of_contacts_score;
    }



}
