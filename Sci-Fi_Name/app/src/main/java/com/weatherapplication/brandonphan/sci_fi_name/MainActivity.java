package com.weatherapplication.brandonphan.sci_fi_name;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText firstNameEditText, lastNameEditText;
    Button nextButton;

    String sci_FiFirstName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firstNameEditText = (EditText) findViewById(R.id.firstNameEditText);
        lastNameEditText = (EditText) findViewById(R.id.lastNameEditText);
        nextButton = (Button) findViewById(R.id.nextButton);

        firstNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((firstNameEditText.getText().toString().length() >= 2) && (lastNameEditText.getText().toString().length() >= 2)) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

        lastNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((lastNameEditText.getText().toString().length() >= 2) && (firstNameEditText.getText().toString().length() >= 2)) {
                    nextButton.setEnabled(true);
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

    }

    public void onbuttonClick(View v) {
        SciFiName sci_FiNameGenerator = new SciFiName();
        sci_FiFirstName = sci_FiNameGenerator.generateSci_FiFirstName(firstNameEditText.getText().toString(), lastNameEditText.getText().toString());

        String key = "sci_FiFirstName";

        Intent intent = new Intent(MainActivity.this, SecondActivity.class);
        intent.putExtra(key, sci_FiFirstName);
        startActivity(intent);
    }
}
