package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uc on 10/1/16.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {
    private final static String TAG = EarthquakeAdapter.class.getName();

    public EarthquakeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public EarthquakeAdapter(Context context, int resource, ArrayList<Earthquake> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View earthquakeView = convertView;
        if (earthquakeView == null){
            earthquakeView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earth_quake_item,parent,false);
        }
        // Get the {@link Earthquake} object located at this position in the list
        Earthquake currentEarthquake = getItem(position);

        // Find the TextView in the earth_quake_item.xml layout with the ID textView_mag
        TextView mag = (TextView) earthquakeView.findViewById(R.id.textView_mag);
        mag.setText(currentEarthquake.getmMagFormatted());
        formatLocation(currentEarthquake, earthquakeView);

        // Find the TextView in the earth_quake_item.xml layout with the ID textView_date
        TextView date = (TextView) earthquakeView.findViewById(R.id.textView_date);
        date.setText(currentEarthquake.getmDateFormatted());

        // Find the TextView in the earth_quake_item.xml layout with the ID textView_time
        TextView time = (TextView) earthquakeView.findViewById(R.id.textView_time);
        time.setText(currentEarthquake.getmTimeFormatted());

        // Set the proper background color on the magnitude circle.
        // Fetch the background from the TextView, which is a GradientDrawable.
        GradientDrawable magnitudeCircle = (GradientDrawable) mag.getBackground();

        // Get the appropriate background color based on the current earthquake magnitude
        int magnitudeColor = getMagnitudeColor(currentEarthquake.getmMag());

        // Set the color on the magnitude circle
        magnitudeCircle.setColor(magnitudeColor);

        return earthquakeView;
    }

    private void formatLocation(Earthquake currentEarthquake, View earthquakeView) {
        int index;
        String primary;
        String offset;
        String fullLocation = currentEarthquake.getmLocation();

        if (fullLocation.contains("of")){
            index = fullLocation.indexOf("of");
            // Log.i(TAG, "formatLocation: has of " + index + " " + fullLocation);
            offset = fullLocation.substring(0, index + 2); // index +2 to include "of"
            // Log.i(TAG, "formatLocation: " + offset);
            primary = fullLocation.substring(index + 3, fullLocation.length());// index +3 to skip the space after of
            // Log.i(TAG, "formatLocation: " + primary);
        }
        else{
            // Log.i(TAG, "formatLocation: dose not have of");
            offset = "Near the";
            // Log.i(TAG, "formatLocation: using offset " + offset);
            primary = fullLocation;
            // Log.i(TAG, "formatLocation: primary " + primary);
        }
        // Find the TextView in the earth_quake_item.xml layout with the ID textView_loc_primary
        TextView primaryView = (TextView) earthquakeView.findViewById(R.id.textView_loc_primary);
        primaryView.setText(primary);
        // Find the TextView in the earth_quake_item.xml layout with the ID textView_loc_primary
        TextView offsetView = (TextView) earthquakeView.findViewById(R.id.textView_loc_offset);
        offsetView.setText(offset);

    }

    private int getMagnitudeColor(double magnitude) {
        int magnitudeColorResourceId;
        int magnitudeFloor = (int) Math.floor(magnitude);
        switch (magnitudeFloor) {
            case 0:
            case 1:
                magnitudeColorResourceId = R.color.magnitude1;
                break;
            case 2:
                magnitudeColorResourceId = R.color.magnitude2;
                break;
            case 3:
                magnitudeColorResourceId = R.color.magnitude3;
                break;
            case 4:
                magnitudeColorResourceId = R.color.magnitude4;
                break;
            case 5:
                magnitudeColorResourceId = R.color.magnitude5;
                break;
            case 6:
                magnitudeColorResourceId = R.color.magnitude6;
                break;
            case 7:
                magnitudeColorResourceId = R.color.magnitude7;
                break;
            case 8:
                magnitudeColorResourceId = R.color.magnitude8;
                break;
            case 9:
                magnitudeColorResourceId = R.color.magnitude9;
                break;
            default:
                magnitudeColorResourceId = R.color.magnitude10plus;
                break;
        }
        return ContextCompat.getColor(getContext(), magnitudeColorResourceId);
    }

}
