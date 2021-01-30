package com.example.healthcare;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.*;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.sql.Time;
import java.util.HashMap;
import java.util.Map;

public class ReservationActivity extends AppCompatActivity implements singlechoice.SingleChoiceListener {
    Button selectDate, button_time;
    TextView date;
    DatePickerDialog datePickerDialog;
    int year;
    int month;
    int dayOfMonth;
    Calendar calendar;
    TextView text_hour;
    String date_selected;
    String time_selected;
    String user_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reservation_activity);
        text_hour = findViewById(R.id.textView5);
        selectDate = findViewById(R.id.btnDate);
        date = findViewById(R.id.tvSelectedDate);
        FirebaseAuth fauth=FirebaseAuth.getInstance();
        String user_id=fauth.getCurrentUser().getUid();

        String dr_id=getIntent().getStringExtra("dr_id");
        DocumentReference data = FirebaseFirestore.getInstance().collection("Users")
                .document(user_id);
                data.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = (DocumentSnapshot) task.getResult();
                    if (document.exists()) {
                        Log.d("DocumentSnapshot data: " , String.valueOf(document.get("FullName")));
                        user_name= (String) document.get("FullName");
                    } else {
                        Log.d("No such document"," ");
                    }
                } else {
                    Log.d( "get failed with ", String.valueOf(task.getException()));
                }
            }
        });
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
                                date.setText(day + "/" + (month + 1) + "/" + year);
                                 date_selected= day + "/" + (month + 1) + "/" + year;
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());
                datePickerDialog.show();

            }
        });
        Button button_confirm = (Button) findViewById(R.id.button_confirm);
        button_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                confirmAppointment(dr_id,user_name,user_id,date_selected,time_selected);
                Map<String, Object> appointment = new HashMap<>();
                appointment.put("name",user_name);
                appointment.put("date",date_selected);
                appointment.put("time",time_selected);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(dr_id).collection("appointments")
                        .add(appointment);

                Intent i = new Intent(getBaseContext(),MainActivity.class);
                startActivity(i);

            }
        });

        button_time = (Button) findViewById(R.id.button_time);
        button_time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DialogFragment singleChoiceDialog = new singlechoice();
                singleChoiceDialog.setCancelable(false);
                singleChoiceDialog.show(getSupportFragmentManager(), "Single Choice Dialog");


            }
        });

    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        text_hour.setText("Selected Hour = " + list[position]);
        time_selected=list[position];
    }

    @Override
    public void onNegativeButtonClicked() {
        text_hour.setText("Reservation  hour is  canceled");
    }


}

