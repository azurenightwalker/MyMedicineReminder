package com.mobilemedicsolutions.mymedicinereminder.data;

public enum ScheduleType {

    Daily(1),
    Weekly(1<<1),
    SetDays(1<<2),
    SetInterval(1<<3);

    public final int Value;

    ScheduleType(final int val) {
        this.Value = val;
    }

    public static ScheduleType parse(final int val)
    {
        switch (val)
        {
            case 1:
                return  ScheduleType.Daily;
            case 1<<1:
                return  ScheduleType.Weekly;
            case 1<<2:
                return  ScheduleType.SetDays;
            case 1<<3:
                return  ScheduleType.SetInterval;
            default:
                return null;
        }
    }

}
