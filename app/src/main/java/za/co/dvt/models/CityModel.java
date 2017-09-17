package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class CityModel {
    @SerializedName("id")
    public double id;

    @SerializedName("name")
    public double cityName;

    @SerializedName("coord")
    public CoordinatesModel latLng;

    @SerializedName("country")
    public double countryCode;
}
