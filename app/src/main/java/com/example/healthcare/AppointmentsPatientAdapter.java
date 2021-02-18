package com.example.healthcare;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class AppointmentsPatientAdapter extends ArrayAdapter<Appointment> {
    public AppointmentsPatientAdapter(Context context, ArrayList<Appointment> appos){

        super(context, 0, appos);


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_appointment, parent, false);
        }




        Appointment appointment= (Appointment) getItem(position);

    Log.d("Document","uuuuuussssssseeeeeeeerrrrrrr");
    TextView docName = (TextView) convertView.findViewById(R.id.name_text);
    TextView docDay = (TextView) convertView.findViewById(R.id.day_text);
    TextView docHour = (TextView) convertView.findViewById(R.id.hour_text);
    docName.setText(appointment.dr_name);
    docDay.setText(appointment.day);
    docHour.setText(appointment.hour);

        return convertView;

    }
}

