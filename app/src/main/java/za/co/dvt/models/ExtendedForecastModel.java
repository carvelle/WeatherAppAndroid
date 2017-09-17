package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class ExtendedForecastModel {

    @SerializedName("cnt")
    public int count;

    @SerializedName("list")
    public ArrayList<WeatherConditionModel> weatherCollection;
}
