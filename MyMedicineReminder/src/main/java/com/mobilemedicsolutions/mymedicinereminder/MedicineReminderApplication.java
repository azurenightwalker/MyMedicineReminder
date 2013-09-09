package com.mobilemedicsolutions.mymedicinereminder;

import android.app.Application;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

public class MedicineReminderApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.init(getApplicationContext());
    }
}
