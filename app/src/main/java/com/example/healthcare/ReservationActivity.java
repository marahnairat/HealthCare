package com.example.healthcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class ReservationActivity extends AppCompatActivity implements singlechoice.SingleChoiceListener{
        Button selectDate,button;
        TextView date;
        DatePickerDialog datePickerDialog;
        int year;
        int month;
        int dayOfMonth;
        Calendar calendar;
TextView text_hour;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.reservation_activity);
            text_hour = findViewById(R.id.textView5);
            selectDate = findViewById(R.id.btnDate);
            date = findViewById(R.id.tvSelectedDate);
            FragmentManager fragmentManager = getSupportFragmentManager();
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            final user_appointment_fragment myFragment = new user_appointment_fragment();
            selectDate.setOnClickListener(new View.OnClickListener() {
                @RequiresApi(api = Build.VERSION_CODES.N)
                @Override
                public void onClick(View view) {
                    calendar = Calendar.getInstance();
                    year = calendar.get(Calendar.YEAR);
                    month = calendar.get(Calendar.MONTH);
                    dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);
                    datePickerDialog = new DatePickerDialog(ReservationActivity.this,
                            new DatePickerDialog.OnDateSetListener() {
                                @Override
                                public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                    date.setText("Selected Day:"+day + "/" + (month + 1) + "/" + year);
                                }
                            }, year, month, dayOfMonth);
                    datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                    datePickerDialog.show();

                }
            });
            button = (Button) findViewById(R.id.button);
           /* button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    loadFragment(new user_appointment_fragment());

}});
*/
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DialogFragment singleChoiceDialog = new singlechoice();
                    singleChoiceDialog.setCancelable(false);
                    singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");


                }
            });


            Button confirm = (Button) findViewById(R.id.confirm_button2);

            confirm.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(ReservationActivity.this,MainActivity.class);
                    startActivity(i);


                }
            });





        }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        text_hour.setText("Selected Hour = " + list[position]);
    }

    @Override
    public void onNegativeButtonClicked() {
        text_hour.setText("Reservation  hour is  canceled");
    }
/*
    private void loadFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.check_frame, fragment);
        fragmentTransaction.commit(); // save the changes
    }*/
    }//END CLASS
