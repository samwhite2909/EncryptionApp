package com.swhite.encryptionapp.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.swhite.encryptionapp.R;

public class MainMenuActivity extends AppCompatActivity {

    private CardView encryptionCard;
    private CardView historyCard;
    private CardView settingsCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        encryptionCard = findViewById(R.id.encryption_card);
        historyCard = findViewById(R.id.history_card);
        settingsCard = findViewById(R.id.settings_card);


        encryptionCard.setOnClickListener(v -> startActivity(new Intent(
                MainMenuActivity.this, EncryptionActivity.class)));

        historyCard.setOnClickListener(v -> startActivity(new Intent(
                MainMenuActivity.this, HistoryActivity.class)));

        settingsCard.setOnClickListener(v -> startActivity(new Intent(
                MainMenuActivity.this, SettingsActivity.class)));

    }
}