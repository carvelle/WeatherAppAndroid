package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class ExtendedWeatherModel {

    @SerializedName("temp")
    public double mainTemp;

    @SerializedName("temp_min")
    public double minTemp;

    @SerializedName("temp_max")
    public double maxTemp;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("sea_level")
    public double seaLevel;

    @SerializedName("grnd_level")
    public double groundLevel;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_kf")
    public double tempKf;
}
