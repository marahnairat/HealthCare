package com.example.healthcare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import java.nio.BufferUnderflowException;

import butterknife.BindView;

public class StratActivity extends Fragment {
    @BindView(R.id.startcheck) Button start;

    private static final int REQUEST_CALL = 1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view= inflater.inflate(R.layout.activity_start,container,false);
        Button start= (Button) view.findViewById(R.id.startcheck);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                Intent i = new Intent(getActivity(),TwoFragmentsActivity.class);
                startActivity(i);

            }
        });



        ImageView ambalanceBT= (ImageView) view.findViewById(R.id.ambalance);
        ambalanceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                callAmbalance();

            }
        });

        ImageView reminderBT= (ImageView) view.findViewById(R.id.remind);
        reminderBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(getActivity(),ReminderActivity.class);
                startActivity(i);

            }



        });
        return view;
    }



    private void callAmbalance() {
        String number = "101";
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(getActivity(),
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(getActivity(),
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(getActivity(), "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAmbalance();
            } else {
                Toast.makeText(getActivity(), "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }





  @SuppressLint("MissingPermission")
  public void call_amb(View view){


     {
              try {
                  Intent callIntent = new Intent(Intent.ACTION_CALL);
                  callIntent.setData(Uri.parse("tel:100"));
                  startActivity(callIntent);
              } catch (ActivityNotFoundException activityException) {
                  Log.e("Calling a Phone Number", "Call failed", activityException);
              }
          }





  }


}
