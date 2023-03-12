package com.swhite.encryptionapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.swhite.encryptionapp.R;
import com.swhite.encryptionapp.db.EncryptionAppDatabase;
import com.swhite.encryptionapp.db.OperationsAdapter;
import com.swhite.encryptionapp.di.EncryptionApplication;
import com.swhite.encryptionapp.models.Operation;

import java.util.List;

import javax.inject.Inject;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    @Inject
    EncryptionAppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        EncryptionApplication.get().encryptionComponent.inject(this);

        recyclerView = findViewById(R.id.recycler_view);

        //Gets a list of all the logs from the database.
        List<Operation> logItems = db.operationsDAO().getAllItems();

        //Places the list of items into the recycler view.
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new OperationsAdapter(logItems);
        recyclerView.setAdapter(adapter);

    }
}