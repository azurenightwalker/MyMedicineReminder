package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduleType;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduledDay;
import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

import java.util.HashSet;

public class NewMedicine extends BaseActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);
        alertMinute = PreferenceHelper.getInstance().getInt("DefaultAlertMinute",0);
        alertHour = PreferenceHelper.getInstance().getInt("DefaultAlertHour",12);
        ((Spinner) findViewById(R.id.schedType)).setOnItemSelectedListener(this);
        ((TextView)findViewById(R.id.notifyTime)).setText(String.format(
                getResources().getString(R.string.notifyTime),alertHour,alertMinute));
    }

    public void onSave(@SuppressWarnings("UnusedParameters") View v)
    {
        String name = "";
        String description = "";
        int interval = 0;
        EditText ev = (EditText)findViewById(R.id.name);
        if (ev != null)
            name = ev.getText().toString();
        ev = (EditText)findViewById(R.id.description);
        if (ev != null)
            description = ev.getText().toString();
        ev = (EditText)findViewById(R.id.dayInt);
        if (ev != null)
            interval = Integer.parseInt(ev.getText().toString());

        ScheduleType scheduleType = ScheduleType.parse(
                1<<((Spinner) findViewById(R.id.schedType)).getSelectedItemPosition());
        HashSet<ScheduledDay> scheduledDays = new HashSet<ScheduledDay>();
        if (((CheckBox)findViewById(R.id.schedMonday)).isChecked())
            scheduledDays.add(ScheduledDay.Monday);
        if (((CheckBox)findViewById(R.id.schedTuesday)).isChecked())
            scheduledDays.add(ScheduledDay.Tuesday);
        if (((CheckBox)findViewById(R.id.schedWednesday)).isChecked())
            scheduledDays.add(ScheduledDay.Wednesday);
        if (((CheckBox)findViewById(R.id.schedThursday)).isChecked())
            scheduledDays.add(ScheduledDay.Thursday);
        if (((CheckBox)findViewById(R.id.schedFriday)).isChecked())
            scheduledDays.add(ScheduledDay.Friday);
        if (((CheckBox)findViewById(R.id.schedSaturday)).isChecked())
            scheduledDays.add(ScheduledDay.Saturday);
        if (((CheckBox)findViewById(R.id.schedSunday)).isChecked())
            scheduledDays.add(ScheduledDay.Sunday);

        DrugHelper.InsertDrug(NewMedicine.this, new Drug(name, description, scheduleType,
                scheduledDays, alertHour, alertMinute,interval));
        finish();
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
        findViewById(R.id.schedDayRow).setVisibility(View.GONE);
        findViewById(R.id.dayIntRow).setVisibility(View.GONE);
        if (position == 2)
            findViewById(R.id.schedDayRow).setVisibility(View.VISIBLE);
        else if (position == 3)
            findViewById(R.id.dayIntRow).setVisibility(View.VISIBLE);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void openDatePicker(View v)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TimePickerFragment.newInstance(alertHour,alertMinute).show(ft, "dialog");
    }
}
