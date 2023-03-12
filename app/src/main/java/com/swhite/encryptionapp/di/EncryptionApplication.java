package com.swhite.encryptionapp.di;

import android.app.Application;

//Used for dagger instantiation, allows easy getting of the context anywhere within the application.
public class EncryptionApplication extends Application {

    public EncryptionComponent encryptionComponent;
    public static EncryptionApplication instance;

    public static EncryptionApplication get() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        encryptionComponent = DaggerEncryptionComponent.create();
        instance = this;
    }
}

