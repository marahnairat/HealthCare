package com.example.healthcare;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.fragment.app.Fragment;


public class user_appointment_fragment extends Fragment implements View.OnClickListener {
    static int w;
    View view;
    CheckBox B1, B2, B3, B4, B5, B6, B7;
    CheckBox B8, B9, B10, B11, B12, B13, B14;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.checkform, container, false);


        // initiate views
        B1 = (CheckBox) view.findViewById(R.id.B4_5);
        B1.setOnClickListener(this);
        B2 = (CheckBox) view.findViewById(R.id.B5_6);
        B2.setOnClickListener(this);
        B3 = (CheckBox) view.findViewById(R.id.B6_7);
        B3.setOnClickListener(this);
        B4 = (CheckBox) view.findViewById(R.id.B7_8);
        B4.setOnClickListener(this);
        B5 = (CheckBox) view.findViewById(R.id.B8_9);
        B5.setOnClickListener(this);
        B6 = (CheckBox) view.findViewById(R.id.B9_10);
        B6.setOnClickListener(this);
        B7 = (CheckBox) view.findViewById(R.id.b11_12);
        B7.setOnClickListener(this);
        ///////////////////////////////////////
        B8 = (CheckBox) view.findViewById(R.id.b2_3);
        B8.setOnClickListener(this);
        B9 = (CheckBox) view.findViewById(R.id.b3_4);
        B9.setOnClickListener(this);
        B10 = (CheckBox) view.findViewById(R.id.b9_10);
        B10.setOnClickListener(this);
        B11= (CheckBox) view.findViewById(R.id.BOX11_12);
        B11.setOnClickListener(this);
        B12= (CheckBox) view.findViewById(R.id.B1_2);
        B12.setOnClickListener(this);
        B13= (CheckBox) view.findViewById(R.id.B12_1);
        B13.setOnClickListener(this);
        B14= (CheckBox) view.findViewById(R.id.B10_11);
        B14.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {

        int i = 1;
        switch (v.getId()) {

            case R.id.b9_10:
                if (B10.isChecked()&&(i==1)) {
                    Toast.makeText(getActivity().getApplicationContext(), B10.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;
            case R.id.B10_11:
                if (B14.isChecked() && (i == 1)) {
                    Toast.makeText(getActivity().getApplicationContext(), B14.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;

                }
                break;
            case R.id.b11_12:
                if (B7.isChecked() && i == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), B7.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;
            case R.id.B12_1:
                if (B13.isChecked() && i == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), B13.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;
            case R.id.B1_2:
                if (B12.isChecked() && i == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), B12.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;
            case R.id.b2_3:
                if (B8.isChecked() && i == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), B8.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;
            case R.id.b3_4:
                if (B6.isChecked() && i == 1) {
                    Toast.makeText(getActivity().getApplicationContext(), B6.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                    i++;
                }
                break;


            case R.id.B4_5:
                if (B1.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), B1.getText().toString() + "  selected", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.B5_6:
                if (B2.isChecked() ) {
                    Toast.makeText(getActivity().getApplicationContext(), B2.getText().toString() + "  selected", Toast.LENGTH_LONG).show();


                }
                break;
            case R.id.B6_7:
                if (B3.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), B3.getText().toString() + "  selected", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.B7_8:
                if (B4.isChecked() ) {
                    Toast.makeText(getActivity().getApplicationContext(), B4.getText().toString() + "  selected", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.B8_9:
                if (B5.isChecked() ) {
                    Toast.makeText(getActivity().getApplicationContext(), B5.getText().toString() + "  selected", Toast.LENGTH_LONG).show();

                }
                break;
            case R.id.B9_10:
                if (B6.isChecked()) {
                    Toast.makeText(getActivity().getApplicationContext(), B6.getText().toString() + "  selected", Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.BOX11_12:
                if (B11.isChecked() ) {
                    Toast.makeText(getActivity().getApplicationContext(), B11.getText().toString() + "  selected", Toast.LENGTH_LONG).show();

                }
                break;
            //   default:
//                Toast.makeText(getActivity().getApplicationContext(), "choose correct chooice please", Toast.LENGTH_LONG).show();

        }

    }

}















































