package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class CoordinatesModel {

    @SerializedName("lat")
    public double latitude;

    @SerializedName("lon")
    public double longitude;
}
