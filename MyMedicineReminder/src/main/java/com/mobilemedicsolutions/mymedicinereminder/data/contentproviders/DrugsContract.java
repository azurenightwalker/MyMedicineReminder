package com.mobilemedicsolutions.mymedicinereminder.data.contentproviders;

import android.net.Uri;

public final class DrugsContract {
    public static final String _ID = "_id";
    public static final String NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String SCHEDULE_TYPE = "schedule";
    public static final String SCHEDULED_DAYS = "days";

    public static final Uri CONTENT_URI =
            Uri.parse("content://com.mobilemedicsolutions.mymedicinereminder/drugs");

    public static final String[] PROJECTION = new String[] {
        DrugsContract._ID,
        DrugsContract.NAME,
        DrugsContract.DESCRIPTION,
        DrugsContract.SCHEDULE_TYPE,
        DrugsContract.SCHEDULED_DAYS
    };
}