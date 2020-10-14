package com.example.healthcare;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class choose_symptom extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_symptom);

    }


    public void opensection1(View view) {
        startActivity(new Intent(choose_symptom.this, section1.class));
    }

    public void opensection7(View view) {
        startActivity(new Intent(choose_symptom.this, section7.class));
    }

    public void opensection6(View view) {
        startActivity(new Intent(choose_symptom.this, section6.class));
    }

    public void opensection5(View view) {
        startActivity(new Intent(choose_symptom.this, section5.class));
    }

    public void opensection4(View view) {
        startActivity(new Intent(choose_symptom.this, section4.class));
    }

    public void opensection3(View view) {
        startActivity(new Intent(choose_symptom.this, section3.class));
    }

    public void opensection2(View view) {
        startActivity(new Intent(choose_symptom.this, section2.class));
    }
}