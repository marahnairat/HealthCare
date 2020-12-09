package com.example.healthcare;


import android.app.Activity;
import androidx.fragment.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;

public class OneFragment extends Fragment {

    OneFragmentListener activityCallback;
    CheckBox checkBox;
    CheckBox ch1;
    CheckBox ch2;
    CheckBox ch3;
    CheckBox ch4;

    //Listener for onButtonClick UI
    public interface OneFragmentListener {
        public void onButtonClick();

    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            activityCallback = (OneFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()
                    + " must implement OneFragmentListener");
        }
    }

    //We get the reference to the editText and the button setUp the OnClickListener
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.one_fragment, container, false);
//        ch1=(CheckBox) view.findViewById(R.id.checkbox1);
//        ch2=(CheckBox) view.findViewById(R.id.checkbox2);
//        ch3=(CheckBox) view.findViewById(R.id.checkbox3);
//        ch4=(CheckBox) view.findViewById(R.id.checkbox4);

        final Button button = (Button) view.findViewById(R.id.buttonchange);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                buttonClicked(v);
            }
        });

        return view;
   }

    //Set the activityCallback to onButtonClick passing the text in the mEditText
    public void buttonClicked(View view) {
        activityCallback.onButtonClick();

    }

//    public void changeTextCheckbox(View view,String[] s) {
//        for (int i=0;i<s.length;i++) {
//
//            ch1=(CheckBox) view.findViewById(R.id.checkbox1);
//            checkBox.setText(s[i]);
//        }
////        ch1.setText(s[0]);
////        ch2.setText(s[1]);
////        ch3.setText(s[2]);
////        ch4.setText(s[3]);
//
//
//    }


}