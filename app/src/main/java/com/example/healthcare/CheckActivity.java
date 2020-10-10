package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class CheckActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check);

        Button button= (Button) findViewById(R.id.continu);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //hendl action here eg

                Intent i = new Intent(getBaseContext(),ListViews.class);
                startActivity(i);

            }
        });

    }



}
