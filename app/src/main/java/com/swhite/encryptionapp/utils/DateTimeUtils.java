package com.swhite.encryptionapp.utils;

import android.util.Log;

import com.swhite.encryptionapp.di.EncryptionApplication;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeUtils {

    public DateTimeUtils() {
        EncryptionApplication.get().encryptionComponent.inject(this);
    }

    public String getCurrentDateTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        Log.d("TEST123",dtf.format(now));
        return dtf.format(now);
    }
}
