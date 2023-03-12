package com.swhite.encryptionapp.di;


import androidx.room.Room;

import com.swhite.encryptionapp.db.EncryptionAppDatabase;
import com.swhite.encryptionapp.encryption.AES;
import com.swhite.encryptionapp.encryption.EncryptionHandler;
import com.swhite.encryptionapp.encryption.KeyGenerator;
import com.swhite.encryptionapp.encryption.RSA;
import com.swhite.encryptionapp.utils.DateTimeUtils;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

//Dagger dependency injection module, contains all the provides methods to inject components into
//the application.
@Module
public class EncryptionModule {

    @Singleton
    @Provides
    KeyGenerator providesKeyGenerator(){
        return new KeyGenerator();
    }

    @Singleton
    @Provides
    AES providesAES(){
        return new AES();
    }

    @Singleton
    @Provides
    RSA providesRSA(){
        return new RSA();
    }

    @Singleton
    @Provides
    EncryptionHandler providesEncryptionHandler(){
        return new EncryptionHandler();
    }

    @Singleton
    @Provides
    DateTimeUtils providesDateTimeUtils() {
        return new DateTimeUtils();
    }

    @Singleton
    @Provides
    EncryptionAppDatabase providesAppDatabase(){
        return Room.databaseBuilder(EncryptionApplication.get(), EncryptionAppDatabase.class, "db")
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build();
    }

}

