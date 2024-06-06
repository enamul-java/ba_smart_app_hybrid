package eraapps.bankasia.bdinternetbanking.apps.util;

import android.util.Log;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtil {


    public static String geCurrendDate() {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String month1 = null;
        String day1 = null;
        if (month < 9) {
            month1 = "0" + (month + 1);
        } else {
            month1 = month + 1 + "";
        }
        if (day <= 9) {
            day1 = "0" + day;
        } else {
            day1 = day + "";
        }

        return day1 + "/" + month1 + "/" + year;

    }

    /**
     * Md. Enamul Haque
     * Date: 26 Oct 2023
     * Format Date as wish
     * @param inputDate Date to format
     * @param inputFormat Current Format
     * @param outputFormat Expected Output format
     * @return Expected Output
     */
    public static String metlifeDueDateFormat(String inputDate, String inputFormat, String outputFormat){

        if(null == inputFormat
        || null ==outputFormat
        || inputFormat.equals("")
        || outputFormat.equals("") ){
            return inputDate;
        }

        SimpleDateFormat sdf = new SimpleDateFormat(inputFormat);//"yyyyMMdd");
        SimpleDateFormat outsdf = new SimpleDateFormat(outputFormat);//"dd/MM/yyyy");
        Date date = new Date();
        String sDate = inputDate;
        try {
            date = sdf.parse(inputDate);
            sDate = outsdf.format(date);
        } catch (ParseException e) {
            return inputDate;
        }
        return sDate;
    }

    public static String geCurrendDateNew() {

        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        String month1 = null;
        String day1 = null;
        if (month < 9) {
            month1 = "0" + (month + 1);
        } else {
            month1 = month + 1 + "";
        }
        if (day <= 9) {
            day1 = "0" + day;
        } else {
            day1 = day + "";
        }

        return month1 + "/" + day1 + "/" + year;

    }


    public static String getCardDate(String cardDate) {
        String output = "";
        if (cardDate != null) {

            //  String input = "2012/01/20 12:05:10.321";
            DateFormat inputFormatter = new SimpleDateFormat("yyMM");
            Date date;
            try {
                date = inputFormatter.parse(cardDate);
                DateFormat outputFormatter = new SimpleDateFormat("MM/yy");
                output = outputFormatter.format(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        } else {
            output = cardDate;
        }

        return output;
    }


    public static String getNextDate() {

        final Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 1); //minus number would decrement the days
        int year = c.get(Calendar.YEAR);
        int month = 0;
        int day = 0;
        int daycheck = c.get(Calendar.DAY_OF_MONTH);
        String monthchk = new SimpleDateFormat("MMM").format(c.getTime()).toUpperCase();

        day = c.get(Calendar.DAY_OF_MONTH);
        month = c.get(Calendar.MONTH);


        String month1 = null;
        String day1 = null;
        if (month < 9) {
            month1 = "0" + (month + 1);
        } else {
            month1 = month + 1 + "";
        }
        if (day <= 9) {
            day1 = "0" + day;
        } else {
            day1 = day + "";
        }

        return day1 + "/" + month1 + "/" + year;

    }

    public static void dateRang() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        int i = c.get(Calendar.DAY_OF_WEEK) - c.getFirstDayOfWeek();
        c.add(Calendar.DATE, -i - 7);
        Date start = c.getTime();

        c.add(Calendar.DATE, 6);
        Date end = c.getTime();
        Log.e("date", "date-->" + start + "--" + end);

        SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
        String startDate = DateFor.format(start);

        String endDate = DateFor.format(end);
        Log.e("startDate-->", startDate);
        Log.e("endDate-->", endDate);


    }

    public static String getCommission_date(int minusday) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Date today = new Date();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, -minusday);
        Date date = cal.getTime();
        return formatter.format(date).toUpperCase();
    }

    public static String getAddDays(int addDays) {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

        Date today = new Date();
        Calendar cal = Calendar.getInstance(Locale.ENGLISH);
        cal.setTime(today);
        cal.add(Calendar.DAY_OF_MONTH, addDays);
        Date date = cal.getTime();
        return formatter.format(date).toUpperCase();
    }

    public static boolean isValidToCancel(String date){
        if(null == date)
            return false;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy hh:mm a");
            Date lastCancelDate = sdf.parse(date);
            Log.e("ShohozCancelDate", "lastCancelDate = " + lastCancelDate);
            Log.e("ShohozCancelDate", "Date Today "+sdf.format(new Date()));
            return new Date().before(lastCancelDate);
        } catch (ParseException ex) {
            Log.e("ShohozCancelDate", ex.toString());
        }
        return false;
    }

    public static String getVisaCurDateTime() {
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        String formattedDate = df.format(c.getTime());
        return formattedDate;
    }

    public static Date NextDay(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, 1); //minus number would decrement the days
        return cal.getTime();
    }


    public static String dateWithAmPm(){
        SimpleDateFormat format = new SimpleDateFormat("dd MMM yyyy hh:mm:ss a");
        String s = format.format(new Date());
        s = s.replace("am", "AM").replace("pm","PM");
        return s;
    }

    public static String formatFullDate(String inputDate, String outputFormatString){
        String outFormatString = "dd MMM, yyyy";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = new Date();
        try{
            date = format.parse(inputDate);
        }catch (Exception e){
            return inputDate;
        }
        if(null!=outputFormatString && !outputFormatString.equals("") && !outputFormatString.equals("null")){
            outFormatString = outputFormatString;
        }
        SimpleDateFormat outFormat = new SimpleDateFormat(outFormatString);
        String outDate = inputDate;
        try{
            outDate = outFormat.format(date);
        }catch (Exception e){
            return inputDate;
        }
        return outDate;
    }


    public static String dateParse(String dateStr) {
        int year = 0;
        int month = 0;
        int day = 0;
        String month1 = null;
        String day1 = null;
        try {
            String lastTwo = " ";
            if (dateStr != null && dateStr.length() >= 9) {
                lastTwo = dateStr.substring(dateStr.length() - 9);
            }

            //Log.e("lastTwo", String.valueOf(lastTwo));
            //String dateStr = "20Nov1996";
            DateFormat formatter = new SimpleDateFormat("ddMMMyyyy");
            Date date = (Date) formatter.parse(lastTwo);

            //Log.e("formatedDate : " , date.toString());

            Calendar cal = Calendar.getInstance();
            cal.setTime(date);
            year = cal.get(Calendar.YEAR);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            //Log.e("day", String.valueOf(day));

            if (month < 9) {
                month1 = "0" + (month + 1);
            } else {
                month1 = month + 1 + "";
            }
            if (day <= 9) {
                day1 = "0" + day;
            } else {
                day1 = day + "";
            }
            //Log.e("formatedDate ", day1 + "/" + month1 + "/" + year);

        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Errortag", e.getMessage().toString());
        }

        return day1 + "/" + month1 + "/" + year;
    }

    public static boolean isValidDate(String inDate) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }

    public static boolean isValidDateOnlineOCR(String inDate) {
        Log.e("date", inDate);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMM yyyy");
        dateFormat.setLenient(false);
        try {
            dateFormat.parse(inDate.trim());
        } catch (ParseException pe) {
            return false;
        }
        return true;
    }


}
