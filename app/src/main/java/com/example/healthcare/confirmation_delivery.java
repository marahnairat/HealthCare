package com.example.healthcare;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class confirmation_delivery extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void buclik(View view) {


        AlertDialog.Builder obj=new AlertDialog.Builder(this);
        obj.setMessage("Are you sure you want to request home treatment?").setIcon(android.R.drawable.stat_notify_error)
                .setTitle("HOME TREATMENT REQUEST CONFIRMATION... ")
                .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(getApplicationContext(), "Request sent", Toast.LENGTH_LONG).show();
                    }
                }).setNegativeButton("no", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(getApplicationContext(), "Request ignored", Toast.LENGTH_LONG).show();

            }
        }).show();
    }
}
