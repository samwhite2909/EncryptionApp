package com.swhite.encryptionapp.di;


import com.swhite.encryptionapp.encryption.AES;
import com.swhite.encryptionapp.encryption.EncryptionHandler;
import com.swhite.encryptionapp.encryption.KeyGenerator;
import com.swhite.encryptionapp.encryption.RSA;

import dagger.Module;
import dagger.Provides;

//Dagger dependency injection module, contains all the provides methods to inject components into
//the application.
@Module
public class EncryptionModule {

    @Provides
    KeyGenerator providesKeyGenerator(){
        return new KeyGenerator();
    }

    @Provides
    AES providesAES(){
        return new AES();
    }

    @Provides
    RSA providesRSA(){
        return new RSA();
    }

    @Provides
    EncryptionHandler providesEncryptionHandler(){
        return new EncryptionHandler();
    }


}

