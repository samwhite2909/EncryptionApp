package com.swhite.encryptionapp.encryption;


import android.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

//Allows for encryption and decryption using AES when given a String, Secret Key and an
//Initialisation Vector.
public class AES {

    private String method = "AES/CBC/PKCS7Padding";

    //Encrypts the string.
    public String encrypt(String input, SecretKey AESKey, IvParameterSpec IV1) throws Exception {
        Cipher c = Cipher.getInstance(method);
        c.init(Cipher.ENCRYPT_MODE, AESKey, IV1);
        byte[] encVal = c.doFinal(input.getBytes());
        return Base64.encodeToString(encVal, Base64.DEFAULT);
    }

    //Decrypts the string.
    public String decrypt(String outputString, SecretKey AESKey, IvParameterSpec IV1)
            throws Exception {
        Cipher c = Cipher.getInstance(method);
        c.init(Cipher.DECRYPT_MODE, AESKey, IV1);
        byte[] decVal = Base64.decode(outputString, Base64.DEFAULT);
        byte[] decValue = c.doFinal(decVal);
        return new String(decValue);
    }
}
