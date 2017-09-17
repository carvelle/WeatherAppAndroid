package za.co.dvt.utilities;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class DateUtils {

    public static String unixTimestampToDate(long timestamp) {
        SimpleDateFormat weatherDateTime = new SimpleDateFormat("EEEE, MMMM d");
        Date time=new Date(timestamp*1000);
        String dateTime = weatherDateTime.format(time);
        return dateTime;
    }

    public  static  String Get24HourTimeFromTimeStamp(long timestamp){
        SimpleDateFormat weatherDateTime = new SimpleDateFormat("HH:mm");
        Date time=new Date(timestamp*1000);
        String dateTime = weatherDateTime.format(time);
        return dateTime;
    }

    public  static  String GetDayOfTheWeek(long timestamp){
        SimpleDateFormat weatherDateTime = new SimpleDateFormat("EEEE");
        Date time=new Date(timestamp*1000);
        String dateTime = weatherDateTime.format(time);
        return dateTime;
    }

    public static Date convertDateTimeToDate(String datetime){
        String sourceDate = datetime;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date myDate = null;
        try {
            myDate = format.parse(sourceDate);

        }catch (Exception e){

            e.printStackTrace();
        }
        return myDate;
    }

    public static Date addDays(Date date, int days){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DATE, days);
        return cal.getTime();
    }

    public static String ConvertDateToString(Date dateString){
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String formattedDate = dateFormat.format(dateString);
        return formattedDate;
    }

}
