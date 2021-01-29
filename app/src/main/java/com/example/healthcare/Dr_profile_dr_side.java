package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Dr_profile_dr_side extends AppCompatActivity {
    TextView d_name;
    TextView d_city;
    TextView d_phone;
    TextView d_specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_doctor_side);
        d_name=  findViewById(R.id.d_name);
        d_city=findViewById(R.id.d_address);
        d_phone= findViewById(R.id.phone_num);
        d_specialization=findViewById(R.id.d_spec);
        Intent intent = getIntent();
        String phone= intent.getStringExtra("doctor_phone");
        d_phone.setText(phone);
        String name= intent.getStringExtra("doctor_name");
        d_name.setText(name);
        Button show_appointment = findViewById(R.id.show_appointment);
        show_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



            }
        });
    }


}
