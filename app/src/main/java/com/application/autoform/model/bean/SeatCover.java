package com.application.autoform.model.bean;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Sandeep on 29/10/2016.
 */

public class SeatCover extends Product {
    @SerializedName("seatcolorimagelist")
    ArrayList<SeatCoverImageData> mSeatCoverPath;

    public void addSeatCoverImageDetail(SeatCoverImageData data) {
        mSeatCoverPath.add(data);
    }

    public class SeatCoverImageData extends LargeImageData {
        @SerializedName("colorMajorName")
        String mMajorColor;
        @SerializedName("colorMinorURLName")
        String mMinorColor;
        @SerializedName("colorMinorURL")
        String mMinorColorPath;
        @SerializedName("colorMajorURL")
        String mMajorColorPath;

        public String getmMajorColor() {
            return mMajorColor;
        }

        public void setmMajorColor(String mMajorColor) {
            this.mMajorColor = mMajorColor;
        }

        public String getmMinorColor() {
            return mMinorColor;
        }

        public void setmMinorColor(String mMinorColor) {
            this.mMinorColor = mMinorColor;
        }

        public String getmMinorColorPath() {
            mMinorColorPath = mMinorColorPath.replace('\\', '/').replace(" ", "%20");
            return mMinorColorPath;
        }

        public void setmMinorColorPath(String mMinorColorPath) {
            this.mMinorColorPath = mMinorColorPath;
        }

        public String getmMajorColorPath() {
            mMajorColorPath = mMajorColorPath.replace('\\', '/').replace(" ", "%20");
            return mMajorColorPath;
        }

        public void setmMajorColorPath(String mMajorColorPath) {
            this.mMajorColorPath = mMajorColorPath;
        }
    }
}
