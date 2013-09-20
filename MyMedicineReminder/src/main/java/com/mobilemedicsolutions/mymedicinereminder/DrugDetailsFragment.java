package com.mobilemedicsolutions.mymedicinereminder;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.CursorAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugCursorAdapter;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduledDay;

public class DrugDetailsFragment extends DialogFragment {

    private Drug drug;

    static DrugDetailsFragment newInstance(long drugId) {
        DrugDetailsFragment f = new DrugDetailsFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putLong("DrugID", drugId);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        drug = DrugHelper.GetDrug(getActivity(),getArguments().getLong("DrugID"));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle(drug.getName());
        View v = inflater.inflate(R.layout.drug_details, container, false);
        ((TextView) v.findViewById(R.id.drug_description)).setText(drug.getDescription());
        for(ScheduledDay day : drug.getScheduledDays())
        {
            Toast.makeText(getActivity(),day.toString(), Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
