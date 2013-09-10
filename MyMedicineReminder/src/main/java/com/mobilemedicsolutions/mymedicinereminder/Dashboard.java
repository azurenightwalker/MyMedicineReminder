package com.mobilemedicsolutions.mymedicinereminder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

public class Dashboard extends Activity {

    public static final int CONFIGURE_REQUEST = 1066;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initializeApp();
    }

    private void initializeApp() {
        if (!PreferenceHelper.getInstance().getBoolean("FirstRun",false))
        {
            startActivityForResult(new Intent(Dashboard.this,Configure.class), CONFIGURE_REQUEST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode)
        {
            case CONFIGURE_REQUEST:
                if (resultCode == Activity.RESULT_OK)
                    PreferenceHelper.getInstance().setBoolean("FirstRun",true);
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }

    public void takenMedicine(View v)
    {

    }

    public void addNewMedicine(View v)
    {

    }

    public void allMedicines(View v)
    {

    }

    public void todayMedicines(View v)
    {

    }

    public void configure(View v)
    {

    }
    
}
