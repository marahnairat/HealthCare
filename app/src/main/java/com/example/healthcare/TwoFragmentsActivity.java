package com.example.healthcare;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
   // List<String> valuesselected = Arrays.asList();
    ArrayList<String>  valuesselected=new ArrayList<String>();
   // tring [] valuesselected ;S
    HashMap<String, String> map = new HashMap<String, String>();
    String result=" ";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);


        final Button button = (Button) findViewById(R.id.buttonchange);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                onButtonClick();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.two_fragments, menu);
//        getMenuInflater().inflate(R.menu.one_fragment, menu);
        return true;
    }




    @Override
    public void onButtonClick() {
        TwoFragment textFragment;
        textFragment = (TwoFragment) getSupportFragmentManager()
                .findFragmentById(R.id.two_fragment);
        textFragment.changeTextProperties(" "+result);
        result=" ";
    }


    public void continueCheck(View view) {
                Intent i = new Intent(getBaseContext(),ListViews.class);
                startActivity(i);

    }





    @Override
    public void onSpecializationButtonClick(String area) {
      DocumentReference chestData= FirebaseFirestore.getInstance().collection("areas").document( area );
        chestData.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot chestdoc =task.getResult();
                    if (chestdoc.exists())
                    {

                        String[] m= chestdoc.getData().keySet().toString().split(",");
                        System.out.println(chestdoc.getData());
                        System.out.println(chestdoc.getData().getClass().getName());
                        String[] m_value= chestdoc.getData().values().toString().split(",");

                        for (int i=0;i<m.length;i++){
                            map.put(m[i],m_value[i]);
                        }
                        System.out.println(map);
//                        ArrayList<String[]> n=new ArrayList<>();
//                        for(int i=0;i<m.length;i++)
//                           n.add(chestdoc.getData().get(m[i]).toString().split(","));
                        changeTextCheckbox(m);

                      Log.d("Document", chestdoc.getData().values().toString());

                    }
                    else
                    {
                        Log.d("Document","noooooooooo data");
                    }

                }
            }
        });

    }
    public void changeTextCheckbox(String[] s) {
        for (int i=0;i<s.length;i++) {

            createChekbox(s[i],i);
        }


    }
    @SuppressLint("ResourceType")
    private void createChekbox(String s, int i) {

        final LinearLayout ll = (LinearLayout) findViewById(R.id.one_layout);
        CheckBox cb = new CheckBox(this);


        cb.setText(s);
        cb.setId(i);
        ll.addView(cb);

cb.setOnClickListener(new View.OnClickListener() {
                          @Override
                          public void onClick(View v) {
                              if (((CheckBox) v).isChecked()) {

                                  result+=cb.getText()+" , ";
                                  Toast.makeText(TwoFragmentsActivity.this, cb.getText()+"SELECTED" , Toast.LENGTH_SHORT).show();
                                    valuesselected.add(map.get(cb.getText()));

                                         Log.i("Test", valuesselected.get(0));

                                 // Log.i("Test", "log infos");
                              } else {
                                  Toast.makeText(TwoFragmentsActivity.this, cb.getText()+"Not selected", Toast.LENGTH_SHORT).show();
                              }
                          }
                      }
);

    }


}