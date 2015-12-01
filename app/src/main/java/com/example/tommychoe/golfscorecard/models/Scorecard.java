package com.example.tommychoe.golfscorecard.models;

import android.content.SharedPreferences;

import com.example.tommychoe.golfscorecard.ui.MainActivity;

public class Scorecard {
    private Hole[] mHoles;

    public Scorecard(SharedPreferences sharedPreferences) {
        mHoles = new Hole[18];
        int strokes = 0;
        for (int i = 0; i < mHoles.length; i++) {
            strokes = sharedPreferences.getInt(MainActivity.KEY_STROKECOUNT + i, 0);
            mHoles[i] = new Hole("Hole " + (i + 1) + ":", strokes);
        }
    }

    public Hole[] getHoles() {
        return mHoles;
    }

    public void setHoles(Hole[] holes) {
        mHoles = holes;
    }
}
