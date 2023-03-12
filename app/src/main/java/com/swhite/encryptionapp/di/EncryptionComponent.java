package com.swhite.encryptionapp.di;

import com.swhite.encryptionapp.db.OperationsAdapter;
import com.swhite.encryptionapp.encryption.EncryptionHandler;
import com.swhite.encryptionapp.ui.EncryptionActivity;
import com.swhite.encryptionapp.ui.HistoryActivity;
import com.swhite.encryptionapp.utils.DateTimeUtils;
import com.swhite.encryptionapp.utils.StringValidator;

import javax.inject.Singleton;

import dagger.Component;

//Dagger component section providing framework for dependency injection.
@Singleton
@Component(modules = EncryptionModule.class)
public interface EncryptionComponent {

    void inject (EncryptionHandler encryptionHandler);

    void inject(EncryptionActivity encryptionActivity);

    void inject(DateTimeUtils dateTimeUtils);

    void inject(OperationsAdapter operationsAdapter);

    void inject(HistoryActivity historyActivity);

    void inject(StringValidator stringValidator);
}
