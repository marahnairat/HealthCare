package com.example.healthcare;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;


public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
    String result=" ";
    String symp=" ";
   CheckBox cb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two_fragments);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.two_fragments, menu);
//        getMenuInflater().inflate(R.menu.one_fragment, menu);
        return true;
    }


    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.checkbox1:
                if (checked) {
                    cb=(CheckBox)findViewById(R.id.checkbox1);
                    Toast.makeText(TwoFragmentsActivity.this,cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();

                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.checkbox2:
                if (checked) {
                    cb=(CheckBox)findViewById(R.id.checkbox2);
                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();

                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.checkbox3:
                if (checked) {
                    cb = (CheckBox) findViewById(R.id.checkbox3);
                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();

                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkbox4:
                if (checked) {
                    cb = (CheckBox) findViewById(R.id.checkbox4);
                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();

                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;

            case R.id.checkbox5:
                if (checked) {
                    cb = (CheckBox) findViewById(R.id.checkbox5);

                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();
                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;
            // TODO: Veggie sandwich

            case R.id.checkbox6:
                if (checked) {
                    cb = (CheckBox) findViewById(R.id.checkbox6);

                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();
                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;
            // TODO: Veggie sandwich
            case R.id.checkbox7:
                if (checked) {
                    cb = (CheckBox) findViewById(R.id.checkbox7);

                    Toast.makeText(TwoFragmentsActivity.this, cb.getText(), Toast.LENGTH_SHORT).show();
                    result=result+"   "+ cb.getText();
                }
                else
                    Toast.makeText(TwoFragmentsActivity.this,"delete",Toast.LENGTH_SHORT).show();
                break;
            // TODO: Veggie sandwich

        }

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



//    public void specializationButton(View view)
//    {
//
//
//    }
//




    @Override
    public void onSpecializationButtonClick() {

        String [] chestArray;
        DocumentReference chestData= FirebaseFirestore.getInstance().collection("areas").document("chestandback");
        chestData.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if(task.isSuccessful())
                {
                    DocumentSnapshot chestdoc=task.getResult();
                    if (chestdoc.exists())
                    {
                        OneFragment checkboxFragment;
                        checkboxFragment= (OneFragment) getSupportFragmentManager()
                                .findFragmentById(R.id.one_fragment);
                        checkboxFragment.changeTextCheckbox(chestdoc.getData().get("s1").toString());

                        Log.d("Document",chestdoc.getData().get("s1").toString());
                    }
                    else
                    {
                        Log.d("Document","noooooooooo data");
                    }

                }
            }
        });



    }

}