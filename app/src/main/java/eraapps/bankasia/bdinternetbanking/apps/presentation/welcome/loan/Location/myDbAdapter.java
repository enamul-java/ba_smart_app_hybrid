package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.Location;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

public class myDbAdapter {
        myDbHelper myhelper;

        public myDbAdapter(Context context) {
            myhelper = new myDbHelper(context);
        }

        public long insertData(String Location_Name, String Date_Time) {


            Log.d("Location_Name","Location_NameDate_Time");
            Log.d("Location_Name",Location_Name+" "+Date_Time);

            SQLiteDatabase dbb = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.Location_Name, Location_Name);
            contentValues.put(myDbHelper.Date_Time, Date_Time);
            long id = dbb.insert(myDbHelper.TABLE_NAME, null, contentValues);
            return id;

        }

        public String getData() {
            HashMap<String, Integer> night_location = new HashMap<String, Integer>();

            //I am just focusing on location moving or not in a single day.
            //Finding multiple location in a single day
            //Then checking it for 31 days of a month
            //if the "different"/"same" percentage is bigger than 70% than taking decision

            //Here's I can apply one more algorithm but not doing that for reducing complexity

            int day;
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

            Log.d("target","targetMonth: "+targetMonth+" targetYear: "+targetYear);


            String loc_date, loc_time;
            String decision;
            int different_loc = 0;

            int hour,sameLocationCount = 0;


            SQLiteDatabase db = myhelper.getWritableDatabase();
            String[] columns = {myDbHelper.UID, myDbHelper.Location_Name, myDbHelper.Date_Time};
            Cursor cursor = db.query(myDbHelper.TABLE_NAME, columns, null, null, null, null, null);
//            StringBuffer buffer = new StringBuffer();
            while (cursor.moveToNext()) {
                @SuppressLint("Range") int cid = cursor.getInt(cursor.getColumnIndex(myDbHelper.UID));
                @SuppressLint("Range") String Location_Name = cursor.getString(cursor.getColumnIndex(myDbHelper.Location_Name));
                @SuppressLint("Range") String Date_Time = cursor.getString(cursor.getColumnIndex(myDbHelper.Date_Time));
//                buffer.append(cid + "   " + Location_Name + "   " + Date_Time + " \n");

                HashMap<String, Integer> per_day_locs = new HashMap<String, Integer>();

                loc_date = (Date_Time.substring(0, 10));
                loc_time = (Date_Time.substring(11, 19));
                hour = Integer.parseInt(loc_time.split(":")[0]);
                day = Integer.parseInt(loc_date.split("-")[0]);
                month = Integer.parseInt(loc_date.split("-")[1]);
                year = Integer.parseInt(loc_date.split("-")[2]);

                Log.d("loc_datetime","loc_date: "+loc_date+" loc_time: "+loc_time + " hour: "+hour);
                if((year==targetYear)&&(month==targetMonth)) {
                    for(int k=1;k<32;k++){
                        if (k==day){
                            per_day_locs.put(Location_Name,day);
                        }
                    }
                    if(per_day_locs.size()>1){
                        different_loc++;
                    }
                }

//                if(hour>9 && hour <18){
//                    for (String morning_loc_name : morning_location.keySet()) {
//                        Log.d("morning_location", "morning_loc_name: " + morning_loc_name+ " count: " + morning_location.get(morning_loc_name));
//                        if(morning_loc_name.contains(Location_Name)){
//                            morning_location.put(morning_loc_name, morning_location.get(morning_loc_name) + 1);
//                        }
//                        else{
//                            morning_location.put(morning_loc_name, 1);
//                        }
//                    }
//                }
//                else{
//                    for (String night_loc_name : night_location.keySet()) {
//                        Log.d("night_loc_name", "night_loc_name: " + night_loc_name+ " count: " + night_location.get(night_loc_name));
//                        if(night_loc_name.contains(Location_Name)){
//                            night_location.put(night_loc_name, morning_location.get(night_loc_name) + 1);
//                        }
//                        else{
//                            night_location.put(night_loc_name, 1);
//                        }
//                    }
//                }


            }
            if(different_loc>20){
                decision = "Different";
            }
            else{
                decision="Same";
            }
            return decision;
        }

//        public int delete(String uname) {
//            SQLiteDatabase db = myhelper.getWritableDatabase();
//            String[] whereArgs = {uname};
//
//            int count = db.delete(myDbHelper.TABLE_NAME, myDbHelper.NAME + " = ?", whereArgs);
//            return count;
//        }

//        public int updateName(String oldName, String newName) {
//            SQLiteDatabase db = myhelper.getWritableDatabase();
//            ContentValues contentValues = new ContentValues();
//            contentValues.put(myDbHelper.NAME, newName);
//            String[] whereArgs = {oldName};
//            int count = db.update(myDbHelper.TABLE_NAME, contentValues, myDbHelper.NAME + " = ?", whereArgs);
//            return count;
//        }

        static class myDbHelper extends SQLiteOpenHelper {
            private static final String DATABASE_NAME = "LocationDatabase";    // Database Name
            private static final String TABLE_NAME = "LocationTable";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
            private static final String UID = "_id";     // Column I (Primary Key)
            private static final String Location_Name = "Location_Name";    //Column II
            private static final String Date_Time = "Date_Time";    // Column III
            private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
                    " (" + UID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + Location_Name + " VARCHAR(255) ," + Date_Time + " VARCHAR(225));";
            private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;
            private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context = context;
            }

            public void onCreate(SQLiteDatabase db) {

                try {
                    db.execSQL(CREATE_TABLE);
                } catch (Exception e) {
                    Message.message(context, "" + e);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
                    Message.message(context, "OnUpgrade");
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                } catch (Exception e) {
                    Message.message(context, "" + e);
                }
            }
        }
}
