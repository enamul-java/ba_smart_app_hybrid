package eraapps.bankasia.bdinternetbanking.apps.util;

import android.graphics.Bitmap;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

public class Base64Util {

    public static String convertToBase64FromBitmap(Bitmap bitmap)  {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 70, byteArrayOutputStream);
        byte [] byteArray = byteArrayOutputStream.toByteArray();
        // Get the size of the compressed image data in bytes
        int compressedImageSize = byteArray.length;
        Log.e("compressedImageSize-->",String.valueOf(compressedImageSize));
        int sizeInKB = byteArray.length / 1024;
        Log.e(" kb-->",String.valueOf(sizeInKB));
        // 6,37,101
        // 6,58,340
        //kb-->: 642
        return android.util.Base64.encodeToString(byteArray, Base64.NO_WRAP);
    }

    /*
    fun convertToBase64FromBitmap(bitmap: Bitmap): String {
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        val byteArray = byteArrayOutputStream.toByteArray()

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            return Base64.getEncoder().encodeToString(byteArray)
        }else{
            //return Base64.getEncoder().encodeToString(byteArray)
            return android.util.Base64.encodeToString(byteArray, android.util.Base64.DEFAULT)
        }

    }
     */
}



