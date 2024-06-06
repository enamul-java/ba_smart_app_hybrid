package eraapps.bankasia.bdinternetbanking.apps.util;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.kaopiz.kprogresshud.KProgressHUD;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import eraapps.bankasia.bdinternetbanking.apps.R;
import eraapps.bankasia.bdinternetbanking.apps.alert.SweetAlertDialog;
import eraapps.bankasia.bdinternetbanking.apps.presentation.home.remote.login.screen.LoginActivity;


public class CustomAlert {

    public static SweetAlertDialog showProgressDialog(Activity activity, String languagecode) {
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        if (TextContants.banglaLanguageCode.equals(languagecode)) {
            pDialog.setTitleText("অনুগ্রহপূর্বক অপেক্ষা করুন...");
        } else {
            pDialog.setTitleText("Please wait...");
        }
        pDialog.setCanceledOnTouchOutside(false);
        pDialog.setCancelable(false);
        return pDialog;

    }

    public static SweetAlertDialog showProgressDialog(Activity activity) {
        SweetAlertDialog pDialog = new SweetAlertDialog(activity, SweetAlertDialog.PROGRESS_TYPE);
        pDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        pDialog.setTitleText("Please wait...");
        pDialog.setCanceledOnTouchOutside(false);
        return pDialog;

    }

    public static KProgressHUD showProgressDialog1(Activity activity, String languagecode) {
        // KProgressHUD pDialog = new KProgressHUD(activity);
        if (TextContants.banglaLanguageCode.equals(languagecode)) {
            return KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("অনুগ্রহপূর্বক অপেক্ষা করুন...")
                    .setWindowColor(Color.parseColor("#777777"))
                    .setDetailsLabel("")
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(1.5f);
        } else {
            return KProgressHUD.create(activity)
                    .setStyle(KProgressHUD.Style.SPIN_INDETERMINATE)
                    .setLabel("Please wait...")
                    .setWindowColor(Color.parseColor("#777777"))
                    .setDetailsLabel("")
                    .setCancellable(false)
                    .setAnimationSpeed(2)
                    .setDimAmount(1.5f);
        }

        // .show();

        // return pDialog;

    }


    public static void showInternetConnectionMessage(Activity activity, String languageCode) {
        if (TextContants.banglaLanguageCode.equals(languageCode)) {
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("ইন্টারনেট কানেকশান ").setContentText("কোনো ইন্টারনেট সংযোগ নেই!").show();

        } else {
            new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                    .setTitleText("Internet Connection").setContentText("Please turn on your internet connection and try again").show();
        }

    }

    public static void showErrorMessage(Activity activity, String message, String languageCode) {

        if (TextContants.banglaLanguageCode.equals(languageCode)) {
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("সতর্ক!").setContentText(message).show();
        } else {
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Attention!").setContentText(message).show();
        }


    }

    public static void showErrorMessage(Activity activity, String engMessage,String bngMessage, String languageCode) {

        if (TextContants.banglaLanguageCode.equals(languageCode)) {
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("সতর্ক!").setContentText(bngMessage).show();
        } else {
            new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("Attention!").setContentText(engMessage).show();
        }


    }

    public static void showNetworkErrorMessage(Activity activity, String message) {
        new SweetAlertDialog(activity, SweetAlertDialog.WARNING_TYPE)
                .setTitleText("Attention!").setContentText(message).show();
    }

    public static void showSuccessMessage(Activity activity, String message, String languageCode) {
        if (TextContants.banglaLanguageCode.equals(languageCode)) {
            new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("অভিনন্দন.").setContentText(message).show();
        } else {
            new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("Successful").setContentText(message).show();
        }

    }

    public static void showOTP(Activity activity, String message, String languageCode) {
        if (!((activity)).isFinishing()) {
            if ("BAN".equals(languageCode)) {
                new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("ওয়ান টাইম পাসওয়ার্ড")
                        .setContentText(message)
                        .show();

            } else {

                new SweetAlertDialog(activity, SweetAlertDialog.SUCCESS_TYPE)
                        .setTitleText("OTP")
                        .setContentText(message)
                        .show();
            }
        }
    }

    public static void showOTPnew(Activity activity, String languageCode) {
        if (!((activity)).isFinishing()) {
            if ("BAN".equals(languageCode)) {
                new SweetAlertDialog(activity)
                        .setTitleText("ওটিপি")
                        .setContentText("আপনার মোবাইলে/ইমেলে ওটিপি পাঠানো হয়েছে।")
                        .show();

            } else {

                new SweetAlertDialog(activity)
                        .setTitleText("One Time Password(OTP)")
                        .setContentText("OTP Sent to Your Mobile/Email.")
                        .show();
            }
        }
    }

    public static void showComingSoon(Activity activity, String message, String languageCode) {
        if ("BAN".equals(languageCode)) {
            new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("শীঘ্রই আসছে...")
                    .setContentText(message).show();

        } else {

            new SweetAlertDialog(activity, SweetAlertDialog.NORMAL_TYPE)
                    .setTitleText("Coming Soon...")
                    .setContentText(message).show();
        }

    }

    public static boolean isOnline(Activity activity) {
        ConnectivityManager cm = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnected()) {
            return true;
        } else {
            return false;
        }
    }

    public static void clearActivity(Activity from, Activity to, String languageCode) {

        englishclearActivity(from, to);
    }

    public static void englishclearActivity(Activity from, Activity to) {

        Intent intent = new Intent(from, to.getClass());
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
        intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
        intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
        from.startActivity(intent);
        from.finish();


    }

    public static void logout(Activity activity, GlobalVariable globalVariable) {

        englishLogout(activity, globalVariable);
    }

    public static void appClose(Activity activity, String languageCode) {

        englishAppclose(activity, languageCode);
    }

    public static void englishLogout(Activity activity, GlobalVariable globalVariable) {

        final android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(activity).setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View reg_layout = inflater.inflate(R.layout.logout_dialog, null);

        final Button btn_cancel = reg_layout.findViewById(R.id.btn_cancel);
        final Button btn_submit = reg_layout.findViewById(R.id.btn_submit);
        final ImageView menu_icon = reg_layout.findViewById(R.id.menu_icon);
        final TextView menu_item_description = reg_layout.findViewById(R.id.menu_item_description);
        final TextView menu_item_name = reg_layout.findViewById((R.id.menu_item_name));


        dialog.setView(reg_layout);
        final android.app.AlertDialog alertDialog = dialog.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        if (TextContants.banglaLanguageCode.equals(globalVariable.getLanguageCode())) {
            menu_item_description.setText(R.string.log_out_title_bagnla);
            menu_item_name.setText(R.string.log_out_bangla);
            btn_cancel.setText(R.string.cancel_bangla);
            btn_submit.setText(R.string.log_out_bangla);
        }

        btn_submit.setOnClickListener(v -> {


                    Intent intent = new Intent(activity, LoginActivity.class);
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                    intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                    intent.setFlags(FLAG_ACTIVITY_CLEAR_TOP); // To clean up all activities
                    activity.startActivity(intent);
                    //activity.finish();
                    globalVariable.setSessionId("");

                    activity.finishAffinity();
                }

        );


        btn_cancel.setOnClickListener(v -> alertDialog.cancel());

        alertDialog.show();


    }


    public static void englishAppclose(Activity activity, String languageCode) {

        final android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(activity).setCancelable(false);
        LayoutInflater inflater = LayoutInflater.from(activity);
        View reg_layout = inflater.inflate(R.layout.logout_dialog, null);

        final Button btn_cancel = reg_layout.findViewById(R.id.btn_cancel);
        final Button btn_submit = reg_layout.findViewById(R.id.btn_submit);
        final ImageView menu_icon = reg_layout.findViewById(R.id.menu_icon);
        final TextView menu_item_description = reg_layout.findViewById(R.id.menu_item_description);
        final TextView menu_item_name = reg_layout.findViewById((R.id.menu_item_name));


        dialog.setView(reg_layout);
        final android.app.AlertDialog alertDialog = dialog.create();
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        menu_icon.setImageResource(R.drawable.balance_icon);

        if (TextContants.banglaLanguageCode.equals(languageCode)) {
            menu_item_description.setText(R.string.exit_title_bagnla);
            menu_item_name.setText(R.string.lb_exit_bangla);
            btn_cancel.setText(R.string.cancel_bangla);
            btn_submit.setText(R.string.lb_exit_bangla);
        } else {
            menu_item_description.setText(R.string.exit_title);
            menu_item_name.setText(R.string.lb_exit);
            btn_cancel.setText(R.string.cancel);
            btn_submit.setText(R.string.lb_exit);
        }

        btn_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                activity.finishAffinity();
            }
        });


        btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.cancel();
            }
        });

        alertDialog.show();

    }

    public static void showOK(final Activity activity, String message, String languageCode) {
        if (!((activity)).isFinishing()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity);
            if ("BAN".equals(languageCode)) {
                sweetAlertDialog
                        .setTitleText(TextContants.successbangla)
                        .setContentText(message)
                        .setConfirmText("ঠিক আছে")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                Intent intent = activity.getIntent();
                                activity.startActivity(intent);

                            }
                        })
                        .setContentText(message).show();

            } else {

                sweetAlertDialog
                        .setTitleText(TextContants.success)
                        .setContentText(message)
                        .setConfirmText("OK")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                //sDialog.dismissWithAnimation();
                                Intent intent = activity.getIntent();
                                activity.startActivity(intent);
                            }
                        })
                        .show();
            }
        }

    }

    public static void showOKFragment(final Activity activity, String position, String message, String languageCode) {
        if (!((activity)).isFinishing()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(activity);
            if ("BAN".equals(languageCode)) {
                sweetAlertDialog
                        .setTitleText(TextContants.successbangla)
                        .setContentText(message)
                        .setConfirmText("ঠিক আছে")
                        .setConfirmClickListener(sDialog -> {

                            Intent intent = activity.getIntent();
                            intent.putExtra("BENE", position);
                            activity.startActivity(intent);


                        })
                        .setContentText(message).show();

            } else {

                sweetAlertDialog
                        .setTitleText(TextContants.success)
                        .setContentText(message)
                        .setConfirmText("OK")
                        .setConfirmClickListener(sDialog -> {

                            Intent intent = activity.getIntent();
                            intent.putExtra("BENE", position);
                            activity.startActivity(intent);
                        })
                        .show();
            }
        }

    }

    public static void successBackToActivity(final Activity fromActivity, final Intent intent, String message, String languageCode) {
        if (!((fromActivity)).isFinishing()) {
            SweetAlertDialog sweetAlertDialog = new SweetAlertDialog(fromActivity);
            if ("BAN".equals(languageCode)) {
                sweetAlertDialog
                        .setTitleText(TextContants.successbangla)
                        .setContentText(message)
                        .setConfirmText("ঠিক আছে")
                        .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                            @Override
                            public void onClick(SweetAlertDialog sDialog) {
                                fromActivity.startActivity(intent);
                            }
                        })
                        .setContentText(message).show();

            } else {

                sweetAlertDialog
                        .setTitleText(TextContants.success)
                        .setContentText(message)
                        .setConfirmText("OK")
                        .setConfirmClickListener(sDialog -> {
                            //sDialog.dismissWithAnimation();

                            fromActivity.startActivity(intent);
                        })
                        .show();
            }
        }

    }

    public static Bitmap getBitmapfromUrl(String imageUrl) {
        try {
            URL url = new URL(imageUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            return BitmapFactory.decodeStream(input);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getRetrofit_NullCheck(String s) {
        String rValue = "";
        if (s == null || s.isEmpty() || s.endsWith("null")) {
            rValue = "";
        } else {
            rValue = s;
        }
        return rValue;
    }

}
