package com.mobilemedicsolutions.mymedicinereminder.data;

import java.util.HashSet;

public enum ScheduledDay {

    Sunday(1<<1),
    Monday(1<<2),
    Tuesday(1<<3),
    Wednesday(1<<4),
    Thursday(1<<5),
    Friday(1<<6),
    Saturday(1<<7);

    public final int Value;

    ScheduledDay(final int val) {
        this.Value = val;
    }

    public static HashSet<ScheduledDay> parse(final int value)
    {
        HashSet<ScheduledDay> days = new HashSet<ScheduledDay>();
        for(ScheduledDay day : ScheduledDay.values())
        {
            if ((value & day.Value) == day.Value) days.add(day);
        }
        return days;
    }
}
