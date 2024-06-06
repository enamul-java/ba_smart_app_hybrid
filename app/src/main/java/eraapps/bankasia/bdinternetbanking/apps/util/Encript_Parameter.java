package eraapps.bankasia.bdinternetbanking.apps.util;


import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;
import java.util.UUID;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import eraapps.bankasia.bdinternetbanking.apps.common.Constants;

public class Encript_Parameter {



    public static String publicKey = Constants.publickey;
   // public static String publicKey = BuildConfig.publickey;

    public static String encodeParam(String param){
        String uuid = UUID.randomUUID().toString();
        String s= uuid.replaceAll("[-]*", "");
        String id = s.substring(0, 8);
        String name=  Base64.encodeToString((id+param).getBytes(), Base64.NO_WRAP);
        return  name;
    }


    public static PublicKey getPublicKey(String base64PublicKey) {
        PublicKey publicKey = null;
        try {

            //  String img = "your_base_64_string";
            byte[] decodedString = Base64.decode(base64PublicKey, Base64.DEFAULT);
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(decodedString);
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            publicKey = keyFactory.generatePublic(keySpec);
            return publicKey;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            e.printStackTrace();
        }
        return publicKey;
    }

    public static byte[] encrypt(String data, String publicKey) throws BadPaddingException, IllegalBlockSizeException, InvalidKeyException, NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
        cipher.init(Cipher.ENCRYPT_MODE, getPublicKey(publicKey));
        return cipher.doFinal(data.getBytes());
    }

    public static String getRsa_encrypt(String paramText){
        String rValue= "";
        try {

            byte[] encrypt = encrypt(encodeParam(paramText), publicKey);
            rValue = Base64.encodeToString(encrypt, Base64.NO_WRAP);

        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        // String s =  Base64.getEncoder().encodeToString(encrypt("Hello", publicKey));

        return  rValue;
    }


}

