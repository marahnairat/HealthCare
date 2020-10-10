package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import butterknife.BindView;

public class StratActivity extends AppCompatActivity {
    @BindView(R.id.startcheck) Button start;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Button button= (Button) findViewById(R.id.startcheck);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                    Intent i = new Intent(getBaseContext(),CheckActivity.class);
                    startActivity(i);

            }
        });
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
