package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model;

import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.core.app.ActivityCompat;

import org.json.JSONArray;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.Location.LocationModel;

public class FinalScoreModel {
    ArrayList<SMSModel> SMSModelArrayList;
    String sender;
    String msg;
    String formattedDate;
    int score_from_model;
    int outGoingCallUnderEmploymentModelScore;
    int outGoingCallUnderConsumptionProfileModelScore;
    int missedCallScore;
    int StorageScore;
    int callDurationScore;
    int callReceivedScore;
    int DeviceOSScore;
    int DeviceTypeScore;
    int DeviceManufacturerScore;
    int DeviceOperatingSystemScore;
    int DataPackageScore;
    int SocialMediaAppsScore;
    int MusicFileScore;
    int ImageFileScore;
    int PicTakenScore;
    int VpnScore;
    int CalendarEventCountUnderLifeHabitsScore;
    int CalendarEventCountUnderSocialCapitalScore;
    int CalendarEventCountUnderEmploymentScore;
    int CalendarEventTimeScore;
    int Entertainment_apps_score;
    int Number_of_contacts_score;
    int installed_apps_score;
    int received_sms_count_score;
    int sent_sms_count_score;
    int location_score;

    Context context;
    List<SMSItemModel> arrayOfSMSDataPoint = new ArrayList<SMSItemModel>();

    //For fetching phone number
    private static String phoneNumber;
    private String EditedPhoneNumber;
    private static final int PHONE_NUMBER_HINT = 100;
    private final int PERMISSION_REQ_CODE = 200;
    DatabaseHelper db;

    int Finalscore = 0;
    private int without_sms_Score = 0;

    private String[] PERMISSIONS;
    int sms_permission = 1;
    int calendar_permission = 1;
    int external_storage_permission = 1;
    int location_permission = 1;
    int call_log_permission = 1;
    int contacts_permission = 1;
    HashMap<String, Integer> Final_score_with_details = new HashMap<String, Integer>();


    public FinalScoreModel(Context context){
        this.context = context;
    }

    private boolean hasPermissions(Context context, String... PERMISSIONS) {

        if (context != null && PERMISSIONS != null) {

            for (String permission: PERMISSIONS){

                if (ActivityCompat.checkSelfPermission(context,permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }

        return true;
    }

    //read sms function
    @RequiresApi(api = Build.VERSION_CODES.O)
    public HashMap<String,Integer> FinalScore() {
        SMSModelArrayList = new ArrayList<SMSModel>();
        try {

            sms_permission = 1;
            PERMISSIONS = new String[] {
                    android.Manifest.permission.READ_SMS
            };
            if (!hasPermissions(context,PERMISSIONS)) {
                //Toast.makeText(context, "Don't have sms permission", Toast.LENGTH_SHORT).show();
                sms_permission = 0;
            }

            calendar_permission = 1;
            PERMISSIONS = new String[] {
                    android.Manifest.permission.READ_CALENDAR,
            };
            if (!hasPermissions(context,PERMISSIONS)) {
               // Toast.makeText(context, "Don't have READ_CALENDAR permission", Toast.LENGTH_SHORT).show();
                calendar_permission = 0;
            }

            //If less then 13 then
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.TIRAMISU) {
                external_storage_permission = 1;
                PERMISSIONS = new String[]{
                        android.Manifest.permission.READ_EXTERNAL_STORAGE,
                };

                if (!hasPermissions(context, PERMISSIONS)) {
                    // Toast.makeText(context, "Don't have READ_EXTERNAL_STORAGE permission", Toast.LENGTH_SHORT).show();
                    external_storage_permission = 0;
                }
            }else{
                external_storage_permission = 0;
            }

            location_permission = 1;
            PERMISSIONS = new String[] {
                    android.Manifest.permission.ACCESS_FINE_LOCATION,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION
            };

            if (!hasPermissions(context,PERMISSIONS)) {
               // Toast.makeText(context, "Don't have READ_EXTERNAL_STORAGE permission", Toast.LENGTH_SHORT).show();
                location_permission = 0;
            }

            //Block
            //call_log_permission = 1;
            call_log_permission = 0;
            /*PERMISSIONS = new String[] {
                    android.Manifest.permission.READ_CALL_LOG
            };

            if (!hasPermissions(context,PERMISSIONS)) {
                //Toast.makeText(context, "Don't have READ_CALL_LOG permission", Toast.LENGTH_SHORT).show();
                call_log_permission = 0;
            }*/

            contacts_permission = 1;
            PERMISSIONS = new String[] {
                    android.Manifest.permission.READ_CONTACTS
            };

            if (!hasPermissions(context,PERMISSIONS)) {
               // Toast.makeText(context, "Don't have READ_CONTACTS permission", Toast.LENGTH_SHORT).show();
                contacts_permission = 0;
            }

            Log.d("permissions","location_permission: "+ location_permission );
            Log.d("permissions","external_storage_permission: "+ external_storage_permission );
            Log.d("permissions","calendar_permission: "+ calendar_permission );
            Log.d("permissions","sms_permission: "+ sms_permission );

            if(sms_permission==1) {

                //getting sms code:
                Cursor cursor = context.getApplicationContext().getContentResolver().query(Uri.parse("content://sms/inbox"),
                        null, null, null, null);

                //Building json array to send sms data to python backend
                cursor.moveToFirst();
                JSONArray jsonArray = new JSONArray();
                SMSModelArrayList = new ArrayList<SMSModel>();

                if (cursor.moveToFirst()) { // must check the result to prevent exception
                    do {
                        //JSONObject msgJSOBObject = new JSONObject();
                        try {
//                        msgJSOBObject.put("sender", sender);
//                        msgJSOBObject.put("msg", msg);
//                        msgJSOBObject.put("date", formattedDate);
//                        msgJSOBObject.put("receiver", phoneNumber);
                            // Preprocessing SMS data to pass to later abstractions
                            sender = cursor.getString(cursor.getColumnIndexOrThrow("address"));
                            msg = cursor.getString(cursor.getColumnIndexOrThrow("body"));
                            Date date = new Date(cursor.getLong(cursor.getColumnIndexOrThrow("date")));
                            formattedDate = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy").format(date);
                            SMSModelArrayList.add(new SMSModel(sender, msg, formattedDate, phoneNumber));

                            //Log.d("sender",sender);
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        //jsonArray.put(msgJSOBObject);
                    } while (cursor.moveToNext());
                }
            }

            try {
                    Finalscore=0;

                    try {

                        HashMap<String, String> bkash_balance_score = new HashMap<String, String>();
                        BkashBalanceModel bkashModel = new BkashBalanceModel(context.getApplicationContext());
                        bkash_balance_score = bkashModel.bkashModel(SMSModelArrayList);
                        if (bkash_balance_score.isEmpty()) {
                            Final_score_with_details.put("Bkash_Balance_Score", -1);
                        } else {
                            Final_score_with_details.put("Bkash_Balance_Score", -1);
                            for (String scoreName : bkash_balance_score.keySet()) {
                                Log.d("bkash_score", "scoreName: " + scoreName + " Score: " + bkash_balance_score.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bkash_balance_score.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bkash_balance_score.get(scoreName));

                                Final_score_with_details.put("Bkash_Balance_Score", Integer.parseInt(bkash_balance_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bkash_deposit_score = new HashMap<String, String>();
                        BkashDepositModel bkashDepositModel = new BkashDepositModel(context.getApplicationContext());
                        bkash_deposit_score = bkashDepositModel.bkashDepositModel(SMSModelArrayList);
                        if (bkash_deposit_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bkash_Deposit_Score", -1);
                        } else {
                            Final_score_with_details.put("Bkash_Deposit_Score", -1);
                            for (String scoreName : bkash_deposit_score.keySet()) {
                                Log.d("bkash_deposit_score", "scoreName: " + scoreName + " Score: " + bkash_deposit_score.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bkash_deposit_score.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bkash_deposit_score.get(scoreName));

                                Final_score_with_details.put("Bkash_Deposit_Score", Integer.parseInt(bkash_deposit_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bkash_withdraw_score = new HashMap<String, String>();
                        BkashWithdrawModel bkashWithdrawModel = new BkashWithdrawModel(context.getApplicationContext());
                        bkash_withdraw_score = bkashWithdrawModel.bkashWithdrawModel(SMSModelArrayList);
                        if (bkash_withdraw_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bkash_Withdraw_Score", -1);
                        } else {
                            Final_score_with_details.put("Bkash_Withdraw_Score", -1);
                            for (String scoreName : bkash_withdraw_score.keySet()) {
                                Log.d("bkash_withdraw_score", "scoreName: " + scoreName + " Score: " + bkash_withdraw_score.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bkash_withdraw_score.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bkash_withdraw_score.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bkash_withdraw_score.get(scoreName));

                                Final_score_with_details.put("Bkash_Withdraw_Score", Integer.parseInt(bkash_withdraw_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bankDepositScore = new HashMap<String, String>();
                        BankDepositUnderIncomeModel bankDepositUnderIncomeModel = new BankDepositUnderIncomeModel(context.getApplicationContext());
                        bankDepositScore = bankDepositUnderIncomeModel.bankDepositUnderIncomeModel(SMSModelArrayList);
                        if (bankDepositScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bank_Deposit_Score_IncomeModel", -1);
                        } else {
                            Final_score_with_details.put("Bank_Deposit_Score_IncomeModel", -1);
                            for (String scoreName : bankDepositScore.keySet()) {
                                Log.d("bankDepositScore", "scoreName: " + scoreName + " newscore: " + bankDepositScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bankDepositScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bankDepositScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bankDepositScore.get(scoreName));

                                Final_score_with_details.put("Bank_Deposit_Score_IncomeModel", Integer.parseInt(bankDepositScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bankDepositScoreFinancial = new HashMap<String, String>();
                        BankDepositUnderFinancialModel bankDepositUnderFinancialModel = new BankDepositUnderFinancialModel(context.getApplicationContext());
                        bankDepositScoreFinancial = bankDepositUnderFinancialModel.bankDepositUnderFinancialModel(SMSModelArrayList);
                        if (bankDepositScoreFinancial.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bank_Deposit_Score_FinancialModel", -1);
                        } else {
                            Final_score_with_details.put("Bank_Deposit_Score_FinancialModel", -1);
                            for (String scoreName : bankDepositScoreFinancial.keySet()) {
                                //Log.d("bankDepositScoreFinancial", "scoreNameasd: " + scoreName+ " score : " + bankDepositScoreFinancial.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bankDepositScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bankDepositScoreFinancial.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bankDepositScoreFinancial.get(scoreName));

                                Final_score_with_details.put("Bank_Deposit_Score_FinancialModel", Integer.parseInt(bankDepositScoreFinancial.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bankWithdrawScore = new HashMap<String, String>();
                        BankWithdrawModel bankWithdrawModel = new BankWithdrawModel(context.getApplicationContext());
                        bankWithdrawScore = bankWithdrawModel.bankWithdrawModel(SMSModelArrayList);
                        if (bankWithdrawScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bank_Withdraw_Score", -1);
                        } else {
                            Final_score_with_details.put("Bank_Withdraw_Score", -1);
                            for (String scoreName : bankWithdrawScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bankWithdrawScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bankWithdrawScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bankWithdrawScore.get(scoreName));

                                Final_score_with_details.put("Bank_Withdraw_Score", Integer.parseInt(bankWithdrawScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bankBalanceScore = new HashMap<String, String>();
                        BankBalanceUnderIncomeModel bankBalanceUnderIncomeModel = new BankBalanceUnderIncomeModel(context.getApplicationContext());
                        bankBalanceScore = bankBalanceUnderIncomeModel.bankBalanceModel(SMSModelArrayList);
                        if (bankBalanceScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bank_Balance_Score_IncomeModel", -1);
                        } else {
                            Final_score_with_details.put("Bank_Balance_Score_IncomeModel", -1);
                            for (String scoreName : bankBalanceScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bankBalanceScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bankBalanceScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bankBalanceScore.get(scoreName));

                                Final_score_with_details.put("Bank_Balance_Score_IncomeModel", Integer.parseInt(bankBalanceScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bankBalanceFinancialScore = new HashMap<String, String>();
                        BankBalanceUnderFinancialModel bankBalanceUnderFinancialModel = new BankBalanceUnderFinancialModel(context.getApplicationContext());
                        bankBalanceFinancialScore = bankBalanceUnderFinancialModel.bankBalanceUnderFinancialModel(SMSModelArrayList);
                        if (bankBalanceFinancialScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bank_Balance_Score_FinancialModel", -1);
                        } else {
                            Final_score_with_details.put("Bank_Balance_Score_FinancialModel", -1);
                            for (String scoreName : bankBalanceFinancialScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +bankBalanceScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bankBalanceFinancialScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bankBalanceFinancialScore.get(scoreName));

                                if (Integer.parseInt(bankBalanceFinancialScore.get(scoreName)) > -1) {
                                    score_from_model = Integer.parseInt(bankBalanceFinancialScore.get(scoreName));
                                } else {
                                    score_from_model = -1;
                                }
                                Log.d("Balance_Score_", "score: " + score_from_model);
                                Final_score_with_details.put("Bank_Balance_Score_FinancialModel", score_from_model);
                            }
                        }

                        HashMap<String, String> salaryScore = new HashMap<String, String>();
                        SalaryModel salaryModel = new SalaryModel(context.getApplicationContext());
                        salaryScore = salaryModel.salaryModel(SMSModelArrayList);
                        if (salaryScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Salary_Score", -1);
                        } else {
                            Final_score_with_details.put("Salary_Score", -1);
                            for (String scoreName : salaryScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +salaryScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + salaryScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(salaryScore.get(scoreName));

                                Final_score_with_details.put("Salary_Score", Integer.parseInt(salaryScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> dataPackageScore = new HashMap<String, String>();
                        DataPackageUnderIncomeModel dataPackageUnderIncomeModel = new DataPackageUnderIncomeModel(context.getApplicationContext());
                        dataPackageScore = dataPackageUnderIncomeModel.dataPackageUnderIncomeModel(SMSModelArrayList);
                        if (dataPackageScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Data_Package_Score_IncomeModel", -1);
                        } else {
                            Final_score_with_details.put("Data_Package_Score_IncomeModel", -1);
                            for (String scoreName : dataPackageScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + dataPackageScore.get(scoreName));
//                                SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(dataPackageScore.get(scoreName));

                                Final_score_with_details.put("Data_Package_Score_IncomeModel", Integer.parseInt(dataPackageScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> dataPackageScore2 = new HashMap<String, String>();
                        DataPackageUnderFinancialSkillsModel dataPackageUnderFinancialSkillsModel = new DataPackageUnderFinancialSkillsModel(context.getApplicationContext());
                        dataPackageScore2 = dataPackageUnderFinancialSkillsModel.dataPackageUnderFinancialSkillsModel(SMSModelArrayList);
                        if (dataPackageScore2.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Data_Package_Score_FinancialSkillsModel", -1);
                        } else {
                            Final_score_with_details.put("Data_Package_Score_FinancialSkillsModel", -1);
                            for (String scoreName : dataPackageScore2.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + dataPackageScore2.get(scoreName));
//                                SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(dataPackageScore2.get(scoreName));

                                Final_score_with_details.put("Data_Package_Score_FinancialSkillsModel", Integer.parseInt(dataPackageScore2.get(scoreName)));
                            }
                        }


                        HashMap<String, String> dataPackageScore3 = new HashMap<String, String>();
                        DataPackageUnderConsumptionProfileModel dataPackageUnderConsumptionProfileModel = new DataPackageUnderConsumptionProfileModel(context.getApplicationContext());
                        dataPackageScore3 = dataPackageUnderConsumptionProfileModel.dataPackageUnderConsumptionProfileModel(SMSModelArrayList);
                        if (dataPackageScore3.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Data_Package_Score_ConsumptionProfileModel", -1);
                        } else {
                            Final_score_with_details.put("Data_Package_Score_ConsumptionProfileModel", -1);
                            for (String scoreName : dataPackageScore3.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + dataPackageScore3.get(scoreName));
//                                SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(dataPackageScore3.get(scoreName));

                                Final_score_with_details.put("Data_Package_Score_ConsumptionProfileModel", Integer.parseInt(dataPackageScore3.get(scoreName)));
                            }
                        }


                        HashMap<String, String> mobileConnectionScore = new HashMap<String, String>();
                        MobileConnectionTypeModel mobileConnectionTypeModel = new MobileConnectionTypeModel(context.getApplicationContext());
                        mobileConnectionScore = mobileConnectionTypeModel.mobileConnectionTypeModel(SMSModelArrayList);
                        if (mobileConnectionScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Mobile_Connection_Score", -1);
                        } else {
                            Final_score_with_details.put("Mobile_Connection_Score", -1);
                            for (String scoreName : mobileConnectionScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + mobileConnectionScore.get(scoreName));
//                                SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(mobileConnectionScore.get(scoreName));

                                Final_score_with_details.put("Mobile_Connection_Score", Integer.parseInt(mobileConnectionScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> topUpScore = new HashMap<String, String>();
                        TopUpModel topUpModel = new TopUpModel(context.getApplicationContext());
                        topUpScore = topUpModel.topUpModel(SMSModelArrayList);
                        if (topUpScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Top_Up_Score", -1);
                        } else {
                            Final_score_with_details.put("Top_Up_Score", -1);
                            for (String scoreName : topUpScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + topUpScore.get(scoreName));
//                                SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(topUpScore.get(scoreName));

                                Final_score_with_details.put("Top_Up_Score", Integer.parseInt(topUpScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> creditCardBalanceScore = new HashMap<String, String>();
                        CreditCardBalanceModel creditCardBalanceModel = new CreditCardBalanceModel(context.getApplicationContext());
                        creditCardBalanceScore = creditCardBalanceModel.creditCardBalanceModel(SMSModelArrayList);
                        if (creditCardBalanceScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Credit_Card_Balance_Score", -1);
                        } else {
                            Final_score_with_details.put("Credit_Card_Balance_Score", -1);
                            for (String scoreName : creditCardBalanceScore.keySet()) {
                                //Log.d("creditDebitScore", "scoreName: " + scoreName+ " Date: " + creditDebitScore.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + creditCardBalanceScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(creditCardBalanceScore.get(scoreName));

                                Final_score_with_details.put("Credit_Card_Balance_Score", Integer.parseInt(creditCardBalanceScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> CreditCard_repayment_behaviour_score = new HashMap<String, String>();
                        CreditCardRepaymentBehaviour creditCardRepaymentBehaviour = new CreditCardRepaymentBehaviour(context.getApplicationContext());
                        CreditCard_repayment_behaviour_score = creditCardRepaymentBehaviour.creditCardRepaymentBehaviour(SMSModelArrayList);
                        if (CreditCard_repayment_behaviour_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Credit_Card_Repayment_Behaviour_Score", -1);
                        } else {
                            Final_score_with_details.put("Credit_Card_Repayment_Behaviour_Score", -1);
                            for (String scoreName : CreditCard_repayment_behaviour_score.keySet()) {
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + CreditCard_repayment_behaviour_score.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(CreditCard_repayment_behaviour_score.get(scoreName));

                                Final_score_with_details.put("Credit_Card_Repayment_Behaviour_Score", Integer.parseInt(CreditCard_repayment_behaviour_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> repayment_behaviour_score = new HashMap<String, String>();
                        RepaymentBehaviourModel repaymentBehaviourModel = new RepaymentBehaviourModel(context.getApplicationContext());
                        repayment_behaviour_score = repaymentBehaviourModel.repaymentBehaviourModel(SMSModelArrayList);
                        if (repayment_behaviour_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Repayment_Behaviour_Score", -1);
                        } else {
                            Final_score_with_details.put("Repayment_Behaviour_Score", -1);
                            for (String scoreName : repayment_behaviour_score.keySet()) {
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + repayment_behaviour_score.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(repayment_behaviour_score.get(scoreName));

                                Final_score_with_details.put("Repayment_Behaviour_Score", Integer.parseInt(repayment_behaviour_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> creditCardMonthlyExpenseScore = new HashMap<String, String>();
                        CreditCardMonthlyExpenseModel creditCardMonthlyExpenseModel = new CreditCardMonthlyExpenseModel(context.getApplicationContext());
                        creditCardMonthlyExpenseScore = creditCardMonthlyExpenseModel.creditCardMonthlyExpenseModel(SMSModelArrayList);
                        if (creditCardMonthlyExpenseScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Credit_Card_Monthly_Expense_Score", -1);
                        } else {
                            Final_score_with_details.put("Credit_Card_Monthly_Expense_Score", -1);
                            for (String scoreName : creditCardMonthlyExpenseScore.keySet()) {
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + creditCardMonthlyExpenseScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(creditCardMonthlyExpenseScore.get(scoreName));

                                Final_score_with_details.put("Credit_Card_Monthly_Expense_Score", Integer.parseInt(creditCardMonthlyExpenseScore.get(scoreName)));
                            }
                        }


                        HashMap<String, String> creditCardMinimumDueScore = new HashMap<String, String>();
                        CreditCardMinimumDue creditCardMinimumDue = new CreditCardMinimumDue(context.getApplicationContext());
                        creditCardMinimumDueScore = creditCardMinimumDue.creditCardMinimumDue(SMSModelArrayList);
                        if (creditCardMinimumDueScore.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Credit_Card_Minimum_Due_Score", -1);
                        } else {
                            Final_score_with_details.put("Credit_Card_Minimum_Due_Score", -1);
                            for (String scoreName : creditCardMinimumDueScore.keySet()) {
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + creditCardMinimumDueScore.get(scoreName));
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(creditCardMinimumDueScore.get(scoreName));

                                Final_score_with_details.put("Credit_Card_Minimum_Due_Score", Integer.parseInt(creditCardMinimumDueScore.get(scoreName)));
                            }
                        }

                        HashMap<String, String> bkashAccountHolder_score = new HashMap<String, String>();
                        BkashAccountHolderModel bkashAccountHolderModel = new BkashAccountHolderModel(context.getApplicationContext());
                        bkashAccountHolder_score = bkashAccountHolderModel.bkashAccountHolderModel(SMSModelArrayList);
                        if (bkashAccountHolder_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bkash_Account_Holder_score", -1);
                        } else {
                            Final_score_with_details.put("Bkash_Account_Holder_score", -1);
                            for (String scoreName : bkashAccountHolder_score.keySet()) {
                                Log.d("bkashAccountHolder", "scoreName: " + scoreName + " Date: " + bkashAccountHolder_score.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bkashAccountHolder_score.get(scoreName));
                                //SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bkashAccountHolder_score.get(scoreName));
                                Final_score_with_details.put("Bkash_Account_Holder_score", Integer.parseInt(bkashAccountHolder_score.get(scoreName)));
                            }
                        }


                        HashMap<String, String> bkashPayment_score = new HashMap<String, String>();
                        BkashPaymentModel bkashPaymentModel = new BkashPaymentModel(context.getApplicationContext());
                        bkashPayment_score = bkashPaymentModel.bkashPaymentModel(SMSModelArrayList);
                        if (bkashPayment_score.isEmpty() || sms_permission == 0) {
                            Final_score_with_details.put("Bkash_Payment_score", -1);
                        } else {

                            Final_score_with_details.put("Bkash_Payment_score", -1);

                            for (String scoreName : bkashPayment_score.keySet()) {
                                Log.d("bkashPayment_score", "scoreName: " + scoreName + " Date: " + bkashPayment_score.get(scoreName));
                                //arrayList.add(scoreName+ " : " +dataPackageScore.get(scoreName));
                                SMSItemModel smsDataPoint = new SMSItemModel(scoreName, "Score: " + bkashPayment_score.get(scoreName));
                                // SMSItemModel smsDataPoint = new SMSItemModel("Data package amount 750mb","Score: 12");
                                arrayOfSMSDataPoint.add(smsDataPoint);
                                Finalscore = Finalscore + Integer.parseInt(bkashPayment_score.get(scoreName));

                                Final_score_with_details.put("Bkash_Payment_score", Integer.parseInt(bkashPayment_score.get(scoreName)));
                            }
                        }


                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    //==================================================================
                    outGoingCallUnderEmploymentModelScore=-1;
                    Final_score_with_details.put("OutGoing_Call_Score_EmploymentModel", outGoingCallUnderEmploymentModelScore);
                    try {
                        if (call_log_permission == 1) {
                            OutGoingCallUnderEmploymentModel outGoingCallUnderEmploymentModel = new OutGoingCallUnderEmploymentModel((context.getApplicationContext()));
                            outGoingCallUnderEmploymentModelScore = outGoingCallUnderEmploymentModel.outGoingCallModel();
                            if (outGoingCallUnderEmploymentModelScore >= 0) {
                                Final_score_with_details.put("OutGoing_Call_Score_EmploymentModel", outGoingCallUnderEmploymentModelScore);
                            } else {
                                Final_score_with_details.put("OutGoing_Call_Score_EmploymentModel", -1);
                            }
                        } else {
                            Final_score_with_details.put("OutGoing_Call_Score_EmploymentModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    outGoingCallUnderConsumptionProfileModelScore = -1;
                    Final_score_with_details.put("OutGoing_Call_Score_ConsumptionProfile", outGoingCallUnderConsumptionProfileModelScore);
                    try {
                        if (call_log_permission == 1) {
                            OutGoingCallUnderConsumptionProfileModel outGoingCallUnderConsumptionProfileModel = new OutGoingCallUnderConsumptionProfileModel((context.getApplicationContext()));
                            outGoingCallUnderConsumptionProfileModelScore = outGoingCallUnderConsumptionProfileModel.outGoingCallUnderConsumptionProfileModel();
                            if (outGoingCallUnderConsumptionProfileModelScore >= 0) {
                                Final_score_with_details.put("OutGoing_Call_Score_ConsumptionProfile", outGoingCallUnderConsumptionProfileModelScore);
                            } else {
                                Final_score_with_details.put("OutGoing_Call_Score_ConsumptionProfile", -1);
                            }
                        } else {
                            Final_score_with_details.put("OutGoing_Call_Score_ConsumptionProfile", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    callReceivedScore = -1;

                    Final_score_with_details.put("Call_Received_Score", callReceivedScore);
                    try {
                        if (call_log_permission == 1) {
                            CallReceivedModel callReceivedModel = new CallReceivedModel(context.getApplicationContext());
                            callReceivedScore = callReceivedModel.callReceivedModel();
                            if (callReceivedScore >= 0) {
                                Final_score_with_details.put("Call_Received_Score", callReceivedScore);
                            } else {
                                Final_score_with_details.put("Call_Received_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Call_Received_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    missedCallScore = -1;
                    Final_score_with_details.put("Missed_Call_Score", missedCallScore);
                    try {
                        if (call_log_permission == 1) {
                            MissedCallModel missedCallModel = new MissedCallModel(context.getApplicationContext());
                            missedCallScore = missedCallModel.missedCallModel();
                            if (missedCallScore >= 0) {
                                Final_score_with_details.put("Missed_Call_Score", missedCallScore);
                            } else {
                                Final_score_with_details.put("Missed_Call_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Missed_Call_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    callDurationScore = -1;
                    Final_score_with_details.put("Call_Duration_Score", callDurationScore);
                    try {
                        if (call_log_permission == 1) {
                            CallDurationModel callDurationModel = new CallDurationModel(context.getApplicationContext());
                            callDurationScore = callDurationModel.callDurationModel();
                            if (callDurationScore >= 0) {
                                Final_score_with_details.put("Call_Duration_Score", callDurationScore);
                            } else {
                                Final_score_with_details.put("Call_Duration_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Call_Duration_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    StorageScore = -1;
                    Final_score_with_details.put("Storage_Score", StorageScore);
                    try {
                        if (external_storage_permission == 1) {
                            StorageModel storageModel = new StorageModel(context.getApplicationContext());
                            StorageScore = storageModel.storageModel();
                            if (StorageScore >= 0) {
                                Final_score_with_details.put("Storage_Score", StorageScore);
                            } else {
                                Final_score_with_details.put("Storage_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Storage_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    DeviceOSScore = -1;
                    Final_score_with_details.put("Device_OS_Score", DeviceOSScore);
                    try {
                        DeviceOsVersionModel deviceOsVersionModel = new DeviceOsVersionModel(context.getApplicationContext());
                        DeviceOSScore = deviceOsVersionModel.deviceOSModel();
                        if (DeviceOSScore >= 0) {
                            Final_score_with_details.put("Device_OS_Score", DeviceOSScore);
                        } else {
                            Final_score_with_details.put("Device_OS_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    DeviceTypeScore = -1;
                    Final_score_with_details.put("Device_Type_Score_IncomeModel", DeviceTypeScore);
                    try {
                        DeviceTypeUnderIncomeModel deviceTypeUnderIncomeModel = new DeviceTypeUnderIncomeModel(context.getApplicationContext());
                        DeviceTypeScore = deviceTypeUnderIncomeModel.deviceTypeModel();
                        if (DeviceTypeScore >= 0) {
                            Final_score_with_details.put("Device_Type_Score_IncomeModel", DeviceTypeScore);
                        } else {
                            Final_score_with_details.put("Device_Type_Score_IncomeModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    DeviceTypeScore = -1;
                    Final_score_with_details.put("Device_Type_Score_LifeHabitsModel", DeviceTypeScore);
                    try {
                        DeviceTypeUnderLifeHabitsModel deviceTypeUnderLifeHabitsModel = new DeviceTypeUnderLifeHabitsModel(context.getApplicationContext());
                        DeviceTypeScore = deviceTypeUnderLifeHabitsModel.deviceTypeModel();
                        if (DeviceTypeScore >= 0) {
                            Final_score_with_details.put("Device_Type_Score_LifeHabitsModel", DeviceTypeScore);
                        } else {
                            Final_score_with_details.put("Device_Type_Score_LifeHabitsModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    DeviceManufacturerScore = -1;
                    Final_score_with_details.put("Device_Manufacturer_Score", DeviceManufacturerScore);
                    try {
                        DeviceManufacturerModel deviceManufacturerModel = new DeviceManufacturerModel(context.getApplicationContext());
                        DeviceManufacturerScore = deviceManufacturerModel.deviceManufacturerModel();
                        if (DeviceManufacturerScore >= 0) {
                            Final_score_with_details.put("Device_Manufacturer_Score", DeviceManufacturerScore);
                        } else {
                            Final_score_with_details.put("Device_Manufacturer_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    DeviceOperatingSystemScore = -1;
                    Final_score_with_details.put("Device_Operating_System_Score", DeviceOperatingSystemScore);
                    try {
                        DeviceOperatingSystemModel deviceOperatingSystemModel = new DeviceOperatingSystemModel(context.getApplicationContext());
                        DeviceOperatingSystemScore = deviceOperatingSystemModel.deviceOperatingSystemModel();
                        if (DeviceOperatingSystemScore >= 0) {
                            Final_score_with_details.put("Device_Operating_System_Score", DeviceOperatingSystemScore);
                        } else {
                            Final_score_with_details.put("Device_Operating_System_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    SocialMediaAppsScore = -1;
                    Final_score_with_details.put("Social_Media_Apps_Score", SocialMediaAppsScore);
                    try {
                        if (external_storage_permission == 1) {
                            SocialMediaAppsModel socialMediaAppsModel = new SocialMediaAppsModel(context.getApplicationContext());
                            SocialMediaAppsScore = socialMediaAppsModel.socialMediaAppsModel();
                            if (SocialMediaAppsScore >= 0) {
                                Final_score_with_details.put("Social_Media_Apps_Score", SocialMediaAppsScore);
                            } else {
                                Final_score_with_details.put("Social_Media_Apps_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Social_Media_Apps_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    Entertainment_apps_score = -1;
                    Final_score_with_details.put("Entertainment_Apps_Score", Entertainment_apps_score);
                    try {
                        if (external_storage_permission == 1) {
                            EntertainmentAppsModel entertainmentAppsModel = new EntertainmentAppsModel(context.getApplicationContext());
                            Entertainment_apps_score = entertainmentAppsModel.entertainmentAppsModel();
                            if (Entertainment_apps_score >= 0) {
                                Final_score_with_details.put("Entertainment_Apps_Score", Entertainment_apps_score);
                            } else {
                                Final_score_with_details.put("Entertainment_Apps_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Entertainment_Apps_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    Number_of_contacts_score = -1;
                    Final_score_with_details.put("Number_Of_Contacts_Score", Number_of_contacts_score);
                    try {
                        if (contacts_permission == 1) {
                            NumberOfContactsModel numberOfContactsModel = new NumberOfContactsModel(context.getApplicationContext());
                            Number_of_contacts_score = numberOfContactsModel.numberOfContactsModel();
                            if (Number_of_contacts_score >= 0) {
                                Final_score_with_details.put("Number_Of_Contacts_Score", Number_of_contacts_score);
                            } else {
                                Final_score_with_details.put("Number_Of_Contacts_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Number_Of_Contacts_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    received_sms_count_score=-1;
                    Final_score_with_details.put("Received_Sms_Count_Score", received_sms_count_score);
                    try {
                        if (sms_permission == 1) {
                            SmsReceivedModel smsReceivedModel = new SmsReceivedModel(context.getApplicationContext());
                            received_sms_count_score = smsReceivedModel.smsReceivedModel();
                            if (received_sms_count_score >= 0) {
                                Final_score_with_details.put("Received_Sms_Count_Score", received_sms_count_score);
                            } else {
                                Final_score_with_details.put("Received_Sms_Count_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Received_Sms_Count_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    sent_sms_count_score=-1;
                    Final_score_with_details.put("Sent_Sms_Count_Score", sent_sms_count_score);
                    try {
                        if (sms_permission == 1) {
                            SmsSentModel smsSentModel = new SmsSentModel(context.getApplicationContext());
                            sent_sms_count_score = smsSentModel.smsSentModel();
                            if (sent_sms_count_score >= 0) {
                                Final_score_with_details.put("Sent_Sms_Count_Score", sent_sms_count_score);
                            } else {
                                Final_score_with_details.put("Sent_Sms_Count_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Sent_Sms_Count_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }



                    MusicFileScore=-1;
                    Final_score_with_details.put("Music_File_Score", MusicFileScore);
                    try {
                        if (external_storage_permission == 1) {
                            MusicFilesModel musicFilesModel = new MusicFilesModel(context.getApplicationContext());
                            MusicFileScore = musicFilesModel.musicFilesModel();
                            if (MusicFileScore >= 0) {
                                Final_score_with_details.put("Music_File_Score", MusicFileScore);
                            } else {
                                Final_score_with_details.put("Music_File_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Music_File_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    ImageFileScore=-1;
                    Final_score_with_details.put("Image_File_Score", ImageFileScore);
                    try {
                        if (external_storage_permission == 1) {
                            ImageFilesModel imageFilesModel = new ImageFilesModel(context.getApplicationContext());
                            ImageFileScore = imageFilesModel.imageFilesModel();
                            if (ImageFileScore >= 0) {
                                Final_score_with_details.put("Image_File_Score", ImageFileScore);
                            } else {
                                Final_score_with_details.put("Image_File_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Image_File_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    PicTakenScore=-1;
                    Final_score_with_details.put("Pic_Taken_Per_Day", PicTakenScore);
                    try {
                        if (external_storage_permission == 1) {
                            PicTakenPerDay picTakenPerDay = new PicTakenPerDay(context.getApplicationContext());
                            PicTakenScore = picTakenPerDay.picTakenPerDay();
                            if (PicTakenScore >= 0) {
                                Final_score_with_details.put("Pic_Taken_Per_Day", PicTakenScore);
                            } else {
                                Final_score_with_details.put("Pic_Taken_Per_Day", -1);
                            }
                        } else {
                            Final_score_with_details.put("Pic_Taken_Per_Day", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    VpnScore=-1;
                    Final_score_with_details.put("Vpn_Score", VpnScore);
                    try {
                        VpnModel vpnModel = new VpnModel(context.getApplicationContext());
                        VpnScore = vpnModel.vpnModel();
                        if (VpnScore >= 0) {
                            Final_score_with_details.put("Vpn_Score", VpnScore);
                        } else {
                            Final_score_with_details.put("Vpn_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }

                    CalendarEventCountUnderEmploymentScore=-1;
                    Final_score_with_details.put("Calendar_Event_Score_EmploymentModel", CalendarEventCountUnderEmploymentScore);
                    try {
                        if (calendar_permission == 1) {
                            CalendarEventCountUnderEmploymentModel calendarEventCountModel = new CalendarEventCountUnderEmploymentModel(context.getApplicationContext());
                            CalendarEventCountUnderEmploymentScore = calendarEventCountModel.calendarEventCountUnderEmploymentModel();
                            if (CalendarEventCountUnderEmploymentScore >= 0) {
                                Final_score_with_details.put("Calendar_Event_Score_EmploymentModel", CalendarEventCountUnderEmploymentScore);
                            } else {
                                Final_score_with_details.put("Calendar_Event_Score_EmploymentModel", -1);
                            }
                        } else {
                            Final_score_with_details.put("Calendar_Event_Score_EmploymentModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    CalendarEventTimeScore=-1;
                    Final_score_with_details.put("Calendar_Event_Time_Score", CalendarEventTimeScore);

                    try {
                        if (calendar_permission == 1) {
                            CalendarEventTimeModel calendarEventTimeModel = new CalendarEventTimeModel(context.getApplicationContext());
                            CalendarEventTimeScore = calendarEventTimeModel.calendarEventTimeModel();
                            if (CalendarEventTimeScore > -1) {
                                Final_score_with_details.put("Calendar_Event_Time_Score", CalendarEventTimeScore);
                            } else {
                                Final_score_with_details.put("Calendar_Event_Time_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Calendar_Event_Time_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    CalendarEventCountUnderLifeHabitsScore=-1;
                    Final_score_with_details.put("Calendar_Event_Count_Score_LifeHabitsModel", CalendarEventCountUnderLifeHabitsScore);
                    try {
                        if (calendar_permission == 1) {
                            CalendarEventCountUnderLifeHabitsModel CalendarEventCountScore = new CalendarEventCountUnderLifeHabitsModel(context.getApplicationContext());
                            CalendarEventCountUnderLifeHabitsScore = CalendarEventCountScore.calendarEventCountUnderLifeHabitsModel();
                            if (CalendarEventCountUnderLifeHabitsScore >= 0) {
                                Final_score_with_details.put("Calendar_Event_Count_Score_LifeHabitsModel", CalendarEventCountUnderLifeHabitsScore);
                            } else {
                                Final_score_with_details.put("Calendar_Event_Count_Score_LifeHabitsModel", -1);
                            }
                        } else {
                            Final_score_with_details.put("Calendar_Event_Count_Score_LifeHabitsModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    CalendarEventCountUnderSocialCapitalScore=-1;
                    Final_score_with_details.put("Calendar_Event_Count_Score_SocialCapitalModel", CalendarEventCountUnderSocialCapitalScore);
                    try {
                        if (calendar_permission == 1) {
                            CalendarEventCountUnderSocialCapitalModel calendarEventCountUnderLifeHabitsModel = new CalendarEventCountUnderSocialCapitalModel(context.getApplicationContext());
                            CalendarEventCountUnderSocialCapitalScore = calendarEventCountUnderLifeHabitsModel.calendarEventCountUnderSocialCapitalModel();
                            if (CalendarEventCountUnderSocialCapitalScore >= 0) {
                                Final_score_with_details.put("Calendar_Event_Count_Score_SocialCapitalModel", CalendarEventCountUnderSocialCapitalScore);
                            } else {
                                Final_score_with_details.put("Calendar_Event_Count_Score_SocialCapitalModel", -1);
                            }
                        } else {
                            Final_score_with_details.put("Calendar_Event_Count_Score_SocialCapitalModel", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    installed_apps_score=-1;
                    Final_score_with_details.put("Installed_Apps_Score", installed_apps_score);
                    try {
                        if (external_storage_permission == 1) {
                            AppsInstalledModel appsInstalledModel = new AppsInstalledModel(context.getApplicationContext());
                            installed_apps_score = appsInstalledModel.appsInstalledModel();
                            if (installed_apps_score >= 0) {
                                Final_score_with_details.put("Installed_Apps_Score", installed_apps_score);
                            } else {
                                Final_score_with_details.put("Installed_Apps_Score", -1);
                            }
                        } else {
                            Final_score_with_details.put("Installed_Apps_Score", -1);
                        }
                    }catch (Exception e){
                        Log.e("Scoring", e.toString());
                    }


                    location_score=-1;
                    Final_score_with_details.put("Location_Score", location_score);
                    if(location_permission == 1){
                        LocationModel locationModel = new LocationModel(context.getApplicationContext());
                        location_score = locationModel.locationModel();
                        if(location_score>=0){
                            Final_score_with_details.put("Location_Score", location_score);
                        }else{
                            Final_score_with_details.put("Location_Score", -1);
                        }
                    }else{
                        Final_score_with_details.put("Location_Score", -1);
                    }


                    without_sms_Score =
                    StorageScore
                    +outGoingCallUnderEmploymentModelScore
                    +callReceivedScore
                    +callDurationScore
                    +DeviceOSScore
                    +SocialMediaAppsScore
                    +DeviceTypeScore
                    +DeviceManufacturerScore
                    +DeviceOperatingSystemScore
                    +MusicFileScore
                    +ImageFileScore
                    +PicTakenScore
                    +VpnScore
                    +CalendarEventCountUnderLifeHabitsScore
                    +Entertainment_apps_score
                    +Number_of_contacts_score
                    +outGoingCallUnderConsumptionProfileModelScore
                    +missedCallScore
                    +CalendarEventTimeScore
                    +CalendarEventCountUnderEmploymentScore
                    +CalendarEventCountUnderSocialCapitalScore
                    +installed_apps_score
                    +received_sms_count_score
                    +sent_sms_count_score
                    +location_score
                    +300;

                    Log.d("Finalscore",String.valueOf(Finalscore));

                }catch (Exception e){
                    e.printStackTrace();
                }

            Finalscore = Finalscore + without_sms_Score;

            Final_score_with_details.put("meta_score", Finalscore);


        } catch (Exception e) {
            e.printStackTrace();
        }
        return Final_score_with_details;
    }

    /*
        Calendar_Event_Time_Score: str
        Image_File_Score: str
        Vpn_Score: str
        Installed_Apps_Score: str
        Pic_Taken_Per_Day: str
        Calendar_Event_Count_Score_LifeHabitsModel: str
        Calendar_Event_Score_EmploymentModel: str
        Calendar_Event_Count_Score_SocialCapitalModel: str
        Location_Score: str
        READ_CALL_LOG_permission: str
        */
}
