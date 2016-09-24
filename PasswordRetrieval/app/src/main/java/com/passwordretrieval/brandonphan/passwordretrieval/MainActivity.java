package com.passwordretrieval.brandonphan.passwordretrieval;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    EditText IDEditText;
    EditText emailEditText;
    CheckBox emailCheckBox;
    Button retrieveButton;
    TextView passwordTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IDEditText = (EditText) findViewById(R.id.studentIDEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        emailCheckBox = (CheckBox) findViewById(R.id.checkBox);
        retrieveButton = (Button) findViewById(R.id.retrieveButon);
        passwordTextView = (TextView) findViewById(R.id.passwordTextView);
    }

    public void retrieveButtonClicked(View v) {

    }
}
