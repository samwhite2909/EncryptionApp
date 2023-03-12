package com.swhite.encryptionapp.di;

import com.swhite.encryptionapp.encryption.EncryptionHandler;
import com.swhite.encryptionapp.ui.EncryptionActivity;

import javax.inject.Singleton;

import dagger.Component;

//Dagger component section providing framework for dependency injection.
@Singleton
@Component(modules = EncryptionModule.class)
public interface EncryptionComponent {

    void inject (EncryptionHandler encryptionHandler);

    void inject(EncryptionActivity encryptionActivity);
}
