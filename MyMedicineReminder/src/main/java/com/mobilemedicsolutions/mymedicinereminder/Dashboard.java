package com.mobilemedicsolutions.mymedicinereminder;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduleType;
import com.mobilemedicsolutions.mymedicinereminder.data.ScheduledDay;
import com.mobilemedicsolutions.mymedicinereminder.data.contentproviders.PreferenceHelper;

import java.util.HashSet;

public class Dashboard extends Activity {

    private static final int CONFIGURE_REQUEST = 1066;

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

    /*@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.dashboard, menu);
        return true;
    }*/

    public void takenMedicine(@SuppressWarnings("UnusedParameters") View v)
    {
        startActivity(new Intent(Dashboard.this,TakenMedicine.class));
    }

    public void addNewMedicine(@SuppressWarnings("UnusedParameters") View v)
    {
        startActivity(new Intent(Dashboard.this,NewMedicine.class));
    }

    public void allMedicines(@SuppressWarnings("UnusedParameters") View v)
    {
        Intent intent = new Intent(Dashboard.this,MedicineList.class);
        intent.putExtra("Filter",0);
        startActivity(intent);
    }

    public void todayMedicines(@SuppressWarnings("UnusedParameters") View v)
    {
        Intent intent = new Intent(Dashboard.this,MedicineList.class);
        intent.putExtra("Filter",System.currentTimeMillis());
        startActivity(intent);
    }

    public void configure(@SuppressWarnings("UnusedParameters") View v)
    {
        startActivity(new Intent(Dashboard.this, Configure.class));
    }
    
}
