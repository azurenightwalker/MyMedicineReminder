package com.mobilemedicsolutions.mymedicinereminder.data.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

public class MedicineReminderDB extends SQLiteOpenHelper{
    public static final String TABLE_DRUGS = "drugsManager";
    private static final String DATABASE_NAME = "MyMedicineReminder";

    private static final String DATABASE_CREATE_DRUGS =
        "create table "+ TABLE_DRUGS +" (" +
            DrugsContract._ID + " integer primary key autoincrement, " +
            DrugsContract.NAME + " text not null, " +
            DrugsContract.DESCRIPTION + " text not null, " +
            DrugsContract.SCHEDULE_TYPE + " int not null, " +
            DrugsContract.SCHEDULED_DAYS + " int null, " +
            DrugsContract.ALERT_HOUR + " int not null, " +
            DrugsContract.ALERT_MINUTE + " int not null, " +
            DrugsContract.LAST_ALERT + " int not null, " +
            DrugsContract.DAY_INTERVAL + " int null)";

    public MedicineReminderDB(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(DATABASE_CREATE_DRUGS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i2) {

    }
}
