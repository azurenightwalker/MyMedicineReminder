package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduleType;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduledDay;

import java.util.HashSet;

public class NewMedicine extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_medicine);
    }

    public void onSave(@SuppressWarnings("UnusedParameters") View v)
    {
        String name = ((EditText)findViewById(R.id.name)).getText().toString();
        String description = ((EditText)findViewById(R.id.description)).getText().toString();
        ScheduleType scheduleType = ScheduleType.parse(
                1<<((Spinner) findViewById(R.id.schedType)).getSelectedItemPosition());
        HashSet<ScheduledDay> scheduledDays = ScheduledDay.parse(
                1<<(((Spinner) findViewById(R.id.schedDays)).getSelectedItemPosition()+1));

        DrugHelper.InsertDrug(NewMedicine.this, new Drug(name, description, scheduleType, scheduledDays));
        finish();
    }
}
