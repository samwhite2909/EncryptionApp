package com.swhite.encryptionapp.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.swhite.encryptionapp.models.Operation;


@Database(entities = {Operation.class}, version = 2, exportSchema = false)
public abstract class EncryptionAppDatabase extends RoomDatabase {
    //Used for performing operations on items.
    public abstract OperationsDAO operationsDAO();

}
