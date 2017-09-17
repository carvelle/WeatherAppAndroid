package za.co.dvt.helpers;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

import za.co.dvt.models.ExtendedForecastModel;
import za.co.dvt.models.WeatherConditionModel;
import za.co.dvt.utilities.DateUtils;

/**
 * Created by sibusiso on 2017/09/17.
 */

public class WeatherObjectHelper {

    public static ArrayList<WeatherConditionModel> GetNextTwoDaysWeather(ExtendedForecastModel extendedForecastModel){
        ArrayList<WeatherConditionModel> weatherConditionsList = new ArrayList<>();
        Date today = DateUtils.convertDateTimeToDate(extendedForecastModel.weatherCollection.get(0).dateTime);
        Date tomorrow = DateUtils.addDays(today,1);
        Date thirdDay = DateUtils.addDays(today,2);
        String tomorrowDateString = DateUtils.ConvertDateToString(tomorrow);
        String thirdDayString = DateUtils.ConvertDateToString(thirdDay);

        for (Iterator<WeatherConditionModel> iterator = extendedForecastModel.weatherCollection.iterator(); iterator.hasNext();) {
            WeatherConditionModel conditionModel = iterator.next();
            if(conditionModel.dateTime.equals(tomorrowDateString +" 12:00:00")){
                weatherConditionsList.add(conditionModel);
            }else if(conditionModel.dateTime.equals(thirdDayString +" 12:00:00")){
                weatherConditionsList.add(conditionModel);
            }
        }
            return weatherConditionsList;
    }
}
