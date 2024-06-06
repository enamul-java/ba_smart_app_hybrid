package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class EntertainmentAppsModel {
    DatabaseHelper db;
    Context context;

    public EntertainmentAppsModel(Context context){
        this.context = context ;
    }
    public Integer entertainmentAppsModel() {

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
            if(compName.contains("Life Habits"))
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
                                if((varID == vl.get_value_variable_id()) && varName.contains("Entertainment Apps installed"))
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
                                if((varID == vl.get_value_variable_id()) && varName.contains("Entertainment Apps installed"))
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

        PackageManager pm = context.getPackageManager();
        Intent main = new Intent(Intent.ACTION_MAIN, null);
        main.addCategory(Intent.CATEGORY_LAUNCHER);
        ArrayList<String> app_name_list = new ArrayList<String>();
        ArrayList<String> app_package_list = new ArrayList<String>();
        List<ResolveInfo> packages = pm.queryIntentActivities(main, 0);
        for(ResolveInfo resolve_info : packages) {
            try {
                String package_name = resolve_info.activityInfo.packageName;
                String app_name = (String)pm.getApplicationLabel(
                        pm.getApplicationInfo(package_name
                                , PackageManager.GET_META_DATA));
                boolean same = false;
                for(int i = 0 ; i < app_name_list.size() ; i++) {
                    if(package_name.equals(app_package_list.get(i)))
                        same = true;
                }
                if(!same) {
                    app_name_list.add(app_name);
                    app_package_list.add(package_name);
                }
                Log.d("Check", "package = <" + package_name + "> name = <" + app_name + ">");
            } catch(Exception e) {
                e.printStackTrace();
            }
        }
//        listViewAppsInstalled.setAdapter(new ArrayAdapter<String>(MainActivity.this, android.R.layout.simple_list_item_1, stringsList));
//        textViewStatus.setText(listApplicationInfo.size() + " Apps are installed");




        int Entertainment_apps_score = 0;
        int Entertainment_apps_count = 0;

        // get list of all the apps installed
        @SuppressLint("QueryPermissionsNeeded") List<PackageInfo> packList = context.getPackageManager().getInstalledPackages(0);
        String[] apps = new String[packList.size()];
        for (int i = 0; i < packList.size(); i++) {
            PackageInfo packInfo = packList.get(i);
            apps[i] = packInfo.applicationInfo.loadLabel(context.getPackageManager()).toString();
            Log.d("Socialapps",packInfo.applicationInfo.loadLabel(context.getPackageManager()).toString());
            if(apps[i].contains(valName1)){
                Entertainment_apps_score = Integer.parseInt(valScore1);
                Entertainment_apps_count++;
            }
            else if (apps[i].contains(valName2)){
                Entertainment_apps_score = Integer.parseInt(valScore2);
                Entertainment_apps_count++;
            }
            else if (apps[i].contains(valName3)){
                Entertainment_apps_score = Integer.parseInt(valScore3);
                Entertainment_apps_count++;
            }
            else if (apps[i].contains(valName4)){
                Entertainment_apps_score = Integer.parseInt(valScore4);
                Entertainment_apps_count++;
            }
            else if (apps[i].contains(valName5)){
                Entertainment_apps_score = Integer.parseInt(valScore5);
                Entertainment_apps_count++;
            }
            else if (apps[i].contains(valName6)){
                Entertainment_apps_score = Integer.parseInt(valScore6);
                Entertainment_apps_count++;
            }
            else if(Entertainment_apps_count==6){
                Entertainment_apps_score = Integer.parseInt(valScore6);
            }

        }
        Log.d("Score",String.valueOf(Entertainment_apps_score));
        Log.d("Score",String.valueOf(apps.length));


        //TESTING................

        boolean x;
        x=isPackageInstalled(context,"com.whatsapp");
        Log.d("ok","x: "+x);

        boolean y;
        y=isAppInstalled();
        Log.d("ok","y: "+y);


        List<ApplicationInfo> apps2 = context.getPackageManager().getInstalledApplications(0);
        for(ApplicationInfo app : apps2) {
            if((app.flags & (ApplicationInfo.FLAG_UPDATED_SYSTEM_APP | ApplicationInfo.FLAG_SYSTEM)) > 0) {
                Log.d("system",app.toString());// It is a system app
            } else {
                // It is installed by the user
                Log.d("user",app.toString());
            }
        }

        return Entertainment_apps_score;
    }

    public static boolean isPackageInstalled(Context c, String targetPackage) {
        PackageManager pm = c.getPackageManager();
        try {
            PackageInfo info = pm.getPackageInfo(targetPackage, PackageManager.GET_META_DATA);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean isAppInstalled() {
        try {
            context.getApplicationContext().getPackageManager().getApplicationInfo("com.whatsapp", 0);
            return true;
        } catch (PackageManager.NameNotFoundException e) {
            return false;
        }
    }


}
