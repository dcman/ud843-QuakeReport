package com.example.android.quakereport;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.List;

/**
 * Created by uc on 10/8/16.
 */

public class EarthquakeLoader extends AsyncTaskLoader<List<Earthquake>>{
    static final String TAG = EarthquakeLoader.class.getName();
    private String url;

    public EarthquakeLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        //Log.d(TAG, "onStartLoading: Started \n" + url);
        forceLoad();
    }

    @Override
    public List<Earthquake> loadInBackground() {
        //Log.d(TAG, "loadInBackground: Started \n" + url);
        String json = fetchEarthquakeData(url);
        return QueryUtils.extractEarthquakes(json);
    }

    private String fetchEarthquakeData(String requestUrl) {
        String jsonReturn = null;

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        try {
            jsonReturn = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(TAG, "Error closing input stream", e);
        }

        return jsonReturn;
    }

    private URL createUrl(String requestUrl) {
        URL url = null;
        try{
            url = new URL(requestUrl);
        } catch (MalformedURLException e) {
            Log.e(TAG, "Error with creating URL ", e);
        }
        return url;
    }

    private String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            Log.e(TAG, "makeHttpRequest: null URL" );
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(TAG, "Problem retrieving the earthquake JSON results.", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }
    private String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }
}
