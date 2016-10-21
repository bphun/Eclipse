package com.brandonphan.medialib.Activities;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.brandonphan.medialib.Media.Book;
import com.brandonphan.medialib.Media.Human;
import com.brandonphan.medialib.Media.MediaFile;
import com.brandonphan.medialib.Media.MediaLib;
import com.brandonphan.medialib.Media.Movie;
import com.brandonphan.medialib.Media.Song;
import com.brandonphan.medialib.MediaListAdapter;
import com.brandonphan.medialib.R;
import com.brandonphan.medialib.Strings.Greeting;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.Scanner;

public class MainActivity extends AppCompatActivity {

    MediaLib mediaLib;
    MediaFile mediaFile;
//    ViewGroup content_main = (ViewGroup) findViewById(R.id.content_main);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Greeting greeting = new Greeting();
        TextView greetingTextView = (TextView) findViewById(R.id.welcomeTextView);
        greetingTextView.setText(greeting.getGreeting());

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.addMediaButton);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    public void showMedia(View v) {
        mediaLib = new MediaLib();

        if (mediaLib.getMediaLib().size() >= 1) {

            mediaFile = new MediaFile();
            ListView mediaListView = (ListView) findViewById(R.id.mediaListView);

            ArrayList<Object> mediaLibrary = mediaLib.getMediaLib();

            MediaListAdapter mediaAdapter = new MediaListAdapter(this, mediaLibrary);
            for (Object o : mediaLibrary) {
                System.out.print(o);
            }

            mediaListView.setAdapter(mediaAdapter);

        } else {
            Toast.makeText(this, "Your media library is empty", Toast.LENGTH_SHORT).show();
        }
    }
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.menu_main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }
}
