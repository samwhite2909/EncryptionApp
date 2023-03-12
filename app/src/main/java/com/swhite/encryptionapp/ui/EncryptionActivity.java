package com.swhite.encryptionapp.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.swhite.encryptionapp.R;
import com.swhite.encryptionapp.constants.EncryptionMethods;
import com.swhite.encryptionapp.constants.OperationTypes;
import com.swhite.encryptionapp.db.EncryptionAppDatabase;
import com.swhite.encryptionapp.di.EncryptionApplication;
import com.swhite.encryptionapp.encryption.EncryptionHandler;
import com.swhite.encryptionapp.models.Operation;
import com.swhite.encryptionapp.utils.DateTimeUtils;

import org.json.JSONException;

import javax.inject.Inject;

public class EncryptionActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView resultTextView;
    private Button encryptButton;
    private Button decryptButton;

    @Inject
    EncryptionHandler encryptionHandler;
    @Inject
    DateTimeUtils dateTimeUtils;

    @Inject
    EncryptionAppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_encryption);

        inputStringEditText = findViewById(R.id.input_string_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        encryptButton = findViewById(R.id.encrypt_button);
        decryptButton = findViewById(R.id.decrypt_button);

        EncryptionApplication.get().encryptionComponent.inject(this);

        encryptButton.setOnClickListener(v -> {
            Toast.makeText(this, R.string.encrypting_text, Toast.LENGTH_SHORT).show();
            String input  = inputStringEditText.getText().toString();
            String output = "";

            try {
                output = encryptionHandler.encryptString(input);
            } catch (Exception e) {
                Log.e("TEST123", e.getMessage());
                throw new RuntimeException(e);
            }

            resultTextView.setText(output);

            saveToDatabase(createOperationForHistory(OperationTypes.ENCRYPTION,
                    input.trim(), output.trim()));

        });

        decryptButton.setOnClickListener(v -> {
            Toast.makeText(this, R.string.decrypting_text, Toast.LENGTH_SHORT).show();

            String input = resultTextView.getText().toString();
            String output = "";

            try {
                output = encryptionHandler.decryptString(input);
            } catch (Exception e) {
                Log.e("TEST123", e.getMessage());
                throw new RuntimeException(e);
            }
            resultTextView.setText(output);

            saveToDatabase(createOperationForHistory(OperationTypes.DECRYPTION,
                    input.trim(), output.trim()));
        });
    }

    private Operation createOperationForHistory(String type, String input, String output) {
        return new Operation(
                type, EncryptionMethods.AES_CBC_PKCS7,
                dateTimeUtils.getCurrentDateTime(), input, output);
    }

    private void saveToDatabase(Operation operation) {
        try {
            db.operationsDAO().insertAll(operation);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}