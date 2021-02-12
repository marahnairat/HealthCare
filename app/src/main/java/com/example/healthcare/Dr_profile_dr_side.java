package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class Dr_profile_dr_side extends AppCompatActivity {
    TextView d_name;
    TextView d_city;
    TextView d_phone;
    TextView d_specialization;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctor_profile_doctor_side);
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        toolbar.inflateMenu(R.menu.menu);

        setActionBar(toolbar);
        d_name=  findViewById(R.id.d_name);
        d_city=findViewById(R.id.d_address);
        d_specialization=findViewById(R.id.d_spec);
        Intent intent = getIntent();;
        String name= intent.getStringExtra("doctor_name");
        d_name.setText(name);
        Button show_appointment = findViewById(R.id.show_appointment);
        show_appointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<DataObject> appointments=new ArrayList<DataObject>();
                FirebaseAuth fauth=FirebaseAuth.getInstance();
                String doctor_id=fauth.getCurrentUser().getUid();
                FirebaseFirestore db1 = FirebaseFirestore.getInstance();
                db1.collection("Users").document(doctor_id).collection("appointments")
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
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.signout){
            Toast.makeText(getApplicationContext(),"signout",Toast.LENGTH_SHORT).show();

            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            // user is now signed out
                            startActivity(new Intent(Dr_profile_dr_side.this, the_final_login.class));
                            finish();
                        }
                    });
        }
        return true ;
    }
}
