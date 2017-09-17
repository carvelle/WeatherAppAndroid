package za.co.dvt.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import za.co.dvt.enums.RequestMethod;
import za.co.dvt.interfaces.HttpClientDataProvider;

/**
 * Created by sibusiso on 2017/09/16.
 */

public class WrappedHttpClient implements HttpClientDataProvider {
    @Override
    public String RequestAsync(String requestUri, RequestMethod method) {
        String result;

        try {
            URL url = new URL(requestUri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(3000);
            conn.setConnectTimeout(3000);
            conn.setRequestMethod(method.name());
            conn.setDoInput(true);
            conn.connect();
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            result = sb.toString();
        } catch(Exception e) {
            return null;
        }

        return result;
    }
}
