package com.mobilemedicsolutions.mymedicinereminder.data;

import android.content.ContentValues;
import android.database.Cursor;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;

public class Drug {
    private long _id;
    private final String name;
    private final String description;
    private final ScheduleType scheduleType;
    private final HashSet<ScheduledDay> scheduledDays;
    private final int alertHour;
    private final int alertMinute;
    private final Date lastAlert;
    private final int dayInterval;

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
        cv.put(DrugsContract.ALERT_HOUR,alertHour);
        cv.put(DrugsContract.ALERT_MINUTE,alertMinute);
        cv.put(DrugsContract.LAST_ALERT,lastAlert.getTime());
        cv.put(DrugsContract.DAY_INTERVAL,dayInterval);
        return cv;
    }

    public Drug (Cursor cursor)
    {
        _id = cursor.getLong(cursor.getColumnIndex(DrugsContract._ID));
        name = cursor.getString(cursor.getColumnIndex(DrugsContract.NAME));
        description = cursor.getString(cursor.getColumnIndex(DrugsContract.DESCRIPTION));
        scheduleType = ScheduleType.parse(cursor.getInt(cursor.getColumnIndex(DrugsContract.SCHEDULE_TYPE)));
        scheduledDays = ScheduledDay.parse(cursor.getInt(cursor.getColumnIndex(DrugsContract.SCHEDULED_DAYS)));
        alertHour = cursor.getInt(cursor.getColumnIndex(DrugsContract.ALERT_HOUR));
        alertMinute = cursor.getInt(cursor.getColumnIndex(DrugsContract.ALERT_MINUTE));
        lastAlert = new Date(cursor.getLong(cursor.getColumnIndex(DrugsContract.LAST_ALERT)));
        dayInterval = cursor.getInt(cursor.getColumnIndex(DrugsContract.DAY_INTERVAL));
    }

    public Drug (String name, String description, ScheduleType scheduleType,
                 HashSet<ScheduledDay> scheduledDays, int alertHour, int alertMinute, int dayInterval)
    {
        this.name = name;
        this.description = description;
        this.scheduleType = scheduleType;
        this.scheduledDays = scheduledDays;
        this.alertHour = alertHour;
        this.alertMinute = alertMinute;
        this.lastAlert = new Date();
        this.dayInterval = dayInterval;
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

    public Date getNextAlertTime() {
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        switch (scheduleType)
        {
            case Daily:
                if (c.get(Calendar.HOUR) > alertHour || (c.get(Calendar.HOUR) == alertHour && c.get(Calendar.MINUTE) > alertMinute))
                    c.add(Calendar.DATE,1);
                break;
            case SetDays:
                break;
            case SetInterval:
                c.setTime(lastAlert);
                c.add(Calendar.DATE,dayInterval);
                break;
            case Weekly:
                c.setTime(lastAlert);
                c.add(Calendar.DATE,7);
                break;
        }
        int a = c.get(Calendar.HOUR);
        int b = c.get(Calendar.MINUTE);
        int d = c.get(Calendar.DATE);
        c.set(Calendar.HOUR,alertHour);
        c.set(Calendar.MINUTE,alertMinute);
        a = c.get(Calendar.HOUR);
        b = c.get(Calendar.MINUTE);
        d = c.get(Calendar.DATE);
        return c.getTime();
    }
}
