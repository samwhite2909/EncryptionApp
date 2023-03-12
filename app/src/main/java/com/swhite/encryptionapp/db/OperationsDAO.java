package com.swhite.encryptionapp.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.swhite.encryptionapp.models.Operation;

import org.json.JSONException;

import java.util.List;

@Dao
public interface OperationsDAO {

    //Gets a list of all items.
    @Query("SELECT * FROM operation")
    List<Operation> getAllItems();

    //Adds items.
    @Insert
    void insertAll(Operation... operations) throws JSONException;

    //Deletes a specific item.
    @Delete
    void delete(Operation operation);
}

