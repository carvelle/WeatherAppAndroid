package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class WindModel {

    @SerializedName("speed")
    public double speed;

    @SerializedName("deg")
    public double bearing;
}
