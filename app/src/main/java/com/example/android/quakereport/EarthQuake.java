package com.example.android.quakereport;

import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by uc on 10/1/16.
 */

public class EarthQuake {
    public static final String TAG = EarthQuake.class.getName();

    private String mLocation, mDateFormated;
    private Date mDate;
    private double mMag;


    public EarthQuake(String mLocation, Date mDate, double mMag) {
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mMag = mMag;
        formatDate();
    }

    private void formatDate() {
        //Convert current time to String using specified format
        String format = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        mDateFormated = simpleDateFormat.format(mDate);
        Log.i(TAG, "formatDate: " + mDateFormated);

    }

    public double getmMag() {
        return mMag;
    }

    public String getmLocation() {
        return mLocation;
    }

    public Date getmDate(){
        return mDate;
    }

    public String getmDateFormated() {
        return mDateFormated;
    }

    @Override
    public String toString() {
        return "EarthQuake{" +
                "mLocation='" + mLocation + '\'' +
                ", mDate=" + mDate +
                ", mMag=" + mMag +
                '}';
    }
}
