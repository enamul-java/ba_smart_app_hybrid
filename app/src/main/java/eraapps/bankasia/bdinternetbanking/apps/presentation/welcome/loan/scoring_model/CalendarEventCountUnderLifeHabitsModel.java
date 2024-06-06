package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.icu.util.Calendar;
import android.net.Uri;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.Bkash;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing.CreditCard;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Component;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Value;
import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.entity.Variable;

public class CalendarEventCountUnderLifeHabitsModel {

//    public static String nameOfEvent = "";
    public static String startDates = "";
//    public static String endDates = "";
//    public static String descriptions = "";
    private Context context;
    int eventCount = 0;

    DatabaseHelper db;

    public CalendarEventCountUnderLifeHabitsModel(Context context) {
        this.context = context;
    }

    public Integer calendarEventCountUnderLifeHabitsModel() {
        int month;
        int year;

        int targetMonth;
        int targetYear;
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
        //Log.d("date", "date: " + date);
        targetMonth = Integer.parseInt(date.split("-")[1]) - 1;
        targetYear = Integer.parseInt(date.split("-")[2]);
        if (targetMonth == 0) {
            targetMonth = 12;
            targetYear = targetYear - 1;
        }

//        Log.d("target","month: "+targetMonth);
//        Log.d("target","year: "+targetYear);

        Cursor cursor = context.getContentResolver()
                .query(
                        Uri.parse("content://com.android.calendar/events"),
                        new String[]{"calendar_id", "title", "description",
                                "dtstart", "dtend", "eventLocation"}, null,
                        null, null);
        cursor.moveToFirst();
        if (cursor.moveToFirst()) {
            do {
                startDates = (getDate(Long.parseLong(cursor.getString(3))));
                month = Integer.parseInt(startDates.split("/")[1]);
                year = Integer.parseInt(startDates.split("/")[2]);

                if ((year == targetYear) && (month == targetMonth)) {
                    //Log.d("calendar", cursor.getString(1));
//                    Log.d("calendar", "startDate: "+getDate(Long.parseLong(cursor.getString(3))));
//                    Log.d("calendar", "month: "+month);
//                    Log.d("calendar", "year: "+year);
                    eventCount = eventCount + 1;
                }
            } while (cursor.moveToNext());
        }
        Log.d("calendar", "check"+eventCount);


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
                                if((varID == vl.get_value_variable_id()) && varName.contains("Calender Events Attended"))
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
                                if((varID == vl.get_value_variable_id()) && varName.contains("Calender Events Attended"))
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

//        Log.d("chek","valScore1: "+valScore1);
//        Log.d("chek","valScore2: "+valScore2);
//        Log.d("chek","valScore3: "+valScore3);
//        Log.d("chek","valScore4: "+valScore4);
//        Log.d("chek","valScore5: "+valScore5);
//        Log.d("chek","valScore6: "+valScore6);




        int CalendarScore = 0;
        if((eventCount>=lowerSlab1)&&(eventCount<=upperSlab1)){
            CalendarScore = Integer.parseInt(valScore1);
        }
        else if((eventCount>=lowerSlab2)&&(eventCount<=upperSlab2)){
            CalendarScore = Integer.parseInt(valScore2);
        }
        else if((eventCount>=lowerSlab3)&&(eventCount<=upperSlab3)){
            CalendarScore = Integer.parseInt(valScore3);
        }
        else if((eventCount>=lowerSlab4)&&(eventCount<=upperSlab4)){
            CalendarScore = Integer.parseInt(valScore4);
        }
        else if((eventCount>=lowerSlab5)&&(eventCount<=upperSlab5)){
            CalendarScore = Integer.parseInt(valScore5);
        }
        else if((eventCount>=lowerSlab6)&&(eventCount<=upperSlab6)){
            CalendarScore = Integer.parseInt(valScore6);
        }

        return CalendarScore;
    }

    @SuppressLint("NewApi")
    public static String getDate(long milliSeconds) {
        SimpleDateFormat formatter = new SimpleDateFormat(
                "dd/MM/yyyy");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(milliSeconds);
        return formatter.format(calendar.getTime());
    }
}
