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

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class EarthquakeActivity extends AppCompatActivity {

    public static final String LOG_TAG = EarthquakeActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.earthquake_activity);

        // Create a fake list of earthquake locations.
        ArrayList<Earthquake> earthquakes = new ArrayList<Earthquake>();

        earthquakes.add(new Earthquake("San Francisco", new Date(),2.5));
        earthquakes.add(new Earthquake("London", new Date(), 0.2));
        earthquakes.add(new Earthquake("Tokyo", new Date(), 1.5));
        earthquakes.add(new Earthquake("Mexico City", new Date(), 8.5));
        earthquakes.add(new Earthquake("Moscow", new Date(), 1.52));
        earthquakes.add(new Earthquake("Rio de Janeiro", new Date(), 6.2));
        earthquakes.add(new Earthquake("Paris", new Date(), 1.1));

        // Find a reference to the {@link ListView} in the layout
        ListView earthquakeListView = (ListView) findViewById(R.id.list);

        // Create a new {@link ArrayAdapter} of earthquakes
        ArrayAdapter<Earthquake> adapter = new EarthquakeAdapter(
                this, R.layout.earth_quake_item, earthquakes);

        // Set the adapter on the {@link ListView}
        // so the list can be populated in the user interface
        if (earthquakeListView != null) {
            earthquakeListView.setAdapter(adapter);
        }
    }
}
