package com.mobilemedicsolutions.mymedicinereminder;

import android.app.ActionBar;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.os.Build;
import android.view.View;
import android.widget.Spinner;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

public class Configure extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configure);
        // Show the Up button in the action bar.
        setupActionBar();
    }

    /**
     * Set up the {@link android.app.ActionBar}, if the API is available.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    private void setupActionBar() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            ActionBar ab = getActionBar();
            if (ab != null)
                ab.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.configure, menu);
        return true;
    }
    

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // This ID represents the Home or Up button. In the case of this
                // activity, the Up button is shown. Use NavUtils to allow users
                // to navigate up one level in the application structure. For
                // more details, see the Navigation pattern on Android Design:
                //
                // http://developer.android.com/design/patterns/navigation.html#up-vs-back
                //
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void savePreferences(@SuppressWarnings("UnusedParameters") View view) {
        PreferenceHelper preferenceHelper = PreferenceHelper.getInstance();
        preferenceHelper.setInt("Gender",
                ((Spinner) findViewById(R.id.gender)).getSelectedItemPosition());
        preferenceHelper.setInt("Complexity",
                ((Spinner) findViewById(R.id.complexity)).getSelectedItemPosition());
        this.setResult(Activity.RESULT_OK);
        this.finishActivity(Dashboard.CONFIGURE_REQUEST);
        this.finish();
    }
}
