package com.mobilemedicsolutions.mymedicinereminder;

import android.os.Bundle;
import android.widget.Toast;

import com.mobilemedicsolutions.mymedicinereminder.data.Drug;
import com.mobilemedicsolutions.mymedicinereminder.data.DrugHelper;

public class MedicineList extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medicine_list);
        for(Drug d : DrugHelper.GetAllDrugs(MedicineList.this))
        {
            Toast.makeText(MedicineList.this,d.getName()+d.getDescription()+d.getScheduleType().toString()+d.getScheduledDays().size(),Toast.LENGTH_SHORT).show();
        }
    }
}
