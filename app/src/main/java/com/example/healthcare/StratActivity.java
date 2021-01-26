package com.example.healthcare;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import butterknife.BindView;

public class StratActivity extends FragmentActivity implements
       NewsActivity.NewsActivityListener {
    @BindView(R.id.startcheck) Button start;

    private static final int REQUEST_CALL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        android.widget.Toolbar toolbar = findViewById(R.id.toolbar2);
        setActionBar(toolbar);
        Button start= (Button) findViewById(R.id.startcheck);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                    Intent i = new Intent(getBaseContext(),CheckActivity.class);
                    startActivity(i);

            }
        });



        ImageView ambalanceBT= (ImageView) findViewById(R.id.ambalance);
        ambalanceBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                callAmbalance();

            }
        });

        ImageView reminderBT= (ImageView) findViewById(R.id.remind);
        reminderBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


               Intent i = new Intent(getBaseContext(),ReminderActivity.class);
                startActivity(i);

            }



             });



    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        if(id == R.id.signout){
            Toast.makeText(getApplicationContext(),"signout",Toast.LENGTH_SHORT).show();
        }
        return true ;
    }


    private void callAmbalance() {
        String number = "101";
        if (number.trim().length() > 0) {
            if (ContextCompat.checkSelfPermission(StratActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(StratActivity.this,
                        new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);
            } else {
                String dial = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dial)));
            }
        } else {
            Toast.makeText(StratActivity.this, "Enter Phone Number", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == REQUEST_CALL) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callAmbalance();
            } else {
                Toast.makeText(this, "Permission DENIED", Toast.LENGTH_SHORT).show();
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
