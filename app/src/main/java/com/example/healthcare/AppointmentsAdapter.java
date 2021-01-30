package com.example.healthcare;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class AppointmentsAdapter  extends ArrayAdapter<Appointment> {
    public AppointmentsAdapter(Context context, ArrayList<Appointment> appos){
        super(context, 0, appos);


    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_appointment, parent, false);
        }

        Appointment appointment= (Appointment) getItem(position);

        TextView docName = (TextView) convertView.findViewById(R.id.Name_text);
        TextView docDay = (TextView) convertView.findViewById(R.id.day_text);
        TextView docHour = (TextView) convertView.findViewById(R.id.hour_text);

        docName.setText(appointment.name);
        docDay.setText(appointment.day);
        docHour.setText(appointment.hour);

        return convertView;

    }
}

