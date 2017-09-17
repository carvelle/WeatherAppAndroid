package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class CurrentWeatherModel {

    @SerializedName("coord")
    public CoordinatesModel coordinates;

    @SerializedName("weather")
    public ArrayList<WeatherModel> weather;

    @SerializedName("base")
    public String base;

    @SerializedName("main")
    public TemperaturePressureModel TemperaturePressure;

    @SerializedName("wind")
    public WindModel wind;

    @SerializedName("dt")
    public long unixTimeStamp;

    @SerializedName("sys")
    public SystemModel systemInfo;
    public int id;

    @SerializedName("name")
    public String city;
    public int cod;
}
