package com.example.healthcare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AppointmentsListViews extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest5_dr);
        ArrayList<DataObject> appos = (ArrayList<DataObject>) getIntent().getSerializableExtra("appointments");
       String isdoctor = getIntent().getStringExtra("isdoctor");
        populateAppointmentsList(appos, isdoctor);
    }

    private void populateAppointmentsList(ArrayList<DataObject> appos, String isdoctor) {


        ArrayList<Appointment> arrayOfAppointments = Appointment.getAppos(appos);

        if(!(Boolean.parseBoolean(isdoctor))) {
            AppointmentsPatientAdapter adapter = new AppointmentsPatientAdapter(this, arrayOfAppointments);
            ListView listView = (ListView) findViewById(R.id.doctor_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    FirebaseAuth fauth=FirebaseAuth.getInstance();
                    String user_id=fauth.getCurrentUser().getUid();
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE: {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("Users").document(user_id).collection("appointments").document(arrayOfAppointments.get(position).app_id)
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG, ","DocumentSnapshot successfully deleted!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error deleting document", e);
                                                }
                                            });
                                    arrayOfAppointments.remove(position);
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(getApplicationContext(), "remove item at " + position, Toast.LENGTH_LONG).show();


                                }
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentsListViews.this,R.style.AlertDialog);
                    builder.setMessage("This appointment done?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            });
        }
        else if((Boolean.parseBoolean(isdoctor)))
        {
            AppointmentsDoctorAdapter adapter = new AppointmentsDoctorAdapter(this, arrayOfAppointments);
            ListView listView = (ListView) findViewById(R.id.doctor_list);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                    FirebaseAuth fauth=FirebaseAuth.getInstance();
                    String user_id=fauth.getCurrentUser().getUid();
                    DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            switch (which){
                                case DialogInterface.BUTTON_POSITIVE: {
                                    FirebaseFirestore db = FirebaseFirestore.getInstance();
                                    db.collection("Users").document(user_id).collection("appointments").document(arrayOfAppointments.get(position).app_id)
                                            .delete()
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d("TAG, ","DocumentSnapshot successfully deleted!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w("TAG", "Error deleting document", e);
                                                }
                                            });
                                    arrayOfAppointments.remove(position);
                                    adapter.notifyDataSetChanged();

                                    Toast.makeText(getApplicationContext(), "remove item at " + position, Toast.LENGTH_LONG).show();


                                }
                                case DialogInterface.BUTTON_NEGATIVE:
                                    //No button clicked
                                    break;
                            }
                        }
                    };

                    AlertDialog.Builder builder = new AlertDialog.Builder(AppointmentsListViews.this,R.style.AlertDialog);
                    builder.setMessage("This appointment done?").setPositiveButton("Yes", dialogClickListener)
                            .setNegativeButton("No", dialogClickListener).show();
                }
            });
        }



    }



}
