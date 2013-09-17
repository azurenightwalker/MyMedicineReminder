package com.mobilemedicsolutions.mymedicinereminder.data.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobilemedicsolutions.mymedicinereminder.data.ScheduleType;

import java.util.EnumSet;

public class MedicineReminderDB extends SQLiteOpenHelper{
    private static int DATABASE_VERSION = 1;
    private static final String TABLE_DRUGS = "drugsManager";
    private static final String DATABASE_NAME = "MyMedicineReminder";

    // Drugs Table fields
    private static final String _ID = "_id";
    private static final String NAME = "name";
    private static final String SCHEDULE_TYPE = "schedule";
    private static final String SCHEDULED_DAYS = "days";
    private static final String DATABASE_CREATE_DRUGS =
            "create table "+ TABLE_DRUGS +" (" +
                    _ID + " integer primary key autoincrement, " +
                    NAME + " text not null, " +
                    SCHEDULE_TYPE + " int not null, " +
                    SCHEDULED_DAYS + " int not null)";


    public MedicineReminderDB(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_DRUGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
