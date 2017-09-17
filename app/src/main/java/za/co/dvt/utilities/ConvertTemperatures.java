package za.co.dvt.utilities;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class ConvertTemperatures {

    public static String AppendDegreeCharacter(double temp){
        return RoundTemperature(temp) + "º";
    }

    public static String RoundTemperature(double temp){
        return String.valueOf(Math.round(temp));
    }
}

