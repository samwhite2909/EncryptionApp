package com.swhite.encryptionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputStringEditText;
    private TextView resultTextView;
    private Button encryptButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputStringEditText = findViewById(R.id.input_string_edit_text);
        resultTextView = findViewById(R.id.result_text_view);
        encryptButton = findViewById(R.id.encrypt_button);

        encryptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input  = inputStringEditText.getText().toString();
                resultTextView.setText(input);
            }
        });
    }
}