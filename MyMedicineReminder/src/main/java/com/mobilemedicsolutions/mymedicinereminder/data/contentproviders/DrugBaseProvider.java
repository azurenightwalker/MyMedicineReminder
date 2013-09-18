package com.mobilemedicsolutions.mymedicinereminder.data.contentproviders;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.text.TextUtils;

import com.mobilemedicsolutions.mymedicinereminder.data.database.MedicineReminderDB;

public class DrugBaseProvider extends ContentProvider
{
    private static final int DRUG = 0;
    private static final int DRUG_ID = 1;

    public static final String PROVIDER_NAME =
            "com.mobilemedicsolutions.mymedicinereminder";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);
    private static final UriMatcher uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
    static {
        uriMatcher.addURI(PROVIDER_NAME, "drugs", DRUG);
        uriMatcher.addURI(PROVIDER_NAME, "drugs/#", DRUG_ID);
    }

    private MedicineReminderDB mMedicineReminderDB;

    public boolean onCreate() {
        mMedicineReminderDB = new MedicineReminderDB(getContext());
        return true;
    }

    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Default projection if none supplied
        if(projection == null) projection = getDefaultProjection(uri);

        // Defaults & ID's
        switch(uriMatcher.match(uri))
        {
            case DRUG:
                if (TextUtils.isEmpty(sortOrder)) sortOrder = "_ID ASC";
                break;
            case DRUG_ID:
                selection += DrugsContract._ID + " = " + uri.getLastPathSegment();
                break;
            default:
                return null;
        }
        final SQLiteDatabase db = mMedicineReminderDB.getReadableDatabase();
        if (db != null)
            return db.query(findTableName(uri), projection, selection, selectionArgs, null, null, sortOrder);
        return null;
    }

    public String getType(Uri uri) {
        switch(uriMatcher.match(uri))
        {
            case DRUG:
                return "vnd.android.cursor.dir/vnd.com.mobilemedicsolutions.mymedicinereminder.drug";
            case DRUG_ID:
                return "vnd.android.cursor.item/vnd.com.mobilemedicsolutions.mymedicinereminder.drug";
            default:
                return null;
        }
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        final SQLiteDatabase db = mMedicineReminderDB.getWritableDatabase();
        if (db != null) {
            long id = db.insert(findTableName(uri),"",contentValues);
            db.close();
            return ContentUris.withAppendedId(uri,id);
        }
        return null;
    }
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mMedicineReminderDB.getWritableDatabase();
        if (db != null) {
            int affectedRows = db.delete(findTableName(uri),selection,selectionArgs);
            db.close();
            return affectedRows;
        }
        return 0;
    }

    public int update(Uri uri, ContentValues contentValues, String selection, String[] selectionArgs) {
        final SQLiteDatabase db = mMedicineReminderDB.getWritableDatabase();
        if (db != null) {
            int affectedRows = db.update(findTableName(uri),contentValues,selection,selectionArgs);
            db.close();
            return affectedRows;
        }
        return 0;
    }

    private String findTableName(Uri uri)
    {
        switch(uriMatcher.match(uri))
        {
            case DRUG:
                return MedicineReminderDB.TABLE_DRUGS;
            case DRUG_ID:
                return MedicineReminderDB.TABLE_DRUGS;
            default:
                return null;
        }
    }

    private String[] getDefaultProjection(Uri uri)
    {
        switch(uriMatcher.match(uri))
        {
            case DRUG:
                return DrugsContract.PROJECTION;
            case DRUG_ID:
                return DrugsContract.PROJECTION;
            default:
                return null;
        }
    }
}
