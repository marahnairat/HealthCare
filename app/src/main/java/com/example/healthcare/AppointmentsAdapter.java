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

public class AppointmentsAdapter  extends ArrayAdapter<Appointment> {
    public AppointmentsAdapter(Context context, ArrayList<Appointment> appos){

        super(context, 0, appos);


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        FirebaseAuth fauth=FirebaseAuth.getInstance();
        String user_id=fauth.getCurrentUser().getUid();
         boolean[] isdoctor = new boolean[1];
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_appointment, parent, false);
        }

        DocumentReference docdata= FirebaseFirestore.getInstance().collection("Users").document( user_id );
        docdata.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot doc =task.getResult();
                    if (doc.exists())
                    {  Log.d("Document",(String) doc.get("isDoctor").toString() );

                        isdoctor[0]=Boolean.parseBoolean((String) doc.get("isDoctor").toString());

                        }

                    }
                    else
                    {
                        Log.d("Document","noooooooooo data");
                    }

                }

        });


        Appointment appointment= (Appointment) getItem(position);
if(!isdoctor[0]) {
    Log.d("Document","uuuuuussssssseeeeeeeerrrrrrr");
    TextView docName = (TextView) convertView.findViewById(R.id.name_text);
    TextView docDay = (TextView) convertView.findViewById(R.id.day_text);
    TextView docHour = (TextView) convertView.findViewById(R.id.hour_text);
    docName.setText(appointment.dr_name);
    docDay.setText(appointment.day);
    docHour.setText(appointment.hour);
}
else if(isdoctor[0])
{
 Log.d("Document","dooooooocccdoc");
    TextView docName = (TextView) convertView.findViewById(R.id.name_text);
    TextView docDay = (TextView) convertView.findViewById(R.id.day_text);
    TextView docHour = (TextView) convertView.findViewById(R.id.hour_text);

    docName.setText(appointment.p_name);
    docDay.setText(appointment.day);
    docHour.setText(appointment.hour);
}
        return convertView;

    }
}

