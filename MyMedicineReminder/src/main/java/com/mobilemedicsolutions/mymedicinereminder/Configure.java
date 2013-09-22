package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

public class Configure extends BaseActivity {

    private PreferenceHelper preferenceHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        preferenceHelper = PreferenceHelper.getInstance();
        ((Spinner) findViewById(R.id.gender)).setSelection(preferenceHelper.getInt("Gender",0));
        ((Spinner) findViewById(R.id.complexity)).setSelection(preferenceHelper.getInt("Complexity",0));
        alertMinute = preferenceHelper.getInt("DefaultAlertMinute",0);
        alertHour = preferenceHelper.getInt("DefaultAlertHour",12);
        ((TextView)findViewById(R.id.notifyTime)).setText(String.format(
                getResources().getString(R.string.notifyTime),alertHour,alertMinute));
    }

    public void savePreferences(@SuppressWarnings("UnusedParameters") View view) {
        preferenceHelper.setInt("Gender",
                ((Spinner) findViewById(R.id.gender)).getSelectedItemPosition());
        preferenceHelper.setInt("Complexity",
                ((Spinner) findViewById(R.id.complexity)).getSelectedItemPosition());
        preferenceHelper.setInt("DefaultAlertMinute",alertMinute);
        preferenceHelper.setInt("DefaultAlertHour",alertHour);
        setResult(Activity.RESULT_OK);
        finish();
    }

    public void openDatePicker(View v)
    {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        TimePickerFragment.newInstance(alertHour,alertMinute).show(ft, "dialog");
    }
}
