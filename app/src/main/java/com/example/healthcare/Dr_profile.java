

        package com.example.healthcare;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

public class Dr_profile extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;
    private TextView mEditTextNumber;
    TextView name;
    TextView town;
    TextView phone;
    TextView specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dr_profile);
        name=  findViewById(R.id.tv_name);
        town=findViewById(R.id.tv_address);
        phone= findViewById(R.id.phone_num);
        specialization=findViewById(R.id.spec);
        String namedr=getIntent().getStringExtra("name");
        String towndr=getIntent().getStringExtra("town");
        String phonedr=getIntent().getStringExtra("phone");
        String spical=getIntent().getStringExtra("specialization");
        name.setText(namedr);
        town.setText(towndr);
        phone.setText(phonedr);
        specialization.setText(spical);
        mEditTextNumber = findViewById(R.id.phone_num);
        Button call_btn = findViewById(R.id.delivery_bn);
        call_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePhoneCall();
            }
        });

        Button booking = findViewById(R.id.clinical_bn);
        booking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               makeAppointment();

            }
        });

    }


    private void makeAppointment() {

        Intent i = new Intent(getBaseContext(),ReservationActivity.class);
        startActivity(i);
//        Intent reservationIntent = new Intent(getApplicationContext(), ReservationActivity.class);
//                startActivity(reservationIntent);

    }








    private void makePhoneCall() {
        String number = mEditTextNumber.getText().toString();
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(Dr_profile.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(Dr_profile.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(Dr_profile.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                makePhoneCall();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

}