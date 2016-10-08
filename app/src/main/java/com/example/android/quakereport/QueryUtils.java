package com.example.android.quakereport;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Date;

/**
 * Helper methods related to requesting and receiving earthquake data from USGS.
 */
public final class QueryUtils {
    private static final String TAG = QueryUtils.class.getName();

    /**
     * Create a private constructor because no one should ever create a {@link QueryUtils} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name QueryUtils (and an object instance of QueryUtils is not needed).
     */
    private QueryUtils() {
    }

    /**
     * Return a list of {@link Earthquake} objects that has been built up from
     * parsing a JSON response.
     */
    public static ArrayList<Earthquake> extractEarthquakes(String json) {
        // Create an empty ArrayList that we can start adding earthquakes to
        ArrayList<Earthquake> earthquakes = new ArrayList<>();

        // Try to parse the SAMPLE_JSON_RESPONSE. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {

            // build up a list of Earthquake objects with the corresponding data.
            JSONObject jsonRootObject = new JSONObject(json);
            // pullout the features array from rootObject
            JSONArray jsonFeaturesArray = jsonRootObject.getJSONArray("features");

            for (int i = 0; i < jsonFeaturesArray.length(); i++) {
                // pullout a feature from the FeaturesArray
                JSONObject temp = jsonFeaturesArray.getJSONObject(i);
                //Log.i(TAG, "extractEarthquakes: feature " + temp);

                // pullout a properties object from temp
                JSONObject prop = temp.getJSONObject("properties");
                //Log.i(TAG, "extractEarthquakes: properties " + prop);

                // pull out place, magnitude and date from prop
                String place = prop.getString("place");
                double mag = prop.getDouble("mag");
                Date date = new Date(prop.getLong("time"));
                //Log.i(TAG, "extractEarthquakes: " + prop.getLong("time"));
                String url = prop.getString("url");
                //Log.i(TAG, "extractEarthquakes: " + mag + " " + place + " " + date + "\n" + url);
                // build new earthquake and add it to earthquakes list
                earthquakes.add(new Earthquake(place, date, mag, url));
            }


        } catch (JSONException e) {
            // If an error is thrown when executing any of the above statements in the "try" block,
            // catch the exception here, so the app doesn't crash. Print a log message
            // with the message from the exception.
            Log.e("QueryUtils", "Problem parsing the earthquake JSON results", e);
        }

        // Return the list of earthquakes
        //Log.i(TAG, "extractEarthquakes: " + earthquakes);
        return earthquakes;
    }
}