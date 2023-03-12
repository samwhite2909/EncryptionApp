package com.swhite.encryptionapp.utils;

import com.swhite.encryptionapp.di.EncryptionApplication;

public class StringValidator {

    public StringValidator() {
        EncryptionApplication.get().encryptionComponent.inject(this);
    }

    public boolean checkStringIsNotEmpty(String input) {
        return input.trim().isEmpty();
    }
}
