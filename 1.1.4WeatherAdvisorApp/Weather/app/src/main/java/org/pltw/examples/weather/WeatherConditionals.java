package org.pltw.examples.weather;

import android.view.Window;

/**
 * Created by wdumas on 3/3/16.
 */
public class WeatherConditionals {

    enum WeatherCondtion {
        Tornado,Tropica_Storm,Hurricane,Severe_Thunderstorm,Thunderstorm,Mixed_Rain_And_Snow, Mixed_Rain_And_Sleet,
        Freezing_Drizzle,Drizzle,Freezing_Rain,Showers,Snow_Flurries,Light_Snow_Showers_Blowing_Snow,Snow,
        Hail,Sleet,Dust,Foggy,Haze,Smoky,Blustery,Windy,Cold,Cloudy,Mostly_Cloudy_Night,Mostly_Cloudy_Day,
        Partly_Cloudy_Night,Partly_Cloudy_Day,Clear_Night,Sunny,Fair_Night,Fair_Day,Mixed_Rain_And_Hail,
        Hot,Isolated_ThunderStorms,Scattered_Thunderstorms,Scattered_Snow,Scattered_Snow_Showers,Heavy_Snow,
        Partly_Cloudy,Thundershowers,Snow_Showers,Isolated_Thundershowers,Not_Available
    }

    public  String getWeatherAdvice(int temperature, int conditionCode) {

        String weatherCondtion = "";

        switch (conditionCode) {
            case 0:
                weatherCondtion = "Tornado";
                break;
            case 1:
                weatherCondtion = "Tropical Storm";
                break;
            case 2:
                weatherCondtion = "Hurricane";
                break;
            case 3:
                weatherCondtion = "Severe Thunderstorm";
                break;
            case 4:
                weatherCondtion = "Thunderstorms";
                break;
            case 5:
                weatherCondtion = "Mixed Rain and Snow";
                break;
            case 6:
                weatherCondtion = "Mixed Rain nad Sleet";
                break;
            case 7:
                weatherCondtion = "Mixed Snow and Sleet";
                break;
            case 8:
                weatherCondtion = "Freezing Drizzle";
                break;
            case 9:
                weatherCondtion = "Drizzle";
                break;
            case 10:
                weatherCondtion = "Freezing Rain";
                break;
            case 11:
                weatherCondtion = "Showers";
                break;
            case 12:
                weatherCondtion = "Showers";
                break;
            case 13:
                weatherCondtion = "Snow Flurries";
                break;
            case 14:
                weatherCondtion = "Light Snow Showers";
                break;
            case 15:
                weatherCondtion = "Blowing Snow";
                break;
            case 16:
                weatherCondtion = "Snow";
                break;
            case 17:
                weatherCondtion = "Hail";
                break;
            case 18:
                weatherCondtion = "Sleet";
                break;
            case 19:
                weatherCondtion = "Dust";
                break;
            case 20:
                weatherCondtion = "Foggy";
                break;
            case 21:
                weatherCondtion = "Haze";
                break;
            case 22:
                weatherCondtion = "Smokey";
                break;
            case 23:
                weatherCondtion = "Blustery";
                break;
            case 24:
                weatherCondtion = "Windy";
                break;
            case 25:
                weatherCondtion = "Cold";
                break;
            case 26:
                weatherCondtion = "Cloudy";
                break;
            case 27:
                weatherCondtion = "Mostly Cloudy (Night)";
                break;
            case 28:
                weatherCondtion = "Mostly Cloudy (Day)";
                break;
            case 29:
                weatherCondtion = "Partly Cloudy (Night)";
                break;
            case 30:
                weatherCondtion = "Partly Cloudy (Day)";
                break;
            case 31:
                weatherCondtion = "Clear (Night)";
                break;
            case 32:
                weatherCondtion = "Sunny";
                break;
            case 33:
                weatherCondtion = "Fair (Night)";
                break;
            case 34:
                weatherCondtion = "Fair (Day)";
                break;
            case 35:
                weatherCondtion = "Mixed Rain and Hail";
                break;
            case 36:
                weatherCondtion = "Hot";
                break;
            case 37:
                weatherCondtion = "Isolated Thunderstorms";
                break;
            case 38:
                weatherCondtion = "Scattered Thunderstorms";
                break;
            case 39:
                weatherCondtion = "Scattered Thunderstorms";
                break;
            case 40:
                weatherCondtion = "Scattered Showers";
                break;
            case 41:
                weatherCondtion = "Heavy Snow";
                break;
            case 42:
                weatherCondtion = "Scattered Snow Showers";
                break;
            case 43:
                weatherCondtion = "Heavy Snow";
                break;
            case 44:
                weatherCondtion = "Partly Cloudy";
                break;
            case 45:
                weatherCondtion = "Thunderstorms";
                break;
            case 46:
                weatherCondtion = "Snow Showers";
                break;
            case 47:
                weatherCondtion = "Isolated Thunderstorms";
                break;
            case 3200:
                weatherCondtion = "Current conditions unavailable";
                break;
        }

        weatherCondtion = temperature + "°F" + " and " + weatherCondtion;

        return weatherCondtion;
    }



}
