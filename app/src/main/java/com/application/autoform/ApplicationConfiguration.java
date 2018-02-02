package com.application.autoform;

import android.app.Application;
import android.content.Context;
import android.content.ContextWrapper;

/**
 * Created by sandeep.g9 on 3/10/2017.
 */

public class ApplicationConfiguration {
//    Context mApplicationContext;

    String mAccessoryType;

    boolean mShouldShowAdvertiserPage;
    private static final ApplicationConfiguration ourInstance = new ApplicationConfiguration();

    public static ApplicationConfiguration getInstance() {
        return ourInstance;
    }

    private ApplicationConfiguration() {
    }

    public String getmAccessoryType() {
        return mAccessoryType;
    }

    public void setmAccessoryType(String mAccessoryType) {
        this.mAccessoryType = mAccessoryType;
    }

    public boolean getmShouldShowAdvertiserPage() {
        return mShouldShowAdvertiserPage;
    }

    public void setmShouldShowAdvertiserPage(boolean mShouldShowAdvertiserPage) {
        this.mShouldShowAdvertiserPage = mShouldShowAdvertiserPage;
    }

    /*   public Context getApplicationContext() {
        return mApplicationContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mApplicationContext = this;
    }*/
}
