package za.co.dvt.utilities;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class AppendCoordinates {

    public static String AppendCoordinates(String url, String lat, String lon, String appid)
    {
        return String.format("%s&lat=%2$s&lon=%3$s&appid=%4$s", url, lat, lon, appid);
    }
}
