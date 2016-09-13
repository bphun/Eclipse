package org.example.pltw.medialib;

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
            greeting = "Good Morning, Welcome to your media library";
        } else if ((hour > 12) && (hour < 16)) {
            greeting = "Good Afternoon, Welcome to your media library";
        } else if (hour >= 16) {
            greeting = "Good Evening, Welcome to your media library";
        }

//        String hourStr = String.valueOf(hour);

        return greeting;
    }

}
