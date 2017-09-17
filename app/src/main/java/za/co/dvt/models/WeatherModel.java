package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class WeatherModel {

    @SerializedName("id")
    public int id;

    @SerializedName("main")
    public String main;

    @SerializedName("description")
    public String description;

    @SerializedName("icon")
    public String icon;
}
