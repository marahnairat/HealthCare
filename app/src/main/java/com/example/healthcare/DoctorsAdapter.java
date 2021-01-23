package com.example.healthcare;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.DrawableRes;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DoctorsAdapter extends ArrayAdapter<Doctor> {

    public DoctorsAdapter(Context context, ArrayList<Doctor> doctors){
        super(context, 0, doctors);


    }
    @Override
    public View getView(int position,View convertView, ViewGroup parent) {

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.listview_row, parent, false);
        }

        Doctor doctor= (Doctor) getItem(position);

        ImageView docImage=(ImageView)convertView.findViewById(R.id.image_dr1);
        TextView docName = (TextView) convertView.findViewById(R.id.dr1_name);
        TextView docHome = (TextView) convertView.findViewById(R.id.dr1_addres);
        TextView docPhone = (TextView) convertView.findViewById(R.id.dr1_phone);
        // Populate the data into the template view using the data object

        docName.setText(doctor.name);
        docHome.setText(doctor.hometown);
        docPhone.setText(doctor.phone);
        Picasso.get().load(doctor.image).into(docImage);
      // Return the completed view to render on screen
        return convertView;

    }
}