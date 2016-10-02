package com.example.android.quakereport;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by uc on 10/1/16.
 */

public class EarthQuakeAdapter extends ArrayAdapter<EarthQuake> {


    public EarthQuakeAdapter(Context context, int resource) {
        super(context, resource);
    }

    public EarthQuakeAdapter(Context context, int resource, ArrayList<EarthQuake> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if an existing view is being reused, otherwise inflate the view
        View earthQuakeView = convertView;
        if (earthQuakeView == null){
            earthQuakeView = LayoutInflater.from(getContext()).inflate(
                    R.layout.earth_quake_item,parent,false);
        }
        // Get the {@link EarthQuake} object located at this position in the list
        EarthQuake currentEearthQuake = getItem(position);
        // Find the TextView in the earth_quake_item.xml layout with the ID textView_mag
        TextView mag = (TextView) earthQuakeView.findViewById(R.id.textView_mag);
        mag.setText(String.valueOf(currentEearthQuake.getmMag()));
        // Find the TextView in the earth_quake_item.xml layout with the ID textView_loc
        TextView loc = (TextView) earthQuakeView.findViewById(R.id.textView_loc);
        loc.setText(currentEearthQuake.getmLocation());
        // Find the TextView in the earth_quake_item.xml layout with the ID textView_date
        TextView date = (TextView) earthQuakeView.findViewById(R.id.textView_date);
        date.setText(currentEearthQuake.getmDateFormated());
        return earthQuakeView;
    }
}
