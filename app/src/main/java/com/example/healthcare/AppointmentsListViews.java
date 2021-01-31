package com.example.healthcare;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import org.json.JSONArray;

import java.io.Serializable;
import java.util.ArrayList;

public class AppointmentsListViews extends Activity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nearest5_dr);
        ArrayList<DataObject> appos = (ArrayList<DataObject>) getIntent().getSerializableExtra("appointments");
        populateAppointmentsList(appos);
    }

    private void populateAppointmentsList(ArrayList<DataObject> appos) {


        ArrayList<Appointment> arrayOfAppointments = Appointment.getAppos(appos);
        AppointmentsAdapter adapter = new AppointmentsAdapter(this, arrayOfAppointments);
        ListView listView = (ListView) findViewById(R.id.doctor_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                FirebaseAuth fauth=FirebaseAuth.getInstance();
                String doctor_id=fauth.getCurrentUser().getUid();
                DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case DialogInterface.BUTTON_POSITIVE: {
                                FirebaseFirestore db = FirebaseFirestore.getInstance();
                                db.collection("Users").document(doctor_id).collection("appointments").document(arrayOfAppointments.get(position).app_id)
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
