package com.mobilemedicsolutions.mymedicinereminder.data.database;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

public class MedicineReminderDB extends SQLiteOpenHelper{
    private static int DATABASE_VERSION = 1;
    public static final String TABLE_DRUGS = "drugsManager";
    private static final String DATABASE_NAME = "MyMedicineReminder";

    private static final String DATABASE_CREATE_DRUGS =
        "create table "+ TABLE_DRUGS +" (" +
            DrugsContract._ID + " integer primary key autoincrement, " +
            DrugsContract.NAME + " text not null, " +
            DrugsContract.DESCRIPTION + " text not null, " +
            DrugsContract.SCHEDULE_TYPE + " int not null, " +
            DrugsContract.SCHEDULED_DAYS + " int not null)";

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
