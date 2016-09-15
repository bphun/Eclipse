package org.example.pltw.medialib;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.Objects;

public class showMediaInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_media_info);

        Intent intent = new Intent();
        Object value = intent.getBundleExtra("Media");

        if (value != null) {
            System.out.print("Received Media");
        }

    }
}
