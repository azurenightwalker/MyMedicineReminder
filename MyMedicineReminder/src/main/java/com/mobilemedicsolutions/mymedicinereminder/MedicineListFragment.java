package com.mobilemedicsolutions.mymedicinereminder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.mobilemedicsolutions.mymedicinereminder.data.DrugCursorAdapter;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;

public class MedicineListFragment extends ListFragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private CursorAdapter mAdapter;

    @Override
    public  void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAdapter = new DrugCursorAdapter(getActivity());
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

    @Override
    public void onListItemClick(final ListView list, final View view, final int position, final long id) {
        super.onListItemClick(list, view, position, id);
        openDrugDetails(id);
    }

    void openDrugDetails(long id)
    {
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);

        // Create and show the dialog.
        DialogFragment newFragment = DrugDetailsFragment.newInstance(id);
        newFragment.show(ft, "dialog");
    }
}
