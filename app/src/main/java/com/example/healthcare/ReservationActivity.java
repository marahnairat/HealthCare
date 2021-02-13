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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
import com.google.firebase.firestore.QuerySnapshot;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ReservationActivity extends AppCompatActivity implements singlechoice.SingleChoiceListener {
    Button selectDate, button_time, show_appo;
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
        ImageView confirm= (ImageView)findViewById(R.id.confirmimage);
      confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                confirmAppointment(dr_id,user_name,user_id,date_selected,time_selected);
                Map<String, Object> appointment = new HashMap<>();
                String dr_name=getIntent().getStringExtra("dr_name");
                appointment.put("p_name",user_name);
                appointment.put("date",date_selected);
                appointment.put("time",time_selected);
                appointment.put("dr_name",dr_name);
                FirebaseFirestore db = FirebaseFirestore.getInstance();
                db.collection("Users").document(dr_id).collection("appointments")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                    boolean ifexist=false;
                                    for (QueryDocumentSnapshot document : task.getResult
                                            ()) {
                                     if((document.get("date").toString().equals(date_selected))&&  document.get("time").toString().equals(time_selected))
                                     {
                                         Toast.makeText(getApplicationContext(), "This time is already reserved ! ", Toast.LENGTH_LONG).show();
                                         ifexist=true;
                                          break;
                                     }


                                    }
                                    if(!ifexist)
                                    {
                                        addAppointment(appointment);
                                    }
                                } else {
                                    Log.w("all documents", "Error getting documents.", task.getException());
                                }



                            }
                        });





            }

          private void addAppointment(Map<String, Object> appointment) {
              FirebaseFirestore db1 = FirebaseFirestore.getInstance();
              db1.collection("Users").document(dr_id).collection("appointments")
                      .add(appointment);
              db1.collection("Users").document(user_id).collection("appointments")
                      .add(appointment);
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

        show_appo = (Button) findViewById(R.id.show_appo);
        show_appo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                        ArrayList<DataObject> appointments=new ArrayList<DataObject>();
                        FirebaseAuth fauth=FirebaseAuth.getInstance();
                        String user_id=fauth.getCurrentUser().getUid();
                        FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                        db1.collection("Users").document(user_id).collection("appointments")
                                .get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {

                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        if (task.isSuccessful()) {

                                            for (QueryDocumentSnapshot document : task.getResult
                                                    ()) {
                                                appointments.add(new DataObject(Objects.requireNonNull(document.get("dr_name")).toString(),
                                                        Objects.requireNonNull(document.get("p_name")).toString(),
                                                        Objects.requireNonNull(document.get("date")).toString(),
                                                        Objects.requireNonNull(document.get("time")).toString(),
                                                        document.getId()));



                                            }
                                        } else {
                                            Log.w("all documents", "Error getting documents.", task.getException());
                                        }

                                        Intent i = new Intent(getBaseContext(),AppointmentsListViews.class);
                                        i.putExtra("appointments",appointments);
                                        startActivity(i);


                                    }
                                });


                    }



        });

    }

    @Override
    public void onPositiveButtonClicked(String[] list, int position) {
        text_hour.setText( list[position]);
        time_selected=list[position];
    }

    @Override
    public void onNegativeButtonClicked() {
        text_hour.setText("Reservation  hour is  canceled");
    }


}

