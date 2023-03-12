package com.swhite.encryptionapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.swhite.encryptionapp.R;
import com.swhite.encryptionapp.di.EncryptionApplication;
import com.swhite.encryptionapp.encryption.EncryptionHandler;

import javax.inject.Inject;

public class EncryptionActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView resultTextView;
    private Button encryptButton;
    private Button decryptButton;

    @Inject
    EncryptionHandler encryptionHandler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        inputStringEditText = findViewById(R.id.input_string_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        encryptButton = findViewById(R.id.encrypt_button);
        decryptButton = findViewById(R.id.decrypt_button);

        EncryptionApplication.get().gpsComponent.inject(this);

        encryptButton.setOnClickListener(v -> {
            String input  = inputStringEditText.getText().toString();

            try {
                input = encryptionHandler.encryptString(input);
                Log.e("TEST123", input);
            } catch (Exception e) {
                Log.e("TEST123", e.getMessage());
                throw new RuntimeException(e);
            }

            resultTextView.setText(input);
        });

        decryptButton.setOnClickListener(v -> {

            String input = resultTextView.getText().toString();

            Log.e("TEST123", input);
            try {
                input = encryptionHandler.decryptString(input);
            } catch (Exception e) {
                Log.e("TEST123", e.getMessage());
                throw new RuntimeException(e);
            }
            resultTextView.setText(input);
        });
    }
}