package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.Location;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import eraapps.bankasia.bdinternetbanking.apps.R;

public class LocationWithDBActivity extends AppCompatActivity {

    double longitude;
    double latitude;
    myDbAdapter helper;
    TextView locationTextView;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_with_dbactivity);

        locationTextView=findViewById(R.id.locationTextView);

        try{
            helper = new myDbAdapter(this);
            //Location model to get location and insert data in DB sqlite
            //------------------------------------------------------------
            LocationModel locationModel = new LocationModel(getApplicationContext());
            Integer LocationDecision = locationModel.locationModel();
            //locationTextView.setText("Category : Employment \n\n9 Am to 6 PM/6 PM to 8 AM : "+ LocationDecision);

            locationTextView.setText("Category : Employment \n\n9 Am to 6 PM/6 PM to 8 AM : "+ "\n\n Score: "+ LocationDecision);
//            String data = helper.getData();
//            Log.d("data",data);
//            Message.message(this,data);

            //Location service scheduler:
            //------------------------------------------------------------
//            startService(new Intent( this, LocationService.class ) );

        }catch (Exception e){
            e.printStackTrace();
        }

        ScheduledExecutorService scheduler =
                Executors.newSingleThreadScheduledExecutor();



//        scheduler.scheduleAtFixedRate(new Runnable() {
//            @Override
//            public void run() {
////                System.out.println("scheduleAtFixedRate:    " + new Date());
//                Log.d("scheduleAtFixedRate","scheduleAtFixedRate");
//            }
//        }, 1, 3L , TimeUnit.SECONDS);

        scheduler.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                Log.d("scheduleAtFixedRate","scheduleWithFixedDelay");

                try{
//                    helper = new myDbAdapter(this);

                    //Location model to get location and insert data in DB sqlite
                    //------------------------------------------------------------
                    LocationModel locationModel = new LocationModel(getApplicationContext());
                    Integer LocationDecision = locationModel.locationModel();
//            String data = helper.getData();
//            Log.d("data",data);
//            Message.message(this,data);

                    //Location service scheduler:
                    //------------------------------------------------------------
//            startService(new Intent( this, LocationService.class ) );

                }catch (Exception e){
                    e.printStackTrace();
                }

            }
        }, 1, 3L , TimeUnit.HOURS);


//        try {
//
//            helper = new myDbAdapter(this);
//
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},
//                    1);
//            LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
//            //lm.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, (LocationListener) this);
//
//            if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                // TODO: Consider calling
//                //    ActivityCompat#requestPermissions
//                // here to request the missing permissions, and then overriding
//                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                //                                          int[] grantResults)
//                // to handle the case where the user grants the permission. See the documentation
//                // for ActivityCompat#requestPermissions for more details.
//                return;
//            }
//            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//
//
//            if(location != null){
//                longitude = location.getLongitude();
//                latitude = location.getLatitude();
//
//                Log.d("loc","longitude: "+longitude+" latitude: "+latitude);
//
//                location = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//                longitude = location.getLongitude();
//                latitude = location.getLatitude();
//
//                Log.d("loc","longitude: "+longitude+" latitude: "+latitude);
//            } else {
//                Log.d("loc","loc null");
//            }
//
//
//            try {
//                Geocoder geocoder = new Geocoder(LocationWithDBActivity.this, Locale.getDefault());
//                List<Address> addresses = geocoder.getFromLocation(latitude, longitude, 1);
//                Address obj = addresses.get(0);
//                String add = obj.getAddressLine(0);
//                add = "Full Address: "+ obj.getAddressLine(0);
//                add = add + "\n\nBuilding Name: " + obj.getFeatureName();
//                add = add + "\nBuilding Number: " + obj.getSubThoroughfare();
//                add = add + "\nSub Locality(Thana): " + obj.getSubLocality();
//                add = add + "\nLocality: " + obj.getLocality();
//                add = add + "\nPostalCode: " + obj.getPostalCode();
//                add = add + "\nAdminArea: " + obj.getAdminArea();
//                add = add + "\nSubAdminArea: " + obj.getSubAdminArea();
//                add = add + "\nCountryName: " + obj.getCountryName();
//                add = add + "\nCountryCode: " + obj.getCountryCode();
//
//                Log.d("TimerExample", "Address" + add);
//                // Toast.makeText(this, "Address=>" + add,
//                // Toast.LENGTH_SHORT).show();
//
//                // TennisAppActivity.showDialog(add);
//
//                Date date = new Date();
//                @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
//
//                String t1 = obj.getSubLocality();
//                String t2 = formatter.format(date).toString();
//
//                if(t1.isEmpty() || t2.isEmpty())
//                {
//                    Message.message(getApplicationContext(),"Enter Both Name and Password");
//                } else
//                {
//                    long id = helper.insertData(t1,t2);
//                    if(id<=0)
//                    {
//                        Message.message(getApplicationContext(),"Insertion Unsuccessful");
//
//                    }else
//                    {
//                        Message.message(getApplicationContext(),"Insertion Successful");
//                    }
//                }
//            }catch (IOException e) {
//                // TODO Auto-generated catch block
//                e.printStackTrace();
//                Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//
//
//            //Log.d("location","longitude: "+String.valueOf(longitude)+"latitude: "+String.valueOf(latitude));
//            startService(new Intent( this, LocationService.class ) );
//            //startService(new Intent(getBaseContext(), LocationService.class));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }



    }
    public void Back(View view) {
       // Intent intent = new Intent(LocationWithDBActivity.this, HomeActivity.class);
    //    startActivity(intent);
    }
}
