package org.pltw.examples.weather;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private Exception error;
    public static String LOCATION = "";
    private TextView textView;
    EditText locationEditText;

    public static Intent newIntent(Context context) {
        return new Intent(context, MainActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.weatherTest);
        final Button searchButton = (Button) findViewById(R.id.search_button);
        locationEditText = (EditText) findViewById(R.id.location_editText);

        textView.setY((float) 200.0);
        locationEditText.setY((float) -900.0);
        searchButton.setY((float) 17);
        searchButton.setX((float) 1000.0);


        locationEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (locationEditText.getText().toString().length() > 2) {
                    searchButton.setEnabled(true);
                } else if (locationEditText.getText().toString().length() < 2) {
                    searchButton.setEnabled(false);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void afterTextChanged(Editable s) { }
        });
    }


    public void onSearchClick(View v) {

        LOCATION = locationEditText.getText().toString();

        WeatherService.setServiceAlarm(getApplicationContext(), true);
        new AsyncTask<String, Void, JSONObject>(){
            @Override
            protected JSONObject doInBackground(String[] locations){
                Log.d(TAG, "location: " + locations[0]);
                String location = locations[0];
                String unit = "f";

                String YQL = String.format("select * from weather.forecast where woeid in (select woeid from geo.places(1) where text=\"%s\") and u='" + unit + "'", location);

                String endpoint = String.format("https://query.yahooapis.com/v1/public/yql?q=%s&format=json", Uri.encode(YQL));

                try {
                    URL url = new URL(endpoint);

                    URLConnection connection = url.openConnection();
                    connection.setUseCaches(false);
                    InputStream inputStream = connection.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
                    StringBuilder result = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        result.append(line);
                    }

                    JSONObject data = new JSONObject(result.toString());
                    JSONObject queryResults = data.optJSONObject("query");

                    int count = queryResults.optInt("count");

                    if (count == 0) {
                        error = new Exception("No weather information found for " + location);
                        return null;
                    }

                    JSONObject jsonObject = queryResults.optJSONObject("results").optJSONObject("channel");

                    return jsonObject;

                } catch (Exception e) {
                    error = e;
                    Log.e(TAG, error.toString());
                }

                return null;
            }

            @Override
            protected void onPostExecute(JSONObject result) {
                WeatherJSONProcessor weatherJSONProcessor = new WeatherJSONProcessor(result, LOCATION);

                WeatherConditionals weatherCondition = new WeatherConditionals();

                String currentConditions = weatherCondition.getWeatherAdvice(weatherJSONProcessor.getTemperature(), weatherJSONProcessor.getConditionCode());
                textView.setText("" + weatherJSONProcessor.getLocation() + ": " + currentConditions);
                Log.d(TAG, "The temp is: " + weatherJSONProcessor.getTemperature());
                Log.d(TAG, "The condition is: " + weatherJSONProcessor.getCondition());
                Log.d(TAG, "The JSON is: " + weatherJSONProcessor.getJson());
            }
        }.execute(LOCATION);
    }

}

