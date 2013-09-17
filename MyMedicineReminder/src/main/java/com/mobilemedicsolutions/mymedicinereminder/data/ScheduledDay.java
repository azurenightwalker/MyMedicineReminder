package com.mobilemedicsolutions.mymedicinereminder.data;

public enum ScheduledDay {

    Monday(1<<0),
    Tuesday(1<<1),
    Wednesday(1<<2),
    Thursday(1<<3),
    Friday(1<<4),
    Saturday(1<<5),
    Sunday(1<<6);

    public final int Value;

    ScheduledDay(final int val) {
        this.Value = val;
    }

}
