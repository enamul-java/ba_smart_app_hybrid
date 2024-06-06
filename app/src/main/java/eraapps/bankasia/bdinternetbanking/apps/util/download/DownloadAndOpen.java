package eraapps.bankasia.bdinternetbanking.apps.util.download;

import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;
import static android.provider.Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.ClipData;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Environment;
import android.util.Log;

import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

public class DownloadAndOpen {
    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static final String[] PERMISSION_STORAGE = {
            READ_EXTERNAL_STORAGE,
            WRITE_EXTERNAL_STORAGE,
    };

    File file;
    private String fileName;

    private Activity activity;

    DownloadState pdfDownloadState;

    // File url to download
    private String file_url = "";

    private DownloadState.DownLoadType downLoadType = DownloadState.DownLoadType.PDF;



    private boolean isInitializeOk  = false;

    public DownloadAndOpen(//File file,
                           String fileName,
                           Activity activity,
                           String url,
                           DownloadState.DownLoadType type,
                           DownloadState pdfDownloadState) {
        try {
            this.file = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);

            this.fileName = fileName;
            this.activity = activity;
            this.file_url = url;
            this.pdfDownloadState = pdfDownloadState;
            this.downLoadType = type;
            isInitializeOk = true;
            Log.e("Download", url);
        }catch (Exception e){
            Log.e("Download", "Download Init problem..."+e.toString());
            isInitializeOk = false;
        }
    }

    public void download(){
        if(isInitializeOk) {
            if (verifyStoragePermission()) {
                new DownloadFileFromURL(fileName, true).execute(file_url);
            }
        }else {
            Log.e("Download", "Download Init problem...");
        }
    }

    private class DownloadFileFromURL extends AsyncTask<String, String, String> {


        String errCode = "1";
        String errCodeDesc = "";
        String fileName;
        boolean isSSL;
        public DownloadFileFromURL(String fileName, boolean isSSL){
            this.fileName = fileName;
            this.isSSL = isSSL;
        }
        /**
         * Before starting background thread
         * Show Progress Bar Dialog
         * */
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pdfDownloadState.downloadStatus(DownloadState.DownLoadState.START);
            pdfDownloadState.downloadProgress(0);
        }

        /**
         * Downloading file in background thread
         * */
        @Override
        protected String doInBackground(String... f_url) {
            int count;
            URLConnection non_ssl_conection = null;
            try {
                URL url = new URL(f_url[0]);

                // getting file length
                int lenghtOfFile;
                // input stream to read file - with 8k buffer
                InputStream input;

                non_ssl_conection = url.openConnection();
                non_ssl_conection.connect();

                // getting file length
                lenghtOfFile = non_ssl_conection.getContentLength();

                // input stream to read file - with 8k buffer
                input = new BufferedInputStream(url.openStream(), 8192);


                String path = file.toString();
                Log.e("Download", path);


                try{
                    File file = new File(path+"/"+this.fileName);
                    if(file.exists()){
                        file.delete();
                    }
                }catch (Throwable e){

                }

                // Output stream to write file
                OutputStream output = new FileOutputStream(file+"/"+this.fileName);

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress(""+(int)((total*100)/lenghtOfFile));

                    // writing data to file
                    output.write(data, 0, count);
                }

                // flushing output
                output.flush();

                // closing streams
                output.close();
                input.close();
                this.errCode = "0";
            } catch (Exception e) {
                Log.e("Error: ", e.getMessage());
                errCodeDesc = e.toString();
                //showToast("Server Not Avilable!");
                //new AlertMessageCreator().createAlert(MainActivity.this, "", "Server Not Avilable!", 1);
            }finally {
                //No Close method
                if (null != non_ssl_conection) {
                    //non_ssl_conection
                }
            }

            return null;
        }

        /**
         * Updating progress bar
         * */
        protected void onProgressUpdate(String... progress) {
            // setting progress percentage
            pdfDownloadState.downloadProgress(Integer.parseInt(progress[0]));
            pdfDownloadState.downloadStatus(DownloadState.DownLoadState.DOWNLOADING);
        }

        /**
         * After completing background task
         * Dismiss the progress dialog
         * **/
        @Override
        protected void onPostExecute(String file_url) {

            pdfDownloadState.downloadStatus(DownloadState.DownLoadState.FINISH);
            pdfDownloadState.downloadProgress(100);
            if("0".equals(this.errCode)){
                try {
                    //Open File Action
                    Log.e("Download", "File Download Successful");
                    File pdfFile = new File(file.toString()+"/"+this.fileName);
                    if(downLoadType.equals(DownloadState.DownLoadType.PDF)) {
                        openPDF(pdfFile);
                    }
                } catch (Exception e) {
                    //File Open Problem
                    Log.e("Download", "File Open Problem"+e.toString());
                }
            }else{
                Log.e("Download", "File Download Problem"+errCodeDesc);
                //Error Message
            }
        }
    }

    // Access pdf from storage and using to Intent get options to view application in available applications.
    private void openPDF(File file) {

        // Get the File location and file name.
        //File file = new File(Environment.getExternalStorageDirectory(), "Download/TRENDOCEANS.pdf");
        //Log.d("pdfFIle", "" + file);

        // Get the URI Path of file.
        Uri uriPdfPath = FileProvider.getUriForFile(activity, activity.getPackageName() + ".provider", file);
        Log.d("pdfPath", "" + uriPdfPath);

        // Start Intent to View PDF from the Installed Applications.
        Intent pdfOpenIntent = new Intent(Intent.ACTION_VIEW);
        pdfOpenIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        pdfOpenIntent.setClipData(ClipData.newRawUri("", uriPdfPath));
        pdfOpenIntent.setDataAndType(uriPdfPath, "application/pdf");
        pdfOpenIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION |  Intent.FLAG_GRANT_WRITE_URI_PERMISSION);

        try {
            activity.startActivity(pdfOpenIntent);
        } catch (ActivityNotFoundException activityNotFoundException) {
            Log.e("Download", "No Pdf Viewer Found");
            unableToOpenFile();
        }
    }

    //Permissions Check
    public boolean verifyStoragePermission() {
        int permission = ActivityCompat.checkSelfPermission(activity, WRITE_EXTERNAL_STORAGE);

        // Surrounded with if statement for Android R to get access of complete file.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (!Environment.isExternalStorageManager() && permission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        (Activity) activity,
                        PERMISSION_STORAGE,
                        REQUEST_EXTERNAL_STORAGE);

                // Abruptly we will ask for permission once the application is launched for sake demo.
                pdfDownloadState.downloadStatus(DownloadState.DownLoadState.FINISH);
                showAlertToAccGrant();
                return false;
            }else{
                return true;
            }
        }else{
            return true;
        }
    }

    private void showAlertToAccGrant(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
        alertBuilder.setTitle("Manage Storage Permission Required!");
        alertBuilder.setMessage("Do you really want to allow access Manage Storage Permission?");
        alertBuilder.setCancelable(false);

        alertBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });
        alertBuilder.setPositiveButton("Allow", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();

                Intent intent = new Intent();
                intent.setAction(ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", activity.getPackageName(), null);
                intent.setData(uri);
                activity.startActivity(intent);
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }

    private void unableToOpenFile(){
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(activity);
        alertBuilder.setTitle("No Pdf Viewer Found!");
        alertBuilder.setMessage("Your statement saved in /Download Folder as "+fileName);
        alertBuilder.setCancelable(false);
        alertBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = alertBuilder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
    }
}