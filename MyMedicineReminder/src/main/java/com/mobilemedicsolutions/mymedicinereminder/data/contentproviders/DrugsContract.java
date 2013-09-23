package com.mobilemedicsolutions.mymedicinereminder.data.contentproviders;

import android.net.Uri;

public final class DrugsContract {
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String SCHEDULE_TYPE = "schedule";
    public static final String SCHEDULED_DAYS = "days";
    public static final String ALERT_HOUR = "hour";
    public static final String ALERT_MINUTE = "minute";
    public static final String LAST_ALERT = "lastAlert";
    public static final String DAY_INTERVAL = "dayInterval";

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.mobilemedicsolutions.mymedicinereminder/drugs");

    public static final String[] PROJECTION = new String[] {
        DrugsContract._ID,
        DrugsContract.NAME,
        DrugsContract.DESCRIPTION,
        DrugsContract.SCHEDULE_TYPE,
        DrugsContract.SCHEDULED_DAYS,
        DrugsContract.ALERT_HOUR,
        DrugsContract.ALERT_MINUTE,
        DrugsContract.LAST_ALERT,
        DrugsContract.DAY_INTERVAL
    };
}
