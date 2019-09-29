package com.syncode.moviecataglogv3.localdata;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class SharedPreference {


    private Activity activity;

    private SharedPreferences sharedPreferences;

    private final static String KEY_LANGUAGE = "language";
    private final static String ALARM = "alarm";

    public SharedPreference(Activity activity) {
        this.activity = activity;
    }

    public void setPrefLang(String LANGUAGE, String lang2) {
        sharedPreferences = activity.getSharedPreferences(KEY_LANGUAGE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("lang", LANGUAGE);
        editor.putString("lang2", lang2);
        editor.apply();
    }

    public String getReferencesLang(String lang) {
        sharedPreferences = activity.getSharedPreferences(KEY_LANGUAGE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(lang, "");
    }


    public void setPrefAlarmDaily(boolean dailySwitch) {
        sharedPreferences = activity.getSharedPreferences(ALARM, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("daily", dailySwitch);
        editor.apply();
    }

    public void setPrefAlarmRelease(boolean dailySwitch) {
        sharedPreferences = activity.getSharedPreferences(ALARM, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean("release", dailySwitch);
        editor.apply();
    }


    public boolean getAlarmRelease(String key) {
        sharedPreferences = activity.getSharedPreferences(ALARM, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }
}
