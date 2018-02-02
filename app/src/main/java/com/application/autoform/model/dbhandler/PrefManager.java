package com.application.autoform.model.dbhandler;

import android.content.Context;
import android.content.SharedPreferences;

import com.application.autoform.model.bean.Car;
import com.application.autoform.networknew.GsonFactory;

/**
 * Created by Madhuri on 2/9/2016.
 */
public class PrefManager {
    // Shared preferences file name
    private static final String PREF_NAME = "AUTOFORM";
    private static final String KEY_VEHICLE = "VEHICLE";
    // Shared Preferences
    SharedPreferences pref;
    // Editor for Shared preferences
    SharedPreferences.Editor editor;
    // Context
    Context _context;
    // Shared pref mode
    int PRIVATE_MODE = 0;
    // All Shared Preferences Keys

    public PrefManager(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public Car getVehicle() {
        return GsonFactory.getGson().fromJson(pref.getString(KEY_VEHICLE, null), Car.class);
    }

    public void setVehicle(Car vehicle) {
        editor.putString(KEY_VEHICLE, GsonFactory.getGson().toJson(vehicle));
        editor.commit();
    }

    public void clearVehicle() {
        editor.remove(KEY_VEHICLE);
        editor.commit();
    }
}