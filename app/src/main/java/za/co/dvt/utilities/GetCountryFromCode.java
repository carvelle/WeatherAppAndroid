package za.co.dvt.utilities;

import java.util.Locale;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class GetCountryFromCode {

    public static String getCountry(String countryCode){
        Locale loc = new Locale("",countryCode);
        return  loc.getDisplayCountry();
    }
}
