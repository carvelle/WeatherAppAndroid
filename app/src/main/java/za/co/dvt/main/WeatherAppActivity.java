package za.co.dvt.main;
import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import za.co.dvt.adapters.SlideshowAdapter;
import za.co.dvt.helpers.BackgroundImageChanger;
import za.co.dvt.helpers.CronJobRunner;
import za.co.dvt.helpers.LocationHelper;
import za.co.dvt.helpers.WeatherObjectHelper;
import za.co.dvt.models.CurrentWeatherModel;
import za.co.dvt.models.ExtendedForecastModel;
import za.co.dvt.models.WeatherConditionModel;
import za.co.dvt.models.WeatherModel;
import za.co.dvt.network.BaseConnectivityManager;
import za.co.dvt.utilities.ConvertTemperatures;
import za.co.dvt.utilities.DateUtils;
import za.co.dvt.utilities.GetCountryFromCode;
import za.co.dvt.utilities.GetIconImage;
import za.co.weatherappdvt.R;

public class WeatherAppActivity extends AppCompatActivity {

    String maxLabel;
    String minLabel;
    LocationHelper gpsHelper;
    ViewPager mViewPager;
    SlideshowAdapter slideshowAdapter;
    TextView dateLabel, mainTempLabel, areaLabel, maxTempLabel, minTempLabel, lastUpdated,
            extMaxTempRow1, extMinTempRow1, dayRow1,
            extMaxTempRow2, extMinTempRow2, dayRow2;
    ImageView weatherIcon, iconImageRow1,iconImageRow2;
    double lat;
    double lng;
    public static final int MY_PERMISSIONS_REQUEST_GPS = 99;
    Runnable weatherRunnable = new Runnable() {
        public void run() {
            LoadWeatherData();
        }
    };
    Runnable ExtendedWeatherRunnable = new Runnable() {
        public void run() {
            LoadExtendedWeatherData();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_layout);

        dateLabel = (TextView) findViewById(R.id.date_label);
        mainTempLabel = (TextView) findViewById(R.id.main_temp);
        areaLabel = (TextView) findViewById(R.id.area_label);
        maxTempLabel = (TextView) findViewById(R.id.max_temp);
        minTempLabel = (TextView) findViewById(R.id.min_temp);
        lastUpdated = (TextView) findViewById(R.id.last_updated);
        weatherIcon = (ImageView) findViewById(R.id.icon_image);
        mViewPager = (ViewPager) findViewById(R.id.pager);
        //Extended Weather
        extMaxTempRow1 =(TextView) findViewById(R.id.ext_max_temp_row1);
        extMinTempRow1 = (TextView) findViewById(R.id.ext_min_temp_row1);
        dayRow1 = (TextView) findViewById(R.id.day_label_row1);
        iconImageRow1 =  (ImageView) findViewById(R.id.ext_icon_image_row1);
        extMaxTempRow2 =(TextView) findViewById(R.id.ext_max_temp_row2);
        extMinTempRow2 = (TextView) findViewById(R.id.ext_min_temp_row2);
        dayRow2 = (TextView) findViewById(R.id.day_label_row2);
        iconImageRow2 =  (ImageView) findViewById(R.id.ext_icon_image_row2);
        slideshowAdapter = new SlideshowAdapter(this);
        mViewPager.setAdapter(slideshowAdapter);
        maxLabel = this.getResources().getString(R.string.max_string);
        minLabel = this.getResources().getString(R.string.min_string);

        gpsHelper = new LocationHelper(this);
        Handler handler = new Handler();
        getGPSCoordinates();
        manageBackgroundImages(handler);
        QueueCronJobs(handler);
        LoadExtendedWeatherData();
    }

    public void LoadWeatherData(){

        if(lat != 0.0 && lng != 0.0) {
            String url = getResources().getString(R.string.service_url);
            PointF latlng = new PointF((float)lat,(float)lng);
            BaseConnectivityManager connectivityManager = new BaseConnectivityManager<>(CurrentWeatherModel.class, this, url, latlng);
            connectivityManager.setnetworkTaskCompleteListener(new BaseConnectivityManager.NetworkTaskCompleteListener() {
                @Override
                public void onRemoteCallComplete(Object dataModel) {

                    CurrentWeatherModel weatherModel = (CurrentWeatherModel) dataModel;
                    setLabelValues(weatherModel);
                }
            });
            connectivityManager.execute();
        }
    }

    public void LoadExtendedWeatherData(){

        if(lat != 0.0 && lng != 0.0) {
            String url = getResources().getString(R.string.extended_forecast_url);
            PointF latlng = new PointF((float)lat,(float)lng);
            BaseConnectivityManager connectivityManager = new BaseConnectivityManager<>(ExtendedForecastModel.class, this, url, latlng);
            connectivityManager.setnetworkTaskCompleteListener(new BaseConnectivityManager.NetworkTaskCompleteListener() {
                @Override
                public void onRemoteCallComplete(Object dataModel) {

                    ExtendedForecastModel extendedForecastModel = (ExtendedForecastModel) dataModel;
                    ArrayList<WeatherConditionModel> threeDayForecast = WeatherObjectHelper.GetNextTwoDaysWeather(extendedForecastModel);
                    setExtendForecastValues(threeDayForecast);
                }
            });
            connectivityManager.execute();
        }
    }

    private void setLabelValues(CurrentWeatherModel model){
        String countryCity = model.city +", " +GetCountryFromCode.getCountry(model.systemInfo.countryCode);
        String lastUpdatedLabel = this.getResources().getString(R.string.lastupdated_string);
        dateLabel.setText(DateUtils.unixTimestampToDate(model.unixTimeStamp));
        mainTempLabel.setText(ConvertTemperatures.AppendDegreeCharacter(model.TemperaturePressure.temperature));
        maxTempLabel.setText(maxLabel+" "+ConvertTemperatures.AppendDegreeCharacter(model.TemperaturePressure.tempMax));
        minTempLabel.setText(minLabel+" "+ConvertTemperatures.AppendDegreeCharacter(model.TemperaturePressure.tempMin));
        areaLabel.setText(countryCity);
        String sdsd = DateUtils.Get24HourTimeFromTimeStamp(model.unixTimeStamp);
        lastUpdated.setText(lastUpdatedLabel+" "+sdsd);
        weatherIcon.setImageResource(manageWeatherIcon(model.weather));
    }

    private void setExtendForecastValues(ArrayList<WeatherConditionModel> extendedForecast){
        extMaxTempRow1.setText(maxLabel+" "+ConvertTemperatures.AppendDegreeCharacter(extendedForecast.get(0).weatherCondition.maxTemp));
        extMinTempRow1.setText(minLabel+" "+ConvertTemperatures.AppendDegreeCharacter(extendedForecast.get(0).weatherCondition.minTemp));
        dayRow1.setText(DateUtils.GetDayOfTheWeek(extendedForecast.get(0).unixTimestamp));
        iconImageRow1.setImageResource(manageWeatherIcon(extendedForecast.get(0).weather));

        extMaxTempRow2.setText(maxLabel+" "+ConvertTemperatures.AppendDegreeCharacter(extendedForecast.get(1).weatherCondition.maxTemp));
        extMinTempRow2.setText(minLabel+" "+ConvertTemperatures.AppendDegreeCharacter(extendedForecast.get(1).weatherCondition.minTemp));
        dayRow2.setText(DateUtils.GetDayOfTheWeek(extendedForecast.get(1).unixTimestamp));
        iconImageRow2.setImageResource(manageWeatherIcon(extendedForecast.get(1).weather));
    }

    public void manageBackgroundImages(Handler handler){
        BackgroundImageChanger bgChanger = new BackgroundImageChanger(handler, this, mViewPager);
        bgChanger.pageSwitcher(60);
    }

    public int manageWeatherIcon(List<WeatherModel> modelList){
        int resId = 0;
        for (WeatherModel weather : modelList) {
            resId = GetIconImage.GetImage(weather.icon, weather.id);
        }
        return  resId;
    }

    public void getGPSCoordinates(){
        LocationManager locationManager = (LocationManager)
                getSystemService(Context.LOCATION_SERVICE);
        LocationHelper locHelper = new LocationHelper(this);
        LocationListener locationListener = locHelper.new GpsListener();

        //For testing on the Emulator purposes
        lat = -23.0000;
        lng = 23.00000;

        if (locHelper.checkRequiredPermissions()&&ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            locationManager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, 5000, 10, locationListener);
            String locationProvider = LocationManager.NETWORK_PROVIDER;
            Location lastKnownLocation = locationManager.getLastKnownLocation(locationProvider);
            if(lastKnownLocation != null) {
                lat = lastKnownLocation.getLatitude();
                lng = lastKnownLocation.getLongitude();
            }
            LoadWeatherData();
        }

    }

    public void QueueCronJobs(Handler handler){
        CronJobRunner CurrentWeatherCronJobRunner = new CronJobRunner(handler);
        CurrentWeatherCronJobRunner.QueueJob(60000, weatherRunnable);

        CronJobRunner ExtendedWeatherCronJobRunner = new CronJobRunner(handler);
        ExtendedWeatherCronJobRunner.QueueJob(20, ExtendedWeatherRunnable);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults)
    {
        switch (requestCode)
        {
            case MY_PERMISSIONS_REQUEST_GPS:
            {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) == PackageManager.PERMISSION_GRANTED)
                    {

                    }
                }
                else
                {
                    Toast.makeText(this, "permission denied", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }

}
