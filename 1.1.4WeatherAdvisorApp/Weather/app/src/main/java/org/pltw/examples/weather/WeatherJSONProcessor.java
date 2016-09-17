package org.pltw.examples.weather;

import org.json.JSONObject;

/**
 * Created by wdumas on 3/3/16.
 */
public class WeatherJSONProcessor {

    private String temperature;
    private String condition;
    private String json;
    private String location;
    private int conditionCode;
    private String region;

    public WeatherJSONProcessor(JSONObject jsonObject, String location) {
        temperature = jsonObject.optJSONObject("item").optJSONObject("condition").optString("temp");
        condition = jsonObject.optJSONObject("item").optJSONObject("condition").optString("text");
        conditionCode = Integer.parseInt(jsonObject.optJSONObject("item").optJSONObject("condition").optString("code"));
        json = jsonObject.toString();
        this.location = location;
    }

    public int getTemperature() {
        return new Integer(temperature);
    }

    public String getCondition() {
        return condition;
    }

    public String getJson() {
        return json;
    }

    public int getConditionCode() { return conditionCode; }

    public String getLocation() {
        return location;
    }

    public String getRegion() { return region; }
}
