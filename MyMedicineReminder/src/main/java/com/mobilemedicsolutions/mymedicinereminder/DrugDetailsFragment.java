package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduledDay;

import java.text.SimpleDateFormat;

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
        final View v = inflater.inflate(R.layout.drug_details, container, false);
        final TextView tv = (TextView) v.findViewById(R.id.drug_description);
        if (tv != null)
            tv.setText(drug.getDescription());
        final TextView tn = (TextView) v.findViewById(R.id.drug_takeNext);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
        if (tn != null)
            tn.setText(sdf.format(drug.getNextAlertTime()));
        for(ScheduledDay day : drug.getScheduledDays())
        {
            Toast.makeText(getActivity(),day.toString(), Toast.LENGTH_SHORT).show();
        }
        return v;
    }
}
