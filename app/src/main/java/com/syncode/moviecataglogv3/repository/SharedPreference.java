package com.syncode.moviecataglogv3.repository;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {


    private Activity activity;

    private SharedPreferences sharedPreferences;

    private final static String KEY_LANGUAGE = "language";

    public SharedPreference(Activity activity) {
        this.activity = activity;
    }

    public void setPref(String LANGUAGE) {
        sharedPreferences = activity.getSharedPreferences(KEY_LANGUAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", LANGUAGE);
        editor.apply();
    }

    public String getReferences() {
        sharedPreferences = activity.getSharedPreferences(KEY_LANGUAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getString("lang", "");
    }
}
