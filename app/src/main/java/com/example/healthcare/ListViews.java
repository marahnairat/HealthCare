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

public class ListViews extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest5_dr);
        ArrayList<DataObject> docs = (ArrayList<DataObject>) getIntent().getSerializableExtra("array of doctors");
        populateDoctorsList(docs);
    }

    private void populateDoctorsList(ArrayList<DataObject> docs) {


        ArrayList<Doctor> arrayOfDoctors = Doctor.getDoctors(docs);
        // Create the adapter to convert the array to views
        DoctorsAdapter adapter = new DoctorsAdapter(this, arrayOfDoctors);
        // Attach the adapter to a ListViews

        ListView listView = (ListView) findViewById(R.id.doctor_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Doctor dr = adapter.getItem(i);
                Intent intent=new Intent(ListViews.this,Dr_profile.class);
              intent.putExtra("name",dr.getName());
              intent.putExtra("town",dr.getHometown());
              intent.putExtra("phone",dr.getPhone());
              intent.putExtra("dr_id",dr.getDr_id());
                String spec=getIntent().getStringExtra("specialization");
                intent.putExtra("specialization",spec);
                Log.d("1",dr.getName());
                startActivity(intent);
            }
        });
//        listView.setOnItemClickListener((parent, view, position, id) -> {
//            switch(position){
//                case 0:
//                    //Toast.makeText(getApplicationContext(), (String)parent.getItemAtPosition(position), Toast.LENGTH_LONG).show();
//                    Intent i = new Intent(ListViews.this,Dr_profile.class);
//                    startActivity(i);
//                    break;
//                case 1:
//                    Intent i1 = new Intent(ListViews.this,Dr_profile.class);
//                    startActivity(i1);
//                    break;
//
//
//            }
//        });


    }



}
