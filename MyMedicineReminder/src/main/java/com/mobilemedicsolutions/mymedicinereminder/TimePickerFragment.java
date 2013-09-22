package com.mobilemedicsolutions.mymedicinereminder;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TimePicker;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    private int hour;
    private int minute;

    static TimePickerFragment newInstance(int hour, int minute) {
        TimePickerFragment f = new TimePickerFragment();

        // Supply num input as an argument.
        Bundle args = new Bundle();
        args.putInt("Hour", hour);
        args.putInt("Minute", minute);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        hour = getArguments().getInt("Hour");
        minute = getArguments().getInt("Minute");
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Create a new instance of TimePickerDialog and return it
        return new TimePickerDialog(getActivity(), this, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        ((BaseActivity)getActivity()).setDate(hourOfDay,minute);
    }
}
