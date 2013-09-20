package com.mobilemedicsolutions.mymedicinereminder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;

import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.DrugsContract;

public class MedicineListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter mAdapter;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] uiBindFrom = { DrugsContract.NAME };
        int[] uiBindTo = {android.R.id.text1};
        mAdapter = new SimpleCursorAdapter(getActivity(),android.R.layout.simple_list_item_1,
                null, uiBindFrom, uiBindTo,0);
        setListAdapter(mAdapter);
        this.getLoaderManager().initLoader(1, null, this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(final int i, final Bundle bundle) {
        Bundle extras = getActivity().getIntent().getExtras();
        if (extras != null && extras.getBoolean("Today",false))
            return DrugHelper.GetTodayDrugsAsync(getActivity());
        return DrugHelper.GetAllDrugsAsync(getActivity());
    }

    @Override
    public void onLoadFinished(final Loader<Cursor> cursorLoader, final Cursor cursor) {
        if(mAdapter!=null && cursor!=null)
            mAdapter.swapCursor(cursor);
        else
            Log.v("MMR", "OnLoadFinished: mAdapter is null");
    }

    @Override
    public void onLoaderReset(final Loader<Cursor> cursorLoader) {
        if(mAdapter!=null)
            mAdapter.swapCursor(null);
        else
            Log.v("MMR","OnLoadFinished: mAdapter is null");
    }
}
