package za.co.dvt.utilities;

import static za.co.weatherappdvt.R.drawable.*;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class GetIconImage {

    public static int GetImage(String iconCode, int conditionId)
    {
        switch (iconCode)
        {
            case "01d" :
                return d01;
            case "01n" :
                return n01;
            case "02n" :
                return n02;
            case "02d" :
                return d02;
            case "03d" :
                return dn03;
            case "03n" :
                return dn03;
            case "04d" :
                return dn03;
            case "04n" :
                return dn03;
            case "09d" :
                return dn09;
            case "09n" :
                return dn09;
            case "10d" :
                return dn09;
            case "10n" :
                return dn09;
            case "11d" :
                return dn11;
            case "11n" :
                return dn11;
            case "50d" :
                return dn50;
            case "50n" :
                return dn50;
            default :
                int weatherIcon = GetImageByWeatherConditionId(conditionId);
                return weatherIcon;
        }
    }

    public static int GetImageByWeatherConditionId(int conditionId)
    {
        switch (conditionId)
        {
            case 900 :
                return hurricane;
            case 901 :
                return hurricane;
            case 902 :
                return hurricane;
            case 903 :
                return verycold;
            case 904 :
                return veryhot;
            case 905 :
                return windy;
            case 906 :
                return sleet;
            default :
                return (int)01d;
        }
    }
}
