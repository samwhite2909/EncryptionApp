package com.swhite.encryptionapp.encryption;

import static android.security.keystore.KeyProperties.KEY_ALGORITHM_AES;

import android.security.keystore.KeyGenParameterSpec;
import android.security.keystore.KeyProperties;

import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

//Handles the generation of all keys for encryption.
public class KeyGenerator {

    private static final String ALIAS = "rsa_alias5";

//        @Inject VariableStore variableStore;

    public KeyGenerator() {
//            GPSApplication.get().gpsComponent.inject(this);
    }

    //Generates a new AES key.
    public SecretKey generateAESKey() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return new SecretKeySpec(bytes, KEY_ALGORITHM_AES);
    }

    //Generates a new IV.
    public IvParameterSpec generateIV() {
        SecureRandom secureRandom = new SecureRandom();
        byte[] bytes = new byte[16];
        secureRandom.nextBytes(bytes);
        return new IvParameterSpec(bytes);
    }

    //Generates a new pair of RSA keys.
    public KeyPair generateKeyPair() throws NoSuchProviderException, NoSuchAlgorithmException,
            InvalidAlgorithmParameterException, IOException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(
                KeyProperties.KEY_ALGORITHM_RSA, "AndroidKeyStore");
        keyPairGenerator.initialize(new KeyGenParameterSpec.Builder(
                ALIAS,
                KeyProperties.PURPOSE_DECRYPT | KeyProperties.PURPOSE_ENCRYPT)
                .setDigests(KeyProperties.DIGEST_SHA256, KeyProperties.DIGEST_SHA512)
                .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_RSA_OAEP)
                .build()
        );
        return keyPairGenerator.generateKeyPair();
    }

    //If RSA keys exist, it gets them, if not, new ones are created.
    public KeyPair getKeys() throws NoSuchAlgorithmException, IOException, KeyStoreException,
            UnrecoverableKeyException, CertificateException, NoSuchProviderException,
            InvalidAlgorithmParameterException {
        KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
        keyStore.load(null);
        if (!keyStore.containsAlias(ALIAS)) {
            return generateKeyPair();
        }
        //Gets the RSA keys from the Android Keystore.
        PrivateKey privateKey = (PrivateKey) keyStore.getKey(ALIAS, null);
        PublicKey publicKey = keyStore.getCertificate(ALIAS).getPublicKey();
        return new KeyPair(publicKey, privateKey);
    }
}

