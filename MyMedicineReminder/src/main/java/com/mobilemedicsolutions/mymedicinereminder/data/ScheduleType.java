package com.mobilemedicsolutions.mymedicinereminder.data;

public enum ScheduleType {

    Daily(1<<0),
    Weekly(1<<1),
    SetDays(1<<2),
    SetInterval(1<<3);

    public final int Value;

    ScheduleType(final int val) {
        this.Value = val;
    }

}
