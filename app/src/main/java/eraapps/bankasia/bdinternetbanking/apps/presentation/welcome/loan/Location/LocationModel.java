package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.Location;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.util.Log;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.DatabaseHelper;

public class LocationModel {
    DatabaseHelper db;
    Context context;
    double longitude;
    double latitude;
    myDbAdapter helper;
    String LocationDecision;
    int location_score = 0;

    public LocationModel(Context context){
        this.context = context ;
    }

    public Integer locationModel() {

        try {

            helper = new myDbAdapter(context);

//            ActivityCompat.requestPermissions(this.context,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
//                    1);
            LocationManager lm = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);

            if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this.context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return 1;
            }
            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);


            if(location != null){
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                Log.d("loc","longitude: "+longitude+" latitude: "+latitude);

                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
                longitude = location.getLongitude();
                latitude = location.getLatitude();

                Log.d("loc","longitude: "+longitude+" latitude: "+latitude);
            } else {
                Log.d("loc","loc null");
            }


            try {
                Geocoder geocoder = new Geocoder(context, Locale.getDefault());
                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
                Address obj = addresses.get(0);
                String add = obj.getAddressLine(0);
//                add = "Full Address: "+ obj.getAddressLine(0);
//                add = add + "\n\nBuilding Name: " + obj.getFeatureName();
//                add = add + "\nBuilding Number: " + obj.getSubThoroughfare();
                add = add + "\nSub Locality(Thana): " + obj.getSubLocality();
//                add = add + "\nLocality: " + obj.getLocality();
//                add = add + "\nPostalCode: " + obj.getPostalCode();
//                add = add + "\nAdminArea: " + obj.getAdminArea();
//                add = add + "\nSubAdminArea: " + obj.getSubAdminArea();
//                add = add + "\nCountryName: " + obj.getCountryName();
//                add = add + "\nCountryCode: " + obj.getCountryCode();

                Log.d("TimerExample", "Address" + add);
                // Toast.makeText(this, "Address=>" + add,
                // Toast.LENGTH_SHORT).show();

                // TennisAppActivity.showDialog(add);

                Date date = new Date();
                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

                String t1 = obj.getSubLocality();
                String t2 = formatter.format(date).toString();

                if(t1.isEmpty() || t2.isEmpty())
                {
                    Message.message(context.getApplicationContext(),"Enter Both Name and Password");
                } else
                {
                    long id = helper.insertData(t1,t2);
                    if(id<=0)
                    {
                        Message.message(context.getApplicationContext(),"Insertion Unsuccessful");

                    }else
                    {
                        Message.message(context.getApplicationContext(),"Insertion Successful");
                    }
                }
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                Toast.makeText(context, e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            //Log.d("location","longitude: "+String.valueOf(longitude)+"latitude: "+String.valueOf(latitude));
        } catch (Exception e) {
            e.printStackTrace();
        }


        try{

            String data = helper.getData();
            Log.d("data",data);
//            Message.message(context,data);

            //Location service scheduler:
            //------------------------------------------------------------
            //startService(new Intent( this, LocationService.class ) );
            LocationDecision = data;


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

            //FOR Data package
            for (Component cn : components) {
                compID = cn.get_id();
                compName = cn.get_name();
                //Log.d("asd",compName);
                if(compName.contains("Employment"))
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Location (9 AM - 6 PM/ 6 PM- 8 AM)"))
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
                                    if((varID == vl.get_value_variable_id()) && varName.contains("Location (9 AM - 6 PM/ 6 PM- 8 AM)"))
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




            if((LocationDecision.contains(valName1))){
                location_score = Integer.parseInt(valScore1);

            }
            else if((valName2.contains(LocationDecision))){
                location_score = Integer.parseInt(valScore2);
            }

            LocationDecision = LocationDecision+"\n\n Score: "+location_score;
        }catch (Exception e){
            e.printStackTrace();
        }
        return location_score;
    }

}
