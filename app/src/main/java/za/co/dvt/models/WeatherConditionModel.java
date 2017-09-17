package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class WeatherConditionModel {

    @SerializedName("dt")
    public long unixTimestamp;

    @SerializedName("main")
    public ExtendedWeatherModel weatherCondition;

    @SerializedName("weather")
    public ArrayList<WeatherModel> weather;

    @SerializedName("clouds")
    public CloudsModel clouds;

    @SerializedName("wind")
    public WindModel wind;

    @SerializedName("dt_txt")
    public String dateTime;
}
