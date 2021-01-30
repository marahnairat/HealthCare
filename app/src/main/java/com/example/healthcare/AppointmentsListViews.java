package com.example.healthcare;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class AppointmentsListViews extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest5_dr);
        ArrayList<DataObject> appos = (ArrayList<DataObject>) getIntent().getSerializableExtra("appointments");
        populateAppointmentsList(appos);
    }

    private void populateAppointmentsList(ArrayList<DataObject> appos) {


        ArrayList<Appointment> arrayOfAppointments = Appointment.getAppos(appos);
        AppointmentsAdapter adapter = new AppointmentsAdapter(this, arrayOfAppointments);
        ListView listView = (ListView) findViewById(R.id.doctor_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//                Doctor dr = adapter.getItem(i);
//                Intent intent=new Intent(AppointmentsListViews.this ,Dr_profile.class);
//                intent.putExtra("name",dr.getName());
//                intent.putExtra("town",dr.getHometown());
//                intent.putExtra("phone",dr.getPhone());
//                intent.putExtra("dr_id",dr.getDr_id());
//                String spec=getIntent().getStringExtra("specialization");
//                intent.putExtra("specialization",spec);
//                Log.d("1",dr.getName());
//                startActivity(intent);
            }
        });


    }



}
