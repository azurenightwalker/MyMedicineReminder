package com.mobilemedicsolutions.mymedicinereminder.data;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.CursorAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobilemedicsolutions.mymedicinereminder.R;
import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

public class DrugCursorAdapter extends CursorAdapter {

    public DrugCursorAdapter(final Context context) {
        super(context,null,0);
    }

    @Override
    public View newView(final Context context, final Cursor cursor, final ViewGroup parent) {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final RelativeLayout ret = (RelativeLayout) inflater.inflate(R.layout.drug_list_item, parent, false);
        if (ret != null)
        {
            return populateView(cursor, ret);
        }
        return null;
    }

    @Override
    public void bindView(final View view, final Context context, final Cursor cursor) {
        populateView(cursor,view);
    }

    private View populateView(Cursor cursor, View ret) {
        final TextView mName = (TextView) ret.findViewById(R.id.drug_name);
        final TextView mDesc = (TextView) ret.findViewById(R.id.drug_description);

        final int nameIdx = cursor.getColumnIndexOrThrow(DrugsContract.NAME);
        final int desc = cursor.getColumnIndex(DrugsContract.DESCRIPTION);
        final String name = cursor.getString(nameIdx);
        final String descriprion = cursor.getString(desc);

        mName.setText(name);
        mDesc.setText(descriprion);
        return ret;
    }


}
