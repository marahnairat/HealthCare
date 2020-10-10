package com.example.healthcare;

import android.content.Intent;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;

import java.util.ArrayList;

public class ListViews extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest5_dr);
        populateDoctorsList();
    }

    private void populateDoctorsList() {


        ArrayList<Doctor> arrayOfDoctors = Doctor.getDoctors();
        // Create the adapter to convert the array to views
        DoctorsAdapter adapter = new DoctorsAdapter(this, arrayOfDoctors);
        // Attach the adapter to a ListViews

        ListView listView = (ListView) findViewById(R.id.doctor_list);
        listView.setOnItemClickListener((parent, view, position, id) -> {
            switch(position){
                case 0:
                    //Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
                    Intent i = new Intent(ListViews.this,Dr_profile.class);
                    startActivity(i);
                    break;
                case 1:
                    Intent i1 = new Intent(ListViews.this,Dr_profile.class);
                    startActivity(i1);
                    break;


            }
        });
        listView.setAdapter(adapter);

    }



}
