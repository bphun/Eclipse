package org.example.pltw.medialib;

import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by BrandonPhan on 9/12/16.
 */
public class Greeting {

    public String getGreeting() {
        Calendar calender = Calendar.getInstance();
        int hour = calender.get(Calendar.HOUR_OF_DAY);

        String greeting = "";
        if (hour < 12) {
            greeting = "Good Morning... \n Welcome to your media library";
        } else if ((hour >= 12) && (hour <= 16)) {
            greeting = "Good Afternoon... \n Welcome to your media library";
        } else if (hour >= 16) {
            greeting = "Good Evening... \n Welcome to your media library";
        }
        return greeting;
    }
}
