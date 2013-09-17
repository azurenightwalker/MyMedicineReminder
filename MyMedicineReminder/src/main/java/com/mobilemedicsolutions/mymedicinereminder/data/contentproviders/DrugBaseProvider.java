package com.mobilemedicsolutions.mymedicinereminder.data.contentproviders;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;

public class DrugBaseProvider extends ContentProvider
{
    private static final int DRUG = 0;
    private static final int DRUG_ID = 1;

    public static final String PROVIDER_NAME =
            "com.mobilemedicsolutions.mymedicinereminder";
    public static final Uri CONTENT_URI = Uri.parse("content://" + PROVIDER_NAME);
    private static final UriMatcher uriMatcher;
    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(PROVIDER_NAME, "drugs", DRUG);
        uriMatcher.addURI(PROVIDER_NAME, "drugs/#", DRUG_ID);
    }

    public boolean onCreate() {
        return true;
    }

    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        switch(uriMatcher.match(uri))
        {
            default:
                return null;
        }
    }

    public String getType(Uri uri) {
        switch(uriMatcher.match(uri))
        {
            case DRUG:
                return "vnd.android.cursor.dir/vnd.com.mobilemedicsolutions.mymedicinereminder.drug";
            default:
                return null;
        } // mime type : vnd.android.cursor.dir/vnd.com.example.provider.table1
    }

    public Uri insert(Uri uri, ContentValues contentValues) {
        switch(uriMatcher.match(uri))
        {
            case DRUG:

            default:
                return null;
        }
    }
    public int delete(Uri uri, String s, String[] strings) {
        return 0; // Number affected
    }

    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0; // Number affected
    }

}
