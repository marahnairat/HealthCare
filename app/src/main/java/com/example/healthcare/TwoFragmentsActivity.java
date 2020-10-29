package com.example.healthcare;


import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.fragment.app.FragmentActivity;

public class TwoFragmentsActivity extends FragmentActivity implements
        OneFragment.OneFragmentListener , ThreeFragment.ThreeFragmentListener {
    String result=" ";
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
}