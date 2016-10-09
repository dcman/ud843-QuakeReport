/*
 * Copyright (C) 2016 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.android.quakereport;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class EarthquakeActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Earthquake>> {

    public static final String TAG = EarthquakeActivity.class.getName();
    final String url = "http://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&eventtype=earthquake&orderby=time&minmag=6&limit=10";
    private Context mContext = this;
    private TextView emptyTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);
        // Setup loader to load data in the background
        getLoaderManager().initLoader(0,null,this);
        //Get the empty text view
        emptyTextView = (TextView ) findViewById(R.id.empty);
    }

    @Override
    public Loader<List<Earthquake>> onCreateLoader(int id, Bundle args) {
        // Create a new loader
        EarthquakeLoader earthquakeLoader = new EarthquakeLoader(this, url);
      //  Log.d(TAG, "onCreateLoader: Started");
        return earthquakeLoader;
    }

    @Override
    public void onLoadFinished(Loader<List<Earthquake>> loader, List<Earthquake> data) {
        //Log.d(TAG, "onLoadFinished: Stated");

        // Create list of earthquake locations.
        ArrayList<Earthquake> earthquakes = (ArrayList<Earthquake>) data;

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<Earthquake> adapter = new EarthquakeAdapter(
                mContext, R.layout.earth_quake_item, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(adapter);
        }
        // Get the empty view and set text
        emptyTextView.setText("No earthquakes found.");

    }

    @Override
    public void onLoaderReset(Loader<List<Earthquake>> loader) {
       // Log.d(TAG, "onLoaderReset: Started");
    }
}
