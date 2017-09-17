package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class TemperaturePressureModel {

    @SerializedName("temp")
    public double temperature;

    @SerializedName("pressure")
    public double pressure;

    @SerializedName("humidity")
    public double humidity;

    @SerializedName("temp_min")
    public double tempMin;

    @SerializedName("temp_max")
    public double tempMax;
}
