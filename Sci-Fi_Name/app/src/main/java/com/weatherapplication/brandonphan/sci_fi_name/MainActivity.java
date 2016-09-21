package com.weatherapplication.brandonphan.sci_fi_name;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText firstNameEditText;
    EditText lastNameEditText;
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
                if (lastNameEditText.getText().toString().length() >= 2) {
                    if (lastNameEditText.getText().toString().length() >= 2) {
                        nextButton.setEnabled(true);
                    } else {
                        nextButton.setEnabled(false);
                    }
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
                if (firstNameEditText.getText().toString().length() >= 2) {
                    if (lastNameEditText.getText().toString().length() >= 2) {
                        nextButton.setEnabled(true);
                    } else {
                        nextButton.setEnabled(false);
                    }
                } else {
                    nextButton.setEnabled(false);
                }
            }
        });

    }

    public void onbuttonClick(View v) {
        SciFiName sci_FiNameGenerator = new SciFiName();
        sci_FiFirstName = sci_FiNameGenerator.generateSci_FiFirstName(firstNameEditText.getText().toString(), lastNameEditText.getText().toString());

        Intent intent = new Intent(MainActivity.this, LastNameActivity.class);
        intent.putExtra("sci_FiFirstName", sci_FiFirstName);
        startActivity(intent);
    }


}
