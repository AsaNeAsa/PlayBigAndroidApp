package com.playbig.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;
import android.text.TextUtils;



import java.io.Serializable;
import java.util.Map;


public class PreferenceUtility {


    private SharedPreferences sharedPreference;
    private static PreferenceUtility session;

    public PreferenceUtility() {

    }

    public static PreferenceUtility getSession(Context context) {
        if (session == null) {
            session = new PreferenceUtility();
        }
        session.sharedPreference = context.getSharedPreferences("PlAYBIG", Context.MODE_PRIVATE);
        return session;
    }

    public static PreferenceUtility getSession(Context context, String prefName) {
        if (session == null) {
            session = new PreferenceUtility();
        }
        session.sharedPreference = context.getSharedPreferences(prefName, Context.MODE_PRIVATE);
        return session;
    }




    public SharedPreferences getPref() {
        return sharedPreference;
    }

    public void putBoolean(String key, boolean value) {
        sharedPreference.edit().putBoolean(key, value).commit();
    }

    public void putString(String key, String value) {
        sharedPreference.edit().putString(key, value).commit();
    }

    public void putInt(String key, int value) {
        sharedPreference.edit().putInt(key, value).commit();
    }

    public void setDefaultView(int a) {
        sharedPreference.edit().putInt("default_view", a).commit();
    }

    public int getDefaultView() {
        return sharedPreference.getInt("default_view", ConstantCodes.DEFAULT_VIEW_GRID);
    }

    public boolean getBoolean(String key) {
        return sharedPreference.getBoolean(key, false);
    }

    public String getString(String key) {
        return sharedPreference.getString(key, "");
    }

    public int getInt(String key) {
        return sharedPreference.getInt(key, 0);
    }

    public void clear(String key) {
        sharedPreference.edit().remove(key).commit();
    }

    public void clearAll() {
        Map<String, ?> keys = sharedPreference.getAll();

        SharedPreferences.Editor editor = sharedPreference.edit();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {

            //sharedPreference.getString(key, "");
            if (!entry.getKey().equalsIgnoreCase(ConstantCodes.PREFERENCE_KEY_SESSIONID)) {
                editor.remove(entry.getKey());
                editor.apply();
            }
            /*Log.d("map values",entry.getKey() + ": " +
                    entry.getValue().toString());*/
        }

        //sharedPreference.edit().clear().commit();
    }

    public void resetAndClear() {
        sharedPreference.edit().clear().commit();
    }

    public boolean contains(String key) {
// TODO Auto-generated method stub
        return sharedPreference.contains(key);
    }
}
