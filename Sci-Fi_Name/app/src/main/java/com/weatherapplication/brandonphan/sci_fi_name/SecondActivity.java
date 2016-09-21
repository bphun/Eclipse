package com.weatherapplication.brandonphan.sci_fi_name;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    EditText cityEditText;
    EditText elementaryEditText;
    EditText relativeNameEditText;
    TextView textView;
    Button generateNameButton;

    String Sci_FiFirstName;
    String Sci_FiLastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        cityEditText = (EditText) findViewById(R.id.cityEditText);
        elementaryEditText = (EditText) findViewById(R.id.elementarySchoolEditText);
        relativeNameEditText = (EditText) findViewById(R.id.relativeNameEditText);
        generateNameButton = (Button) findViewById(R.id.generateSci_FiNameButton);
        textView = (TextView) findViewById(R.id.Sci_FiTextview);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extra = getIntent().getExtras();

        if (extra != null) {
            Sci_FiFirstName = extra.get("sci_FiFirstName").toString();
        }

        //  TODO:   Modify addTextChangedListener to work with more than two EditText views
        elementaryEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}
            @Override
            public void afterTextChanged(Editable s) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((elementaryEditText.getText().toString().length() >= 2) && (cityEditText.getText().toString().length() >= 2) && (relativeNameEditText.getText().toString().length() >= 2)) {
                    generateNameButton.setEnabled(true);
                } else {
                    generateNameButton.setEnabled(false);
                }
            }
        });

        cityEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ((cityEditText.getText().toString().length() >= 2) && (elementaryEditText.getText().toString().length() >= 2) && (relativeNameEditText.getText().toString().length() >= 2)) {
                    generateNameButton.setEnabled(true);
                } else {
                    generateNameButton.setEnabled(false);
                }
            }
        });

        relativeNameEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void afterTextChanged(Editable s) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if ( (relativeNameEditText.getText().toString().length() >= 2) && (elementaryEditText.getText().toString().length() >= 2) && (cityEditText.getText().toString().length() >= 2)) {
                    generateNameButton.setEnabled(true);
                } else {
                    generateNameButton.setEnabled(false);
                }
            }
        });

    }

    public void onButtonClick(View v) {
        SciFiName sci_FiNameGenerator = new SciFiName();

        Sci_FiLastName = sci_FiNameGenerator.generateSci_FiLastName(cityEditText.getText().toString(), elementaryEditText.getText().toString());

        textView.setText("Hello, " + Sci_FiFirstName + " " + Sci_FiLastName);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                textView.setText("");
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
