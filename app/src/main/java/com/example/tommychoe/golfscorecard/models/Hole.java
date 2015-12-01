package com.example.tommychoe.golfscorecard.models;

public class Hole {

    private String mHoleLabel;
    private int mCount;

    public Hole(String holeLabel, int count) {
        mHoleLabel = holeLabel;
        mCount = count;
    }

    public String getHoleLabel() {
        return mHoleLabel;
    }

    public void setHoleLabel(String holeLabel) {
        mHoleLabel = holeLabel;
    }

    public int getCount() {
        return mCount;
    }

    public void setCount(int count) {
        mCount = count;
    }

    public int addOneToCount(){
        return mCount + 1;
    }

    public int subtractOneFromCount(){
        return mCount - 1;
    }
}
