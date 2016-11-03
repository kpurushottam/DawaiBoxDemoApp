package com.krp.android.demoapp;

import android.app.Application;
import android.content.SharedPreferences;

import com.krp.android.demoapp.utils.Constants;

/**
 * Created by Surati on 11/3/2016.
 */
public class DawaiBoxApplication extends Application {

    private final String SHARED_PREFS_NAME = "com.krp.android.demo.dawaibox.prefs";
    private static DawaiBoxApplication mInstance;

    private SharedPreferences mPrefs;

    public static DawaiBoxApplication getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        mPrefs = getSharedPreferences(SHARED_PREFS_NAME, MODE_PRIVATE);
    }

    public void setUser(String user) {
        mPrefs.edit().putString(Constants.PREF_USER, user).apply();
    }
    public String getUser() {
        return mPrefs.getString(Constants.PREF_USER, "");
    }

    public void setPassword(String pwd) {
        mPrefs.edit().putString(Constants.PREF_PWD, pwd).apply();
    }
    public String getPassword() {
        return mPrefs.getString(Constants.PREF_PWD, "");
    }
}
