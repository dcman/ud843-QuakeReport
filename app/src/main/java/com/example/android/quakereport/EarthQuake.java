package com.example.android.quakereport;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by uc on 10/1/16.
 */

public class Earthquake {
    public static final String TAG = Earthquake.class.getName();

    private String mLocation, mDateFormatted, mTimeFormatted;
    private Date mDate;
    private double mMag;


    public Earthquake(String mLocation, Date mDate, double mMag) {
        this.mLocation = mLocation;
        this.mDate = mDate;
        this.mMag = mMag;
        formatDate();
    }

    private void formatDate() {
        //Convert current time to String using specified format
        //yyyy-MM-dd HH:mm:ss a
        String format = "MMM dd, yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        mDateFormatted = simpleDateFormat.format(mDate);
        // Log.i(TAG, "formatDate: " + mDateFormatted);
        format = "hh:mm a";
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(format);
        mTimeFormatted = simpleTimeFormat.format(mDate);
        //Log.i(TAG, "formatDate: " + mTimeFormatted);
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

    public String getmDateFormatted() {
        return mDateFormatted;
    }

    public String getmTimeFormatted() {
        return mTimeFormatted;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "mLocation='" + mLocation + '\'' +
                ", mDate=" + mDate +
                ", mMag=" + mMag +
                '}';
    }
}
