package za.co.dvt.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class SystemModel {

    @SerializedName("type")
    public double Type;

    @SerializedName("id")
    public int id;

    @SerializedName("message")
    public double message;

    @SerializedName("country")
    public String countryCode;

    @SerializedName("sunrise")
    public double sunrise;

    @SerializedName("sunset")
    public double sunset;
}
