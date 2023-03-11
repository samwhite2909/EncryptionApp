package com.swhite.encryptionapp.encryption;

import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.MGF1ParameterSpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.OAEPParameterSpec;
import javax.crypto.spec.PSource;
import javax.crypto.spec.SecretKeySpec;

//Handles RSA encryption operations on a string (which will be the byte array containing an AES
//key in this case).
public class RSA {

    private static final String RSA_CIPHER_METHOD = "RSA/ECB/OAEPWithSHA-256AndMGF1Padding";

    //Encrypts the string using the public key.
    public byte[] encrypt(PublicKey key, SecretKey plainText) throws BadPaddingException,
            IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, InvalidAlgorithmParameterException {
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_METHOD);
        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256",
                "MGF1", new MGF1ParameterSpec("SHA-1"),
                PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.ENCRYPT_MODE, key, oaepParameterSpec);
        byte[] bytes = plainText.getEncoded();
        return cipher.doFinal(bytes);
    }

    //Decrypts the string using the public key.
    public SecretKey decrypt(PrivateKey key, byte[] encrypted) throws NoSuchPaddingException,
            NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
            BadPaddingException, IllegalBlockSizeException {
        Cipher cipher = Cipher.getInstance(RSA_CIPHER_METHOD);
        OAEPParameterSpec oaepParameterSpec = new OAEPParameterSpec("SHA-256",
                "MGF1", new MGF1ParameterSpec("SHA-1"),
                PSource.PSpecified.DEFAULT);
        cipher.init(Cipher.DECRYPT_MODE, key, oaepParameterSpec);
        byte[] unencrypted = cipher.doFinal(encrypted);
        return new SecretKeySpec(unencrypted, "AES");
    }
}

