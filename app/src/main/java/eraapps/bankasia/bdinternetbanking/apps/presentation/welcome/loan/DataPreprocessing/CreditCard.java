package eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.DataPreprocessing;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;

import eraapps.bankasia.bdinternetbanking.apps.presentation.welcome.loan.scoring_model.SMSModel;

public class CreditCard extends Thread{
    public HashMap<String, HashMap<String, String>> creditCard(ArrayList<SMSModel> SMSArrayList)
    {
        String Sender;
        String Msg;
        String FormattedDate;
        String PhoneNumber;
        String Date;
        String key = "null";
        int flag = 0;

        HashMap<String, HashMap<String, String>> CREDIT_DEBIT = new HashMap<String, HashMap<String, String>>();

        HashMap<String, String> credited_amount_list = new HashMap<String, String>();
        HashMap<String, String> card_balance_list = new HashMap<String, String>();
        HashMap<String, String> salary_list = new HashMap<String, String>();
        HashMap<String, String> closing_balance_list = new HashMap<String, String>();
        HashMap<String, String> minimum_due_list = new HashMap<String, String>();
        HashMap<String, String> debited_amount_list = new HashMap<String, String>();
        HashMap<String, String> credit_card_monthly_expense_list = new HashMap<String, String>();

        try{
            // Dutch Bangla Bank Outstanding balance and Minimum Due code:
            Msg = "Dear Sir, your DBBL VISA Platinum TQ Card #8443 bill for Oct-22 is : BDT 136,883.44, Min Payment: BDT 6,844.18,Due date: 14-Nov-2022";
            Date = "05/10/2022";
            Msg = Msg.toLowerCase();

            if ((Msg.contains("dbbl visa platinum tq card")) && (Msg.contains("bill for")) && (Msg.contains("min payment:"))) {
                String outstanding_dbbl = "";
                String minimum_due_dbbl = "";
                int BDT_flag = 0;
                String[] splits = Msg.split(" ");
                //Log.d("LblfMsg", Msg+ " "+Date);
                for (String split : splits) {
                    //Log.d("Msg", "MsgSplit:"+split);
                    if (key.equals("bdt") && BDT_flag == 1){
                        //Log.d("salary_list", "balance:" + split + " Date:" + Date);
                        split = split.replace(",","");
                        for(int i =0;i<=split.length();i++)
                        {
                            boolean flag_LBFL_debit = Character.isDigit(split.charAt(i));
                            if(split.charAt(i)=='.')
                            {
                                break;
                            }
                            if(flag_LBFL_debit) {
                                outstanding_dbbl = outstanding_dbbl + split.charAt(i);
                            }
                        }
                        Log.d("dbbl","outstanding_dbbl "+outstanding_dbbl);
                        closing_balance_list.put(outstanding_dbbl,Date);
                        key = "null";
                    }
                    if (key.equals("bdt") && BDT_flag == 2) {
                        split = split.replace(",","");
                        for(int i =0;i<=split.length();i++)
                        {
                            boolean flag_LBFL_debit = Character.isDigit(split.charAt(i));
                            if(split.charAt(i)=='.')
                            {
                                break;
                            }
                            if(flag_LBFL_debit) {
                                minimum_due_dbbl = minimum_due_dbbl + split.charAt(i);
                            }
                        }
                        Log.d("dbbl","minimum_due_dbbl "+minimum_due_dbbl);
                        minimum_due_list.put(minimum_due_dbbl,Date);
                        key = "null";
                        break;
                    }
                    if (split.equals("bdt")) {
                        key = "bdt";
                        BDT_flag = BDT_flag + 1;
                    }
                }
            }




// LankaBangla Finance Limited Debit and Current Balance Code:
            Msg = "Tk8,000.00 paid to Lankabangla Finance Ltd_Credit Card (NexusPay) Id 4108 Bill No 5342730182336501 TxnId:3292237844 Date:19-OCT-22 10:50:43 am.";
            Date = "05/10/2022";
            Msg = Msg.toLowerCase();
            if ((Msg.contains("paid")) && ((Msg.contains("lankabangla finance ltd_credit card")))) {
                String credit_card_payment_LBFL = "";
                String[] splits = Msg.split(" ");
//                Log.d("LBFL","String0" + splits[0]);
                splits[0] = splits[0].replace(",","");
                for(int i =0;i<=splits[0].length();i++)
                {
                    boolean flag_LBFL_credit_card_payment = Character.isDigit(splits[0].charAt(i));
                    if(splits[0].charAt(i)=='.')
                    {
                        break;
                    }
                    if(flag_LBFL_credit_card_payment) {
                        credit_card_payment_LBFL = credit_card_payment_LBFL + splits[0].charAt(i);
                    }
                }
                Log.d("LBFL","credit_card_payment_LBFL "+credit_card_payment_LBFL);
                credit_card_monthly_expense_list.put(credit_card_payment_LBFL,Date);
                key = "null";
            }





// LankaBangla Finance Limited Debit and Current Balance Code:
            Msg = "Dear Sir, your A/C ***0740 debited (Fund Transfer Debit) by Tk8,020.00 on 19-10-2022 10:50:43 AM C/B Tk673.50. NexusPay https://bit.ly/nexuspay";
            Date = "05/10/2022";
            Msg = Msg.toLowerCase();

            if ((Msg.contains("debited")) && (Msg.contains("fund transfer debit")) && (Msg.contains("nexuspay"))) {
                String debit_LBFL = "";
                String LBFL_Current_balance = "";
                String[] splits = Msg.split(" ");
                //Log.d("LblfMsg", Msg+ " "+Date);
                for (String split : splits) {
                    //Log.d("Msg", "MsgSplit:"+split);
                    if (key.equals("by")){
                        //Log.d("salary_list", "balance:" + split + " Date:" + Date);
                        split = split.replace(",","");
                        for(int i =0;i<=split.length();i++)
                        {
                            boolean flag_LBFL_debit = Character.isDigit(split.charAt(i));
                            if(split.charAt(i)=='.')
                            {
                                break;
                            }
                            if(flag_LBFL_debit) {
                                debit_LBFL = debit_LBFL + split.charAt(i);
                            }
                        }
//                        Log.d("LBFL","debit_LBFL "+debit_LBFL);
                        debited_amount_list.put(debit_LBFL,Date);
                        key = "null";
                    }
                    if (key.equals("c/b")) {
//                        split = split.substring(8, split.length());
//                        Log.d("LBFL", "balance:" + split + " Date:" + Date);
                        split = split.replace(",","");
                        for(int i =0;i<=split.length();i++)
                        {
                            boolean flag_LBFL_debit = Character.isDigit(split.charAt(i));
                            if(split.charAt(i)=='.')
                            {
                                break;
                            }
                            if(flag_LBFL_debit) {
                                LBFL_Current_balance = LBFL_Current_balance + split.charAt(i);
                            }
                        }
//                        Log.d("LBFL","LBFL_Current_balance "+LBFL_Current_balance);
                        card_balance_list.put(LBFL_Current_balance,Date);
                        key = "null";
                        break;
                    }
                    if (split.equals("by")) {
                        key = "by";
                    }
                    if (split.equals("c/b")) {
                        key = "c/b";
                    }
                }
            }




            for (int i = 0; i < SMSArrayList.size(); i++) {
//                Log.d("creditCard", "Sender: " + SMSArrayList.get(i).getSender());
//                Log.d("creditCard", "Msg: " + SMSArrayList.get(i).getMsg());
//                Log.d("creditCard", "FormattedDate: " + SMSArrayList.get(i).getFormattedDate());
//                Log.d("creditCard", "PhoneNumber: " + SMSArrayList.get(i).getPhoneNumber());

                Sender = SMSArrayList.get(i).getSender().toLowerCase();
                Msg = SMSArrayList.get(i).getMsg().toLowerCase();
                FormattedDate = SMSArrayList.get(i).getFormattedDate();
                PhoneNumber = SMSArrayList.get(i).getPhoneNumber();
                Date = FormattedDate.substring(9, 19);

                if ((Msg.contains("credited")) && (Msg.contains("bank asia")) && (Msg.contains("salary"))) {
                    String[] splits = Msg.split(" ");
                    //Log.d("Msg", Msg+ " "+Date);
                    for (String split : splits) {
                        //Log.d("Msg", "MsgSplit:"+split);
                        if (key.equals("bdt")){
                            //Log.d("salary_list", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            salary_list.put(Date, split);
                            credited_amount_list.put(Date,split);
                            key = "null";
                        }
                        if (key.equals("current")) {
                            split = split.substring(8, split.length());
                            //Log.d("card_balance_list", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            card_balance_list.put(split, Date);
                            key = "null";
                        }
                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                        if (split.equals("current")) {
                            key = "current";
                        }
                    }
                }

                else if ((Msg.contains("purchased")) && (Msg.contains("bank asia card")) && (Msg.contains("available"))) {
                    String[] splits = Msg.split(" ");
                    //Log.d("credit_card_monthly_expense_list", Msg+ " "+Date);
                    for (String split : splits) {
//                        Log.d("credit_card_monthly_expense_list", "MsgSplit:"+split);
                        if (key.equals("bdt")){
                            //Log.d("credit_card_monthly_expense_list", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            credit_card_monthly_expense_list.put(Date,split);
                            key = "null";
                            break;
                        }
                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                    }
                }


                else if ((Msg.contains("credited")) && (Msg.contains("bank asia")) && (!Msg.contains("salary"))) {
                    String[] splits = Msg.split(" ");
                    for (String split : splits) {
                        //Log.d("Msg", "MsgSplit:"+split);
                        if (key.equals("bdt")){
                            //Log.d("credited_amount_list", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            credited_amount_list.put(Date,split);
                            key = "null";
                        }
                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                        if (key.equals("current")){
                            split = split.substring(8, split.length());
                            //Log.d("card_balance_list", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            card_balance_list.put(split,Date);
                            key = "null";
                        }
                        if (split.equals("current")) {
                            key = "current";
                        }
                    }
                }
                else if ((Msg.contains("debited")) && (Msg.contains("bank asia"))) {
                    String[] splits = Msg.split(" ");
                    for (String split : splits) {
                        //Log.d("Msg", "MsgSplit:"+split);
                        if (key.equals("bdt")){
                            split = split.replace(",","");
                            //Log.d("debited_amount_list", "amount:" + split + " Date:" + Date);
                            debited_amount_list.put(split,Date);
                            key = "null";
                        }
                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                        if (key.equals("current")){
                            split = split.substring(8, split.length());
                            split = split.replace(",","");
                            //Log.d("card_balance_list", "balance:" + split + " Date:" + Date);
                            card_balance_list.put(split,Date);
                            key = "null";
                        }
                        if (split.equals("current")) {
                            key = "current";
                        }
                    }
                }
                else if ((Msg.contains("credited")) && (Sender.contains("one bank"))) {
                    flag = 0;
                    String[] splits = Msg.split(" ");
                    for (String split : splits) {
                        //Log.d("onebank", "MsgSplit:"+split);
                        if ((key.equals("bdt")) && (flag == 0)){
                            split = split.substring(0, split.length() - 1);
                            //Log.d("onebank", "credited_amount:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            credited_amount_list.put(Date,split);
                            key = "null";
                            flag = 1;
                        }
                        if ((key.equals("bdt")) && (flag == 1)){
                            split = split.substring(0, split.length() - 6);
                            //Log.d("onebank", "balance:" + split + " Date:" + Date);
                            split = split.replace(",","");
                            card_balance_list.put(split,Date);
                            key = "null";
                            flag = 0;
                        }

                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                    }
                }
                else if ((Msg.contains("debited")) && (Msg.contains("using cheque")) && (Sender.contains("one bank"))) {
                    flag = 0;
                    String[] splits = Msg.split(" ");
                    for (String split : splits) {
                        //Log.d("onebank", "MsgSplit:"+split);
                        if ((key.equals("bdt")) && (flag == 0)){
                            split = split.replace(",","");
                            //Log.d("onebank", "debited_amount:" + split + " Date:" + Date);
                            debited_amount_list.put(split,Date);
                            key = "null";
                            flag = 1;
                        }
                        if ((key.equals("bdt")) && (flag == 1)){
                            split = split.substring(0, split.length() - 8);
                            split = split.replace(",","");
                            //Log.d("onebank", "balance:" + split + " Date:" + Date);
                            card_balance_list.put(split,Date);
                            key = "null";
                            flag = 0;
                        }

                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                    }
                }
                else if ((Msg.contains("debited")) && (!Msg.contains("using cheque")) && (Sender.contains("one bank"))) {
                    flag = 0;
                    String[] splits = Msg.split(" ");
                    for (String split : splits) {
                        //Log.d("onebank", "MsgSplit:"+split);
                        if ((key.equals("bdt")) && (flag == 0)){
                            split = split.substring(0, split.length() - 5);
                            split = split.replace(",","");
                            //Log.d("onebank", "debited_amount:" + split + " Date:" + Date);
                            debited_amount_list.put(split,Date);
                            key = "null";
                            flag = 1;
                        }
                        if ((key.equals("bdt")) && (flag == 1)){
                            split = split.substring(0, split.length() - 6);
                            split = split.replace(",","");
                            //Log.d("onebank", "balance:" + split + " Date:" + Date);
                            card_balance_list.put(split,Date);
                            key = "null";
                            flag = 0;
                        }

                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                    }
                }
                else if ((Msg.contains("closing balance")) && (Msg.contains("bank asia card"))) {
                    flag = 0;
                    String[] splits = Msg.split(" ");
                    //Log.d("closing", "MsgSplit:"+Msg);
                    for (String split : splits) {

                        if ((key.equals("bdt")) && (flag == 0)){
                            split = split.replace(",","");
                            //Log.d("closing_balance_list", "closing_balance:" + split + " Date:" + Date);
                            closing_balance_list.put(split,Date);
                            key = "null";
                            flag = 1;
                        }
                        if ((key.equals("bdt")) && (flag == 1)){
                            split = split.replace(",","");
                            //Log.d("minimum_due_list", "minimum_due:" + split + " Date:" + Date);
                            minimum_due_list.put(split,Date);
                            key = "null";
                            flag = 0;
                        }
                        if (split.equals("bdt")) {
                            key = "bdt";
                        }
                    }
                }
            }
            Log.d("credited_amount_list", credited_amount_list.toString());
            Log.d("card_balance_list", card_balance_list.toString());
            Log.d("salary_list", salary_list.toString());
            Log.d("closing_balance_list", closing_balance_list.toString());
            Log.d("minimum_due_list", minimum_due_list.toString());
            Log.d("debited_amount_list", debited_amount_list.toString());

            CREDIT_DEBIT.put("credited_amount_list",credited_amount_list);
            CREDIT_DEBIT.put("card_balance_list",card_balance_list);
            CREDIT_DEBIT.put("salary_list",salary_list);
            CREDIT_DEBIT.put("closing_balance_list",closing_balance_list);
            CREDIT_DEBIT.put("minimum_due_list",minimum_due_list);
            CREDIT_DEBIT.put("debited_amount_list",debited_amount_list);
            CREDIT_DEBIT.put("credit_card_monthly_expense_list",credit_card_monthly_expense_list);

        }catch (Exception e){
            e.printStackTrace();
        }
        return CREDIT_DEBIT;
    }
}
