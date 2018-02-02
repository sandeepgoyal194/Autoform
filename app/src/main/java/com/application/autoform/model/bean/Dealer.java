package com.application.autoform.model.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sandeep.g9 on 11/9/2016.
 */

public class Dealer implements Serializable{
    @SerializedName("DealersID")
    int mId;
    @SerializedName("DealerName")
    String mName;
    @SerializedName("DealerAddress")
    String mAddress;
    @SerializedName("DealerMobileNo")
    String mPhoneNo;
    @SerializedName("DealerEmailId")
    String mEmailId;
    @SerializedName("DealerState")
    private String mState;
    @SerializedName("DealerCity")
    private String mCity;

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmPhoneNo() {
        return mPhoneNo;
    }

    public void setmPhoneNo(String mPhoneNo) {
        this.mPhoneNo = mPhoneNo;
    }

    public String getmEmailId() {
        return mEmailId;
    }

    public void setmEmailId(String mEmailId) {
        this.mEmailId = mEmailId;
    }

    public String getmState() {
        return mState;
    }

    public void setmState(String mState) {
        this.mState = mState;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }
}
