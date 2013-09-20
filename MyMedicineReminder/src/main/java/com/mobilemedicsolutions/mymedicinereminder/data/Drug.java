package com.mobilemedicsolutions.mymedicinereminder.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

import java.util.HashSet;

public class Drug {
    private long _id;
    private final String name;
    private final String description;
    private final ScheduleType scheduleType;
    private final HashSet<ScheduledDay> scheduledDays;

    public ContentValues asContentValues()
    {
        ContentValues cv = new ContentValues();
        cv.put(DrugsContract.NAME,name);
        cv.put(DrugsContract.DESCRIPTION,description);
        cv.put(DrugsContract.SCHEDULE_TYPE,scheduleType.Value);
        Integer days = 0;
        for (ScheduledDay sd : scheduledDays)
        {
            days |= sd.Value;
        }
        cv.put(DrugsContract.SCHEDULED_DAYS,days);
        return cv;
    }

    public Drug (Cursor cursor)
    {
        _id = cursor.getLong(cursor.getColumnIndex(DrugsContract._ID));
        name = cursor.getString(cursor.getColumnIndex(DrugsContract.NAME));
        description = cursor.getString(cursor.getColumnIndex(DrugsContract.DESCRIPTION));
        scheduleType = ScheduleType.parse(cursor.getInt(cursor.getColumnIndex(DrugsContract.SCHEDULE_TYPE)));
        scheduledDays = ScheduledDay.parse(cursor.getInt(cursor.getColumnIndex(DrugsContract.SCHEDULED_DAYS)));
    }

    public Drug (String name, String description, ScheduleType scheduleType, HashSet<ScheduledDay> scheduledDays)
    {
        this.name = name;
        this.description = description;
        this.scheduleType = scheduleType;
        this.scheduledDays = scheduledDays;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    /*public ScheduleType getScheduleType()
    {
        return scheduleType;
    }*/

    public HashSet<ScheduledDay> getScheduledDays()
    {
        return scheduledDays;
    }
}
