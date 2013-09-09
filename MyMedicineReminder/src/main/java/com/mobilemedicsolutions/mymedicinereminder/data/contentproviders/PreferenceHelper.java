package com.mobilemedicsolutions.mymedicinereminder.data.contentproviders;

import android.content.Context;
import android.preference.PreferenceManager;

public class PreferenceHelper {
    private static PreferenceHelper ourInstance;
    private final Context mContext;

    public static PreferenceHelper getInstance() {
        return ourInstance;
    }

    public static void init(Context context)
    {
        ourInstance = new PreferenceHelper(context);
    }

    private PreferenceHelper(Context context) {
        mContext = context;
    }

    public boolean getBoolean(String property) {
        return getBoolean(property,true);
    }

    public boolean getBoolean(String property, boolean defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(mContext)
                .getBoolean(property,defaultValue);
    }

    public void setBoolean(String property, boolean value) {
        PreferenceManager.getDefaultSharedPreferences(mContext).edit()
                .putBoolean(property, value).apply();
    }
}
