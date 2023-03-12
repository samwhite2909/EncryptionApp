package com.swhite.encryptionapp.encryption;

import android.util.Base64;

import com.swhite.encryptionapp.di.EncryptionApplication;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import javax.inject.Inject;

//Handles the outside general encryption operations, gets the keys and encrypts/decrypts the AES
//key for relevant use cases.
public class EncryptionHandler {

    private KeyPair kp;
    private SecretKey AESKey;
    private IvParameterSpec IV;
    private PublicKey publicKey;
    private PrivateKey privateKey;
    private byte[] encKey;

    @Inject
    KeyGenerator keyGenerator;
    @Inject
    AES AES;
    @Inject
    RSA RSA;

    public EncryptionHandler() {
        try {
            keySetup();
        } catch (UnrecoverableKeyException | CertificateException | NoSuchAlgorithmException |
                 KeyStoreException | IOException | NoSuchProviderException |
                 InvalidAlgorithmParameterException | IllegalBlockSizeException |
                 InvalidKeyException
                 | NoSuchPaddingException | BadPaddingException e) {
            e.printStackTrace();
        }
    }

    //Encrypts the string using AES.
    public String encryptString(String input) throws Exception {
//        RSA.decrypt(privateKey, encKey);
        return AES.encrypt(input, AESKey, IV);
    }

    //Decrypts the string using AES.
    public String decryptString(String input) throws Exception {
        String output = AES.decrypt(input, AESKey, IV);
//        RSA.encrypt(publicKey, AESKey);
        return output;
    }


    //Calls for AES keys, an IV and the 2 RSA keys.
    private void keySetup() throws UnrecoverableKeyException, CertificateException,
            NoSuchAlgorithmException, KeyStoreException, IOException, NoSuchProviderException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, InvalidKeyException,
            NoSuchPaddingException, BadPaddingException {
        EncryptionApplication.get().encryptionComponent.inject(this);
        AESKey = keyGenerator.generateAESKey();
        IV = keyGenerator.generateIV();
        kp = keyGenerator.getKeys();
        privateKey = kp.getPrivate();
        publicKey = kp.getPublic();
        encKey = RSA.encrypt(publicKey, AESKey);
    }

    public void setAESKey(SecretKey AESKey) {
        this.AESKey = AESKey;
    }

    public void setIV(IvParameterSpec IV) {
        this.IV = IV;
    }

    //Encrypts the AES Key for database storage.
    public String AESKeyForDatabase() {
        return Base64.encodeToString(AESKey.getEncoded(), Base64.DEFAULT);
    }

    //Encrypts the IV for database storage.
    public String IVForDatabase() {
        return Base64.encodeToString(IV.getIV(), Base64.DEFAULT);
    }

    //Decrypts a Secret Key (AES Key) from a string.
    public SecretKey AESFromString(String encAES) {
        byte[] bytes = Base64.decode(encAES, Base64.DEFAULT);
        return new SecretKeySpec(bytes, "AES");
    }

    //Decrypts a IV from a string.
    public IvParameterSpec IVFromString(String encIV) {
        byte[] bytes = Base64.decode(encIV, Base64.DEFAULT);
        return new IvParameterSpec(bytes);
    }
}

