package za.co.dvt.network;


import android.content.Context;
import android.graphics.PointF;
import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.ResponseCache;
import java.net.URL;

import za.co.dvt.enums.RequestMethod;
import za.co.dvt.models.CurrentWeatherModel;
import za.co.dvt.utilities.AppendCoordinates;
import za.co.weatherappdvt.R;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class BaseConnectivityManager<T> extends AsyncTask<T, Void, T>  {
    Class<T> classType;
    String lat;
    String lng;
    String url;
    Context context;
    WrappedHttpClient wrappedHttpClient;

    NetworkTaskCompleteListener networkTaskCompleteListener;
    public BaseConnectivityManager(Class<T> ctype, Context context, String url, PointF latlng, WrappedHttpClient wrappedHttpClient) {
        this.lat = String.valueOf(latlng.x);
        this.lng = String.valueOf(latlng.y);
        this.classType= ctype;
        this.context = context;
        this.url = url;
        this.wrappedHttpClient = wrappedHttpClient;
    }

    public void setnetworkTaskCompleteListener(NetworkTaskCompleteListener networkTaskCompleteListener) {
        this.networkTaskCompleteListener = networkTaskCompleteListener;
    }

    @Override
    protected T doInBackground(T... params) {
        wrappedHttpClient = new WrappedHttpClient();
        String appid = context.getResources().getString(R.string.APPID);
        String completeUrl = AppendCoordinates.AppendCoordinates(url,lat,lng,appid);
        String jsonRespone = wrappedHttpClient.RequestAsync(completeUrl, RequestMethod.GET);
        Gson gson = new GsonBuilder().create();

        T response =gson.fromJson(jsonRespone, this.classType);
        return response;
    }

    @Override
    protected void onPostExecute(T t) {
        networkTaskCompleteListener.onRemoteCallComplete(t);
    }


    public interface NetworkTaskCompleteListener<T> {
        void onRemoteCallComplete(T dataModel);
    }


}
