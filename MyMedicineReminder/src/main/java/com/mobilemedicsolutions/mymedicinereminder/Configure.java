package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Spinner;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

public class Configure extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
    }

    public void savePreferences(@SuppressWarnings("UnusedParameters") View view) {
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        preferenceHelper.setInt("Gender",
                ((Spinner) findViewById(R.id.gender)).getSelectedItemPosition());
        preferenceHelper.setInt("Complexity",
                ((Spinner) findViewById(R.id.complexity)).getSelectedItemPosition());
        setResult(Activity.RESULT_OK);
        finish();
    }
}
