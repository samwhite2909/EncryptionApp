package com.swhite.encryptionapp.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Operation {

    @PrimaryKey(autoGenerate = true)
    public int uid;
    @ColumnInfo(name = "operation_type")
    private String operationType;
    @ColumnInfo(name = "encryption_method")
    private String encryptionMethod;
    @ColumnInfo(name = "date_time")
    private String dateTime;
    @ColumnInfo(name = "input")
    private String input;
    @ColumnInfo(name = "output")
    private String output;

    public Operation(String operationType, String encryptionMethod,
                     String dateTime, String input, String output) {
        this.operationType = operationType;
        this.encryptionMethod = encryptionMethod;
        this.dateTime = dateTime;
        this.input = input;
        this.output = output;
    }

    public int getUid() {
        return uid;
    }

    public String getOperationType() {
        return operationType;
    }

    public String getEncryptionMethod() {
        return encryptionMethod;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getInput() {
        return input;
    }

    public String getOutput() {
        return output;
    }
}
