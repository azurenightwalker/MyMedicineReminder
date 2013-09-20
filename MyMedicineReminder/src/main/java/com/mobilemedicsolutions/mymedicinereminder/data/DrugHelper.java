package com.mobilemedicsolutions.mymedicinereminder.data;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DrugHelper {
    public static List<Drug> GetAllDrugs(Context context)
    {
        Cursor c = context.getContentResolver().query(
                DrugsContract.CONTENT_URI,null,null,null,null
        );
        List<Drug> drugs = new ArrayList<Drug>();
        if (c != null)
        {
            if (c.moveToFirst())
            {
                do
                {
                    drugs.add(new Drug(c));
                } while (c.moveToNext());
            }
            c.close();
        }
        return drugs;
    }

    public static Loader<Cursor> GetAllDrugsAsync(Context context)
    {
        return new CursorLoader(context, DrugsContract.CONTENT_URI, null, null, null, null);
    }

    public static Loader<Cursor> GetTodayDrugsAsync(Context context)
    {
        Calendar calendar = Calendar.getInstance();
        int today = calendar.get(Calendar.DAY_OF_WEEK);
        ScheduledDay day = (ScheduledDay)ScheduledDay.parse(1<<today).toArray()[0];
        String selection = DrugsContract.SCHEDULED_DAYS + " & " + day.Value + " = " + day.Value;
        return new CursorLoader(context, DrugsContract.CONTENT_URI, null, selection, null, null);
    }

    public static Uri InsertDrug(Context context, Drug drug)
    {
        return context.getContentResolver().insert(DrugsContract.CONTENT_URI,drug.asContentValues());
    }
}
